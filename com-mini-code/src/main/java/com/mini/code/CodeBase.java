package com.mini.code;

import com.mini.util.StringUtil;
import com.squareup.javapoet.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import static java.lang.String.format;
import static javax.lang.model.element.Modifier.DEFAULT;
import static javax.lang.model.element.Modifier.PUBLIC;

public final class CodeBase {
    protected static void run(Configure configure, String className, String tableName, String prefix) throws Exception {
        System.out.println("====================================");
        System.out.println("========= Start Code Bean ==========");
        System.out.println("====================================");

        //  Package Name
        String basePackage = format("%s.entity.base", configure.getPackageName());

        // Class Name
        String baseClassName = String.format("%sBase", className);
        // Class Name
        ClassName baseClass = ClassName.get(basePackage, baseClassName);
        TypeVariableName tName = TypeVariableName.get("T", baseClass);

        try (Connection connection = configure.getConnection()) {
            // 获取所有字段信息
            List<Util.FieldInfo> fieldList = Util.getColumns(connection, configure.getDatabaseName(), tableName, prefix);

            // 生成类信息
            TypeSpec.Builder builder = TypeSpec.interfaceBuilder(baseClassName)
                    .addModifiers(PUBLIC)
                    .addSuperinterface(Serializable.class)
                    .addTypeVariable(TypeVariableName.get("T", baseClass))
                    .addJavadoc("$L.java \n", baseClassName)
                    .addJavadoc("@author xchao \n");

            // 处理 Getter Setter 方法
            for (Util.FieldInfo fieldInfo : fieldList) {
                String name = StringUtil.toJavaName(fieldInfo.getFieldName(), false);

                // Getter 方法
                builder.addMethod(MethodSpec.methodBuilder("get" + StringUtil.firstUpperCase(name))
                        .addModifiers(PUBLIC, DEFAULT)
                        // 设置返回类型
                        .returns(fieldInfo.getTypeClass())
                        .addException(UnsupportedOperationException.class)
                        // 方法体内容
                        .addStatement("throw new $T()", UnsupportedOperationException.class)
                        // 生成方法 JAVA DOC
                        .addJavadoc("$L. \n", StringUtil.def(fieldInfo.getRemarks(), "Gets the value of " + name))
                        .addJavadoc("@return The value of $L \n", name)
                        .build());

                // Setter 方法
                builder.addMethod(MethodSpec.methodBuilder("set" + StringUtil.firstUpperCase(name))
                        .addModifiers(PUBLIC, DEFAULT)
                        // 设置返回类型
                        .returns(tName)
                        // 添加方法参数列表
                        .addParameter(fieldInfo.getTypeClass(), name)
                        .addException(UnsupportedOperationException.class)
                        // 添加方法体内容
                        .addStatement("throw new $T()", UnsupportedOperationException.class)
                        // 生成方法 JAVA DOC
                        .addJavadoc("$L. \n", StringUtil.def(fieldInfo.getRemarks(), "Sets the value of " + name))
                        .addJavadoc("@param $L The value of $L \n", name, name)
                        .addJavadoc("@return {@code this} \n")
                        .build());
            }
            // 生成文件信息
            JavaFile javaFile = JavaFile.builder(basePackage, builder.build()).build();
            javaFile.writeTo(new File(configure.getClassPath()));
        }

        System.out.println("====================================");
        System.out.println("========= End Code Bean ============");
        System.out.println("====================================");
        System.out.println("\r\n");
    }

    public static void main(String[] args) throws Exception {
        Configure configure = Config.getConfigure();
        for (String[] bean : configure.getDatabaseBeans()) {
            run(configure, bean[0], bean[1], bean[2]);
        }
    }
}

import com.mini.plugin.extension.StringKt
import com.mini.plugin.state.DbTable
import com.mini.plugin.util.BuilderWriter
import org.apache.commons.lang3.StringUtils

//noinspection GrUnresolvedAccess
DbTable info = tableInfo

// 设置包名与文件名，确定生成代码的路径
//noinspection GrUnresolvedAccess
final BuilderWriter out = builderWriter
out.setPackageName("${info.packageName}.entity")
out.setFileName("${info.entityName}.java")

out.println """package ${info.packageName}.entity;"""
out.println """ """
out.println """import lombok.*; """
out.println """import lombok.experimental.*; """
out.println """import javax.persistence.*;"""
out.println """import org.springframework.data.relational.core.mapping.*; """
out.println """ """
out.println """import java.io.Serializable; """

// 属性类型需要导入
out.println """ """
info.columnList.stream().filter { it.typeImport.length() > 0 }
        .map { it -> it.typeImport }.distinct().forEach {
    out.println """import ${it}; """
}

// 类签名
out.println """ """
out.println """@Data """
out.println """@Entity """
out.println """@Table(name = "${info.tableName}") """
out.println """@SuperBuilder(toBuilder = true) """
out.println """public class ${info.entityName} implements Serializable { """

// 多主键生成主键类
//noinspection DuplicatedCode
if (info.columnList.stream().filter { it -> it.isId() }.count() != 1) {
    out.println """ """
    out.println """    @Data """
    out.println """    @Builder(toBuilder = true) """
    out.println """    public static class ID implements Serializable { """
    info.columnList.stream().filter { it -> it.isId() }.forEach { it ->
        out.println """    private ${it.javaType} ${it.fieldName}; """
    }
    out.println """    } """
}

// 生成字段属性
//noinspection DuplicatedCode
info.columnList.forEach {
    out.println """ """
    if (StringUtils.isNotBlank(it.comment)) {
        out.println """    // ${it.comment} """
    }
    out.println """    @Column(name = "${it.name}") """
    out.println """    private ${it.javaType}  ${it.fieldName}; """
}

// 默认构造方法
out.println """ """
out.println """   @Tolerate"""
out.println """   public ${info.entityName}(){}"""

// 生成所有日期格式化字段
//noinspection DuplicatedCode
info.columnList.stream().filter { it.javaType == "Date" }.forEach {
    out.println """ """
    out.println """     @JSONField(format="yyyy-MM-dd HH:mm:ss") """
    out.println """     public ${it.javaType} get${StringKt.firstUpperCase(it.fieldName)}_DT() { """
    out.println """         return this.${it.fieldName}; """
    out.println """     } """
    out.println """ """
    out.println """     @JSONField(format="yyyy-MM-dd") """
    out.println """     public ${it.javaType} get${StringKt.firstUpperCase(it.fieldName)}_D() { """
    out.println """         return this.${it.fieldName}; """
    out.println """     } """
}

// 结尾
out.println """ """
out.println """ """
out.println """}"""
out.println """ """
out.println """ """


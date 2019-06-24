package com.mini.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

public class MiniProperties extends Properties {
    private static final long serialVersionUID = 6389833449837854599L;

    /**
     * 加载属性文件
     * @param reader Reader
     * @return {@Code this}
     */
    public synchronized MiniProperties miniLoad(Reader reader) throws IOException {
        super.load(reader);
        return this;
    }

    /**
     * 加载属性文件
     * @param inStream 输入流
     * @return {@Code this}
     */
    public synchronized MiniProperties miniLoad(InputStream inStream) throws IOException {
        super.load(inStream);
        return this;
    }

    /**
     * 设置属性，如果存在时跳过
     * @param key   属性key
     * @param value 属性值
     * @return {@Code this}
     */
    public synchronized MiniProperties setPropertyIfAbsent(String key, String value) {
        this.putIfAbsent(key, value);
        return this;
    }

    /**
     * 获取属性，为空时添加默认属性
     * @param key  属性Key
     * @param func 回调
     * @return 属性值
     */
    public synchronized String getPro(String key, Function<? super Object, ?> func) {
        return ObjectUtil.toString(computeIfAbsent(key, func));
    }


    /**
     * 设置子属性文件的所有内容，子文件中存在的键会覆盖当前键
     * @param properties 属性文件内容
     * @return {@Code this}
     */
    public synchronized MiniProperties setProperty(Properties properties) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String value = String.valueOf(entry.getValue());
            String key = String.valueOf(entry.getKey());
            setProperty(key, value);
        }
        return this;
    }

    /**
     * 设置子属性文件的所有内容，当前文件中Key存在时跳过
     * @param properties 属性文件内容
     * @return {@Code this}
     */
    public synchronized MiniProperties setPropertyIfAbsent(Properties properties) {
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String value = String.valueOf(entry.getValue());
            String key = String.valueOf(entry.getKey());
            setPropertyIfAbsent(key, value);
        }
        return this;
    }
}
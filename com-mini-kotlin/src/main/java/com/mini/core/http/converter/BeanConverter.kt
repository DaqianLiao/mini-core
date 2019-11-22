package com.mini.core.http.converter

import com.alibaba.fastjson.JSON
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

@Suppress("unused")
class BeanConverter<T>(private val clazz: Class<T?>) : Converter<T?> {
    @Throws(IOException::class)
    override fun apply(call: Call, response: Response): T? {
        return if (response.isSuccessful) {
            response.body()?.use { body ->
                JSON.parseObject(body.string(), clazz)
            }
        } else throw IOException(response.message())
    }
}
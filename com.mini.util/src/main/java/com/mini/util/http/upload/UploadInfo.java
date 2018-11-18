package com.mini.util.http.upload;

import com.mini.util.PKGenerator;
import com.mini.util.http.Converter;
import com.mini.util.http.builder.PartBuilder;
import com.mini.util.http.call.HttpCall;
import com.mini.util.lang.Function;
import okhttp3.Call;

import java.io.File;
import java.io.IOException;

public class UploadInfo<T> {
    public static final int STATUS_WAIT = 0; // 队列等待中
    public static final int STATUS_PAUSE = 1; // 暂停
    public static final int STATUS_UPLOADING = 2; // 上传中
    public static final int STATUS_FINISH = 3; // 上传完成
    public static final int STATUS_FAIL = 4; // 上传失败

    protected int status;
    protected final long id; // 唯一标题
    protected long sliceSize; // 分片大小
    private boolean callPause; // 是否调用过暂停
    protected final File file; // 需要上传的文件
    protected HttpCall<T> call; // 调用器
    protected long totalLength; // 文件总大小
    protected long uploadLength; //  已上传大小
    protected final Class<T> clazz; // 泛型类型
    protected Converter<T> converter; // 数据转换对象
    protected Function.F1<Long> onPause; // 请求暂停时回调
    protected final PartBuilder builder; // 参数构建器
    protected Function.F1<Long> onStart; // 请求开始时的回调
    protected Function.F3<Long, Call, T> onSuccess; // 成功返回时回调
    protected Function.F3<Long, Long, Long> onUpload; // 上传进度回调
    protected Function.F3<Long, Call, IOException> onFail; // 请求失败时的回调
    protected Function.F4<PartBuilder, Long, Long, T> handler; // 单片上传完成时的回调处理

    public UploadInfo(PartBuilder builder, File file, Class<T> clazz) {
        totalLength = file.length();
        id = PKGenerator.key();
        this.builder = builder;
        this.clazz = clazz;
        this.file = file;
    }

    /**
     * 暂停
     */
    protected void onPause() {
        status = STATUS_PAUSE;
        if (onPause != null) onPause.apply(id);
    }

    /**
     * 开始
     */
    protected void onStart() {
        status = STATUS_UPLOADING;
        if (onStart != null) onStart.apply(id);
    }

    /**
     * 成功
     *
     * @param call
     * @param t
     */
    protected void onSuccess(Call call, T t) {
        status = STATUS_FINISH;
        if (onSuccess != null) onSuccess.apply(id, call, t);

    }

    /**
     * 上传进度
     *
     * @param total
     * @param uploadLength
     */
    protected void onUpload(long total, long uploadLength) {
        if (onUpload != null) onUpload.apply(id, total, uploadLength);
    }

    /**
     * 失败回调
     *
     * @param call
     * @param e
     */
    protected void onFail(Call call, IOException e) {
        status = STATUS_FAIL;
        if (onFail != null) onFail.apply(id, call, e);
    }

    /**
     * @param call
     * @param t
     */
    protected void onSliceSuccess(Call call, T t) {
        try {
            // 已上传的长度与总长度相等，表示上传完成
            if (uploadLength == totalLength) {
                onSuccess(call, t);
                return;
            }
            // 如果调用过暂停方法， 需要暂停
            if (callPause) {
                callPause = false;
                onPause();
                return;
            }
            // 分片大小与分片回调都满足分片，则分片
            if (handler != null && sliceSize > 0) {
                handler.apply(builder, totalLength, uploadLength, t);
                start();
                return;
            }
            // 如果不满足上面的条件，说明上传过程出现问题
            onFail(call, new IOException("ERROR!!!"));
        } catch (Exception e) {
            onFail(call, new IOException(e));
        }
    }

    public UploadInfo<T> setSliceSize(long sliceSize) {
        this.sliceSize = sliceSize;
        return this;
    }

    public UploadInfo<T> setUploadLength(long uploadLength) {
        this.uploadLength = uploadLength;
        return this;
    }

    public UploadInfo<T> setConverter(Converter<T> converter) {
        this.converter = converter;
        return this;
    }

    public UploadInfo<T> setOnPause(Function.F1<Long> onPause) {
        this.onPause = onPause;
        return this;
    }

    public UploadInfo<T> setOnStart(Function.F1<Long> onStart) {
        this.onStart = onStart;
        return this;
    }

    public UploadInfo<T> setOnSuccess(Function.F3<Long, Call, T> onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public UploadInfo<T> setOnUpload(Function.F3<Long, Long, Long> onUpload) {
        this.onUpload = onUpload;
        return this;
    }

    public UploadInfo<T> setOnFail(Function.F3<Long, Call, IOException> onFail) {
        this.onFail = onFail;
        return this;
    }

    public UploadInfo<T> setHandler(Function.F4<PartBuilder, Long, Long, T> handler) {
        this.handler = handler;
        return this;
    }

    /**
     * 获取上传唯一标识
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * 获取上传状态
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     * 获取上传总大小
     *
     * @return
     */
    public long getTotalLength() {
        return totalLength;
    }

    /**
     * 获取已上传大小
     *
     * @return
     */
    public long getUploadLength() {
        return uploadLength;
    }

    /**
     * 开始下载
     */
    public void start() {
        if (status == STATUS_UPLOADING || status == STATUS_FINISH) return;
        call = builder.post(clazz).setConverter(converter).setOnSuccess(this::onSliceSuccess);
        call.setOnPause(this::onPause).setOnStart(this::onStart).setOnFail(this::onFail).enqueue();
    }

    /**
     * 暂停
     */
    public void pause() {
        if (status == STATUS_FINISH || status == STATUS_FAIL) return;
        if (status != STATUS_PAUSE) callPause = true; // 需要暂停
    }
}

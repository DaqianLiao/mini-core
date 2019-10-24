package com.mini.web.model;

import com.mini.http.RangeParse;
import com.mini.http.RangeParse.Range;
import com.mini.util.ObjectUtil;
import com.mini.util.StringUtil;

import javax.annotation.Nonnull;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import static com.mini.util.ObjectUtil.require;
import static java.lang.Math.min;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE;

/**
 * Stream Model类实现
 * @author xchao
 */
public final class StreamModel extends IModel<StreamModel> implements Serializable {
    private static final String MULTIPART_BOUNDARY = "MULTIPART_BOUNDARY";
    private static final long serialVersionUID = -1731063292578685253L;
    private static final String TYPE = "application/octet-stream";
    // curl -i --range 0-9 http://www.baidu.com/img/bdlogo.gif
    private static final int BUFFER_SIZE = 2048;
    private boolean acceptRangesSupport = true;
    private WriteCallback writeCallback;
    private InputStream inputStream;
    private long contentLength;
    private String fileName;


    public StreamModel() {
        super(TYPE);
    }

    @Override
    protected StreamModel model() {
        return this;
    }


    public StreamModel setFileName(String fileName) {
        this.fileName = fileName;
        return model();
    }


    public StreamModel setContentLength(long contentLength) {
        this.contentLength = contentLength;
        return model();
    }


    public StreamModel setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return model();
    }


    public StreamModel setWriteCallback(WriteCallback writeCallback) {
        this.writeCallback = writeCallback;
        return model();
    }


    public StreamModel setAcceptRangesSupport(boolean acceptRangesSupport) {
        this.acceptRangesSupport = acceptRangesSupport;
        return model();
    }


    @Override
    protected void onSubmit(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        try (ServletOutputStream output = response.getOutputStream()) {
            if (!StringUtil.isBlank(StreamModel.this.fileName)) {
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            }

            // 不支持断点续传
            if (!this.acceptRangesSupport) {
                copy(output, 0, contentLength - 1);
                return;
            }

            // 读取客户端上传的请求数据范围数据,并告诉客户端允许断点续传
            String rangeText = request.getHeader("Range");
            response.setHeader("Accept-Ranges", "bytes");

            // 如果请求数据范围不存在，则客户端不需要断点续传，直接返回
            if (rangeText == null || StringUtil.isBlank(rangeText)) {
                this.copy(output, 0, contentLength - 1);
                return;
            }

            // 解析客户端提交的请求数据范围数据
            List<Range> rangeList = RangeParse.parseRange(rangeText);
            if (rangeList == null || rangeList.size() <= 0) {
                response.addHeader("Content-Range", "bytes */" + contentLength);
                response.sendError(SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }

            // 验证客户端提交的请求头是否合法
            for (RangeParse.Range range : rangeList) {
                if (range.validate(contentLength)) {
                    continue;
                }

                // 如果Range对象不合法，返回错误信息
                response.addHeader("Content-Range", "bytes */" + contentLength);
                response.sendError(SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }

            // 客户端只传入一个数据范围
            if (rangeList.size() == 1) {
                // 设置返回的数据范围
                RangeParse.Range range = rangeList.get(0);
                response.addHeader("Content-Range", format("bytes %d-%d/%d", range.getStart(), range.getEnd(), range.getLength()));

                // 设置传回内容在大小和Buffer大小
                long length = range.getEnd() - range.getStart() + 1;
                response.setContentLengthLong(length);
                response.setBufferSize(BUFFER_SIZE);

                // 写数据
                copy(output, range.getStart(), range.getEnd());
                return;
            }

            // 客户端传入了多个数据范围
            response.setContentType("multipart/byteranges; boundary=" + MULTIPART_BOUNDARY);
            for (RangeParse.Range range : rangeList) {
                // 输出每片数据的分隔符
                output.println();
                output.println("--" + MULTIPART_BOUNDARY);

                // 文件类型和设置返回的数据范围
                output.println("Content-Type: " + getContentType());
                output.println(format("Content-Range: bytes %d-%d/%d", range.getStart(), range.getEnd(), range.getLength()));

                // 输出一个空行,再定入数据
                output.println();
                copy(output, range.getStart(), range.getEnd());
            }

            // 输出结尾符
            output.println();
            output.print("--" + MULTIPART_BOUNDARY + "--");
        } catch (Exception | Error exception) {
            response.setStatus(500);
        }
    }

    // 写入数据
    private void copy(OutputStream out, long start, long end) throws Exception {
        getWriteCallback().copy(out, start, end);
    }

    @Nonnull
    private synchronized WriteCallback getWriteCallback() {
        return ObjectUtil.defIfNull(writeCallback, new WriteCallback() {
            public void copy(OutputStream out, long start, long end) throws IOException {
                if (StreamModel.this.inputStream == null) return;
                try (InputStream source = StreamModel.this.inputStream) {
                    long sendLength = end - start + 1, skip = source.skip(start);
                    require(skip >= start, format("Skip fail. [%d, %d]", skip, start));
                    if (end >= 0) transferTo(out, source, sendLength);
                    else source.transferTo(out);
                }
            }

            private void transferTo(OutputStream out, InputStream source, long sendLength) throws IOException {
                int length, size = BUFFER_SIZE;
                byte[] buffer = new byte[size];
                for (; sendLength > 0; sendLength -= length) {
                    size   = min(size, (int) sendLength);
                    length = source.read(buffer, 0, size);

                    if (length <= 0) break;
                    out.write(buffer, 0, length);
                }
            }
        });
    }

    @FunctionalInterface
    public interface WriteCallback {
        void copy(OutputStream out, long start, long end) throws Exception;
    }
}

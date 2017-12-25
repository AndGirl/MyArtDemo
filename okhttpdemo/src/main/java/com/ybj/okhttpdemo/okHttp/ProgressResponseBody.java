package com.ybj.okhttpdemo.okHttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;


/**
 * Created by 杨阳洋 on 2017/12/23.
 * 仿写demo Progress
 */

public class ProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final ProgerssListener progressListener;
    private BufferedSource bufferedSource;

    ProgressResponseBody(ResponseBody responseBody, ProgerssListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            long sum = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
//                long bytesRead = super.read(sink, byteCount);
//                // read() returns the number of bytes read, or -1 if this source is exhausted.
//                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
//                //progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
//                return bytesRead;
                if(totalBytesRead == 0) {
                    totalBytesRead = contentLength();
                }
                long len = super.read(sink, byteCount);
                sum += totalBytesRead != -1 ? len : -1;
                int progress = (int) ((sum * 1.0f / totalBytesRead) * 100);
                if(len == -1) {
                    progressListener.onDone(totalBytesRead);
                }else{
                    progressListener.onProgress(progress);
                }

                return len;

            }
        };
    }
}

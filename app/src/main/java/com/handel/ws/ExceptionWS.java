package com.handel.ws;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by Marco Antonio on 13/02/2018.
 */

public class ExceptionWS extends RuntimeException {
    private String status;
    public ExceptionWS() {
    }

    public ExceptionWS(String status, String message) {
        super(message);
        this.status = status;
    }

    public ExceptionWS(String status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public ExceptionWS(Throwable cause) {
        super(cause);
        this.status = status;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ExceptionWS(String status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

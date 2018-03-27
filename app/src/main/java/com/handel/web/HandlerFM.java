package com.handel.web;

import android.graphics.Bitmap;

/**
 * Created by Marco Antonio on 14/03/2018.
 */

public interface HandlerFM {
    void onSuccess(Bitmap img);
    void onError(Exception e);
}

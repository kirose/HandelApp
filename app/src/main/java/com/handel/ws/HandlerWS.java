package com.handel.ws;

import com.handel.vo.ResponseVO;

/**
 * Created by Marco Antonio on 12/02/2018.
 */

public interface HandlerWS {
    void onSuccess(ResponseVO vo);
    void onError(Exception e);
}

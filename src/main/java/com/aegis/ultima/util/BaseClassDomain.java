package com.aegis.ultima.util;

import com.aegis.ultima.constants.ApplicationConstant;

public class BaseClassDomain<T> {
    private String responseCode;
    private String descErrorCode;
    private T responseData;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescErrorCode() {
        return descErrorCode;
    }

    public void setDescErrorCode(String descErrorCode) {
        this.descErrorCode = descErrorCode;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public void setResponseCodeValue (String responseCode, String descErrCode, String descErrCodeEn) {
        this.responseCode = responseCode;
        this.descErrorCode = descErrCode;
    }

    public void setResponseSucceed(T responseData) {
        this.responseCode = ApplicationConstant.RESPONSE_SUCCEED_CODE;
        this.descErrorCode = ApplicationConstant.RESPONSE_SUCCEED;
        this.responseData = responseData;
    }

    public void setResponseException() {
        this.responseCode = ApplicationConstant.ERROR_EXCEPTION_CODE;
        this.descErrorCode = ApplicationConstant.ERROR_EXCEPTION;
    }

    public void setResponseParamNotComplete() {
        this.responseCode = ApplicationConstant.ERROR_EXCEPTION_CODE;
        this.descErrorCode = "Parameter not complete";
    }
}

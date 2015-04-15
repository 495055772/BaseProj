package com.projectzero.library.util.http.depend;

public interface HttpResponseHandle {

    public void onSuccess(String responseBody, int statusCode);
    public void onFailure(Exception e);
    public void onTimeOut();
}

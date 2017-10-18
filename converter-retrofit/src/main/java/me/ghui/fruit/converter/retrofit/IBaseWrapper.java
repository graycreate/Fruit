package me.ghui.fruit.converter.retrofit;

/**
 * Created by ghui on 24/07/2017.
 */

/**
 * Base interface to wrapper the raw http response, which can give you a chance to
 * retrieve the response.
 */
public interface IBaseWrapper {
    String getResponse();

    void setResponse(String response);
}

package me.ghui.example_android.network.bean;

/**
 * Created by ghui on 21/06/2017.
 * Check whether the model is valid, if invalide try to find the reason from the rawResponse.
 * Such as login expired, no premission, etc.
 */

public abstract class BaseInfo implements IBase {
    public String rawResponse;

    @Override
    public String getResponse() {
        return rawResponse;
    }

    @Override
    public void setResponse(String response) {
        rawResponse = response;
    }
}

package me.ghui.example_android.network.bean;

import me.ghui.fruit.converter.retrofit.IBaseWrapper;

/**
 * Created by ghui on 24/07/2017.
 */

public interface IBase extends IBaseWrapper {
    /**
     * 某个接口返回业务上的合法性
     *
     * @return
     */
    boolean isValid();
}

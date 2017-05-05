package me.ghui.fruit;


import me.ghui.fruit.reflect.TypeToken;

/**
 * Created by ghui on 13/04/2017.
 */

public interface PickAdapterFactory {

    <T> PickAdapter<T> create(Fruit fruit, TypeToken<T> typeToken);

}

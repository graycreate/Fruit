package me.ghui.fruit;

import com.sun.istack.internal.Nullable;
import me.ghui.fruit.annotations.Pick;
import org.jsoup.nodes.Element;



/**
 * Created by ghui on 13/04/2017.
 */

public abstract class PickAdapter<T> {

	public abstract T read(Element element, @Nullable Pick pick);

//	public abstract void write(T value);

}

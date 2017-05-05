package me.ghui.fruit.bind;


import com.sun.istack.internal.Nullable;
import me.ghui.fruit.Fruit;
import me.ghui.fruit.PickAdapter;
import me.ghui.fruit.PickAdapterFactory;
import me.ghui.fruit.annotations.Pick;
import me.ghui.fruit.internal.Types;
import me.ghui.fruit.reflect.TypeToken;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ghui on 13/04/2017.
 */

public final class CollectionPickAdapterFactory implements PickAdapterFactory {

	@Override
	public <C> PickAdapter<C> create(Fruit fruit, TypeToken<C> typeToken) {
		Type type = typeToken.getType();

		Class<? super C> rawType = typeToken.getRawType();
		if (!Collection.class.isAssignableFrom(rawType)) {
			return null;
		}

		Type elementType = Types.getCollectionElementType(type, rawType);
		PickAdapter<?> elementAdapter = fruit.getAdapter(TypeToken.get(elementType));
		@SuppressWarnings({"unchecked"})
		PickAdapter<C> adapter = new Adapter(rawType, elementAdapter);
		return adapter;
	}

	private static final class Adapter<E> extends PickAdapter<Collection<E>> {

		private Class<Collection<E>> type;
		private PickAdapter<E> elementAdapter;

		public Adapter(Class<Collection<E>> type, PickAdapter<E> elementAdapter) {
			this.type = type;
			this.elementAdapter = elementAdapter;
		}

		@Override
		public Collection<E> read(Element element, @Nullable Pick pick) {
			List<E> list;
			if (LinkedList.class.isAssignableFrom(type)) {
				list = new LinkedList<>();
			} else if (List.class.isAssignableFrom(type)) {
				//default ArrayList
				list = new ArrayList<>();
			} else {
				throw new UnsupportedOperationException("Only support ArrayList and LinkedList Collection type for now");
			}

			if (pick != null) {
				Elements elements = element.select(pick.value());
				for (Element e : elements) {
					E instance = elementAdapter.read(e, pick);
					list.add(instance);
				}
			}
			return list;
		}

	}

}

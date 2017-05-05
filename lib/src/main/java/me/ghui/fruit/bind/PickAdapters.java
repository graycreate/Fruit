package me.ghui.fruit.bind;

import com.sun.istack.internal.Nullable;
import me.ghui.fruit.Fruit;
import me.ghui.fruit.PickAdapter;
import me.ghui.fruit.PickAdapterFactory;
import me.ghui.fruit.annotations.Pick;
import me.ghui.fruit.reflect.TypeToken;
import org.jsoup.nodes.Element;

/**
 * Created by ghui on 13/04/2017.
 */

public final class PickAdapters {

    private PickAdapters() {
        throw new UnsupportedOperationException();
    }

    private static final PickAdapter<String> STRING = new PickAdapter<String>() {
        @Override
        public String read(Element element, Pick pick) {
            return parseElement(element, pick, String.class);
        }
    };

    private static final PickAdapter<Number> INTEGER = new PickAdapter<Number>() {
        @Override
        public Number read(Element element, @Nullable Pick pick) {
            return parseElement(element, pick, int.class);
        }
    };

    private static final PickAdapter<Number> LONG = new PickAdapter<Number>() {
        @Override
        public Number read(Element element, @Nullable Pick pick) {
            return parseElement(element, pick, Long.class);
        }
    };

    public static final PickAdapterFactory STRING_FACTORY = newFactory(String.class, STRING);
    public static final PickAdapterFactory INTEGER_FACTORY
            = newFactory(int.class, Integer.class, INTEGER);
    public static final PickAdapterFactory LONG_FACTORY
            = newFactory(long.class, Long.class, LONG);

    public static final PickAdapterFactory COLLECTION_FACTORY = new CollectionPickAdapterFactory();

    public static final ReflectivePickAdapterFactory REFLECTIVE_ADAPTER = new ReflectivePickAdapterFactory();

//**************************************************************************************************

    public static <T> PickAdapterFactory newFactory(final Class<T> type, final PickAdapter<T> adapter) {
        return new PickAdapterFactory() {
            @SuppressWarnings("unchecked")
            @Override
            public <TT> PickAdapter<TT> create(Fruit fruit, TypeToken<TT> typeToken) {
                return typeToken.getRawType() == type ? (PickAdapter<TT>) adapter : null;
            }
        };
    }

    public static <TT> PickAdapterFactory newFactory(
            final Class<TT> unboxed, final Class<TT> boxed, final PickAdapter<? super TT> adapter) {
        return new PickAdapterFactory() {
            @SuppressWarnings("unchecked")
            @Override
            public <T> PickAdapter<T> create(Fruit fruit, TypeToken<T> typeToken) {
                return (unboxed == typeToken.getRawType() || boxed == typeToken.getRawType())
                        ? (PickAdapter<T>) adapter : null;
            }
        };
    }

    @SuppressWarnings("unchecked")
    private static <T> T parseElement(Element element, Pick pick, Class<T> type) {
        String value = null;
        if (pick != null) {
            element = element.select(pick.value()).first();
            if (element == null) return (T) value;
            String attr = pick.attr();
            if ("text".equals(attr)) {
                value = element.text();
            } else if ("ownText".equals(attr)) {
                value = element.ownText();
            } else {
                value = element.attr(attr);
            }
        }
        if (type == int.class || type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == long.class || type == Long.class) {
            return (T) Long.valueOf(value);
        } else if (type == float.class || type == Float.class) {
            return (T) Float.valueOf(value);
        } else if (type == double.class || type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else {
            return (T) value;
        }
    }

}

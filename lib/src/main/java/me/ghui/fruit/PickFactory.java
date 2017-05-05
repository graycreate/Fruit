package me.ghui.fruit;

import me.ghui.fruit.annotations.Pick;

import java.lang.annotation.Annotation;


/**
 * Created by ghui on 16/04/2017.
 */

public class PickFactory {

	public static Pick create(final String query, final String attr) {
		return new Pick() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return Pick.class;
			}

			@Override
			public String value() {
				return query;
			}

			@Override
			public String attr() {
				return attr;
			}
		};
	}
}

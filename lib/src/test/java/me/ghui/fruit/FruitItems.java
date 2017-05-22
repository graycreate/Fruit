package me.ghui.fruit;

import me.ghui.fruit.annotations.Pick;

import java.util.ArrayList;

/**
 * Created by ghui on 22/05/2017.
 */

@Pick("div.fruit")
public class FruitItems extends ArrayList<FruitItems.FruitItem> {

    public static class FruitItem {
        @Pick("strong.name")
        private String name;
        @Pick(".color")
        private String color;
        @Pick(attr = "id")
        private int id;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}

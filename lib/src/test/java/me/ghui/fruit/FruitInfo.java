package me.ghui.fruit;

import me.ghui.fruit.annotations.Pick;
import me.ghui.fruit.internal.Preconditions;

import java.util.List;

/**
 * Created by ghui on 08/05/2017.
 */
public class FruitInfo {
    @Pick("div#fruits div.best")
    private Best best;
    @Pick("div#only")
    private String favorite;
    @Pick(value = "div#only", attr = Attrs.HTML)
    private String favoriteHtml;
    @Pick(value = "div#only", attr = Attrs.INNER_HTML)
    private String favoriteInnerHtml;
    @Pick(value = "div#only", attr = Attrs.OWN_TEXT)
    private String favoriteOne;
    @Pick(value = "img.apple", attr = Attrs.SRC)
    private String img;
    @Pick(value = "a.author", attr = Attrs.HREF)
    private String blog;
    @Pick("body div.fruit")
    private List<Item> items;
    private String fieldWithOutAnnotation = "_fieldWithOutAnnotation_";
    @Pick("div.problem")
    private Problem problem;

    public Best getBest() {
        return best;
    }

    public Problem getProblem() {
        return problem;
    }

    public static class Problem {
        @Pick(attr = Attrs.OWN_TEXT)
        private String title;
        @Pick("ul li")
        private List<String> tips;

        public boolean isEmpty() {
            return Preconditions.isEmpty(tips) && Preconditions.isEmpty(tips);
        }

        public List<String> getTips() {
            return tips;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "Problem{" +
                    "title='" + title + '\'' +
                    ", tips=" + tips +
                    '}';
        }
    }

    public static class Best {
        @Pick("strong.name")
        private String name;
        @Pick("strong.color")
        private String color;

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Best{" +
                    "name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    public String getFavorite() {
        return favorite;
    }

    public String getFavoriteOne() {
        return favoriteOne;
    }

    public String getFavoriteHtml() {
        return favoriteHtml;
    }

    public String getFavoriteInnerHtml() {
        return favoriteInnerHtml;
    }

    public String getFieldWithOutAnnotation() {
        return fieldWithOutAnnotation;
    }

    public String getImg() {
        return img;
    }

    public String getBlog() {
        return blog;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "FruitInfo{" +
                "favorite='" + favorite + '\'' +
                ", favoriteOne='" + favoriteOne + '\'' +
                ", favoriteHtml='" + favoriteHtml + '\'' +
                ", favoriteHtml='" + favoriteInnerHtml + '\'' +
                ", fieldWithOutAnnotation='" + fieldWithOutAnnotation + '\'' +
                ", img='" + img + '\'' +
                ", blog='" + blog + '\'' +
                ", items=" + items +
                ", best=" + best +
                '}';
    }

    public static class Item {
        @Pick("strong.name")
        private String name;
        @Pick(".color")
        private String color;
        @Pick(attr = "id")
        private int id;
        private boolean isRed = false;

        public boolean isRed() {
            isRed = color.equals("red");
            return isRed;
        }

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

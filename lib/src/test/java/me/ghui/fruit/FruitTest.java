package me.ghui.fruit;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by ghui on 16/04/2017.
 */
public class FruitTest {
    private static FruitInfo fruitInfo;

    @BeforeClass
    public static void init() throws IOException, URISyntaxException {
        java.net.URL url = Thread.currentThread().getContextClassLoader().getResource("fruit.html");
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        String htmlStr = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
//        System.out.println("html: " + htmlStr);
        Fruit fruit = new Fruit();
        fruitInfo = fruit.fromHtml(htmlStr, FruitInfo.class);
        System.out.println("fruitInfo: " + fruitInfo);
    }

    @Test
    public void testText() {
        assert fruitInfo.getFavorite().equals("Apple is my favorite fruit.");
    }

    @Test
    public void testOwnText() {
        assert fruitInfo.getFavoriteOne().equals("Apple");
    }

    @Test
    public void testAttr() {
        assert fruitInfo.getImg().equals("http://dwz.cn/5USjpv");
        assert fruitInfo.getBlog().equals("https://ghui.me");
    }

    @Test
    public void testCollection() {
        List<FruitInfo.Item> items = fruitInfo.getItems();
        assert items.size() == 5;
        assert items.get(0).getName().equals("apple");
        assert items.get(4).getColor().equals("pink");
    }

}

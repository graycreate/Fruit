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
    private static String htmlStr;

    @BeforeClass
    public static void init() throws IOException, URISyntaxException {
        java.net.URL url = Thread.currentThread().getContextClassLoader().getResource("fruit.html");
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        htmlStr = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
//        System.out.println("html: " + htmlStr);
        fruitInfo = new Fruit().fromHtml(htmlStr, FruitInfo.class);
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
    public void testHtml() {
        assert fruitInfo.getFavoriteHtml().contains("<div");
    }

    @Test
    public void testInnerHtml() {
        assert fruitInfo.getFavoriteInnerHtml().contains("<em");
        assert !fruitInfo.getFavoriteInnerHtml().contains("<div");
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

    @Test
    public void testFieldWithOutAnnotion() {
        assert fruitInfo.getFieldWithOutAnnotation().equals("_fieldWithOutAnnotation_");
    }

    @Test
    public void testFieldWithOutPickValue() {
        assert fruitInfo.getItems().get(0).getId() == 1;
        assert fruitInfo.getItems().get(4).getId() == 5;
    }

    @Test
    public void testDirctList() {
        FruitItems items = new Fruit().fromHtml(htmlStr, FruitItems.class);
        assert items.size() == 5;
        assert items.get(4).getId() == 5;
        System.out.println("fruitItems: " + fruitInfo);
    }

    @Test
    public void testFieldWithoutPickAnnnotion() {
        assert fruitInfo.getItems().get(0).isRed();
    }

    @Test
    public void testNoneField() {
        assert fruitInfo.getBest() == null;
    }

    @Test
    public void testCollectionElementWithoutPickAnnotion() {
        assert "Problem A".equals(fruitInfo.getProblem().getTips().get(0));
    }
}

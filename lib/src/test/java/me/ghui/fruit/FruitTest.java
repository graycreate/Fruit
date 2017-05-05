package me.ghui.fruit;

import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ghui on 16/04/2017.
 */
public class FruitTest extends TestCase {
    Fruit fruit;
    Element html;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        html = Jsoup.connect("https://www.v2ex.com/?tab=all").get();
        fruit = new Fruit();
    }

    @Test
    public void testFruit() throws IOException {
        NewsInfo newsInfo = fruit.fromHtml(html, NewsInfo.class);
        assert (newsInfo.getItems().size() > 50);
        System.out.println("newsInfo: " + newsInfo);
    }

    // TODO: 06/05/2017 more test case 

}

# Fruit
[ ![Download](https://api.bintray.com/packages/ghui/Java/Fruit/images/download.svg) ](https://bintray.com/ghui/Java/Fruit/_latestVersion)
[![Build Status](https://travis-ci.org/ghuiii/Fruit.svg?branch=master)](https://travis-ci.org/ghuiii/Fruit)

Fruit is a Java library that can be used to convert html to Java Objects ~~and back~~ follow a certain rule.
So, in short, **Fruit for Html just like Gson for Json.**

Fruit was inspired by [Goolge's Gson](https://github.com/google/gson), and powered by [jsoup](https://github.com/jhy/jsoup).

# Example
Let me suppose that you have a html file, just like below:
```html
<html>
<head></head>
<body>
<div id='only'>Apple <em>is my favorite fruit.</em></div>
<img src='http://dwz.cn/5USjpv' class="apple"/>
<a href='https://ghui.me' class="author"> ghui</a>
<div id='fruits'>
    <div class="fruit" id="1">
        <strong class="name">apple</strong>
        <strong class="color">red</strong>
    </div>
    <div class="fruit" id="2">
        <strong class="name">orange</strong>
        <strong class="color">green</strong>
    </div>
    <div class="fruit" id="3">
        <strong class="name">banana</strong>
        <strong class="color">yellow</strong>
    </div>
    <div class="fruit" id="4">
        <strong class="name">pear</strong>
        <strong class="color">yellow</strong>
    </div>
    <div class="fruit" id="5">
        <strong class="name">peach</strong>
        <strong class="color">pink</strong>
    </div>
</div>
</body>
</html>
```
And now you want to parse the document to a Java Object named `FruitInfo`, like below:
```java
public class FruitInfo {
    private String favorite;
    private String favoriteOne;
    private String favoriteHtml;
    private String img;
    private String blog;
    private List<Item> items;

    public static class Item {
        private String name;
        private String color;
        private int id;
    }
}
```
What would you do ? No bullshit, with Fruit you can just do like below:
1. Add `Pick` annotation to FruitInfo.
```java
public class FruitInfo {
    @Pick("div#only")
    private String favorite;
    @Pick(value = "div#only", attr = Attrs.OWN_TEXT)
    private String favoriteOne;
    @Pick(value = "div#only", attr = Attrs.HTML)
    private String favoriteHtml;
    @Pick(value = "img.apple", attr = Attrs.SRC)
    private String img;
    @Pick(value = "a.author", attr = Attrs.HREF)
    private String blog;
    @Pick("div.fruit")
    private List<Item> items;

    public static class Item {
        @Pick("strong.name")
        private String name;
        @Pick(".color")
        private String color;
        @Pick(attr = "id")
        private int id;
    }
}
```
2. Just do pick from the html.
```java
FruitInfo fruitInfo = new Fruit().fromHtml(htmlStr, FruitInfo.class);
```

> Note: There are two field of the Pick annotation, the value field is a `css-like element selector`,
For more details, visit [here](https://jsoup.org/cookbook/extracting-data/selector-syntax).  
The second field is optional, and it's default value is `text`. You can assign *ownText*(direct text), *src*, *href*, and 
any other exist attr-name in your html tree to it. Just run the junit test code for more detail.

# Who uses Fruit ?
## 1. V2er - A V2EX App for Android platform.
[![V2er](http://ghui.u.qiniudn.com/ic_v2er.png-sm)](https://play.google.com/store/apps/details?id=me.ghui.v2er.free)


# Download
* Maven
```xml
<dependency>
  <groupId>me.ghui</groupId>
  <artifactId>Fruit</artifactId>
  <version>latest.version</version>
  <type>pom</type>
</dependency>
```
* Gradle
```groovy
compile 'me.ghui:Fruit:latest.version'
```

# Proguad
```
-keeppackagenames org.jsoup.nodes
```

# Thanks
1. [Gson](https://github.com/google/gson)
2. [jsoup](https://github.com/jhy/jsoup)

# License
Copyright 2017 ghui

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.



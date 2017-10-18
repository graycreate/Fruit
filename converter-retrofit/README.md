Retrofit Converter
==============

A `Converter` which uses [Fruit][1] for deserialization from Html.

A default `Fruit` instance will be created or one can be configured and passed to the
`FruitConverterFactory` to further control the deserialization.

Example
--------
```java
 Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addConverterFactory(FruitConverterFactory.create())
        .build();
    service = retrofit.create(Service.class);
```


Download
--------

* Maven

```xml
<dependency>
  <groupId>me.ghui</groupId>
  <artifactId>fruit-converter-retrofit</artifactId>
  <version>latest.version</version>
  <type>pom</type>
</dependency>
```
or 
* Gradle
```groovy
compile 'me.ghui:fruit-converter-retrofit:latest.version'
```

 [1]: https://github.com/ghuiii/Fruit

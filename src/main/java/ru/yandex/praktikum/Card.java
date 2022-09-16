package ru.yandex.praktikum;

public class Card {
    // вместо захардкоженных полей в жсоне создаём переменные
    private String name;
    private String link;

    // конструктор со всеми параметрами
    public Card(String name, String link) {
        this.name = name;
        this.link = link;
    }

    // конструктор без параметров
    public Card() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

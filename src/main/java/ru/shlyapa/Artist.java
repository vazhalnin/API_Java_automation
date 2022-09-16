package ru.shlyapa;

public class Artist {
    public void paint() {
        System.out.println("Я рисую");
    }
}

class Pointillist extends Artist {
    @Override
    public void paint() {
        System.out.println("Я рисую точками");
    }
}

class Impressionist extends Artist {
    @Override
    public void paint() {
        System.out.println("Я передаю впечатления");
    }
}

class Cubist extends Artist {
    @Override
    public void paint() {
        System.out.println("Я использую простые геометрические формы");
    }
}

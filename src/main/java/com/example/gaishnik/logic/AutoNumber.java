package com.example.gaishnik.logic;

import java.util.Random;
import java.util.TreeSet;

public class AutoNumber {

    /* Очень хотелось добавить, но в по условию не требуется
           прописать проверку на существующие регионы и готово,
           можно даже CRUD "прикрутить" с возможность менять регион,
           добавлять конкретные номера, удалять их и т.д...
    public AutoNumber(int region) {
        this.region = String.format(" %d RUS", region);
    }
       Ещё была мысль использовать паттерн проектирования "Singleton", но в контексте данного задания смысла не увидел,
           точнее он теряется, если расскомментить конструктор с параметром.
     */
    TreeSet<String> numbers = new TreeSet<>(); //Т.к. всё равно планировал импорт TreeSet.
    String[] alphabeticCharacter = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    String region = " 116 RUS";
    int countSeriesActual = 0; //счётчик серии номеров.
    int number = 0; //int часть номера.
    String[] series = getSeries(); //все возможные сочетания допустимых букв.
    Random random = new Random(); //Возможно было бы лучше использовать Math.random(), исправить - 1 минута.

    private String[] getSeries() {
        TreeSet<String> series = new TreeSet<>(); //Выбор пал на TreeSet для порядка и отсутствия дубликатов.
        for (String s : alphabeticCharacter) {
            for (String value : alphabeticCharacter) {
                for (String item : alphabeticCharacter) {
                    series.add(s + value + item);
                    series.add(s + item + value);
                    series.add(value + s + item);
                    series.add(value + item + s);
                    series.add(item + s + value);
                    series.add(item + value + s);
                }
            }
        }
        return series.toArray(new String[0]);//для удобства, всё равно весь метод отработает всего один раз.
    }

    private String generateNext() {

        if (numbers.isEmpty()) {
            this.number = 0;
        }
        if (number == 1000) { //1000 номеров в серии (от 000 до 999).
            this.number = 0;
            if (countSeriesActual == 1727) { //1728, начиная с 0. Если номер например Х999ХХ - next будет А000АА, если ещё не был и т.д.
                this.countSeriesActual = 0;
            } else {
                this.countSeriesActual++;
            }
        }
        String result = String.format("%s%03d%2s", series[countSeriesActual].charAt(0), number, series[countSeriesActual].substring(1, 3) + region);
        this.number++;
        return result;
    }

    public String getNext() {
        if (numbers.size() == 1_728_000) { //больше нет уникальных комбинаций.
            return "All numbers are busy";
        }
        String str = generateNext();
        while (numbers.contains(str)) { // генерация следующего, если текущий уже был.
            str = generateNext();
        }
        this.numbers.add(str);
        return str;
    }


    private String generateRandom() {
        this.number = random.nextInt(1000); //"рандомная" int часть.
        this.countSeriesActual = random.nextInt(1728);//"рандоминая" серия.
        return String.format("%s%03d%2s", series[countSeriesActual].charAt(0), number, series[countSeriesActual].substring(1, 3) + region);
    }

    public String getRandom() { //аналогично getNext();
        if (numbers.size() == 1_728_000) {
            return "All numbers are busy";
        }
        String str = generateRandom();
        while (numbers.contains(str)) {
            str = generateRandom();
        }
        this.numbers.add(str);
        return str;

    }

}
package com.example.gaishnik.logic;

import java.util.Random;
import java.util.TreeSet;

public class AutoNumber {

    TreeSet<String> numbers = new TreeSet<>();
    String[] alphabeticCharacter = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
    String region = " 116 RUS";
    int countSeriesActual = 0;
    int number = 0;
    String[] series = getSeries();
    Random random = new Random();

    private String[] getSeries() {
        TreeSet<String> series = new TreeSet<>();
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
        return series.toArray(new String[0]);
    }

    private String generateNext() {

        if (numbers.isEmpty()) {
            this.number = 0;
        }
        if (number == 1000) {
            this.number = 0;
            if (countSeriesActual == 1727) {
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
        if (numbers.size() == 1_728_000) {
            return "All numbers are busy";
        }
        String str = generateNext();
        while (numbers.contains(str)) {
            str = generateNext();
        }
        this.numbers.add(str);
        return str;
    }


    private String generateRandom() {
        this.number = random.nextInt(1000);
        this.countSeriesActual = random.nextInt(1728);
        return String.format("%s%03d%2s", series[countSeriesActual].charAt(0), number, series[countSeriesActual].substring(1, 3) + region);
    }

    public String getRandom() {
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
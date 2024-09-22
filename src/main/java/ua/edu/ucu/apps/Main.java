package ua.edu.ucu.apps;

import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis();
        System.out.println(temperatureSeriesAnalysis.summaryStatistics().toString());


        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String concat = "NAME".concat(input);


        String password = "12345";
    }
}

package com.github.dmh;

import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        DemonstrationFactory factory = new DemonstrationFactory();
        Demonstration demonstration = factory.getDemonstration();
        String applicableScalaVersion = demonstration.getApplicableScalaVersion();
        boolean correctScalaVersion = demonstration.isCorrectScalaVersion();
        java.util.List<String> items = demonstration.getItems();

        System.out.println("Applicable Scala version: " + applicableScalaVersion);
        System.out.println("Correct Scala version: " + correctScalaVersion);
        System.out.println("Items: " + items.stream().collect(Collectors.joining(",", "[", "]")));
    }
}

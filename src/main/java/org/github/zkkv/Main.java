package org.github.zkkv;

import org.apache.commons.lang3.tuple.Pair;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program expects a single parameter: path to .java file.");
            return;
        }
        String filePath = args[0];

        ComplexityEvaluator evaluator = new ComplexityEvaluator();
        try {
            var matches = evaluator.analyzeConditionals(filePath, 3);
            System.out.println("METHOD - SCORE");
            for (Pair<String, Integer> p : matches) {
                System.out.println(p.getLeft() + " " + p.getRight());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }
}
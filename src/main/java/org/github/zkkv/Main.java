package org.github.zkkv;

import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program expects a single parameter: path to .java file.");
            return;
        }
        String filePath = args[0];

        ComplexityEvaluator evaluator = new ComplexityEvaluator();
        try {
            Map<String, Integer> map = evaluator.analyzeConditionals(filePath);
            for (String str : map.keySet()) {
                System.out.println(str + " " + map.get(str));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
    }
}
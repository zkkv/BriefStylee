package org.github.zkkv;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ComplexityEvaluator {

    private final Filter<CtElement> filter = elem ->
               elem instanceof CtIf
            || elem instanceof CtDo
            || elem instanceof CtSwitch<?>
            || elem instanceof CtFor
            || elem instanceof CtWhile;

    /**
     * Returns the top three methods that have the highest number of conditional statements
     * among all methods in the provided file.
     *
     * @param filePath path to a .java file.
     * @return TODO
     */
    public Map<String, Integer> analyzeConditionals(String filePath) throws FileNotFoundException {
        if (filePath == null || !(new File(filePath).isFile())) {
            throw new FileNotFoundException();
        }

        Map<String, Integer> map = new HashMap<>();
        CtModel model = this.getModel(filePath);

        for (CtType<?> type : model.getAllTypes()) {
            var methods = type.getMethods();
            for (CtMethod<?> method : methods) {
                int count = analyzeConditionalsSingle(method);
                map.put(method.getSimpleName(), count);
            }
        }

        return map;
    }

    private int analyzeConditionalsSingle(CtMethod<?> method) {
        return method.getBody().getElements(filter).size();
    }

    private CtModel getModel(String filePath) {
        Launcher launcher = new Launcher();
        launcher.addInputResource(filePath);
        launcher.buildModel();
        return launcher.getModel();
    }
}

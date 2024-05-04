package org.github.zkkv;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

import java.io.FileNotFoundException;

public class StyleChecker extends TreeChecker{

    /**
     * Returns the percentage of methods in the provided file that violate the camelCase
     * naming convention.
     *
     * @param filePath path to a .java file.
     * @return percentage of methods with violations, or 0 if there are no methods at all.
     * @throws FileNotFoundException if file path is incorrect.
     */
    public double checkStyle(String filePath) throws FileNotFoundException {
        String absolute = this.convertPath(filePath);
        CtModel model = this.getModel(absolute);

        int totalCount = 0;
        int violations = 0;

        for (CtType<?> type : model.getAllTypes()) {
            var methods = type.getMethods();
            for (CtMethod<?> method : methods) {
                totalCount++;
                if (!method.getSimpleName().matches("^[a-z][a-zA-Z0-9]+")) {
                    violations++;
                }
            }
        }

        return totalCount == 0 ? 0.0 : (double) violations / totalCount * 100;
    }
}

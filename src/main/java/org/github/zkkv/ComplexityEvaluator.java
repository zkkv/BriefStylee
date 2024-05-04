package org.github.zkkv;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Filter;
import java.io.FileNotFoundException;
import java.util.*;

public class ComplexityEvaluator extends TreeChecker{

    private final Filter<CtElement> filter = elem ->
               elem instanceof CtIf
            || elem instanceof CtDo
            || elem instanceof CtSwitch<?>
            || elem instanceof CtFor
            || elem instanceof CtWhile;

    /**
     * Returns the top {@code nWorst} methods that have the highest number of conditional statements
     * among all methods in the provided file along with their scores or all methods
     * if {@code nWorst} is larger than the number of methods.
     *
     * @param filePath path to a .java file.
     * @param nWorst how many method names to preserve.
     * @return list of pairs each containing a method name and its score.
     * @throws FileNotFoundException if file path is incorrect.
     */
    public List<Pair<String, Integer>> analyzeConditionals(String filePath, int nWorst)
            throws FileNotFoundException {

        String absolute = this.convertPath(filePath);
        CtModel model = this.getModel(absolute);

        List<Pair<String, Integer>> matches = new ArrayList<>();

        for (CtType<?> type : model.getAllTypes()) {
            var methods = type.getMethods();
            for (CtMethod<?> method : methods) {
                int count = method.getBody().getElements(filter).size();
                matches.add(new ImmutablePair<>(method.getSimpleName(), count));
            }
        }

        matches.sort((a, b) -> -Integer.compare(a.getRight(), b.getRight()));
        return matches.subList(0, Math.min(matches.size(), Math.max(0, nWorst)));
    }
}

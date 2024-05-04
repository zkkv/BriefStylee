package org.github.zkkv;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.*;

public class ComplexityEvaluator {

    private final Filter<CtElement> filter = elem ->
               elem instanceof CtIf
            || elem instanceof CtDo
            || elem instanceof CtSwitch<?>
            || elem instanceof CtFor
            || elem instanceof CtWhile;

    private final Launcher launcher;

    public ComplexityEvaluator() {
        launcher = new Launcher();
    }

    /**
     * Returns the top {@code nWorst} methods that have the highest number of conditional statements
     * among all methods in the provided file along with their scores or all methods
     * if {@code nWorst} is larger than the number of methods.
     *
     * @param filePath path to a .java file.
     * @return list of pairs each containing a method name and its score.
     */
    public List<Pair<String, Integer>> analyzeConditionals(String filePath, int nWorst)
            throws FileNotFoundException {
        if (filePath == null) {
            throw new FileNotFoundException();
        }

        String absolute = FileSystems.getDefault().getPath(filePath).normalize().toAbsolutePath().toString();

        if (!(new File(absolute).isFile())) {
            throw new FileNotFoundException();
        }

        List<Pair<String, Integer>> matches = new ArrayList<>();
        CtModel model = this.getModel(absolute);

        for (CtType<?> type : model.getAllTypes()) {
            var methods = type.getMethods();
            for (CtMethod<?> method : methods) {
                int count = method.getBody().getElements(filter).size();
                matches.add(new ImmutablePair<>(method.getSimpleName(), count));
            }
        }

        matches.sort((a, b) -> -Integer.compare(a.getRight(), b.getRight()));
        return matches.subList(0, Math.min(matches.size(), nWorst));
    }

    private CtModel getModel(String filePath) {
        launcher.addInputResource(filePath);
        launcher.buildModel();
        return launcher.getModel();
    }
}

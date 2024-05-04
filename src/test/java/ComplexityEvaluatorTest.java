import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.github.zkkv.ComplexityEvaluator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class ComplexityEvaluatorTest {

    public ComplexityEvaluator evaluator;
    private final String relPath = "src/test/resources/ComplexityEvaluatorTests/";

    @BeforeEach
    void setUp() {
        evaluator = new ComplexityEvaluator();
    }

    @Test
    void testSingleIf() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "TestSingleIf.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testSingleSwitch() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testSingleSwitch.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testSingleDoWhile() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testSingleDoWhile.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testSingleWhile() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testSingleWhile.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testSingleFor() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testSingleFor.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testNested() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("foo", 7)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "TestNested.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testThreeMethods() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("bar", 3),
                new ImmutablePair<>("foobar", 2),
                new ImmutablePair<>("foo", 1)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testThreeMethods.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testFourMethodsWorstFirst() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("baz", 4),
                new ImmutablePair<>("bar", 3),
                new ImmutablePair<>("foobar", 2)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testFourMethodsWorstFirst.java", 3);
        assertEquals(expected, actual);
    }

    @Test
    void testFourMethodsWorstLast() throws FileNotFoundException {
        List<Pair<String, Integer>> expected = List.of(
                new ImmutablePair<>("baz", 4),
                new ImmutablePair<>("bar", 3),
                new ImmutablePair<>("foobar", 2)
        );
        List<Pair<String, Integer>> actual =
                evaluator.analyzeConditionals(relPath + "testFourMethodsWorstLast.java", 3);
        assertEquals(expected, actual);
    }
}

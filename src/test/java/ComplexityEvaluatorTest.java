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
    private final String relPath = "src/test/resources/";

    @BeforeEach
    void setUp() {
        evaluator = new ComplexityEvaluator();
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
}

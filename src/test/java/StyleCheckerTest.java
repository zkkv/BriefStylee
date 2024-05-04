import org.github.zkkv.StyleChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StyleCheckerTest {

    public StyleChecker checker;
    private final String relPath = "src/test/resources/StyleCheckerTests/";

    @BeforeEach
    void setUp() {
        checker = new StyleChecker();
    }

    @Test
    void testZeroMethods() throws FileNotFoundException {
        double expected = 0.0;
        double actual = checker.checkStyle(relPath + "TestZeroMethods.java");
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void testOneProper() throws FileNotFoundException {
        double expected = 0.0;
        double actual = checker.checkStyle(relPath + "testOneProper.java");
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void testOnePascal() throws FileNotFoundException {
        double expected = 100.0;
        double actual = checker.checkStyle(relPath + "testOnePascal.java");
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void testOneSnake() throws FileNotFoundException {
        double expected = 100.0;
        double actual = checker.checkStyle(relPath + "testOneSnake.java");
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void testMultiple() throws FileNotFoundException {
        double expected = 66.7;
        double actual = checker.checkStyle(relPath + "testMultiple.java");
        assertEquals(expected, actual, 0.1);
    }
}

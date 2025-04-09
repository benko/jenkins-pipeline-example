import static org.junit.jupiter.api.Assertions.*;

public class GreeterTest {
    public static void testGreeting(Greeter g, String input, String expectation) {
        String result = g.greet(input);
        assertEquals(expectation, result);
    }
}

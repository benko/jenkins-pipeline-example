import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OldGreeterTest {
    GreeterOld g;

	@Test
	public void testGreetingEnglish() {
	    this.g = new GreeterOld("en_US");
		String input = "John";
		String result = g.greet(input);
		assertEquals("Hello, John", result);
	}

	@Test
	public void testGreetingFrench() {
	    GreeterOld g = new GreeterOld("fr_FR");
		String input = "John";
		String result = g.greet(input);
		assertEquals("Salut, John", result);
	}

	@Test
	public void testGreetingSpanish() {
	    GreeterOld g = new GreeterOld("es_ES");
		String input = "John";
		String result = g.greet(input);
		assertEquals("Hola, John", result);
	}
}

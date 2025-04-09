import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class ServiceTest {
	Greeter g;

	@Before
	public void set_up_greeter() {
	    this.g = new Greeter("en_US");
	}

	@Test
	public void test_greeting() {
		String input = "John";
		String result = g.greet(input);
		assertEquals("Hello, John", result);
	}
}

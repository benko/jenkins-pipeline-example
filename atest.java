public class atest {
	public void test_greeting() {
		String input = "John";
		String result = greet(input);
		assertEquals("Hello, John", result);
	}
}

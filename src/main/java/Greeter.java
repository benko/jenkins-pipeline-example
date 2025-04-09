public class Greeter {
    String lang;

    public Greeter(String lang) {
	this.lang = lang;
    }

    public String greet(String input) {
	return "Hello, " + input;
    }
}

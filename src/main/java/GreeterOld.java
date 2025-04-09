public class GreeterOld {
    private String lang;

    @Deprecated
    public GreeterOld(final String lang) {
	    this.lang = lang;
    }

    public String greet(String input) {
        String greeting = null;

        switch (lang) {
            case "en_EN":
            case "en_US":
            case "en_UK":
                greeting = "Hello";
                break;

            case "fr_FR":
                greeting = "Salut";
                break;

            case "es_ES":
                greeting = "Hola";
                break;

            default:
                throw new RuntimeException("Unknown language: " + lang);
        }

        return greeting + ", " + input;
    }
}

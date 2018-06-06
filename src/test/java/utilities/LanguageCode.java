package utilities;

public enum LanguageCode {
	EN("en"), TR("tr");

	private String value;

	private LanguageCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

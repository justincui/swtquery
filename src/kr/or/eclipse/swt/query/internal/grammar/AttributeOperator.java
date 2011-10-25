package kr.or.eclipse.swt.query.internal.grammar;

public enum AttributeOperator {
	EQUALS("="), CONTAINS("*=");

	private String text;

	private AttributeOperator(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}

}

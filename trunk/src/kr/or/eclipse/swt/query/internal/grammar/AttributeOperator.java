package kr.or.eclipse.swt.query.internal.grammar;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public enum AttributeOperator {
	EQUALS("="), CONTAINS("*="), NOT_EQUALS("!=");
	public static final AttributeOperator[] VALUE_ARRAY = { EQUALS, CONTAINS, NOT_EQUALS };
	public static final List<AttributeOperator> VALUES = Arrays.asList(VALUE_ARRAY);

	public static AttributeOperator get(String operatorText) {
		for (AttributeOperator each : VALUE_ARRAY) {
			if (each.getText().equals(operatorText)) {
				return each;
			}
		}
		throw new IllegalArgumentException(MessageFormat.format("{0} is not an operator.", operatorText));
	}

	private final String text;

	public String getText() {
		return text;
	}

	private AttributeOperator(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}

}

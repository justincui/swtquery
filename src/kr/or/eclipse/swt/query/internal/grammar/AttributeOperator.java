package kr.or.eclipse.swt.query.internal.grammar;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public enum AttributeOperator {
	EQUALS(0, "="), CONTAINS(1, "*="), NOT_EQUALS(2, "!=");
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

	private final int value;
	private final String text;

	public String getText() {
		return text;
	}

	private AttributeOperator(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public int getValue() {
		return value;
	}

}

package kr.or.eclipse.swt.query.parse;

public class AttributeFilter {
	private String attributeName;
	private String value;
	private AttributeOperator operator;

	public String getAttributeName() {
		return attributeName;
	}

	public AttributeOperator getOperator() {
		return operator;
	}

	public String getValue() {
		return value;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public void setOperator(AttributeOperator operator) {
		this.operator = operator;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AttributeFilter [attributeName=" + attributeName
				+ ", operator=" + operator + ", value=" + value + "]";
	}

}

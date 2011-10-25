package kr.or.eclipse.swt.query.internal.grammar;

public class AttributeFilter {
	private String attributeName;
	private String value;
	private AttributeOperator operator;

	public AttributeFilter(AttributeOperator operator, String attributeName, String value) {
		super();
		this.operator = operator;
		this.attributeName = attributeName;
		this.value = value;
	}

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
		return "AttributeFilter [attributeName=" + attributeName + ", operator=" + operator + ", value=" + value + "]";
	}

}

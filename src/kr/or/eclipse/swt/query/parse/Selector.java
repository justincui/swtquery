package kr.or.eclipse.swt.query.parse;

public class Selector {
	private boolean directChildrenOnly;

	private String typeText;

	public Selector() {
	}

	public String getTypeText() {
		return typeText;
	}

	public boolean isDirectChildrenOnly() {
		return directChildrenOnly;
	}

	public void setDirectChildrenOnly(boolean directChildrenOnly) {
		this.directChildrenOnly = directChildrenOnly;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

}

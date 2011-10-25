package kr.or.eclipse.swt.query.parse;

import java.util.ArrayList;
import java.util.List;

public class Selector {
	private List<AttributeFilter> attributeFilters = new ArrayList<AttributeFilter>();
	private boolean directChildrenOnly;
	private int styleFlag;
	private boolean typeSpecified;

	private String typeText;
	
	public void addAttribute(AttributeOperator op, String attrName, String attrValue){
		
	}
}

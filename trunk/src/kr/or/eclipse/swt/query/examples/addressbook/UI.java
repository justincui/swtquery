package kr.or.eclipse.swt.query.examples.addressbook;

import kr.or.eclipse.swt.query.SWTQuery;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class UI {
	public void create(SWTQuery $parent) {
		$parent.setGridLayout();

		SWTQuery $toolbar = $parent.create(ToolBar.class).setData("role", "toolbar");
		$toolbar.create(ToolItem.class).setText("Ãß°¡");

		$parent.create(Table.class).setGridLayoutData(GridData.FILL_BOTH).setData("role", "viewer");
	}
}

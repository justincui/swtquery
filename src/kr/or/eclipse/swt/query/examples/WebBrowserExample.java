package kr.or.eclipse.swt.query.examples;

import static kr.or.eclipse.swt.query.SWTQuery.$;
import kr.or.eclipse.swt.query.SWTQuery;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class WebBrowserExample {
	public static class Bookmark {
		public String title;
		public String url;

		public Bookmark(String title, String url) {
			super();
			this.title = title;
			this.url = url;
		}
	}

	public static void main(String[] args) {
		// �� ����.
		Bookmark[] bookmarks = new Bookmark[3];
		bookmarks[0] = new Bookmark("eclipse", "http://eclipse.org");
		bookmarks[1] = new Bookmark("��Ŭ����", "http://eclipse.or.kr");
		bookmarks[2] = new Bookmark("����", "http://google.co.kr");

		// UI �ۼ�
		Display display = Display.getDefault();
		final SWTQuery $shell = $(new Shell(display));

		$shell.setGridLayout();
		$shell.create(ToolBar.class, SWT.FLAT).setData("role", "bookmark-bar").setGridLayoutData(GridData.FILL_HORIZONTAL);
		$shell.create(Browser.class, SWT.NORMAL).setGridLayoutData(GridData.FILL_BOTH).setData("role", "browser");

		// ��Ʈ�ѷ� ����: �ϸ�ũ ������ ǥ��
		SWTQuery $bookmarkBar = $shell.select("toolbar[data-role=bookmark-bar]");
		for (Bookmark eachBookmark : bookmarks) {
			$bookmarkBar.create(ToolItem.class, SWT.PUSH).setText(eachBookmark.title).setData("url", eachBookmark.url);
		}

		// �� ����
		$bookmarkBar.select("*[data-url]").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String url = $(event).getData("url");
				$shell.select("browser[data-role=browser]").setUrl(url);
			}
		});
		$shell.setText("������").open().runEventLoopUntilDispose();

		display.dispose();

	}
}

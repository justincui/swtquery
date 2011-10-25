package kr.or.eclipse.swt.query.test;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Test1 {
	public static void main(String[] args) {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setLayout(new GridLayout());

		Group group = new Group(shell, SWT.NORMAL);
		group.setText("파일을 선택하세요.");
		group.setLayout(new GridLayout(3, false));

		for (int i = 0; i < 3; i++) {
			new Link(group, SWT.NORMAL).setText("<a href=\"#\">열기</a>");
			new Text(group, SWT.BORDER);
			new Button(group, SWT.PUSH).setText("찾기..");
		}

		Tree tree = new Tree(shell, SWT.V_SCROLL);
		for (int i = 0; i < 3; i++) {
			TreeItem treeItem = new TreeItem(tree, SWT.NORMAL);
			treeItem.setText("트리 아이템");
			for (int j = 0; j < 3; j++) {
				new TreeItem(treeItem, SWT.NORMAL).setText("서브 아이템");
			}
		}

		$(shell, "group").setGridLayoutData(GridData.FILL_BOTH);
		$(shell, "group>text").setGridLayoutData(GridData.FILL_HORIZONTAL);

		$(shell, "group>button:push").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				String result = dialog.open();
				if (result != null) {
					$(event).prev().setText(result);
				}
			}
		});

		$(shell, "link").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				File file = new File($(event).next().getText().trim());
				if (file.exists()) {
					Program.launch(file.getAbsolutePath());
				}
			}
		});

		Image image = new Image(display, 16, 16);
		GC gc = new GC(image);
		gc.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		gc.fillOval(0, 0, 16, 16);
		gc.drawOval(0, 0, 15, 15);
		gc.dispose();

		ImageData imageData = image.getImageData();
		imageData.transparentPixel = imageData.palette.getPixel(new RGB(255, 255, 255));
		image.dispose();
		image = new Image(display, imageData);

		$(shell, "tree").setGridLayoutData(GridData.FILL_BOTH).select(">treeitem")
				.setForeground(display.getSystemColor(SWT.COLOR_RED)).setImage(image);

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.readAndDispatch();
			}
		}
		display.dispose();
	}
}

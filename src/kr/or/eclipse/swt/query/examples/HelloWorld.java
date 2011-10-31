package kr.or.eclipse.swt.query.examples;

import static kr.or.eclipse.swt.query.SWTQuery.$;
import kr.or.eclipse.swt.query.SWTQuery;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class HelloWorld {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final SWTQuery $shell = $(new Shell(display));

		$shell.setGridLayout();

		$shell.create(Label.class, SWT.NORMAL).setText("안녕하세요!").setGridLayoutData(GridData.FILL_HORIZONTAL)
				.setAlignment(SWT.CENTER);

		SWTQuery $group = $shell.create(Group.class, SWT.NORMAL).setText("이름을 넣으세요");
		$group.setGridLayout(3, false).setGridLayoutData(GridData.FILL_HORIZONTAL);

		$group.create(Label.class, SWT.NORMAL).setText("이름:");
		$group.create(Text.class, SWT.BORDER).setGridLayoutData(GridData.FILL_HORIZONTAL);
		$group.create(Button.class, SWT.PUSH).setText("확인").addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				String name = $shell.select("label[text*='이름']").next().getText();
				SWTQuery $titleLabel = $shell.select("label").first();
				$titleLabel.setText("반갑습니다, " + name + "님.");
			}
		});

		$shell.pack().open().runEventLoopUntilDispose();

	}
}

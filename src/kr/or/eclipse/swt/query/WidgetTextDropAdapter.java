package kr.or.eclipse.swt.query;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import static kr.or.eclipse.swt.query.SWTQuery.$;

public class WidgetTextDropAdapter extends DropTargetAdapter {
	@Override
	public void dragEnter(DropTargetEvent event) {
		if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
			event.detail |= DND.DROP_COPY;
		}

		if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
			event.detail |= DND.DROP_COPY;
		}
	}

	@Override
	public void drop(DropTargetEvent event) {
		if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
			$($(event).getControl()).setText(event.data.toString());
			event.detail |= DND.DROP_MOVE;
		}

		if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
			String[] list = (String[]) event.data;
			$($(event).getControl()).setText(list[0]);
			event.detail |= DND.DROP_MOVE;
		}
	}
}

package kr.or.eclipse.swt.query.internal;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import static kr.or.eclipse.swt.query.SWTQuery.$;

public class AnimationFrame {
	public Widget widget;
	public Rectangle bounds;
	public Color background;
	public Color foreground;

	public AnimationFrame(Widget widget) {
		this.widget = widget;
		bounds = $(widget).getBounds();
		foreground = $(widget).getForeground();
		background = $(widget).getBackground();
	}

	private AnimationFrame() {

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AnimationFrame) {
			AnimationFrame other = (AnimationFrame) obj;
			if (!this.bounds.equals(other.bounds)) {
				return false;
			}

			else if ((this.foreground == null && other.foreground != null)
					|| (this.foreground != null && other.foreground == null)) {
				return false;
			}

			else if (!this.foreground.equals(other.foreground)) {
				return false;
			}

			else if ((this.background == null && other.background != null)
					|| (this.background != null && other.background == null)) {
				return false;
			}

			else if (!this.background.equals(other.background)) {
				return false;
			}

			return true;
		}
		return false;
	}

	public AnimationFrame computeFrame(AnimationFrame after, double timing) {
		AnimationFrame result = new AnimationFrame();

		result.widget = this.widget;

		result.bounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		int dx = after.bounds.x - bounds.x;
		int dy = after.bounds.y - bounds.y;
		int dw = after.bounds.width - bounds.width;
		int dh = after.bounds.height - bounds.height;

		result.bounds.x += Math.round(dx * timing);
		result.bounds.y += Math.round(dy * timing);
		result.bounds.width += Math.round(dw * timing);
		result.bounds.height += Math.round(dh * timing);

		if (this.foreground != null && after.foreground != null) {
			result.foreground = createColor(widget.getDisplay(), foreground, after.foreground, timing);
		}

		if (this.background != null && after.background != null) {
			result.background = createColor(widget.getDisplay(), background, after.background, timing);
		}

		return result;
	}

	@Override
	public String toString() {
		return "AnimationFrame [widget=" + widget + ", bounds=" + bounds + ", foreground=" + foreground + ", background="
				+ background + "]";
	}

	private Color createColor(Display display, Color c1, Color c2, double opacity) {
		int r = (int) (c1.getRed() + (c2.getRed() - c1.getRed()) * opacity);
		int g = (int) (c1.getGreen() + (c2.getGreen() - c1.getGreen()) * opacity);
		int b = (int) (c1.getBlue() + (c2.getBlue() - c1.getBlue()) * opacity);

		r = Math.max(Math.min(255, r), 0);
		g = Math.max(Math.min(255, g), 0);
		b = Math.max(Math.min(255, b), 0);

		return new Color(display, r, g, b);
	}

	public void dispose() {
		if (background != null && !background.isDisposed()) {
			background.dispose();
			background = null;
		}

		if (foreground != null && !foreground.isDisposed()) {
			foreground.dispose();
			foreground = null;
		}

		widget = null;
		bounds = null;
	}

	public void apply() {
		if (widget.isDisposed()) {
			return;
		}

		if (foreground != null) {
			$(widget).setForeground(foreground);
		}
		if (background != null) {
			$(widget).setBackground(background);
		}
		$(widget).setBounds(bounds);
		$(widget).parent().redraw();
	}
}
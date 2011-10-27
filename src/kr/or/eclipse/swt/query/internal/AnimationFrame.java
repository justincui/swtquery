package kr.or.eclipse.swt.query.internal;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public class AnimationFrame {
	public Widget widget;
	public Rectangle bounds;
	public Color background;
	public Color foreground;

	public AnimationFrame(Widget widget) {
		this.widget = widget;
		this.bounds = $(widget).getBounds();
		this.foreground = $(widget).getForeground();
		this.background = $(widget).getBackground();
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

			else if (this.foreground == null && other.foreground != null
					|| this.foreground != null && other.foreground == null) {
				return false;
			}

			else if (!this.foreground.equals(other.foreground)) {
				return false;
			}

			else if (this.background == null && other.background != null
					|| this.background != null && other.background == null) {
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

		result.bounds = new Rectangle(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
		int dx = after.bounds.x - this.bounds.x;
		int dy = after.bounds.y - this.bounds.y;
		int dw = after.bounds.width - this.bounds.width;
		int dh = after.bounds.height - this.bounds.height;

		result.bounds.x += Math.round(dx * timing);
		result.bounds.y += Math.round(dy * timing);
		result.bounds.width += Math.round(dw * timing);
		result.bounds.height += Math.round(dh * timing);

		if (this.foreground != null && after.foreground != null) {
			result.foreground = createColor(this.widget.getDisplay(), this.foreground, after.foreground, timing);
		}

		if (this.background != null && after.background != null) {
			result.background = createColor(this.widget.getDisplay(), this.background, after.background, timing);
		}

		return result;
	}

	@Override
	public String toString() {
		return "AnimationFrame [widget=" + this.widget + ", bounds=" + this.bounds + ", foreground=" + this.foreground + ", background="
				+ this.background + "]";
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
		if (this.background != null && !this.background.isDisposed()) {
			this.background.dispose();
			this.background = null;
		}

		if (this.foreground != null && !this.foreground.isDisposed()) {
			this.foreground.dispose();
			this.foreground = null;
		}

		this.widget = null;
		this.bounds = null;
	}

	public void apply() {
		if (this.widget.isDisposed()) {
			return;
		}

		if (this.foreground != null) {
			$(this.widget).setForeground(this.foreground);
		}
		if (this.background != null) {
			$(this.widget).setBackground(this.background);
		}
		$(this.widget).setBounds(this.bounds);
		$(this.widget).parent().redraw();
	}
}
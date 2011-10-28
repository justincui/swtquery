package kr.or.eclipse.swt.query.graphics;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

public class GCQuery {
	private static Set<Resource> autoDisposePool = new HashSet<Resource>();

	private static Runnable autoDisposer = new Runnable() {
		@Override
		public void run() {
			Resource[] array = autoDisposePool.toArray(new Resource[autoDisposePool.size()]);
			autoDisposePool.clear();

			for (Resource each : array) {
				if (!each.isDisposed()) {
					each.dispose();
				}
			}
		}
	};

	public static void $autoDispose(Resource r) {
		autoDisposePool.add(r);
		Display.getDefault().asyncExec(autoDisposer);
	}

	public static GCQuery $gc(Drawable drawable) {
		GC gc = new GC(drawable);
		return new GCQuery(gc);
	}

	public static GCQuery $gc(GC gc) {
		return new GCQuery(gc);
	}

	private GC gc;

	public GCQuery(GC gc) {
		super();
		this.gc = gc;
	}

	public GCQuery clearClipping() {
		this.gc.setClipping((Rectangle) null);
		return this;
	}

	public GCQuery copyArea(Image image, int x, int y) {
		this.gc.copyArea(image, x, y);
		return this;
	}

	public GCQuery copyArea(int srcX, int srcY, int width, int height, int destX, int destY) {
		this.gc.copyArea(srcX, srcY, width, height, destX, destY);
		return this;

	}

	public GCQuery copyArea(int srcX, int srcY, int width, int height, int destX, int destY, boolean paint) {
		this.gc.copyArea(srcX, srcY, width, height, destX, destY, paint);
		return this;
	}

	public void dispose() {
		this.gc.dispose();
	}

	public GCQuery drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		this.gc.drawArc(x, y, width, height, startAngle, arcAngle);
		return this;
	}

	public GCQuery drawFocus(int x, int y, int width, int height) {
		this.gc.drawFocus(x, y, width, height);
		return this;
	}

	public GCQuery drawImage(Image image, int x, int y) {
		this.gc.drawImage(image, x, y);
		return this;
	}

	public GCQuery drawImage(Image image, int srcX, int srcY, int srcWidth, int srcHeight, int destX, int destY, int destWidth,
			int destHeight) {
		this.gc.drawImage(image, srcX, srcY, srcWidth, srcHeight, destX, destY, destWidth, destHeight);
		return this;
	}

	public GCQuery drawLine(int x1, int y1, int x2, int y2) {
		this.gc.drawLine(x1, y1, x2, y2);
		return this;
	}

	public GCQuery drawOval(int x, int y, int width, int height) {
		this.gc.drawOval(x, y, width, height);
		return this;
	}

	public GCQuery drawPath(Path path) {
		this.gc.drawPath(path);
		return this;
	}

	public GCQuery drawPoint(int x, int y) {
		this.gc.drawPoint(x, y);
		return this;
	}

	public GCQuery drawPolygon(int[] pointArray) {
		this.gc.drawPolygon(pointArray);
		return this;
	}

	public GCQuery drawPolyline(int[] pointArray) {
		this.gc.drawPolyline(pointArray);
		return this;
	}

	public GCQuery drawRectangle(int x, int y, int width, int height) {
		this.gc.drawRectangle(x, y, width, height);
		return this;
	}

	public GCQuery drawRectangle(Rectangle rect) {
		this.gc.drawRectangle(rect);
		return this;
	}

	public GCQuery drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		this.gc.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight);
		return this;
	}

	public GCQuery drawString(String string, int x, int y) {
		this.gc.drawString(string, x, y);
		return this;
	}

	public GCQuery drawString(String string, int x, int y, boolean isTransparent) {
		this.gc.drawString(string, x, y, isTransparent);
		return this;
	}

	public GCQuery drawText(String string, int x, int y) {
		this.gc.drawText(string, x, y);
		return this;
	}

	public GCQuery drawText(String string, int x, int y, boolean isTransparent) {
		this.gc.drawText(string, x, y, isTransparent);
		return this;
	}

	public GCQuery drawText(String string, int x, int y, int flags) {
		this.gc.drawText(string, x, y, flags);
		return this;
	}

	public GCQuery fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		this.gc.fillArc(x, y, width, height, startAngle, arcAngle);
		return this;
	}

	public GCQuery fillGradientRectangle(int x, int y, int width, int height, boolean vertical) {
		this.gc.fillGradientRectangle(x, y, width, height, vertical);
		return this;
	}

	public GCQuery fillOval(int x, int y, int width, int height) {
		this.gc.fillOval(x, y, width, height);
		return this;
	}

	public GCQuery fillPath(Path path) {
		this.gc.fillPath(path);
		return this;
	}

	public GCQuery fillPolygon(int[] pointArray) {
		this.gc.fillPolygon(pointArray);
		return this;
	}

	public GCQuery fillRectangle(int x, int y, int width, int height) {
		this.gc.fillRectangle(x, y, width, height);
		return this;
	}

	public GCQuery fillRectangle(Rectangle rect) {
		this.gc.fillRectangle(rect);
		return this;
	}

	public GCQuery fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		this.gc.fillRoundRectangle(x, y, width, height, arcWidth, arcHeight);
		return this;
	}

	public boolean getAdvanced() {
		return this.gc.getAdvanced();
	}

	public int getAdvanceWidth(char ch) {
		return this.gc.getAdvanceWidth(ch);
	}

	public int getAlpha() {
		return this.gc.getAlpha();
	}

	public int getAntialias() {
		return this.gc.getAntialias();
	}

	public Color getBackground() {
		return this.gc.getBackground();
	}

	public Pattern getBackgroundPattern() {
		return this.gc.getBackgroundPattern();
	}

	public int getCharWidth(char ch) {
		return this.gc.getCharWidth(ch);
	}

	public Rectangle getClipping() {
		return this.gc.getClipping();
	}

	public void getClipping(Region region) {
		this.gc.getClipping(region);
	}

	public Device getDevice() {
		return this.gc.getDevice();
	}

	public int getFillRule() {
		return this.gc.getFillRule();
	}

	public Font getFont() {
		return this.gc.getFont();
	}

	public FontMetrics getFontMetrics() {
		return this.gc.getFontMetrics();
	}

	public Color getForeground() {
		return this.gc.getForeground();
	}

	public Pattern getForegroundPattern() {
		return this.gc.getForegroundPattern();
	}

	public GCData getGCData() {
		return this.gc.getGCData();
	}

	public int getInterpolation() {
		return this.gc.getInterpolation();
	}

	public LineAttributes getLineAttributes() {
		return this.gc.getLineAttributes();
	}

	public int getLineCap() {
		return this.gc.getLineCap();
	}

	public int[] getLineDash() {
		return this.gc.getLineDash();
	}

	public int getLineJoin() {
		return this.gc.getLineJoin();
	}

	public int getLineStyle() {
		return this.gc.getLineStyle();
	}

	public int getLineWidth() {
		return this.gc.getLineWidth();
	}

	public int getStyle() {
		return this.gc.getStyle();
	}

	public int getTextAntialias() {
		return this.gc.getTextAntialias();
	}

	public void getTransform(Transform transform) {
		this.gc.getTransform(transform);
	}

	public boolean getXORMode() {
		return this.gc.getXORMode();
	}

	public int hashCode() {
		return this.gc.hashCode();
	}

	public boolean isClipped() {
		return this.gc.isClipped();
	}

	public boolean isDisposed() {
		return this.gc.isDisposed();
	}

	public void setAdvanced(boolean advanced) {
		this.gc.setAdvanced(advanced);
	}

	public GCQuery setAlpha(int alpha) {
		this.gc.setAlpha(alpha);
		return this;
	}

	public GCQuery setAntialias(int antialias) {
		this.gc.setAntialias(antialias);
		return this;
	}

	public GCQuery setBackground(Color color) {
		this.gc.setBackground(color);
		return this;
	}

	public GCQuery setBackgroundPattern(Pattern pattern) {
		this.gc.setBackgroundPattern(pattern);
		return this;
	}

	public GCQuery setClipping(int x, int y, int width, int height) {
		this.gc.setClipping(x, y, width, height);
		return this;
	}

	public GCQuery setClipping(Path path) {
		this.gc.setClipping(path);
		return this;
	}

	public GCQuery setClipping(Rectangle rect) {
		this.gc.setClipping(rect);
		return this;
	}

	public GCQuery setClipping(Region region) {
		this.gc.setClipping(region);
		return this;
	}

	public GCQuery setFillRule(int rule) {
		this.gc.setFillRule(rule);
		return this;
	}

	public GCQuery setFont(Font font) {
		this.gc.setFont(font);
		return this;
	}

	public GCQuery setForeground(Color color) {
		this.gc.setForeground(color);
		return this;
	}

	public GCQuery setForegroundPattern(Pattern pattern) {
		this.gc.setForegroundPattern(pattern);
		return this;
	}

	public GCQuery setInterpolation(int interpolation) {
		this.gc.setInterpolation(interpolation);
		return this;
	}

	public GCQuery setLineAttributes(LineAttributes attributes) {
		this.gc.setLineAttributes(attributes);
		return this;
	}

	public GCQuery setLineCap(int cap) {
		this.gc.setLineCap(cap);	return this;
	}

	public GCQuery setLineDash(int[] dashes) {
		this.gc.setLineDash(dashes);	return this;
	}

	public GCQuery setLineJoin(int join) {
		this.gc.setLineJoin(join);	return this;
	}

	public GCQuery setLineStyle(int lineStyle) {
		this.gc.setLineStyle(lineStyle);	return this;
	}

	public GCQuery setLineWidth(int lineWidth) {
		this.gc.setLineWidth(lineWidth);	return this;
	}

	public GCQuery setRoundRectClip(int x, int y, int width, int height, int radius) {
		int box = 2 * radius;
		int iw = width - box;
		int ih = height - box;
		Path path = new Path(getDevice());
		path.addArc(x, y, box, box, 90, 90);
		path.addArc(x, y + ih, box, box, 180, 90);
		path.addArc(x + iw, y + ih, box, box, 270, 90);
		path.addArc(x + iw, y, box, box, 0, 90);
		path.moveTo(x, y + radius);
		$autoDispose(path);
		this.gc.setClipping(path);
		return this;
	}

	public GCQuery setTextAntialias(int antialias) {
		this.gc.setTextAntialias(antialias);	return this;
	}

	public GCQuery setTransform(Transform transform) {
		this.gc.setTransform(transform);
		return this;
	}

	public Point stringExtent(String string) {
		return this.gc.stringExtent(string);
	}

	public Point textExtent(String string) {
		return this.gc.textExtent(string);
	}

	public Point textExtent(String string, int flags) {
		return this.gc.textExtent(string, flags);
	}
}

package kr.or.eclipse.swt.query;

import static kr.or.eclipse.swt.query.SWTQuery.$;

import java.util.HashMap;
import java.util.Map;

import kr.or.eclipse.swt.query.internal.AnimationFrame;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public class Animator extends Job {
	public static final String ANIMATOR_KEY = "swt-query-animator";

	private Map<Widget, AnimationFrame> endKeyFrames = new HashMap<Widget, AnimationFrame>();
	private boolean isDisposed = false;
	private long length = 500;

	private Map<Widget, AnimationFrame> previousFrames = new HashMap<Widget, AnimationFrame>();
	private Map<Widget, AnimationFrame> startKeyFrames = new HashMap<Widget, AnimationFrame>();

	private TimingFunction timingFunction = TimingFunction.EASE_IN_OUT;

	public Animator() {
		super("SWTQuery Animation");
	}

	private void computeFrame(double timing) {
		for (final Widget each : endKeyFrames.keySet()) {
			AnimationFrame startKey = startKeyFrames.get(each);
			AnimationFrame endKey = endKeyFrames.get(each);

			AnimationFrame previousFrame = previousFrames.get(each);
			if (previousFrame != null) {
				previousFrame.dispose();
				previousFrames.remove(each);
			}

			final AnimationFrame currentFrame = startKey.computeFrame(endKey, timing);
			previousFrames.put(each, currentFrame);
		}
	}

	public long getLength() {
		return length;
	}

	public TimingFunction getTimingFunction() {
		return timingFunction;
	}

	public boolean isDisposed() {
		return isDisposed;
	}

	public void mark(Widget widget) {
		if (widget.getData(ANIMATOR_KEY) != null) {
			return;
		}
		widget.setData(ANIMATOR_KEY, this);

		if (startKeyFrames.containsKey(widget)) {
			return;
		}

		if (widget instanceof Control) {

			AnimationFrame startKeyFrame = new AnimationFrame(widget);
			startKeyFrames.put(widget, startKeyFrame);

			if (widget instanceof Control) {
				((Control) widget).setRedraw(false);
			}
		}

	}

	private void onAnimationEnd() {
		for (AnimationFrame each : endKeyFrames.values()) {
			each.apply();
		}

		for (AnimationFrame each : previousFrames.values()) {
			each.widget.setData(ANIMATOR_KEY, null);
			each.dispose();
		}

		startKeyFrames.clear();
		previousFrames.clear();
		endKeyFrames.clear();

		isDisposed = true;
	}

	private void onStart() {
		unlockRedraw();

		// 종료 프레임의 작성.
		for (Widget each : startKeyFrames.keySet().toArray(new Widget[startKeyFrames.size()])) {
			AnimationFrame startFrame = startKeyFrames.get(each);
			AnimationFrame endFrame = new AnimationFrame(each);

			// 시작과 끝이 동일한 위젯들은 제거함.
			if (startFrame.equals(endFrame)) {
				each.setData(ANIMATOR_KEY, null);
				startKeyFrames.remove(each);
				endKeyFrames.remove(each);
			}

			else {
				endKeyFrames.put(each, endFrame);
			}
		}

	}

	private void unlockRedraw() {
		SWTQuery targets = $(startKeyFrames.keySet());
		targets.each(new IWidgetFunction() {
			@Override
			public void doFunction(Widget w) {
				if (w instanceof Control) {
					((Control) w).setRedraw(true);
				}
			}
		});
	}

	private void renderFrame() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				for (AnimationFrame each : previousFrames.values()) {
					each.apply();
				}
			}
		});
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					onStart();
				}
			});

			if (startKeyFrames.size() == 0) {
				return Status.OK_STATUS;
			}

			// 애니메이션 시작 시간
			long startTime = System.currentTimeMillis();

			// 메인 애니메이션 루프
			while (System.currentTimeMillis() - startTime < length) {
				double timing = (System.currentTimeMillis() - startTime) / (double) length;
				if (timingFunction != null) {
					timing = timingFunction.applyTiming(timing);
				}
				computeFrame(timing);
				renderFrame();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					onAnimationEnd();
				}
			});
		}
		return Status.OK_STATUS;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setTimingFunction(TimingFunction timingFunction) {
		this.timingFunction = timingFunction;
	}

	@Override
	public boolean shouldSchedule() {
		return !isDisposed;
	}
}

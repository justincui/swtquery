package kr.or.eclipse.swt.query;

/**
 * http://eclipse.or.kr/wiki/타이밍_함수
 * 
 * @author Jeeeyul
 * 
 */
public interface TimingFunction {
	public static final TimingFunction EASE_IN = new TimingFunction() {
		@Override
		public double applyTiming(double originalTime) {
			return originalTime * originalTime;
		}
	};

	public static final TimingFunction EASE_OUT = new TimingFunction() {
		@Override
		public double applyTiming(double originalTime) {
			return 2 * originalTime - (originalTime * originalTime);
		}
	};

	public static final TimingFunction EASE_IN_OUT = new TimingFunction() {
		@Override
		public double applyTiming(double originalTime) {
			if (originalTime < 0.5d)
				return 2d * originalTime * originalTime;
			else
				return (4d * originalTime) - (2d * originalTime * originalTime) - 1d;
		}
	};

	public double applyTiming(double originalTime);
}

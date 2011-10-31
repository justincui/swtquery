package kr.or.eclipse.swt.query;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Widget;

public class SWTQueryExtension {
	protected final SWTQuery $context;

	public SWTQueryExtension(SWTQuery $context) {
		this.$context = $context;
	}

	public SWTQuery getContext() {
		return $context;
	}

	@SuppressWarnings("unchecked")
	protected final <T> T getState(int widgetIndex, String key) {
		Map<String, Object> stateForItem = getStateMap(widgetIndex);
		return (T) stateForItem.get(key);
	}

	private final String getStateKey() {
		String getStateKey = "data-plugin-state-" + this.getClass().getCanonicalName();
		return getStateKey;
	}

	protected final Map<String, Object> getStateMap(int widgetIndex) {
		SWTQuery $item = $context.get(widgetIndex);
		Map<String, Object> stateForItem = $item.getData(getStateKey());
		if (stateForItem == null) {
			stateForItem = new HashMap<String, Object>();
			$item.setData(getStateKey(), stateForItem);
		}
		return stateForItem;
	}

	protected final Map<String, Object> getStateMap(Widget widget) {
		@SuppressWarnings("unchecked")
		Map<String, Object> stateForItem = (Map<String, Object>) widget.getData(getStateKey());
		if (stateForItem == null) {
			stateForItem = new HashMap<String, Object>();
			widget.setData(getStateKey(), stateForItem);
		}
		return stateForItem;
	}

	protected final SWTQueryExtension setState(int widgetIndex, String key, Object value) {
		getStateMap(widgetIndex).put(key, value);
		return this;
	}
}

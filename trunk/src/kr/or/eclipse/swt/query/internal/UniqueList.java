package kr.or.eclipse.swt.query.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UniqueList<T> extends ArrayList<T> {
	private static final long serialVersionUID = 3712615190612455751L;

	public UniqueList(List<T> items) {
		super(items);
	}

	public UniqueList() {
	}

	public void add(int index, T element) {
		if (this.contains(element)) {
			return;
		} else {
			super.add(index, element);
		}
	}

	public boolean add(T e) {
		if (contains(e)) {
			return false;
		} else {
			return super.add(e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		ArrayList<T> cloned = new ArrayList<T>(c);
		cloned.removeAll(this);
		return super.addAll(cloned);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		ArrayList<T> cloned = new ArrayList<T>(c);
		cloned.removeAll(this);
		return super.addAll(index, cloned);
	}
}

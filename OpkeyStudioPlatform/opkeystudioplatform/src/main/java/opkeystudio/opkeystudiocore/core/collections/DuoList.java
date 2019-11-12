package opkeystudio.opkeystudiocore.core.collections;

import java.util.ArrayList;
import java.util.List;

public class DuoList<T, V> {
	private List<T> allFirstValues = new ArrayList<T>();
	private List<V> allSecondValues = new ArrayList<V>();

	public void addFirstValue(T t) {
		this.allFirstValues.add(t);
	}

	public void addSecondValue(V v) {
		this.allSecondValues.add(v);
	}

	public List<T> getAllFirstValues() {
		return this.allFirstValues;
	}

	public List<V> getAllSecondValues() {
		return this.allSecondValues;
	}

}

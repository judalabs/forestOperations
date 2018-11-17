package forestOperations;

import java.util.List;
/**
 * @author Rodrigo Juda Conceicao
 */

public class ForestSort<T extends ForestComparable<T>> implements Sortable<T> {

	private T fetch(List<T> level, T parent) {
		T resultFetch = null;
		
		for (T node : level) {
			if (node.equals(parent))
				return node;
	
			if (node.getChildren() != null && node.getChildren().size() != 0) {
				resultFetch = fetch(node.getChildren(), parent);
				if (resultFetch != null)
					return resultFetch;
			}
		}
		return resultFetch;
	}
	
	public void sort(List<T> list) {
		T resultFetch = null;
		int index = 0;
		int size = list.size();
		
		for (int i = 0; i < size; i++) {
			T actual = list.get(index);
			T parent = actual.getParent();
			
			if (parent != null) {
				resultFetch = fetch(list, parent);
				if(resultFetch == null) {
					throw new RuntimeException("ERROR: Item not found. contact the developers or submit a pull request");
				}
				insert(resultFetch.getChildren(), actual);
				list.remove(index);
			}
			else {
				index++;
			}
		}
		list.sort((e1,e2) -> e1.getOrder() - e2.getOrder());
	}

	private void insert(List<T> nodes, T actual) {
		int i = 0;
		if(nodes.size() == 0) { 
			nodes.add(actual);
			return;
		}
			
		T t = nodes.get(i);
		while(lessThan(t, actual) && i < nodes.size()) {
			++i;
		}
		nodes.add(i, actual);
	}

	private boolean lessThan(T actual, T t) {
		return Integer.compare(actual.getOrder(), t.getOrder()) < 0;
	}
}

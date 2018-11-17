package forestOperations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * @author Rodrigo Juda Conceicao
 */

public class ForestOperations<T extends ForestComparable<T>> {

	private List<T> forest;

	private Sortable<T> sortable;

	public ForestOperations(List<T> data) {
		this.forest = new ArrayList<>(data);
		this.sortable = new ForestSort<>();
	}

	public ForestOperations<T> sort() {
		sortable.sort(this.forest);
		return this;
	}

	public ForestOperations<T> debugForest() {
		debugForest(this.forest);
		System.out.println("\n");
		return this;
	}

	private void debugForest(List<T> forest) {
		if(forest.size() == 0) { 
			return;
		}

		System.out.print("->[ ");
		Iterator<T> iterator = forest.iterator();
		while(iterator.hasNext()) {
			T node = iterator.next();
			System.out.print(node.getId());
			debugForest(node.getChildren());
			if(iterator.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.print(" ] ");
	}

	public ForestOperations<T> flatten(boolean onlyChild) {
		return flatten(onlyChild, false);
	}
	
	public ForestOperations<T> flatten(boolean onlyChild, boolean deleteCircularReferences) {
		List<T> result = new ArrayList<T>();

		for(int i = 0; i < this.forest.size(); i++) {
			flattenIterate(this.forest.get(i), onlyChild, deleteCircularReferences, result);
		}
		
		this.forest = result;
		return this;
	}

	private void flattenIterate(T node, boolean onlyChild, boolean deleteCircularReferences, List<T> result) {
		if(node.getChildren().size() == 0) {
			result.add(node);
		} else {
			if(!onlyChild) 
				result.add(node);

			for(int i = 0; i < node.getChildren().size(); i++)
				flattenIterate(node.getChildren().get(i), onlyChild, deleteCircularReferences, result);
			
			if(deleteCircularReferences)
				deleteChildren(node);
		}
	}

	public ForestOperations<T> getDepthFirstSearch() {
		return getDepthFirstSearch(false);
	}

	public ForestOperations<T> getDepthFirstSearch(boolean deleteCircularReferences) {
		ArrayList<T> result = new ArrayList<>();

		for (T node : forest) {
			result.addAll(getDepthFirstSearch(node, deleteCircularReferences));
			if(deleteCircularReferences) {
				deleteChildren(node);
			}
		}

		this.forest = result;
		return this;
	}

	public ForestOperations<T> getBreadthFirstSearch() {
		return getBreadthFirstSearch(false);
	}

	public ForestOperations<T> getBreadthFirstSearch(boolean deleteCircularReferences) {
		Queue<T> queue = new LinkedList<>(this.forest);
		List<T> result = new ArrayList<>();

		while(queue.size() > 0) {
			T poll = queue.poll();
			for (T child : poll.getChildren()) {
				queue.add(child);
			}

			if(deleteCircularReferences) {
				deleteChildren(poll);
			}
			result.add(poll);
		}

		this.forest = result;
		return this;
	}

	private List<T> getDepthFirstSearch(T node, boolean deleteCircularReferences) {
		ArrayList<T> result = new ArrayList<>();

		for (T child : node.getChildren()) {
			result.addAll(getDepthFirstSearch(child, deleteCircularReferences));
		}

		if(deleteCircularReferences) {
			deleteChildren(node);
		}

		result.add(node);
		return result;
	}

	private void deleteChildren(T node) {
		node.setChildren(new ArrayList<>());
	}

	public List<T> getForest() {
		return forest;
	}

	public void setForest(List<T> data) {
		this.forest = new ArrayList<>(data);
	}

	public Sortable<T> getSortable() {
		return sortable;
	}

	public void setSortable(Sortable<T> sortable) {
		this.sortable = sortable;
	}

}

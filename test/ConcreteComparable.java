package forestOperations;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Juda Conceicao
 */

public class ConcreteComparable implements ForestComparable<ConcreteComparable> {
	
	private long id;
	
	private int order;
	
	private ConcreteComparable parent;
	
	private List<ConcreteComparable> children;

	public ConcreteComparable(long id, int order, ConcreteComparable parent) {
		this.id = id;
		this.order = order;
		this.parent = parent;
		this.children = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public ConcreteComparable getParent() {
		return parent;
	}

	public void setParent(ConcreteComparable parent) {
		this.parent = parent;
	}

	public List<ConcreteComparable> getChildren() {
		return children;
	}

	public void setChildren(List<ConcreteComparable> children) {
		this.children = children;
	}
}

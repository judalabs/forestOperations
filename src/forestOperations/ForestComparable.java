package forestOperations;

import java.util.List;

/**
 * @author Rodrigo Juda Conceicao
 */

public interface ForestComparable<T> {

	Long getId();
	
	int getOrder();
	
	T getParent();
	
	List<T> getChildren();
	
	void setChildren(List<T> children);
	
}

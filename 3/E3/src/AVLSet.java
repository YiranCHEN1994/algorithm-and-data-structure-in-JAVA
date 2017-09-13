
public class AVLSet<A extends Comparable<A>> {
	private AVL<A> root;
	private int size;
	public AVLSet() {
		super();
		this.root = null;
		this.size = 0;
	}
	boolean contains(A x){
		return AVL.contains(root, x);
		
	}
	void remove(A x){//38
		if(this.contains(x))this.size--;
		this.root=AVL.remove(root, x);
	}
	
	void add(A x){
		if(!this.contains(x))this.size++;
		this.root=AVL.add(root, x);
	}
	
	

}

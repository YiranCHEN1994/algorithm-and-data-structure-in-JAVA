import java.util.*;

public class Tree<A extends Comparable<A>> {
	private A value;
	private Tree left;
	private Tree right;
	int height;

	public Tree(A value, Tree left, Tree right) {
		super();
		this.value = value;
		this.left = left;
		this.right = right;
		this.height = 1 + Math.max(this.left.height, this.right.height);
	}

	public Tree(A value) {
		super();
		this.value = value;
		this.left = null;
		this.right = null;
		this.height = 1;
	}

	int size() {
		if (this.left == null && this.right == null) {
			return 1;
		}
		if (this.left == null) {
			return 1 + this.right.size();
		}
		if (this.right == null) {
			return 1 + this.left.size();
		}
		return 1 + this.left.size() + this.right.size();
	}

	int sizeBoucle() {// 31
		int n = this.height;
		int s = 0;
		Vector<Tree<A>> layer = new Vector<Tree<A>>(20);
		layer.add(this);
		for (int i = 0; i < n; i++) {
			Vector<Tree<A>> cmp = new Vector<Tree<A>>(20);
			for (Tree<A> t : layer) {
				s++;
				if (t.left != null) {
					cmp.add(t.left);
				}
				if (t.right != null) {
					cmp.add(t.right);
				}
			}
			layer = cmp;
		}
		return s;

	}

	int heightBoucle() {// 33
		int s = 0;
		Vector<Tree<A>> layer = new Vector<Tree<A>>(20);
		layer.add(this);
		while (layer.size() > 0) {
			s++;
			Vector<Tree<A>> cmp = new Vector<Tree<A>>(20);
			for (Tree<A> t : layer) {
				if (t.left != null)
					cmp.add(t.left);
				if (t.right != null) {
					cmp.add(t.right);
				}

			}
			layer = cmp;
		}
		return s;
	}

	int heightBoucleJolie() {// 33
		int s = 0;
		Stack<Tree<A>> layer = new Stack<Tree<A>>();
		layer.push(this);
		while (!layer.isEmpty()) {
			s++;
			Tree<A> cmp = layer.pop();
			if (cmp.left != null)
				layer.push(cmp.left);
			if (cmp.right != null)
				layer.push(cmp.right);
		}
		return s;
	}

	boolean contains(A a) {// 34
		if (this.value.compareTo(a) == 0)
			return true;
		if (this.value.compareTo(a) < 0) {
			if (this.right == null)
				return false;
			return this.right.contains(a);
		}

		if (this.left == null)
			return false;
		return this.left.contains(a);

	}

	static <A extends Comparable<A>> boolean contanisRec(Tree<A> t, A a) {// 34
		while (t != null) {
			if (t.value.compareTo(a) == 0)
				return true;
			if (t.value.compareTo(a) < 0)
				t = t.right;
			else {
				t = t.left;
			}
		}
		return false;
	}

	static <A extends Comparable<A>> A floor(Tree<A> t, A x) {// 35
		if (t == null)
			return null;
		if (t.value.compareTo(x) == 0)
			return t.value;
		if (t.value.compareTo(x) > 0)
			return (A) floor(t.left, x);// ??

		A b = (A) floor(t.right, x);// ??
		if (b == null)
			return t.value;
		else
			return b;

	}

	static <A extends Comparable<A>> Tree<A> add(Tree<A> t, A x) {//37
		if (t == null)
			return new Tree<A>(x);
		boolean c1, c2;
		c1 = t.value.compareTo(x) < 0;
		c2 = t.value.compareTo(x) > 0;
		Tree<A> cmp = t;
		while (!(c1 && cmp.right == null || c2 && cmp.left == null)) {
			if (c1)
				cmp = cmp.right;
			if (c2)
				cmp = cmp.left;
			c1 = cmp.value.compareTo(x) < 0;
			c2 = cmp.value.compareTo(x) > 0;
		}
		if (c1)
			cmp.right = new Tree<A>(x);
		if (c2)
			cmp.left = new Tree<A>(x);
		return t;

	}

	static <A extends Comparable<A>> Tree<A> leftDeepTree(int n) {// 29
		if (n <= 0) {
			return null;
		}
		Tree<A> racine = new Tree<A>(null);
		Tree<A> t = racine;
		for (int i = 1; i < n; i++) {
			Tree<A> cmp = new Tree<A>(null);
			t.left = cmp;
			racine.height++;
			t = cmp;

		}

		return null;

	}
	

}

import java.util.*;
public class AVL<A extends Comparable<A>> {
private A value;
private AVL<A> left;// Should I mention which class I will use?
private AVL<A> right;
private int height;
private int size;
public AVL(A value, AVL<A> left, AVL<A> right) {
	super();
	this.value = value;
	this.left = left;
	this.right = right;
	this.size=size(left)+size(right)+1;
	this.height=Math.max(height(left),height(right))+1;
}
public AVL(A value){
	this(value, null, null);// calls for the first constructor
	this.size=1;
	this.height=1;
}
public static<A extends Comparable<A>> int size(AVL<A> t){
	if(t==null)return 0;
	return t.size;
}
public static<A extends Comparable<A>> int height(AVL<A> t){
	if(t==null)return 0;
	return t.height;
	
}
public static<A extends Comparable<A>> boolean contains(AVL<A> t,A x){
	if(t==null)return false;
	if(t.value.compareTo(x)>0)return contains(t.left,x);
	if(t.value.compareTo(x)<0)return contains(t.right,x);
	return true;
}
//balance
//right
public static<A extends Comparable<A>> AVL<A> rightRotation(AVL<A> t){
	if(t==null||t.left==null)return t;//cannot rotate a null or t.left==null
	AVL<A>cmp=t.left;
	t.left=cmp.right;
	cmp.right=t;
	
	t.height=Math.max(height(t.left),height(t.right))+1;
	t.size=size(t.left)+size(t.right)+1;
	cmp.height=Math.max(height(cmp.left),height(cmp.right))+1;
	cmp.size=size(cmp.left)+size(cmp.right)+1;
	return cmp;	
}
// left
public static<A extends Comparable<A>> AVL<A> leftRotation(AVL<A> t){
	if(t==null||t.right==null)return t;//cannot rotate a null or t.right==null
	AVL<A>cmp=t.right;
	t.right=cmp.left;
	cmp.left=t;
	
	t.height=Math.max(height(t.left),height(t.right))+1;
	t.size=size(t.left)+size(t.right)+1;
	cmp.height=Math.max(height(cmp.left),height(cmp.right))+1;
	cmp.size=size(cmp.left)+size(cmp.right)+1;
	return cmp;	
}
public static<A extends Comparable<A>> AVL<A>  balance(AVL<A> t){
if(t==null)return null;
if(height(t.left)-height(t.right)>=2){
	if(height(t.left.left)>=height(t.left.right))return rightRotation(t);
	t.left=leftRotation(t.left);
	return rightRotation(t.left);
}
else{
	if(height(t.left)-height(t.right)<=-2){
		AVL r=t.right;
		if(height(r.right)>=height(r.left))return leftRotation(t);
		r=rightRotation(r);
		return leftRotation(r);
	}
	// control of height should be added?
return t;
}
	
	
	
	
}
public static<A extends Comparable<A>> AVL<A> add(AVL<A> t,A x){
	if(t==null) return new AVL<A>(x);
	if(t.value.compareTo(x)==0) return t;
	if(t.value.compareTo(x)>0)t.left=add(t.left,x);
	if(t.value.compareTo(x)<0)t.right=add(t.right,x);
	t.height=Math.max(height(t.left), height(t.right));
	t.size=size(t.left)+size(t.right)+1;
	t=balance(t);
	return t;
}
//suppose t is not null;
public static<A extends Comparable<A>> A getMin(AVL<A> t){
	A m=null;
	while(t!=null){
		m=t.value;
		t=t.left;
	}
	return m;
}

public static<A extends Comparable<A>> AVL<A> removeMin(AVL<A> t){
	if(t==null)return null;
	if(t.left==null)return t.right;
	t.left=removeMin(t.left);
	t.height=Math.max(height(t.left), height(t.right));
	t.size=size(t.left)+size(t.right)+1;
	return balance(t);
}
public static<A extends Comparable<A>> AVL<A> remove(AVL<A> t, A x){
if(t==null)return null;
if(t.value.compareTo(x)>0){
	t.left=remove(t.left,x);
}
if(t.value.compareTo(x)<0){
	t.right=remove(t.right,x);
}
if(t.value.compareTo(x)==0){
if(t.right==null)return t.left;
A m=getMin(t.right);
t.right=removeMin(t.right);
t.value=m;	
}
t.height=Math.max(height(t.left), height(t.right));
t.size=size(t.left)+size(t.right)+1;
return balance(t);
	
}
static<A extends Comparable<A>>LinkedList<A > toList(AVL<A> t){
	LinkedList<A> l=new LinkedList<A>();
	if(t==null)return l;
	l=toList(t.left);
	l.addLast( t.value);
	l.addAll(toList(t.right));
	return l;
}
static<A extends Comparable<A>>AVL<A> ofList(Queue<A> t){//40
	int n=t.size();
	int m=n/2;
	Queue<A> cmp=new LinkedList();
	for(int i=0;i<m;i++){
		cmp.offer(t.poll());
	}
	A a=t.poll();
	AVL x=new AVL(a,ofList(cmp),ofList(t));
	return x;
}
static<A extends Comparable<A>>AVL<A> union(AVL<A> t1,AVL<A> t2){//41
	LinkedList<A> l1=toList(t1);
	LinkedList<A> l2=toList(t2);
	Queue<A> q=new LinkedList<A>();//UNION OF TWO SORTED LIST
	while(!l1.isEmpty()||!l2.isEmpty()){
		if(!l1.isEmpty()&&!l2.isEmpty()){
			A a1=l1.peek();
			A a2=l2.peek();
			if(a1.compareTo(a2)>=0){
				q.offer(a2);
				l2.remove();
			}
			else{
				q.offer(a1);
				l1.remove();
			}
			
		
		}
		else{
			if(!l1.isEmpty()){
				q.offer(l1.remove());
			}
			else{
				q.offer(l2.remove());
			}
			
		}
		
		
		//if(l2.isEmpty()||!l1.isEmpty()&&(l1.peek().compareTo(l2.peek())<=0))
		//	q.offer(l1.remove());
		//else{
		//	q.offer(l2.remove());
		//}
	}
	return ofList(q);
	
}
static<A extends Comparable<A>>AVL<A> difference(AVL<A> t1,AVL<A> t2){//41
	LinkedList<A> l1=toList(t1);
	LinkedList<A> l2=toList(t2);
	Queue<A> q=new LinkedList<A>();//Difference OF TWO SORTED LIST,which means l1-l2+l2-l1
	A a1=null;
	A a2=null;
	while(!l1.isEmpty()||!l2.isEmpty()){
	if(!l1.isEmpty()&&!l2.isEmpty()){
		a1=l1.peek();
		 a2=l2.peek();
		if(a1.compareTo(a2)<0){
			q.offer(a1);
			a1=l1.remove();
		}
		if(a1.compareTo(a2)>0){
			q.offer(a2);
			a2=l2.remove();
			
		}
		}
	else{
		if(!l1.isEmpty()){
			q.offer(l1.remove());
		}
		else{
			q.offer(l2.remove());
		}
	}
	
	}
	return ofList(q);
	
}
static<A extends Comparable<A>>AVL<A> intersection(AVL<A> t1,AVL<A> t2){//41
	LinkedList<A> l1=toList(t1);
	LinkedList<A> l2=toList(t2);
	Queue<A> q=new LinkedList<A>();//INTERSECTION OF TWO SORTED LIST
	A a1=null;
	A a2=null;
	while(!l1.isEmpty()&&!l2.isEmpty()){
		a1=l1.peek();
		a2=l2.peek();
		if(a1.compareTo(a2)==0){
			q.offer(a1);
			l1.remove();
			l2.remove();
		}
		if(a1.compareTo(a2)>0){
			l2.remove();
		}
		if(a1.compareTo(a2)<0){
			l1.remove();
		}
	
		
	}
	return ofList(q);
	
}




}

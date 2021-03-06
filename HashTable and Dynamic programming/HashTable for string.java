
import java.util.*;
class bucket{
	private String s;
	private bucket next;
	public bucket(String s, bucket next) {
		super();
		this.s = s;
		this.next = next;
	}
	public bucket(String s) {
		super();
		this.s = s;
		this.next = null;
	}
	@Override
	public int hashCode(){
		int h=0;
		for(int i=0;i<s.length();i++){
			h=h*19+s.charAt(i);
		}
		return h;
	}
	@Override
	public boolean equals(Object b){
		bucket cmp=(bucket)b;
		return (this.s.equals(cmp.s));
		
	}
	// Generally, we have to incorperate this contains function in the hashTable
	public static boolean contains(bucket b, String s){
		bucket cmp=new bucket(s);
		for(;b!=null;b=b.next){
			if(b.equals(cmp))return true;
		}
		return false;
	}
	public bucket remove(bucket b){
		if(this.equals(b))return this.next;
		bucket cmp=this.next;
		bucket cmp1=this;
		while(cmp!=null){
			if(cmp.equals(b))
				{cmp1.next=cmp.next;
				break;}
			cmp1=cmp;
			cmp=cmp.next;
			
		}
		return this;
	}
	
}

class HashTable{
	private Vector<bucket> buckets;
    private int M=4096; 
    private int n=0;
	
	public HashTable() {
		super();
		M=50000;
		this.buckets = new Vector<bucket>(M);
		buckets.setSize(M);
		n=0;
	}
	private int hash(bucket b){
		return b.hashCode()&(M-1);//Ex28
	}
	void resize(){
		M=M*2;
		Vector<bucket> novel=new Vector<bucket>(M);
		novel.setSize(M);
		for(bucket b:buckets){
			int i=hash(b);
			novel.set(i,b);
		}
		this.buckets=novel;
	}
	void add(String s){
		if(n>=M/2)resize();
		bucket b=new bucket(s);
		int i=this.hash(b);
		if(contains(s))return;
		buckets.set(i, new bucket(s,buckets.get(i)));
		n++;
	}
	int size(){
		return this.n;
	}//on ne veut pas le modifier car c'est determine par le nombre total d'elements 
	boolean contains(String s){
		bucket b=new bucket(s);
		int i=this.hash(b);
		if(this.buckets.get(i)==null)return false;
        return bucket.contains(this.buckets.get(i), s);
        }
	void remove(String s){
		bucket b=new bucket(s);
		int i=this.hash(b);
		if(!contains(s))return;
		buckets.set(i, buckets.get(i).remove(b));
		n--;//don't forget it!!!
		
	}
}
	

	


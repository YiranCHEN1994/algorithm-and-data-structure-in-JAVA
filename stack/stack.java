package Stack_me;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Stack_me {
	public class stackofRA<E>{
		private ResizableArray<E> data;
		stackofRA(int x){
			this.data=new ResizableArray<E>(x);
		}
		public stackofRA(){
			this.data=new ResizableArray<E>();
		}
		public int size(){
			return this.data.getsize();
		}
		public E pop(){
			
			E a=this.data.get(data.getsize()-1);
			data.setsize(data.getsize()-1);
			return a;
		}
		public void push(E d){
			this.data.add(d);
		}
		public E top(){
			return this.data.get(data.getsize()-1);
		}
		
		public String toString(){
			StringBuffer bf=new StringBuffer();
			bf.append("[");
			while(this.size()>1){
				bf.append(""+this.pop()+", ");
			}
			if(this.size()==1)bf.append(this.pop());
			bf.append("]");
			return bf.toString();
		}// 54 E20
	}
	public class ResizableArray<E>{
		private int size;
		private E[] Array;
		ResizableArray(int len){
			this.size=0;
			this.Array=(E[])new Object[len];//bizarre
		}
		ResizableArray(){
			this.size=0;
			this.Array=(E[])new Object[10];
		}
		public int getsize(){
			return this.size;
		}
		public void setsize(int size){
			this.size=size;
		}
		public void add(E data){
			if(this.size==this.Array.length){
				E[] cmp=(E[])new Object[size];
				System.arraycopy(cmp, 0, Array, 0, size);
				this.Array=(E[])new Object[size*2];
				System.arraycopy(Array, 0, cmp, 0, size);
				this.Array[size++]=data;
				
			}
			else{
				this.Array[size++]=data;
			}
		}
		public E get(int i){
			if(i>=this.size)
				throw new ArrayIndexOutOfBoundsException(i);
			else return this.Array[i];
		}
	}
	public class Singly<E> {
		E content;
		Singly next;
		public Singly(E content, Singly next) {
			super();
			this.content = content;
			this.next = next;
		}
		
	}
	public class stackofLC<E>{
		private Singly<E> head;
		int size;
		stackofLC (){
			head=null;
			size=0;
		}
		public void push(E d){
			head=new Singly<E>(d,head);
			size++;
		}
		public E top(){
			if(size>0)
				return head.content;
			throw new NoSuchElementException();
		}
		public E pop(){
			if(size==0)throw new NoSuchElementException();
			E a=head.content;
			head=head.next;
			size--;//Don't forget to change the size of stack which is not needed for chain list because we use just next not size
			return a;
			
				
		}
		public int size(){
			return this.size;
		}
		public String toString(){
			StringBuffer bf=new StringBuffer();
			bf.append("[");
			if(size>0)bf.append(""+this.pop());
			while(size>0){
				bf.append(" ,"+this.pop());
			}
			bf.append("]");
			return bf.toString();
		}
		
	}
	
	

	public static void main(String[] args) throws IOException{
		
		InputStreamReader rinput=new InputStreamReader(new FileInputStream("src/amphi1/data"));
		BufferedReader r=new BufferedReader(rinput);
		//Stack<String> Data=new Stack<String>();//Why?
		stackofLC<String> Data=new amphi1().new stackofLC();
		while(true){
			String str=r.readLine();
			if(str==null)break;
			Data.push(str);
		}
		System.out.println(Data.toString());
		while(Data.size()>0){
			System.out.println(Data.pop());
		}
		
	

	}

}


public class App extends Robe {
Robe left,right;

public App(Robe left, Robe right) {
	super(left.length+right.length);
	this.left = left;
	this.right = right;
}
char charAt(int index){
	if(index<0||index>=this.length) throw new IllegalArgumentException();
	if(index<this.left.length)return this.left.charAt(index);
	return this.right.charAt(index-this.left.length);
}
Robe sub(int begin,int end){
	if(begin>=end||begin<0||end>length) throw new IllegalArgumentException();
	if(end<=this.left.length)return this.left.sub(begin, end);
	if(begin>=this.left.length)return this.right.sub(begin-this.left.length, end-this.left.length);
	if(begin==0&&end==this.length)return this;
	Robe l=this.left.sub(begin, this.left.length);
	Robe r=this.right.sub(0, end-this.left.length);
	return new App(l,r);
}
public String toStringf(){
//calculationally too expensive,because every time we toString, 
//we construct two subString, and then construct an third one 
//,which is StringBuffer for combining them. 
	StringBuffer sb=new StringBuffer(this.left.toString());
	sb.append(this.right.toString());
	return sb.toString();
	
}
void toStringHelper(StringBuffer b){
	this.left.toStringHelper(b);
	this.right.toStringHelper(b);
}

}


abstract class Robe {
protected int length;
Robe(int l){
	this.length=l;
}
int length(){
	return this.length;
}
abstract char charAt(int index);
abstract Robe sub(int begin,int end);
abstract void toStringHelper(StringBuffer b);
public String toString(){
StringBuffer b=new StringBuffer();
this.toStringHelper(b);
return b.toString();
	
}
Robe append(Robe r){
	App a=new App(this,r);
	if(a.length()<=256)return new Str(a.toString());
		
	return a;
}


}

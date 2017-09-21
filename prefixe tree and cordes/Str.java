
public class Str extends Robe{
private String str;
Str(String s){
	super(s.length());
	this.str=s;
}
char charAt(int index){
	if(index<0||index>=this.length)throw new IllegalArgumentException();
	return this.str.charAt(index);
}
Robe sub(int begin,int end){
	if(begin>=end||begin<0||end>length) throw new IllegalArgumentException();
	return new Str(this.str.substring(begin, end));
}
public String toStringf(){
	return this.str;
}
void toStringHelper(StringBuffer b){
	b.append(this.str);
}

}

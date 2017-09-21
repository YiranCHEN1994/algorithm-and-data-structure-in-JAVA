import java.util.HashMap;
public class Trie {
private boolean word;
private HashMap<Character,Trie> branches;
public Trie() {
	super();
	this.word = false;
	this.branches =new HashMap<Character,Trie>() ;
}
void add(String s){
	if(s==null||s.equals(""))return;
	Trie t=this;
	for(int i=0;i<s.length();i++){
		Trie n=t.branches.get(s.charAt(i));
		if(n==null)
			{t.branches.put(s.charAt(i), new Trie());
			}
		t=t.branches.get(s.charAt(i));
	}
	t.word=true;
	return;
}
void remove(String s){
	if(s==null||s.equals(""))return;
	Trie t=this;
	for(int i=0;i<s.length();i++){
		t=t.branches.get(s.charAt(i));
		if(t==null)return;
	}
	t.word=false;
	return;
}
boolean isEmpty(){
	if(!this.word&&this.branches.isEmpty())return true;
	return false;
}
Trie removeE(String s){
	if(s==null)return this;
	if(s.equals("")){
		this.word=false;
		if(this.isEmpty())return null;
		return this;
	}
	
	Trie t=this.branches.get(s.charAt(0));
	if(t==null)return this;
	if(t.removeE(s.substring(1))==null)this.branches.remove(s.charAt(0));
	else this.branches.put(s.charAt(0),t.removeE(s.substring(1)) );
    if(this.isEmpty())return null;
    return this;
	
}
void removeEC(String s){
	if(s==null)return;
	//suppose:s!=null;
	if(s.equals("")){
		this.word=false;
		return;
	}
	char c=s.charAt(0);
	Trie t=this.branches.get(c);
	if(t==null) return;
	if(t.isEmpty())this.branches.remove(c);
	return;
}

}

import java.util.*;
public class game {
	private HashMap<Integer,HashMap<Integer,String>> list;
	private int []base;
	private int n;
	public game(int[] base) {
		super();
		this.list=new HashMap<Integer,HashMap<Integer,String>>();
		this.base = base;
		this.n = base.length;
	}
	public void game_on(){
		for(int i=0;i<n;i++){
			int a=1<<i;
			HashMap<Integer,String> h=new HashMap<Integer,String>();
			h.put(base[i], " "+base[i]);
			list.put(a, h);
		    }
		for(int i=3;i<(1<<n);i++){
			HashMap<Integer,String> hi=new HashMap<Integer,String>();
			if(list.containsKey(i))continue;
			for(int j=1;j<i;j++){
				if(j!=(j&i))continue;
				HashMap<Integer,String> hj=list.get(j);
				hi.putAll(hj);
				int j_=i&~j;
				HashMap<Integer,String> hj_=list.get(j_);
				for(int Ij:hj.keySet()){
					for(int Ij_:hj_.keySet()){
						int si=Ij+Ij_;
						String mi=hj.get(Ij)+" + "+hj_.get(Ij_);
						hi.put(si, mi);
						
					    si=Ij-Ij_;
					    if(si>=0){
					    mi=hj.get(Ij)+" - "+hj_.get(Ij_);
					    hi.put(si, mi);}
					    
					    si=Ij*Ij_;
					    mi=hj.get(Ij)+" * "+hj_.get(Ij_);
					    hi.put(si, mi);
					    
					    if(Ij_!=0&&Ij%Ij_==0){
					    	si=Ij/Ij_;
					    	mi=hj.get(Ij)+" / "+hj_.get(Ij_);
					    	hi.put(si, mi);
					    	}
					    
						
						
					}
				}
				
				
			}
			list.put(i, hi);
		}
	}
	public int isAimed(int aim){
		HashMap<Integer,String> h=this.list.get((1<<n)-1);
		if(h.containsKey(aim))return aim;
		for(int d=1;;d++){
			int key=aim-d;
			if(h.containsKey(key))return key;
			key=aim+d;
			if(h.containsKey(key))return key;
		}
	}
}
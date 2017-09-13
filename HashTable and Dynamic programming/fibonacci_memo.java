import java.util.HashMap;
public class fibonacci_memo {
HashMap<Integer,Long> memo;

public fibonacci_memo() {
	super();
	this.memo = new HashMap<Integer, Long>();
	memo.put(0, new Long(0));
	memo.put(1, new Long(1));
}
public long calculate(int n){
	long result;
	if(memo.containsKey(n))return memo.get(n).intValue();
	result=calculate(n-1)+calculate(n-2);
	memo.put(n, result);
	return result;
	
	
	
}


}

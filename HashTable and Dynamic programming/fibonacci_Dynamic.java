import java.util.*;
public class fibonacci_Dynamique {
Vector<Long> memo;
final private static int M=500;

public fibonacci_Dynamique() {
	super();
	this.memo =new Vector<Long>(500);
	memo.setSize(M);
	memo.set(0, new Long(1));
	memo.set(1, new Long(1));
}
public long calculate(int n){
	long result;
	if(n>=memo.size()){
		memo.setSize(2*n);
		System.out.println(memo.capacity());
		result=calculate(n-1)+calculate(n-2);
		this.memo.set(n, new Long(result));
		return result;
	}
	if(this.memo.get(n)==null){
		result=calculate(n-1)+calculate(n-2);
		this.memo.set(n, new Long(result));
		return result;
	}
	return memo.get(n).intValue();
	
}


}


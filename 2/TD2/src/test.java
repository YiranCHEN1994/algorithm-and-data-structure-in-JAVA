
public class test {

	public static void main(String[] args) {
	 	int []a={2,5,7,13,17,23};
		game g=new game(a);
		g.game_on();
		for(int i=0;i<1183;i++){
			int b=g.isAimed(i);
			if(b==i){
				System.out.println(true);
				continue;
			}
			System.out.println(b);
		}
		
		

	}

}

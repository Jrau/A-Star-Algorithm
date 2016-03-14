
public class Main{

	public static void main(String[] argv){
		if (argv.length > 0){
			System.out.println("Using Makefile argument: " + argv[0]);
			Euclidean euc = new Euclidean(argv[0]);
			Manhattan man = new Manhattan(argv[0]);
		}else{
			Euclidean euc = new Euclidean();
			Manhattan man = new Manhattan();
		}

	}
} 
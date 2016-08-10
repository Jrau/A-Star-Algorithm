
public class Main{
	/** Entry point for the program.
	* runs either a file given as a command line argument
	* or it runs a file given by user input
	* both heauristics are then used to find a path
	* if a path exists
	*/
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
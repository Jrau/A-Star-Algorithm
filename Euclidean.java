
/**
* Extends the BestFirstSearch and runs A* with the Euclidien algorithm
* calculate 
*/
public class Euclidean extends BestFirstSearch{
	Euclidean(){
		super();
		this.outputFile = "EuclideanOutput.res";
		calculateHeuristic();
		System.out.println("Starting Euclidean A*..");
		super.findBestPath();
		System.out.println("Ending Euclidean A*..");
	}

	Euclidean(String readfile){
		super(readfile);
		this.outputFile = "EuclideanOutput.res";
		calculateHeuristic();
		System.out.println("Starting Euclidean A*..");
		super.findBestPath();
		System.out.println("Ending Euclidean A*..");
	}
	/**
	* override the abstract method calculateHeaurisitic to
	* fill the Heauristic map with the Euclidien distances
	* in the grid they are currently in.
	*/
	@Override
	void calculateHeuristic(){
		for (int i = 0; i < mapSize; ++i){
			for (int j = 0; j < mapSize; ++j){
				int ret = calculateEuclideanDistance(i, j);
				hMap[i][j] = ret;
			}
		}
	}
	/**
	* Helper method for calculate heauristic
	*/
	private int calculateEuclideanDistance(int row, int col){
		int ret = 0;
		int hold_row = row - goalNode.getX();
		int hold_col = col - goalNode.getY();
		hold_row = hold_row * hold_row; //square them
		hold_col = hold_col * hold_col; 
		int hold_dist = hold_row + hold_col;
		
		float hold = (float)Math.sqrt(hold_dist);
		//round up for a slightly better heuristic
		if ((hold - (int)hold) > .5){
			hold++;
		}
		ret = (int)hold;
		return ret;
	}
	/**
	*	Prints a map based on the argument given (h for heauristic, r for regular)
	*/
	protected void printMap(char arg){
		super.printMap(arg);
	}
}
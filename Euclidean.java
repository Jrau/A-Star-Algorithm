

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

	@Override
	void calculateHeuristic(){
		for (int i = 0; i < mapSize; ++i){
			for (int j = 0; j < mapSize; ++j){
				int ret = calculateEuclideanDistance(i, j);
				hMap[i][j] = ret;
			}
		}
	}

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

	protected void printMap(char arg){
		super.printMap(arg);
	}
}


public class Manhattan extends BestFirstSearch{

	Manhattan(){
		super();
		this.outputFile = "ManhattanOutput.res";
		calculateHeuristic();
		System.out.println("Starting Manhattan A*..");
		super.findBestPath();
		System.out.println("Ending Manhattan A*..");
	}

	Manhattan(String readfile){
		super(readfile);
		this.outputFile = "ManhattanOutput.res";
		calculateHeuristic();
		System.out.println("Starting Manhattan A*..");
		super.findBestPath();
		System.out.println("Ending Manhattan A*..");
	}

	@Override
	void calculateHeuristic(){
		for (int i = 0; i < mapSize; ++i){
			for (int j = 0; j < mapSize; ++j){
				int ret = calculateManhattanDistance(i, j);
				hMap[i][j] = ret;
			}
		}
	}

	private int calculateManhattanDistance(int row, int col){
		int hold_row = row - goalNode.getX();
		int hold_col = col - goalNode.getY();
		if (hold_row < 0){
			hold_row *= -1;
		}
		if (hold_col < 0){
			hold_col *= -1;
		}
		return (hold_row + hold_col);
	}

	protected void printMap(char arg){
		super.printMap(arg);
	}
}
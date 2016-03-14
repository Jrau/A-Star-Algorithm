

public class Node implements Comparable<Node>{
	int x_coord,y_coord, stepCost, hCost;
	Node parent;
	public Node(){
		x_coord = 0;
		y_coord = 0;
		stepCost = 0;
		hCost = 0;
		parent = null;
	}
	//used for both the initial state
	public Node(int x_c, int y_c){
		x_coord = x_c;
		y_coord = y_c;
		stepCost = 0;
		hCost = 0;
		parent = null;
	}
	//used for testing comparable
	public Node(int x_c, int y_c, int step, int h){
		this.x_coord = x_c;
		this.y_coord = y_c;
		this.stepCost = step;
		this.hCost = h;
		parent = null;
	}
	//most nodes
	public Node(int x_c, int y_c, int step, int h, Node t_parent){
		this.x_coord = x_c;
		this.y_coord = y_c;
		this.stepCost = step;
		this.hCost = h;
		setParentNode(t_parent);
	}
	public Node(Node other){
		this.x_coord = other.getX();
		this.y_coord = other.getY();
		this.stepCost = other.getStepCost();
		this.hCost = other.getHCost();
		setParentNode(other.getParent());
	}
	public boolean greaterThan(Node other){
		return ((this.stepCost + this.hCost) > (other.getStepCost() + other.getHCost()));
	}
	public boolean lessThan(Node other){
		return !(greaterThan(other));
	}
	public boolean equals(Node other){
		return (this.x_coord == other.getX() && this.y_coord == other.getY());
	}
	public int getStepCost(){
		return this.stepCost;
	}
	public int getHCost(){
		return this.hCost;
	}
	public int getX(){
		return this.x_coord;
	}
	public int getY(){
		return this.y_coord;
	}
	public Node getParent(){
		return this.parent;
	}
	public void setStepCost(int step){
		this.stepCost = step;
	}
	public void setHCost(int h){
		this.hCost = h;
	}
	public void setX(int x){
		this.x_coord = x;
	}
	public void setY(int y){
		this.y_coord = y;
	}
	@Override
	public int compareTo(final Node other){
		int ret;
		if (this.equals(other)){
			ret = 0;
		}else if(this.greaterThan(other)){
			ret = 1;
		}else{
			ret = -1;
		}
		return ret;
	}
	public String toString(){
		String ret = "[";
		ret += this.x_coord + ",";
		ret += this.y_coord + ",";
		ret += this.stepCost + ",";
		ret += this.hCost + "]";
		return ret;
	}
	private void setParentNode(Node t_parent){
		if (t_parent != null){
			this.parent = new Node(t_parent.getX(), t_parent.getY(), t_parent.getStepCost(), t_parent.getHCost(), t_parent.getParent());
		}else{
			this.parent = null;
		}
	}
}
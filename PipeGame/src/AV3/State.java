package AV3;

public class State<T>{
	protected double edgeCost;
	protected double pathCost;
	protected State<T> cameFrom;
	protected T stateName;
	
	public T getStateName() {
		return stateName;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}
	
	public State(T stateName)
	{
		this.stateName=stateName;
		this.edgeCost=1;
		this.pathCost=0;
		this.cameFrom=null;
	}
	
	public State()
	{
		this.stateName=null;
		this.edgeCost=1;
		this.pathCost=0;
		this.cameFrom=null;
	}
	public double getEdgeCost() {
		return edgeCost;
	}

	public void setEdgeCost(double edgeCost) {
		this.edgeCost = edgeCost;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	public void setStateName(T stateName) {
		this.stateName= stateName;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	@SuppressWarnings("rawtypes")
	public boolean equals(Object object){
	    if(object == null || object.getClass() != this.getClass()){
	        return false;
	    }
	    State other = (State)object;
	    return this.stateName.equals(other.stateName);
	}
	@Override
	public int hashCode(){
	    return stateName.toString().hashCode();
	}
	public void copy(State <T> s) {
		this.setCameFrom(s.getCameFrom());
		this.setEdgeCost(s.getEdgeCost());
		this.setPathCost(s.getPathCost());
	}


}

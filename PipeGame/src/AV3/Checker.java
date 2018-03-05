package AV3;

public class Checker {
	String bool;
	double cost;
	public Checker(String bool) {
		this.bool=bool;
	}
	public Checker(String bool,double cost) {
		this.bool=bool;
		this.cost=cost;
	}
	public String getBool() {
		return bool;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setBool(String bool) {
		this.bool = bool;
	}
	public boolean equals(String obj) {
		return bool.equals(obj);
	}
	
}

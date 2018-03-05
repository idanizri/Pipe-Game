package AV3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class GameSolution implements Solution , Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6209234149527915840L;
	@SuppressWarnings("rawtypes")
	private Stack<State>solution;
	private ArrayList<String> stringSolution;
	public GameSolution() {
		solution=new Stack<>();
		stringSolution=new ArrayList<String>();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addStateToSolution(State state) {
		// TODO Auto-generated method stub
		solution.add(state);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getStringSolution().hashCode();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Stack<State> getSolution() {
		// TODO Auto-generated method stub
		return solution;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void removeStateFromSolution(State state) {
		// TODO Auto-generated method stub
		solution.remove(state);
	}
	@Override
	public ArrayList<String> getStringSolution() {
		// TODO Auto-generated method stub
		return stringSolution;
	}
	public void addSolution(String line) {
		stringSolution.add(line);
	}
}

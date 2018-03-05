package AV3;

import java.util.ArrayList;
import java.util.Stack;

public interface Solution {
	@SuppressWarnings("rawtypes")
	public Stack<State> getSolution();
	@SuppressWarnings("rawtypes")
	public void removeStateFromSolution(State state);
	@SuppressWarnings("rawtypes")
	public void addStateToSolution(State state);
	public ArrayList<String> getStringSolution();
	public void addSolution(String line);
	@Override
	boolean equals(Object obj);
	@Override
	int hashCode();
	
}

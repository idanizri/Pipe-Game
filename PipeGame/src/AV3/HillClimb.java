package AV3;

import java.util.ArrayList;
import java.util.HashSet;

public class HillClimb extends CommonSearcher {
	@SuppressWarnings("rawtypes")
	private HashSet<State> closedSet;
	private double grade;
	@SuppressWarnings("rawtypes")
	private ArrayList<State> neighbors; 
	public HillClimb() {
		// TODO Auto-generated constructor stub
		closedSet=new HashSet<>();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Solution search(Searchable s) {
		// TODO Auto-generated method stub
		State next = s.getInitialState();
		while(next!=null) {
			grade=0;
			if(next.equals(s.getGoalState()))
				return backTrace(next,s.getInitialState(),closedSet); 
			neighbors = s.getAllPossibleStates(next);
			if(neighbors==null) {
				closedSet.add(next);
				next=next.cameFrom;
			}
			else {
				for (State state : neighbors) {
					if(!closedSet.contains(state)) {
						if(state.getPathCost()>grade) {
							grade=state.getPathCost();
							next=state;
						}
					}
				}
				if(grade==0) {
					closedSet.add(next);
					next=next.cameFrom;
				}
			}
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	private Solution backTrace(State goalState, State initialState,HashSet<State> closedSet) {
		Solution solution=new GameSolution();
		solution.addStateToSolution(goalState);
		State tempState=goalState.getCameFrom();
		
		while(!tempState.equals(initialState))
		{
			solution.addStateToSolution(tempState);
			tempState=tempState.getCameFrom();
		}
		solution.addStateToSolution(initialState);
		
		return solution;
		
	}
}

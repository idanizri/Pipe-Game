package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;

import AV3.CommonSearcher;
import AV3.GameSolution;
import AV3.Searchable;
import AV3.Solution;
import AV3.State;

public class HillClimbing extends CommonSearcher {
	@SuppressWarnings("rawtypes")
	private HashSet<State> closedSet;
	private double grade;
	@SuppressWarnings("rawtypes")
	private ArrayList<State> neighbors; 
	public HillClimbing() {
		// TODO Auto-generated constructor stub
		closedSet=new HashSet<>();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
				next=next.getCameFrom();
			}
			else {
				for (State state : neighbors) {
					if(!closedSet.contains(state)) {
						if(state.getPathCost()>grade) {
							grade=state.getPathCost();
							state.setCameFrom(next);
							next=state;
						}
					}
				}
				if(grade==0) {
					closedSet.add(next);
					next=next.getCameFrom();
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

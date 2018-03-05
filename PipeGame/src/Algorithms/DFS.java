package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import AV3.CommonSearcher;
import AV3.GameSolution;
import AV3.Searchable;
import AV3.Solution;
import AV3.State;

public class DFS extends CommonSearcher {
	public DFS(){
		super();
		openList=new LinkedList<>();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Solution search(Searchable s) {
		// TODO Auto-generated method stub
		this.openList.clear();
		this.evaluatedNodes=0;
		if(s!=null&&s.getGoalState()!=null){
			addToOpenList(s.getInitialState());
			HashSet<State>discovered=new HashSet<State>();
			
			
			while(!openList.isEmpty())
			{
				State n=popOpenList();
				
				if(n.equals(s.getGoalState()))
					return backTrace(n,s.getInitialState(),discovered); 
				
				if(!discovered.contains(n))
				{
					 discovered.add(n);
					 ArrayList<State> successors=s.getAllPossibleStates(n);
					 
					 for(State state : successors)
					 {
						 state.setCameFrom(n);
						 addToOpenList(state);
					 }
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

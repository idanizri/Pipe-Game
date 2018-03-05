package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import AV3.CommonSearcher;
import AV3.GameSolution;
import AV3.Searchable;
import AV3.Solution;
import AV3.State;
import AV3.StateCompare;

public class BestFirstSearch extends CommonSearcher {
	
	public BestFirstSearch () {
		super();
		openList=new PriorityQueue<>(new StateCompare());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Solution search(Searchable s){
		this.openList.clear();
		this.evaluatedNodes=0;
		if(s!=null&&s.getGoalState()!=null)
		{
		  addToOpenList(s.getInitialState());
		  HashSet<State>closedSet=new HashSet<State>();
		while(!openList.isEmpty())
		{
			 State n=popOpenList();
			 closedSet.add(n);
			 if(n.equals(s.getGoalState()))
				return backTrace(n,s.getInitialState(),closedSet); 
			 ArrayList<State> successors=s.getAllPossibleStates(n);
			 for(State state : successors)
			 {
				  if(!closedSet.contains(state) && ! openList.contains(state))
				  {
				      state.setCameFrom(n);
				      addToOpenList(state);
				  } 
				  else
				  {
				     if(isBetterPath(n,state))
				     {
				    	 addToOpenList(state);
				         state.setCameFrom(n);
			
				    	 if(! openList.contains(state))
				    		addToOpenList(state);
				         else
				    	 {
				    		state.setCameFrom(n);
				    		state.setPathCost(state.getEdgeCost()+n.getPathCost());
				    	 }	 
				      }  
				   }
			  }
		}
		}
		return null; 
	}
	@SuppressWarnings("rawtypes")
	private boolean isBetterPath(State n, State state) {
		if(state.getPathCost()>n.getPathCost()+state.getEdgeCost())
			return true;
		else
			return false;
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

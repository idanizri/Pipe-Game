package Algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import AV3.CommonSearcher;
import AV3.GameSolution;
import AV3.Searchable;
import AV3.Solution;
import AV3.State;

public class BFS extends CommonSearcher {
	
	public BFS () {
		super();
		openList=new LinkedList<>();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Solution search(Searchable s){
		this.openList.clear();
		this.evaluatedNodes=0;
		if(s!=null&&s.getGoalState()!=null)
		{
		  addToOpenList(s.getInitialState());
		  HashSet<State>closedSet=new HashSet<State>();
		  int count =0;
		while(!openList.isEmpty())
		{
			 State n=popOpenList();
			 closedSet.add(n);
			System.out.println(count++);
			if(count==100000)
				System.out.println("break");
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

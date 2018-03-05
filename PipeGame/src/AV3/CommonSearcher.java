package AV3;

import java.util.Queue;

public abstract class CommonSearcher implements Searcher {
	@SuppressWarnings("rawtypes")
	protected Queue<State> openList;
	protected int evaluatedNodes;
	
	public CommonSearcher() { 
		evaluatedNodes=0;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract Solution search(Searchable s);
	 // get how many nodes were evaluated by the algorithm
	
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	@SuppressWarnings("rawtypes")
	protected State popOpenList() {
		evaluatedNodes++;
		return openList.poll();
		}
    @SuppressWarnings("rawtypes")
	protected void addToOpenList(State state) {
    	openList.add(state);
	}
}

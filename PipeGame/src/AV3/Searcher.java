package AV3;

public interface Searcher {
	// the search method
	@SuppressWarnings("rawtypes")
	public Solution search(Searchable s);
	// get how many nodes were evaluated by the algorithm
	public int getNumberOfNodesEvaluated();
	}

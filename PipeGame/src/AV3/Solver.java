package AV3;

import AV2.Problem;

public interface  Solver<T> {
	public Solution solve(Problem<T> problem);
	public Boolean check();
}

package AV3;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class StateCompare implements Comparator<State> {

	@Override
	public int compare(State arg0, State arg1) {
		// TODO Auto-generated method stub
		return (int) (arg1.getPathCost()-arg0.getPathCost());
	}

}

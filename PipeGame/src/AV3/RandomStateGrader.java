package AV3;


public class RandomStateGrader<T> implements StateGrader<T> {
	@Override
	public int grade(State<T> s) {
		// TODO Auto-generated method stub
		return (int) s.getPathCost();
	}

}

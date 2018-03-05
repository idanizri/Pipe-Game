package AV2;

public class Problem <T>{
	private T level;
	private Integer hash;
	public Problem(T level) {
		this.level=level;
		this.hash=level.hashCode();
	}
	public T getProblem() {
		return level;
	}

	public void setLevel(T level) {
		this.level = level;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Problem problem=(Problem)obj;
		return this.getProblem().equals(problem.getProblem());
	}
	public Integer getHash() {
		return hash;
	}
}
																																																																																											
package AV2;

import AV3.Solution;

public interface CacheManager {
	@SuppressWarnings("rawtypes")
	public void save(Problem problem, Solution solution);
	@SuppressWarnings("rawtypes")
	public Solution load(Problem problem);
	@SuppressWarnings("rawtypes")
	public Boolean CheckIfExist(Problem problem);
}

package AV2;

import java.util.ArrayList;
import AV3.GameSolution;
import AV3.PipeGameSearchable2;
import AV3.Searchable;
import AV3.Searcher;
import AV3.Solution;
import AV3.Solver;
import AV3.State;

public class PipeGameSolver implements Solver<ArrayList<String>> {
	Searcher searcher;
	public PipeGameSolver(Searcher searcher) {
		// TODO Auto-generated constructor stub
		this.searcher=searcher;
	}
	public Boolean check() {
		if(this.searcher==null)
			return true;
		return false;
	}
	@Override
	public Solution solve(Problem<ArrayList<String>> problem) {
		// TODO Auto-generated method stub
		Solution solution=new GameSolution();
		Searchable<ArrayList<String>>searchable=new PipeGameSearchable2(problem);
		solution=searcher.search(searchable);
		calcStringSolution((GameSolution) solution);
		return solution;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void calcStringSolution(GameSolution solution) {
		// TODO Auto-generated method stub
		State s1=solution.getSolution().pop();
		while(!solution.getSolution().isEmpty()) {
			State s2=solution.getSolution().pop();
			checkChanges(s1,s2,solution);
			s1=s2;
		}
	}
	private void checkChanges(State<ArrayList<String>> s, State<ArrayList<String>> pop,GameSolution solution) {
		// TODO Auto-generated method stub
		int row = 0,column = 0,spins = 0;
		for(int i=0;i<s.getStateName().size();i++) {
			for(int j=0;j<s.getStateName().get(0).length();j++) {
				if(s.getStateName().get(i).charAt(j)!=pop.getStateName().get(i).charAt(j)) {
					row=i;
					column=j;
					spins=checkSpins(s.getStateName().get(i).charAt(j),pop.getStateName().get(i).charAt(j));
				}
			}
		}
		String line=Integer.toString(row)+","+Integer.toString(column)+","+Integer.toString(spins);
		solution.addSolution(line);
	}

	private int checkSpins(char ch1, char ch2) {
		// TODO Auto-generated method stub
		if(ch1=='|'||ch1=='-')
			return 1;
		if(ch1=='7') {
			if(ch2=='J')
				return 1;
			if(ch2=='L')
				return 2;
			return 3;
		}
		if(ch1=='J') {
			if(ch2=='L')
				return 1;
			if(ch2=='F')
				return 2;
			return 3;
		}
		if(ch1=='L') {
			if(ch2=='F')
				return 1;
			if(ch2=='7')
				return 2;
			return 3;
		}
		else{
			if(ch2=='7')
				return 1;
			if(ch2=='J')
				return 2;
			return 3;
		}
	}

}

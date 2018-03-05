package AV2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import AV3.Searcher;
import AV3.GameSolution;
import AV3.GenericFactory;
import AV3.Solution;
import AV3.Solver;


public class CLiHandler implements ClientHandler {
	@SuppressWarnings("rawtypes")
	private Problem problem;
	@SuppressWarnings("rawtypes")
	private Solver solver;
	private Solution solution;
	private CacheManager chachemanager;
	private GenericFactory<Searcher>factory;
	@SuppressWarnings("unused")
	public CLiHandler() {
		factory=new GenericFactory<>();
		chachemanager= new MyCacheManager();
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		String line="BestFirstSearch";
		Boolean flag = true;
		while(flag) {
			//System.out.println("Enter What Searcher");
			//line = bf.readLine();
			line=line.toLowerCase();
			solver=new PipeGameSolver(factory.getNewProduct(line));
			flag=solver.check();
			if(flag)
				System.out.println("Unknown Searcher, Please Try again");
		}
		solution=new GameSolution();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handle(InputStream inFromClient, OutputStream outToClient)throws StopServerException {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		ArrayList<String> level=new ArrayList<>();
		String line;
		ArrayList<String>arr = new ArrayList<>();
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter output=new PrintWriter(outToClient);
		try {
			line=bufferedReader.readLine();
			while(!line.equals("done")||line==null||line.equals("stop")) {
				arr.add(line);
				line=bufferedReader.readLine();
			}
			if(line.equals("stop")) {
				throw new StopServerException("Stop The Server");
			}
			problem=new Problem<ArrayList<String>>(arr);
			ArrayList<String>changes =new ArrayList<String>();
			Problem normalProblem=normal(problem,changes);
			if(!chachemanager.CheckIfExist(normalProblem)){
				solution=solver.solve(normalProblem);
				chachemanager.save(normalProblem, solution);
				}
			else {
				System.out.println("From CacheManager");
				solution=chachemanager.load(normalProblem);
			}
			for(String s1:changes) {
				output.println(s1);
				output.flush();
			}
			for(String s:solution.getStringSolution()) {
				output.println(s);
				output.flush();
			}
			output.println("done");
			output.flush();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Problem<ArrayList<String>> normal(Problem<ArrayList<String>> problem2,ArrayList<String>changes) {
		// TODO Auto-generated method stub
		ArrayList<String> level=new ArrayList<>();
		String line="";
		char pipe;
		int spins;
		for(int i=0;i<problem2.getProblem().size();i++) {
			for(int j=0;j<problem2.getProblem().get(0).length();j++) {
				pipe=problem2.getProblem().get(i).charAt(j);
				if(pipe=='s'||pipe=='g'||pipe==' ')
					line+=pipe;
				else if(pipe=='|'||pipe=='-') {
					line+='|';
					spins=checkSpins(pipe, '|');
					if(spins!=0)
						changes.add(Integer.toString(i)+","+Integer.toString(j)+","+Integer.toString(spins));
				}
				else {
					line+='J';
					spins=checkSpins(pipe, 'J');
					if(spins!=0)
						changes.add(Integer.toString(i)+","+Integer.toString(j)+","+Integer.toString(spins));
				}
			}
			level.add(line);
			line="";
		}
		return new Problem<ArrayList<String>>(level);
	}
	private int checkSpins(char ch1, char ch2) {
		// TODO Auto-generated method stub
		if(ch1==ch2)
			return 0;
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

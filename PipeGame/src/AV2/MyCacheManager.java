package AV2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import AV3.GameSolution;
import AV3.Solution;

public class MyCacheManager implements CacheManager {
	@SuppressWarnings("rawtypes")
	@Override
	public void save(Problem problem, Solution solution) {
		FileWriter file=null;
		PrintWriter writer=null;
		try {
			file=new FileWriter((Integer.toString(problem.getHash())+".txt"));
			writer=new PrintWriter(file);
			for(String sol:solution.getStringSolution())
			{
				writer.println(sol);
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			writer.close();
			try {
				file.close();
			} catch (IOException e) {}
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Solution load(Problem problem) {
		Solution sol = new GameSolution();
		FileReader file = null;
		BufferedReader reader=null;
		ArrayList<String> arr=new ArrayList<>();
		String line;
		try {
			file=new FileReader((Integer.toString(problem.getHash())+".txt"));
			reader=new BufferedReader(file);
			try {
				line=reader.readLine();
				while(line!=null) {
					arr.add(line);
					line=reader.readLine();
				}
				reader.close();
				file.close();
				for(String bline : arr) {
					sol.addSolution(bline);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sol;
	}

	@SuppressWarnings({ "rawtypes", "unused", "resource" })
	@Override
	public Boolean CheckIfExist(Problem problem) {
		FileInputStream inFile=null;
		try {
			inFile = new FileInputStream((Integer.toString(problem.getHash())+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		}
		try {
			inFile.close();
		} catch (IOException e) {}
		return true;
	}
	
}

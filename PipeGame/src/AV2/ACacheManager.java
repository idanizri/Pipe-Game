package AV2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import AV3.Solution;

public class ACacheManager implements CacheManager {
	private HashMap<Integer,Solution> map;
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public ACacheManager() {
		ObjectInputStream ois;
		try {
			FileInputStream fi = new FileInputStream(new File("HashMap.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			map=(HashMap)oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			map=new HashMap<>();	
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unused"})
	@Override
	public void save(Problem problem, Solution solution) {
		// TODO Auto-generated method stub
		try {
			PrintWriter file=new PrintWriter(new FileWriter(Integer.toString(problem.getHash())+".txt"));
			for(String line : solution.getStringSolution()) {
				file.println(line);
				file.flush();
			}
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put(problem.getHash(), solution);
		FileOutputStream fout;
		try {
			FileOutputStream f = new FileOutputStream(new File("HashMap.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(map);
			f.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Boolean CheckIfExist(Problem problem) {
		// TODO Auto-generated method stub
		return map.containsKey(problem.getHash());
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Solution load(Problem problem) {
		// TODO Auto-generated method stub
		return map.get(problem.getHash());
	}

}

package AV3;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import AV2.Problem;

public class PipeGameSearchable2 implements Searchable<ArrayList<String>>{
	private State<ArrayList<String>> startState;
	private State<ArrayList<String>> goalState;
	private LinkedHashSet<State<ArrayList<String>>> Exist;
	public PipeGameSearchable2(Problem<ArrayList<String>> problem) {
		startState=new State<>();
		goalState= new State<>();
		Exist=new LinkedHashSet<>();
		this.startState.setStateName(problem.getProblem());
	}
	private char[][] convertCharToArr(ArrayList<String> problem) {
		// TODO Auto-generated method stub
		char[][]game;
		game=new char[problem.size()][problem.get(0).length()];
		for(int i=0;i<problem.size();i++) {
			for(int j=0;j<problem.get(0).length();j++)
				game[i][j]=problem.get(i).charAt(j);
		}
		return game;
	}
	@Override
	public State<ArrayList<String>> getInitialState() {
		// TODO Auto-generated method stub
		return startState;
	}

	@Override
	public State<ArrayList<String>> getGoalState() {
		// TODO Auto-generated method stub
		return  goalState;
	}

	
	private boolean checkIfGoal(State<char[][]> s) {
		// TODO Auto-generated method stub
		Checker c1=new Checker("false");
		char[][]level=s.getStateName();
		int gRow = 0;
		int gCol = 0;
		int rowEnd=s.getStateName().length-1;
		int colEnd=s.getStateName()[0].length-1;
		level=s.getStateName();
		for(int i=0;i<level.length;i++) {
			for(int j=0;j<level[0].length;j++) {
				if(level[i][j]=='g') {
					gRow=i;
					gCol=j;
				}
				}
			}
		if(gRow>0) {
			if(level[gRow-1][gCol]=='7'||level[gRow-1][gCol]=='F'||level[gRow-1][gCol]=='|')
				c1=reverseToS(level,gRow-1,gCol,gRow,gCol,rowEnd,colEnd);
		}
		if(c1.equals("true"))
			return true;
		if(gRow<rowEnd) {
			if(level[gRow+1][gCol]=='J'||level[gRow+1][gCol]=='L'||level[gRow+1][gCol]=='|')
				c1=reverseToS(level,gRow+1,gCol,gRow,gCol,rowEnd,colEnd);
		}
		if(c1.equals("true"))
			return true;
		if(gCol>0) {
			if(level[gRow][gCol-1]=='-'||level[gRow][gCol-1]=='F'||level[gRow][gCol-1]=='L')
				c1=reverseToS(level,gRow,gCol-1,gRow,gCol,rowEnd,colEnd);
		}
		if(c1.equals("true"))
			return true;
		if(gCol<colEnd) {
			if(level[gRow][gCol+1]=='-'||level[gRow][gCol+1]=='7'||level[gRow][gCol+1]=='J')
				c1=reverseToS(level,gRow,gCol+1,gRow,gCol,rowEnd,colEnd);
		}
		if(c1.equals("true"))
			return true;
		return false;
	}
	@Override
	public State<ArrayList<String>> getStartState() {
		// TODO Auto-generated method stub
		return startState;
	}
	private Boolean checkOutOfBounds(int row,int col,int rowEnd,int colEnd) {
		if(row<0||row>rowEnd||col<0||col>colEnd)
			return true;
		return false;
	}
	private ArrayList<String> convertCharToArr(char[][]level){
		ArrayList<String>arr;
		arr=new ArrayList<>();
		String line;
		for(int i=0;i<level.length;i++) {
			line=String.valueOf(level[i]);
			arr.add(line);
		}
		return arr;
	}
		private State<ArrayList<String>> convertStates(State<char[][]>state){
			State<ArrayList<String>> s=new State<>();
			s.setStateName(convertCharToArr(state.getStateName()));
			s.setEdgeCost(state.getEdgeCost());
			s.setPathCost(state.getPathCost());
			return s;
		}
		private Boolean CheckIfPossible(State<char[][]> s,int row,int column,int rowEnd,int colEnd) {
			Checker c1=null,c2=null;
			char[][]level=s.getStateName();
			if(level[row][column]=='L') {
				c1=reverseToS(level, row, column, row, column+1, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row-1, column, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")) {
					s.setPathCost(c1.getCost());
					return true;
				}
				if(c2.equals("true")) {
					s.setPathCost(c2.getCost());
					return true;
				}
			}
			else if(level[row][column]=='F') {
				c1=reverseToS(level, row, column, row+1, column, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row, column+1, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")) {
					s.setPathCost(c1.getCost());
					return true;
				}
				if(c2.equals("true")) {
					s.setPathCost(c2.getCost());
					return true;
				}
			}
			else if(level[row][column]=='7') {
				c1=reverseToS(level, row, column, row, column-1, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row+1, column, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")) {
					s.setPathCost(c1.getCost());
					return true;
				}
				if(c2.equals("true")) {
					s.setPathCost(c2.getCost());
					return true;
				}
			}
			else if(level[row][column]=='J') {
				c1=reverseToS(level, row, column, row-1, column, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row, column-1, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")||c2.equals("true"))
					return true;
			}
			else if(level[row][column]=='|') {
				c1=reverseToS(level, row, column, row+1, column, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row-1, column, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")) {
					s.setPathCost(c1.getCost());
					return true;
				}
				if(c2.equals("true")) {
					s.setPathCost(c2.getCost());
					return true;
				}
			}
			else if(level[row][column]=='-') {
				c1=reverseToS(level, row, column, row, column+1, rowEnd, colEnd);
				c2=reverseToS(level, row, column, row, column-1, rowEnd, colEnd);
				if(c1.equals("OutOfBounds")||c2.equals("OutOfBounds"))
					return false;
				if(c1.equals("true")) {
					s.setPathCost(c1.getCost());
					return true;
				}
				if(c2.equals("true")) {
					s.setPathCost(c2.getCost());
					return true;
				}
			}
			return false;
		}
		private Checker reverseToS(final char[][] level,int row, int col, int rowFrom, int colFrom, final int rowEnd, final int colEnd){
			int rowOrg=rowFrom;
			int colOrg=colFrom;
			double cost=0;
			char pipe;
			Point p1,p2;
			p1=new Point();
			p2=new Point();
			if(checkOutOfBounds(row, col, rowEnd, colEnd))
				return new Checker("OutOfBounds");
			pipe=level[row][col];
			Move(pipe,row,col,p1,p2);
			if(checkOutOfBounds(p1.getRow(), p1.getCol(), rowEnd, colEnd))
				return new Checker("OutOfBounds");
			if(checkOutOfBounds(p2.getRow(), p2.getCol(), rowEnd, colEnd))
				return new Checker("OutOfBounds");
			while(!checkOutOfBounds(row, col, rowEnd, colEnd)&&level[row][col]!='s'){
				pipe=level[row][col];
				cost++;
				if(pipe==' ')
					return new Checker("false");
				Move(pipe,row,col,p1,p2);
				//check if the first point leads to the last point
				if(p1.getRow()==rowFrom&&p1.getCol()==colFrom) {
					rowFrom=row;
					colFrom=col;
					row=p2.getRow();
					col=p2.getCol();
					if(row==rowOrg&&col==colOrg)
						return new Checker("false");
				}
				//checks if the second point leads to the last point
				else if(p2.getRow()==rowFrom&&p2.getCol()==colFrom) {
					rowFrom=row;
					colFrom=col;
					row=p1.getRow();
					col=p1.getCol();
					if(row==rowOrg&&col==colOrg)
						return new Checker("false");
				}
				else//none of the points leads to the last point
					return new Checker("false");
			}
			if(checkOutOfBounds(row, col, rowEnd, colEnd))
				return new Checker("false");
			if(level[row][col]=='s') {
				return new Checker("true",cost);
			}
			return new Checker("false");
		}
		private void Move(char pipe, int row, int col, Point p1, Point p2) {
			// TODO Auto-generated method stub
			if(pipe=='|') {
				p1.set(row+1, col);
				p2.set(row-1, col);
			}
			else if(pipe=='-') {
				p1.set(row, col+1);
				p2.set(row, col-1);
			}
			else if(pipe=='L') {
				p1.set(row-1, col);
				p2.set(row, col+1);
			}
			else if(pipe=='F') {
				p1.set(row+1, col);
				p2.set(row, col+1);
			}
			else if(pipe=='7') {
				p1.set(row, col-1);
				p2.set(row+1, col);
			}
			else if(pipe=='J') {
				p1.set(row-1, col);
				p2.set(row, col-1);
			}
		}
		public ArrayList<State<ArrayList<String>>> getAllPossibleStates(State<ArrayList<String>> state) {
			int spins=0;
			int row=0,col=0;
			char [][] level=convertCharToArr((state.getStateName()));
			final int rowEnd,colEnd;
			char pipe;
			rowEnd=level.length-1;
			colEnd=level[0].length-1;
			ArrayList<State<ArrayList<String>>>Possibles1=new ArrayList<>();
			State<char[][]> newstate=new State<>();
			State<ArrayList<String>>converted=new State<>();
			while(row<=rowEnd&&col<=colEnd)
			{
				pipe=level[row][col];
				if(pipe=='s'||pipe==' '||pipe=='g') {
					if(col==colEnd){
						col=0;
						row++;
					}
					else
						col++;
				}
				else if(pipe=='-'||pipe=='|') {
					if(spins<1) {
						newstate=spin(level,row,col,spins+1);
						spins++;
						if(CheckIfPossible(newstate, row, col, rowEnd, colEnd)) {
							converted=convertStates(newstate);
							if(!Exist.contains(converted)) {
								Possibles1.add(converted);
								Exist.add(converted);
							}
						//because its a new level we save the possible level
							if(checkIfGoal(newstate))
								this.goalState=convertStates(newstate);
						}
					}
					else if(col==colEnd){
						col=0;
						row++;
						spins=0;
					}
					else {
						col++;
						spins=0;
					}
				}
				else if(pipe=='L'||pipe=='J'||pipe=='F'||pipe=='7'){
					if(spins<3) {
						newstate=spin(level,row,col,spins+1);
						spins++;
						if(CheckIfPossible(newstate, row, col, rowEnd, colEnd)) {
							converted=convertStates(newstate);
							if(!Exist.contains(converted)) {
								Possibles1.add(converted);
								Exist.add(converted);
							}
						//because its a new level we save the possible level
							if(checkIfGoal(newstate))
								this.goalState=convertStates(newstate);
						}
					}
					else if(col==colEnd){
						col=0;
						row++;
						spins=0;
					}
					else {
						col++;
						spins=0;
					}
				
				}
			}
			return Possibles1;
		}
		private State<char[][]> spin(char[][] level,int row,int col,int numOfSpins)
		{
			char[][] afterLevel=new char[level.length][level[0].length];
			char pipe,newPipe = 0;
			pipe=level[row][col];
			for(int i=0;i<numOfSpins;i++)
			{
				if(pipe=='-')
					newPipe='|';
				else if(pipe=='|')
					newPipe='-';
				else if(pipe=='L')
					newPipe='F';
				else if(pipe=='F')
					newPipe='7';
				else if(pipe=='7')
					newPipe='J';
				else if(pipe=='J')
					newPipe='L';
				pipe=newPipe;
			}
			for(int i=0;i<level.length;i++)
			{
				for(int j=0;j<level[0].length;j++)
				{
					if(i==row&&j==col)
						afterLevel[i][j]=newPipe;
					else
					{
						afterLevel[i][j]=level[i][j];
					}
					
				}
			}
			
			return new State<char[][]>(afterLevel);
		}
	}
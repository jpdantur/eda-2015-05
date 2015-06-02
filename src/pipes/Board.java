package pipes;

import java.awt.Point;
import java.util.Map;

public class Board {

	private Pipe[][] matrix;
	private int fil;
	private int col;
	private Map<Pipe,Integer> pipes;
	private Point actual;
	private int pathLength;
	
	
	public Board(int fil, int col, Map<Pipe,Integer> pipes, Pipe[][] matrix, Point inicial){
		this.fil = fil;
		this.col = col;
		this.pipes = pipes;
		this.matrix = matrix;
		this.actual = inicial;
		this.pathLength = 1;
		
	}

	public Board(int fil, int col, Map<Pipe,Integer> pipes, Pipe[][] matrix, Point inicial, int pathLength){
		this.fil = fil;
		this.col = col;
		this.pipes = pipes;
		this.matrix = matrix;
		this.actual = inicial;
		this.pathLength = pathLength;
		
	}
	
	public Pipe[][] getMatrix() {
		return matrix;
	}


	public void setMatrix(Pipe[][] matrix) {
		this.matrix = matrix;
	}


	public int getFil() {
		return fil;
	}


	public void setFil(int fil) {
		this.fil = fil;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public Map<Pipe,Integer> getPipes() {
		return pipes;
	}


	public void setPipes(Map<Pipe,Integer> pipes) {
		this.pipes = pipes;
	}


	public Point getActual() {
		return actual;
	}


	public void setActual(Point actual) {
		this.actual = actual;
	}


	public int getPathLength() {
		return pathLength;
	}


	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	
	private boolean enoughPipes(Map<Pipe, Integer> map){
		for (Map.Entry<Pipe, Integer> each : map.entrySet()) {
			if(each.getValue() > pipes.get(each.getKey())){
				return false;
			}
		}
		
		return true;
	}
	
	private void addPipes(Map<Pipe, Integer> map){
		for (Map.Entry<Pipe, Integer> each : map.entrySet()) {
			pipes.put(each.getKey(), pipes.get(each.getKey()) + each.getValue());
			if(each.getKey().hashCode() == 7)
				pathLength -= each.getValue();
			pathLength -= each.getValue();
		}
	}
	
	private void substractPipes(Map<Pipe, Integer> map){
		for (Map.Entry<Pipe, Integer> each : map.entrySet()) {
			pipes.put(each.getKey(), pipes.get(each.getKey()) - each.getValue());
			if(each.getKey().hashCode() == 7)
				pathLength += each.getValue();
			pathLength += each.getValue();
		}
	}
	
	public boolean changeBoard(int i, int j, SubMatrix actualSubMatrix, SubMatrix newSubMatrix){
		addPipes(actualSubMatrix.getMap());
		if(enoughPipes(newSubMatrix.getMap()) == true){
			substractPipes(newSubMatrix.getMap());
			Pipe[][] m = newSubMatrix.getSubMat();
			matrix[i][j] = m[0][0];
			matrix[i][j + 1] = m[0][1];
			matrix[i + 1][j] = m[1][0];
			matrix[i + 1][j + 1] = m[1][1];
			return true;
		}else{
			substractPipes(actualSubMatrix.getMap());
			return false;
		}
	}
	
	
}

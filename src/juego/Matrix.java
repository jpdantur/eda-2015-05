package juego;

import pipes.Pipe;

public class Matrix {

	private Pipe[][] matrix;
	private int Pathlength;
	private long time = 0;
	
//	public Matrix(Pipe[][] matrix, int pathLength){
//		this.matrix = matrix;
//		this.Pathlength = pathLength;
//	}
	
	public Matrix(Pipe[][] matrix, int pathLength, long time){
		this.matrix = matrix;
		this.Pathlength = pathLength;
		this.time = time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public Pipe[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Pipe[][] matrix) {
		this.matrix = matrix;
	}

	public int getPathlength() {
		return Pathlength;
	}

	public void setPathlength(int pathlength) {
		Pathlength = pathlength;
	}
}
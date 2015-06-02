package juego;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import Window.Window;
import pipes.Board;
import pipes.NextPipe;
import pipes.Pipe;
import pipes.Pipe.Direction;
import pipes.Pipe1;
import pipes.Pipe2;
import pipes.Pipe3;
import pipes.Pipe4;
import pipes.Pipe5;
import pipes.Pipe6;
import pipes.Pipe7;
import pipes.SubMatrix;

public class PipeDreamSolver{
	
	public Matrix exactSolver(Board board, boolean progress){
		Matrix ret = new Matrix(null, 0, 0);
		int[] vec = new int[7];
		for(Entry<Pipe, Integer> pipe : board.getPipes().entrySet())
			vec[pipe.getKey().hashCode() - 1] = pipe.getValue();
		int maxPossiblePath = getMaxPossibleLength(board.getMatrix(), board.getFil(), board.getCol(), vec);
		
		long initialTime = System.currentTimeMillis();
		
		Window window = null;
		if(progress){
			window = new Window(board.getFil(), board.getCol(), board.getMatrix());
		    window.setVisible(true);
		}
		
		if(exactSolverRec(board, maxPossiblePath, ret, progress, window, initialTime))
		{

			if(progress){
			window.dispose();
			}
			ret.setTime(System.currentTimeMillis() - initialTime);
			return ret;
		}
		return null;
	}
	
	public Matrix hillSolver(Board board, long time, boolean progress){
		long initialTime = System.currentTimeMillis();
		
		Matrix ret = new Matrix(null, 0, 0);
		Matrix aux = new Matrix(null, 0, 0);
		Map<Pipe,Integer> pipes;
		
		Window window = null;
		if(progress){
			window = new Window(board.getFil(), board.getCol(), board.getMatrix());
		    window.setVisible(true);
		}
		
		
		while(System.currentTimeMillis() - initialTime < time){
			pipes = new HashMap<>();
			
			for (Map.Entry<Pipe, Integer> each : board.getPipes().entrySet()) {
				pipes.put(each.getKey(),each.getValue());
			}
			
			Pipe[][] m = duplicateMatrix(board.getMatrix(), board.getFil(), board.getCol());
			Point p = new Point(board.getActual().x, board.getActual().y);
			Board auxBoard = new Board(board.getFil(), board.getCol(), pipes, m, p);
			
			if(getRandomSolution(auxBoard)){
				aux = getMaxLocal(auxBoard, time - (System.currentTimeMillis() - initialTime), progress, window, initialTime);
				ret = ret.getPathlength() > aux.getPathlength() ? ret : aux;
			}
		}
		long finalTime = System.currentTimeMillis();
		if(ret.getPathlength() == 0){
			if(progress){
				window.dispose();
			}
			return null;
		}
		if(progress){
			window.dispose();
		}
		return ret;
		
	}
	
	private Matrix getMaxLocal(Board board, long time, boolean progress, Window window, long initialTime){
		long initTime = System.currentTimeMillis();
		
		Pipe[] pipes = {new Pipe1(), new Pipe2(), new Pipe3(), new Pipe4(), new Pipe5(), new Pipe6(), new Pipe7()};
		Map<SubMatrix, SubMatrix> possibleNeighbors2 = new HashMap<>();
		possibleNeighbors2.put(new SubMatrix(null, null, pipes[5], pipes[5]), new SubMatrix(pipes[2], pipes[3], pipes[0], pipes[1]));
		possibleNeighbors2.put(new SubMatrix(pipes[5], pipes[5], null, null), new SubMatrix(pipes[3], pipes[2], pipes[1], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(pipes[4], null, pipes[4], null), new SubMatrix(pipes[1], pipes[3], pipes[2], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(null, pipes[4], null, pipes[4]), new SubMatrix(pipes[2], pipes[0], pipes[1], pipes[3]));
		possibleNeighbors2.put(new SubMatrix(null, null, pipes[2], pipes[3]), new SubMatrix(pipes[2], pipes[3], pipes[4], pipes[4]));
		possibleNeighbors2.put(new SubMatrix(pipes[3], null, pipes[0], null), new SubMatrix(pipes[5], pipes[3], pipes[5], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(pipes[1], pipes[0], null, null), new SubMatrix(pipes[4], pipes[4], pipes[1], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(null, pipes[2], null, pipes[1]), new SubMatrix(pipes[2], pipes[5], pipes[1], pipes[5]));
		possibleNeighbors2.put(new SubMatrix(null, null, pipes[5], pipes[3]), new SubMatrix(pipes[2], pipes[3], pipes[0], pipes[4]));
		possibleNeighbors2.put(new SubMatrix(pipes[4], null, pipes[0], null), new SubMatrix(pipes[1], pipes[3], pipes[5], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(pipes[5], pipes[0], null, null), new SubMatrix(pipes[3], pipes[4], pipes[1], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(null, pipes[4], null, pipes[1]), new SubMatrix(pipes[2], pipes[0], pipes[1], pipes[5]));
		possibleNeighbors2.put(new SubMatrix(null, null, pipes[2], pipes[5]), new SubMatrix(pipes[2], pipes[3], pipes[4], pipes[1]));
		possibleNeighbors2.put(new SubMatrix(pipes[3], null, pipes[4], null), new SubMatrix(pipes[5], pipes[3], pipes[2], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(pipes[1], pipes[5], null, null), new SubMatrix(pipes[4], pipes[2], pipes[1], pipes[0]));
		possibleNeighbors2.put(new SubMatrix(null, pipes[2], null, pipes[4]), new SubMatrix(pipes[2], pipes[5], pipes[1], pipes[3]));

		Map<SubMatrix, SubMatrix> possibleNeighbors4 = new HashMap<>();
		possibleNeighbors4.put(new SubMatrix(null, null, null, pipes[2]), new SubMatrix(pipes[2], pipes[3], pipes[1], pipes[6]));
		possibleNeighbors4.put(new SubMatrix(null, null, pipes[3], null), new SubMatrix(pipes[2], pipes[3], pipes[6], pipes[0]));
		possibleNeighbors4.put(new SubMatrix(pipes[0], null, null, null), new SubMatrix(pipes[6], pipes[3], pipes[1], pipes[0]));
		possibleNeighbors4.put(new SubMatrix(null, pipes[1], null, null), new SubMatrix(pipes[2], pipes[6], pipes[1], pipes[0]));
		
		SubMatrix actualSubMatrix = null;
		boolean flag4 = true;
		boolean flag2 = true;
		while(flag2 == true || flag4 == true){
			
			if(System.currentTimeMillis() - initTime > time){
				return new Matrix(board.getMatrix(), board.getPathLength(), System.currentTimeMillis() - initialTime);
			}
			
			
			flag2 = false;
			flag4 = true;
			while(flag4 == true){
				if(progress)
					window.changeMatrix(board.getMatrix());
				flag4 = false;
				for (int i = 0; i < board.getFil() - 1; i++) {
					for (int j = 0; j < board.getCol() - 1; j++) {
						
						if(System.currentTimeMillis() - initTime > time){
							return new Matrix(board.getMatrix(), board.getPathLength(), System.currentTimeMillis() - initialTime);
						}
						
						actualSubMatrix = new SubMatrix(board.getMatrix()[i][j], board.getMatrix()[i][j+1],
								board.getMatrix()[i+1][j], board.getMatrix()[i+1][j+1]);
						if(possibleNeighbors4.containsKey(actualSubMatrix)){
							if(board.changeBoard(i, j, actualSubMatrix, possibleNeighbors4.get(actualSubMatrix))){
								if(progress){
									window.changeMatrix(board.getMatrix());
								}
								flag4 = true;
							}
						}
					}
					
				}
			}
			for (int i = 0; i < board.getFil() - 1 && flag2 == false; i++) {
				for (int j = 0; j < board.getCol() - 1 && flag2 == false; j++) {
					
					if(System.currentTimeMillis() - initTime > time){
						return new Matrix(board.getMatrix(), board.getPathLength(), System.currentTimeMillis() - initialTime);
					}
					
					actualSubMatrix = new SubMatrix(board.getMatrix()[i][j], board.getMatrix()[i][j+1],
							board.getMatrix()[i+1][j], board.getMatrix()[i+1][j+1]);
					if(possibleNeighbors2.containsKey(actualSubMatrix)){
						if(board.changeBoard(i, j, actualSubMatrix, possibleNeighbors2.get(actualSubMatrix))){
							if(progress){
								window.changeMatrix(board.getMatrix());
							}
							flag2 = true;
						}
					}
				}
			}
		}
		return new Matrix(board.getMatrix(), board.getPathLength(), System.currentTimeMillis() - initialTime);
	}
	
	
	
	private boolean exactSolverRec(Board board, int maxPossiblePath, Matrix bestSolution, boolean progress, Window window, long initialTime){
		if(progress){

			window.changeMatrix(board.getMatrix());
//			showWindow(board.getFil(), board.getCol(), board.getMatrix());
		}
		
		NextPipe nextPipe = getNextPipe(board);
		Direction extreme = nextPipe.getExtreme();
		
//		Si es solucion
		
		if(nextPipe.getNext().x < 0 || nextPipe.getNext().x >= board.getCol() ||
				nextPipe.getNext().y < 0 || nextPipe.getNext().y >= board.getFil()){
			if(bestSolution == null || bestSolution.getPathlength() < board.getPathLength()){
				bestSolution.setMatrix(duplicateMatrix(board.getMatrix(), board.getFil(), board.getCol()));
				bestSolution.setPathlength(board.getPathLength());
				bestSolution.setTime(System.currentTimeMillis() - initialTime);
			}
			
			
			
			return true;
			
		}
		
		if(board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x] != null
				&& board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x].hashCode() == 7){
			board.setPathLength(board.getPathLength() + 1);
			Point aux = board.getActual();
			board.setActual(nextPipe.getNext());
			board.getMatrix()[board.getActual().y][board.getActual().x].setEntrance(extreme);
			
			boolean ret = exactSolverRec(board, maxPossiblePath, bestSolution, progress, window, initialTime);
			board.setActual(aux);
			board.setPathLength(board.getPathLength() - 1);
						
			return ret;
		}
		
		//Poda pathExist
		
				Set<String> set = new HashSet<String>();
				set.add(board.getActual().y + "-" + board.getActual().x);
				if(!pathExists(board.getMatrix(), board.getFil(), board.getCol(), nextPipe.getNext().y, nextPipe.getNext().x, set)){
					return false;
				}
//		if(board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x] != null)
//			return false;
		
		board.setPathLength(board.getPathLength() + 1);
		boolean flag = false;
		boolean ret = false;
		for (Map.Entry<Pipe, Integer> pipe : board.getPipes().entrySet()) {
			if(pipe.getKey().isCompatible(extreme) && pipe.getValue() > 0){
				pipe.setValue(pipe.getValue() - 1);
				Pipe newPipe = pipe.getKey().getPipe();
				newPipe.setEntrance(extreme);
				board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x] = newPipe;
				Point aux = board.getActual();
				board.setActual(nextPipe.getNext());
				
				if(bestSolution != null){
					if(bestSolution.getPathlength() >= maxPossiblePath){
						return true;
						
					}
				}
				
				ret = exactSolverRec(board, maxPossiblePath, bestSolution, progress, window, initialTime);
				if(ret == true){
					flag = true;
				}
				pipe.setValue(pipe.getValue() + 1);
				board.setActual(aux);
			}
		}
		board.setPathLength(board.getPathLength() - 1);
		board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x] = null;
		return flag;
	}

	

	private Pipe[][] duplicateMatrix(Pipe[][] matrix, int fil, int col){
		Pipe[][] m = new Pipe[fil][col];
		for (int i = 0; i < fil; i++) {
			for (int j = 0; j < col; j++) {
				if(matrix[i][j] != null)
					m[i][j] = matrix[i][j].getPipe();
			}
		}
		return m;
	}
	
	private int getMaxPossibleLength(Pipe[][] mat, int fils, int cols, int[] pipes){
		
		int ret = 1;
		
		for(int i = 0; i < 6; i++){
			ret += pipes[i];
		}
		ret += (2 * pipes[6]);
		return ret;
	}
	
	private boolean pathExists(Pipe[][] mat, int fils, int cols, int actFil, int actCol, Set<String> set){
		
		String st = String.valueOf(actFil) + "-" + String.valueOf(actCol);
		if(set.contains(st))
			return false;
		
		
		if(actFil < 0 || actFil >= fils || actCol < 0 || actCol >= cols)
			return true;
		if(mat[actFil][actCol] != null && mat[actFil][actCol].hashCode() != 7)
			return false;
		
		set.add(st);
		
		return pathExists(mat, fils, cols, actFil-1, actCol, set) || pathExists(mat, fils, cols, actFil+1, actCol, set) || 
				pathExists(mat, fils, cols, actFil, actCol-1, set) || pathExists(mat, fils, cols, actFil, actCol+1, set);
	}
	
	
	public boolean getRandomSolution(Board board){
		NextPipe nextPipe = getNextPipe(board);
		Point next = nextPipe.getNext();
		Direction extreme = nextPipe.getExtreme();
		
		if(nextPipe.getNext().x < 0 || nextPipe.getNext().x >= board.getCol() ||
				nextPipe.getNext().y < 0 || nextPipe.getNext().y >= board.getFil()){
			return true;
		}
		
		if(board.getMatrix()[next.y][next.x] != null && board.getMatrix()[next.y][next.x].hashCode() == 7){
			board.setPathLength(board.getPathLength() + 1);
			Point aux = board.getActual();
			board.setActual(nextPipe.getNext());
			board.getMatrix()[board.getActual().y][board.getActual().x].setEntrance(extreme);
			
			boolean ret = getRandomSolution(board);
			board.setActual(aux);
			board.setPathLength(board.getPathLength() - 1);
			return ret;
		}
		
		if(board.getMatrix()[nextPipe.getNext().y][nextPipe.getNext().x] != null)
			return false;
		
		boolean flag = false;
		boolean ret = false;
		Pipe[] pipes = new Pipe[7];
		int i = 0;
		for (Map.Entry<Pipe, Integer> pipe : board.getPipes().entrySet()) {
			if(pipe.getKey().isCompatible(extreme) && pipe.getValue() > 0){
				pipes[i++] = pipe.getKey();
			}
		}
		int dim = i;
		Pipe aux;
		Random rand = new Random();
		for (i = 0; i < dim; i++) {
			int r = Math.abs(rand.nextInt()%dim);
			aux = pipes[r];
			pipes[r] = pipes[i];
			pipes[i] = aux;
		}

		board.setPathLength(board.getPathLength() + 1);
		Point auxPoint = board.getActual();
		board.setActual(next);
		
		for (int j = 0; j < dim; j++) {
			board.getMatrix()[next.y][next.x] = pipes[j].getPipe();

			board.getMatrix()[next.y][next.x].setEntrance(extreme);
			board.getPipes().put(pipes[j], board.getPipes().get(pipes[j]) - 1);
			if(getRandomSolution(board)){
				return true;
			}
			board.getPipes().put(pipes[j], board.getPipes().get(pipes[j]) + 1);
		}
		board.getMatrix()[board.getActual().y][board.getActual().x] = null;

		board.setActual(auxPoint);
		board.setPathLength(board.getPathLength() - 1);
		
		return false;
	}
	
	private NextPipe getNextPipe(Board board){
		Direction extreme = null;
		Point next = new Point();
		next.y = board.getActual().y;
		next.x = board.getActual().x;
//		System.out.println(board.getMatrix()[board.getActual().y][board.getActual().x]);
		switch(board.getMatrix()[board.getActual().y][board.getActual().x].getExit()){
			case NORTH:
				next.y--;
				extreme = Direction.SOUTH;
				break;
			case SOUTH:
				next.y++;
				extreme = Direction.NORTH;
				break;
			case WEST:
				next.x--;
				extreme = Direction.EAST;
				break;
			case EAST:
				next.x++;
				extreme = Direction.WEST;
				break;
		}
		return new NextPipe(next, extreme);
	}
	
	
	
}




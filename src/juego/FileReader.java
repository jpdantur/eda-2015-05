package juego;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Window.ErrorWindow;
import pipes.Board;
import pipes.InitPipe1;
import pipes.InitPipe2;
import pipes.InitPipe3;
import pipes.InitPipe4;
import pipes.Pipe;
import pipes.Pipe1;
import pipes.Pipe2;
import pipes.Pipe3;
import pipes.Pipe4;
import pipes.Pipe5;
import pipes.Pipe6;
import pipes.Pipe7;
import pipes.Wall;

public class FileReader {

	Scanner scanner;
	
	FileReader(File file) throws FileNotFoundException{
			this.scanner = new Scanner(file);
	}
	
	
	
	public Board read() throws MyFileException{
		
		String linea1 = (scanner.nextLine());
		String[] linea = linea1.split(",");
		if(linea.length != 2){
			throw new MyFileException();
		}
		int fil = Integer.parseInt(linea[0]);
		int col = Integer.parseInt(linea[1]);
		
		if(fil <= 0 || col <= 0){
			throw new MyFileException();
		}
		
		Pipe[][] matrix = new Pipe[fil][col];
		
		boolean isStart = false;
		int ii = 0;
		int ij = 0;
		
		for (int i = 0; i < fil; i++) {
			String fila = scanner.nextLine();
			if(fila.length() != col){
				throw new MyFileException();
			}
			char[] filaArray = fila.toCharArray();
			for (int j = 0; j < col; j++) {
				switch(filaArray[j]){
					case '#':
						matrix[i][j] = new Wall();
						break;
					case ' ':
						break;
					case 'N':
					case 'S':
					case 'E':
					case 'W':
						if(isStart == true){
							throw new MyFileException();
						}
						isStart = true;
						matrix[i][j] = createInitPipe(filaArray[j]);
						ii = i;
						ij = j;
						break;
						
				}
			}
		}
		if(isStart == false){
			throw new MyFileException();
		}
		
		
		
		Map<Pipe, Integer> map = new HashMap<>();
		int cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe1(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe2(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe3(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe4(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe5(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe6(), cant);
		cant = Integer.parseInt(scanner.nextLine());
		if(cant < 0)
			throw new MyFileException();
		map.put(new Pipe7(), cant);
		
		Board b = new Board(fil, col, map, matrix, new Point(ij,ii));
		
//		for (int i = 0; i < b.getFil(); i++) {
//			for (int j = 0; j < b.getCol(); j++) {
//				if(b.getMatrix()[i][j] == null){
//					System.out.print(".\t");
//				}
//				else
//					System.out.print(b.getMatrix()[i][j] + "\t");
//			}
//			System.out.println();
//		}
//		
//		for (Map.Entry<Pipe, Integer> pipe : map.entrySet()) {
//			System.out.println(pipe.getKey() + " - " + pipe.getValue());
//		}
//		
					
		return b;
	}
	
	private Pipe createInitPipe(char c){
		switch(c){
			case 'N':
				return new InitPipe2();
			case 'S':
				return new InitPipe1();
			case 'E':
				return new InitPipe3();
			case 'W':
				return new InitPipe4();
			default:
				return null;
		}
	}
}

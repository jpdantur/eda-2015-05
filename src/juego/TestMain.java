package juego;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Window.ErrorWindow;
import Window.Window;
import pipes.Board;
import pipes.InitPipe1;
import pipes.InitPipe3;
import pipes.Pipe;
import pipes.Pipe1;
import pipes.Pipe2;
import pipes.Pipe3;
import pipes.Pipe4;
import pipes.Pipe5;
import pipes.Pipe6;
import pipes.Pipe7;
import pipes.SubMatrix;
import pipes.Pipe.Direction;
import pipes.Wall;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		PipeDreamSolver solver = new PipeDreamSolver();
		boolean error = false;
		boolean wrongFile = false;
		File file = new File(args[0]);
		FileReader fr= new FileReader(file);
		boolean progress = false;
		boolean exact = false;
		int lastArg = 0;
		int time = 0;
		
		if(args.length > 1 && args[1].equals("exact")){
			exact = true;
			if(args.length > 2 && args[2].equals("progress")){
			progress = true;
			lastArg = 2;
			}
			else{
				lastArg = 1;
			}
		}
		else if(args.length > 1 && args[1].equals("approx")){
			exact = false;
			if(args.length <= 2){
				error = true;
			}else{
					time = Integer.parseInt(args[2]);
					if(time<1)
						error = true;
				if(args.length > 3){
					if(args[3].equals("progress")){
						progress = true;
						lastArg = 3;
					}
					else{
						error = true;
					}
				}else
					lastArg = 2;
			 	}
	 	}
		else{
			error = true;
		}
		
		lastArg++;
	 	if(args.length > lastArg)
	 		error = true;
		
	 	if (error)
	 		throw new ErrorException();
		

		
		
		if(!error){
			try {
				Board b = fr.read();
				int fil = b.getFil();
				int col = b.getCol();
				Matrix sol;
				if(exact){
					sol = solver.exactSolver(b, progress);
					}
				else{
					sol = solver.hillSolver(b, time * 60000, progress);
				}
								
				

//				if(sol == null)
//					System.out.println("nada");
//				else{
//					for (int i = 0; i < b.getFil(); i++) {
//						for (int j = 0; j < b.getCol(); j++) {
//							if(sol.getMatrix()[i][j] == null){
//								System.out.print(".\t");
//							}
//							else
//								System.out.print(sol.getMatrix()[i][j] + "\t");
//						}
//						System.out.println();
//					}
//				}
				
				
				if(sol == null){
					ErrorWindow e= new ErrorWindow();
					e.errorMessage("No hay solucion");
					e.dispose();
				}
				else{
				Window window = new Window(fil,col, sol.getMatrix());
		        window.setVisible(true);
		        System.out.println("Encontre una solucion de " + sol.getPathlength() + " en " + sol.getTime() + " milisegundos");
				}
				
				
			} catch (MyFileException e) {
				// TODO Auto-generated catch block
				System.out.println("Archivo invalido");
				ErrorWindow errorWindow = new ErrorWindow();
				errorWindow.errorMessage("Estan mal los parametros");
			}
		}
		else{
			if(!wrongFile){
				System.out.println("Estan mal los parametros");
				ErrorWindow errorWindow = new ErrorWindow();
				errorWindow.errorMessage("Estan mal los parametros");
			}
		}		
		
		} catch(FileNotFoundException f)
		{
			ErrorWindow e= new ErrorWindow();
			e.errorMessage("Archivo invalido");
			e.dispose();
		}
		catch (NumberFormatException n)
		{
			ErrorWindow e=new ErrorWindow();
			e.errorMessage("Error de parametros");
			e.dispose();
		}
		
		catch(ErrorException f)
		{
			ErrorWindow e=new ErrorWindow();
			e.errorMessage("Error de parametros");
			e.dispose();
		}
		catch(IndexOutOfBoundsException i)
		{
			ErrorWindow e=new ErrorWindow();
			e.errorMessage("Error de parametros");
			e.dispose();
		}
		catch(NoSuchElementException n){
			ErrorWindow e= new ErrorWindow();
			e.errorMessage("Archivo invalido");
			e.dispose();
		}
		
	}

}

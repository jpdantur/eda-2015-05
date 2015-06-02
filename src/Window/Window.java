package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Savepoint;

import javax.imageio.ImageIO;
import javax.swing.*;

import pipes.Pipe;
import juego.Matrix;

public class Window extends JFrame{

	private Imagenes imagenes;
    private static final int SIZE = 50;
    private int fil;
    private int col;
    private JLabel[][] matrix;
    
    private JScrollPane scrollPane;
	
	private javax.swing.JPanel tablero;

	
	
	public Window(int fil, int col, Pipe[][] pipes){
        this.lookAndFeel();
        this.setTitle("PipeDream Solver");
		tablero = new javax.swing.JPanel();
		this.fil=fil;
		this.col=col;
		this.matrix =  new JLabel[fil][col];
		this.initComponents();
        imagenes = new Imagenes();
		
		setLayout(new BorderLayout());
        JLabel background = new JLabel(new ImageIcon("/imagenes/fondo.png"));
        add(background);
        background.setLayout(new FlowLayout());
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
//        JLabel jLabel = new JLabel();
//        
//        jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Nada.png")));
//        
//        tablero.add(jLabel, BorderLayout.CENTER);
        
        this.llenarMatriz(pipes);
        

//        JScrollPane myJScrollPane = new JScrollPane(tablero,
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//	    add(new JScrollPane(tablero), null);
     
       
        
        
        
//	    scrollPane = new JScrollPane(tablero, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
//	    add(scrollPane, null);

	    
//        BufferedImage img = new BufferedImage(tablero.getWidth(), tablero.getHeight(), BufferedImage.TYPE_INT_RGB);
//        tablero.paint(img.getGraphics());
//        try {
//            ImageIO.write(img, "png", new File("respuesta.png"));
//            System.out.println("panel saved as image");
//
//        } catch (Exception e) {
//            System.out.println("panel not saved" + e.getMessage());
//        }
        
	    
	    
	}
	
	
	private void initComponents(){
	    
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(col*50+5, fil*50+25));
//		setMinimumSize(new Dimension(800,600));
        setResizable(false);
        
		tablero.setSize(col*50+5, fil*50+25);
//		tablero.setSize(900, 900);
		tablero.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.magenta));
		
		javax.swing.GroupLayout tableroLayout = new javax.swing.GroupLayout(tablero);
		
	    tablero.setLayout(tableroLayout);
	    tableroLayout.setHorizontalGroup(
	        tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 498, Short.MAX_VALUE)
	    );
	    tableroLayout.setVerticalGroup(
	        tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGap(0, 498, Short.MAX_VALUE)
	    );
	    
	    
	    
	    getContentPane().add(tablero, null);
	    
	    
		
	}
	
	
	public void lookAndFeel(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
	
	
	
	
	private  void llenarMatriz(Pipe[][] pipes){
        
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                JPanel casillero = new JPanel();
                casillero.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                casillero.setSize(SIZE, SIZE);
                JLabel jLabel = new JLabel();
                matrix[i][j] = jLabel;
                
//                jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/"+ matrix.getMatrix()[i][j] == null ? "Nada" : 
//                	("pipe" + matrix.getMatrix()[i][j].hashCode())+".png")));
//                
                if(pipes[i][j] == null)
                	jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Nada.png")));
                else{
                	if(pipes[i][j].hashCode() == 20){
                		jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/JeanArmstrong.png")));
                	}
                	else{
                		jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pipe" + pipes[i][j].hashCode() + ".png")));
                	}
                }
                
                tablero.setLayout(null);
                tablero.add(casillero);
                
                
                casillero.setLocation(j * SIZE, i * SIZE);
                
                casillero.setLayout(new BorderLayout());
                casillero.add(jLabel, BorderLayout.CENTER);
            }
        }
    }
	
	public void changeMatrix(Pipe[][] pipes){
		for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
            	if(pipes[i][j] == null)
                	matrix[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Nada.png")));
                else{
                	if(pipes[i][j].hashCode() != 20)
                		matrix[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pipe" + pipes[i][j].hashCode() + ".png")));
                }
                
            }
		}
		 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
}

package Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorWindow extends JFrame{

	public void errorMessage(String s){
	JOptionPane.showMessageDialog(this, s, "PipeDream Solver", JOptionPane.INFORMATION_MESSAGE,
            new javax.swing.ImageIcon(getClass().getResource("/imagenes/JeanArmstrongError.gif")));
	}
	
	
	
}

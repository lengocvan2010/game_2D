package main;

import java.awt.Frame;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setTitle("game");
			
		Panel gp = new Panel();
		jf.add(gp);
		jf.pack();
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
			
		gp.startGame();	
	}
}


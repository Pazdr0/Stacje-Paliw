package pl.stacje;

import javax.swing.JFrame;

public class Main {

	public void start() {
		MyFrame ramka = new MyFrame();
		ramka.setSize(1000, 500);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setLocation(500, 300);
		ramka.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Main().start();
	}

}

package pl.stacje;

import javax.swing.JFrame;

public class Main {

	public void start() {
		MyFrame ramka = new MyFrame();
		ramka.setSize(1000, 700);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setLocation(300, 200);
		ramka.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Main().start();
	}

}

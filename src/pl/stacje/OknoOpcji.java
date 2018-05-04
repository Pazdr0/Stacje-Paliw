package pl.stacje;

import com.teamdev.jxmaps.swing.MapView;

import java.awt.Dimension;

import javax.swing.JWindow;

abstract public class OknoOpcji {

	public OknoOpcji(MapView rodzic, Dimension wymiary) {
		
	}
	abstract public void inicjuj(JWindow okno);
}

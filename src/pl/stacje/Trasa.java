package pl.stacje;

public class Trasa {
	private String punktA;
	private String punktB;
	private double dlugoscTrasy;
	//Jaka jest dopuszczalna odległość jaką można zjechać z trasy
	private double maxOdlegloscOdTrasy;
	
	public Trasa() {
		maxOdlegloscOdTrasy = 0;
	}	
	public String getPunktA() {
		return punktA;
	}
	public void setPunktA(String punktA) {
		this.punktA = punktA;
	}
	public String getPunktB() {
		return punktB;
	}
	public void setPunktB(String punktB) {
		this.punktB = punktB;
	}
	public double getDlugoscTrasy() {
		return dlugoscTrasy;
	}
	public void setDlugoscTrasy(double dlugoscTrasy) {
		this.dlugoscTrasy = dlugoscTrasy;
	}
	public double getMaxOdlegloscOdTrasy() {
		return maxOdlegloscOdTrasy;
	}
	public void setMaxOdlegloscOdTrasy(double maxOdlegloscOdTrasy) {
		this.maxOdlegloscOdTrasy = maxOdlegloscOdTrasy;
	}
}
package pl.stacje;

public class Trasa {
	private double dlugoscTrasy;
	//Jaka jest dopuszczalna odległość jaką można zjechać z trasy
	private double maxOdlegloscOdTrasy;
	
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

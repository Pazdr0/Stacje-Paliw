package pl.stacje;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.swing.MapView;
import com.sun.corba.se.impl.presentation.rmi.DynamicStubImpl;
import com.teamdev.jxmaps.DirectionsLeg;
import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRoute;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.Distance;
import com.teamdev.jxmaps.TravelMode;

import javax.swing.*;

public class MyMap extends MapView{

	private static final long serialVersionUID = 673L;
	private Distance dystans;
	private DirectionsLeg dirLeg = new DirectionsLeg();

	public MyMap() {
		super();
		mapa();
		
//		switch (opcja) {
//		case "wyznaczTrase":

//		}
	}
	
	private void mapa() {
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				if (status == MapStatus.MAP_STATUS_OK) {
					final Map mapka = getMap();
					mapka.setZoom(5.0);
					GeocoderRequest request = new GeocoderRequest();
					request.setAddress("Wrocław");

					getServices().getGeocoder().geocode(request, new GeocoderCallback(mapka) {
						@Override
						public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
							if (status == GeocoderStatus.OK) {
								mapka.setCenter(result[0].getGeometry().getLocation());
//								dirLeg.setStartLocation(result[0].getGeometry().getLocation());
								System.out.println("Result: " + result[0].getGeometry().getLocation());
//								System.out.println("Start: " + dirLeg.getStartLocation());
							}
						}
						
					});
					request.setAddress("Warszawa");
					getServices().getGeocoder().geocode(request, new GeocoderCallback(mapka) {
						@Override
						public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
							if (status == GeocoderStatus.OK) {
								dirLeg.setEndLocation(result[0].getGeometry().getLocation());
								System.out.println("Result: " + result[0].getGeometry().getLocation());
							}
						}
					});
				}
			}
		});
	}
	public void getWspolrzedne() {
		dirLeg.setStartLocation(new LatLng(51.107885, 17.038538));
		dirLeg.setEndLocation(new LatLng(52.229676, 21.012229));
		System.out.println("Start: " + dirLeg.getStartLocation() + ", Koniec: " + dirLeg.getEndLocation());
		Distance dyst = new Distance();
		dyst = dirLeg.getDistance();
		dirLeg.getDistance();
		System.out.println(dirLeg.getDistance().toString());
	}
//	public void dystans() {
//		System.out.println("Dystans2: " + dirLeg.getDistance().toString());
//	}
//	public double getDystans() {
//		String dystans = this.dystans.toString();
//		return Double.parseDouble(dystans);
//	}
	
	public void calculateDirection(JTextField skad, JTextField dokad) {
        // Getting the associated map object
		final Map map = getMap();
        // Creating a directions request
        DirectionsRequest request = new DirectionsRequest();
        // Setting of the origin location to the request
        request.setOriginString(skad.getText());
        // Setting of the destination location to the request
        request.setDestinationString(dokad.getText());
        // Setting of the travel mode
        request.setTravelMode(TravelMode.DRIVING);
        // Calculating the route between locations
        getServices().getDirectionService().route(request, new DirectionsRouteCallback(map) {
            @Override
            public void onRoute(DirectionsResult result, DirectionsStatus status) {
                // Checking of the operation status
                if (status == DirectionsStatus.OK) {
                    // Drawing the calculated route on the map
                    map.getDirectionsRenderer().setDirections(result);
//                    dirLeg.setDistance();
                } else {
                    JOptionPane.showMessageDialog(MyMap.this, "Błąd, Nie można wyznaczyć trasy, proszę o wprowadzenie poprawnych danych");
                }
            }
        });
//        dystans = new Distance();
//        dystans.setText("50");
//
//        
//        dirLeg.setDistance(dystans);
//        dirLeg.setStartLocation(request.getOrigin());
//        dirLeg.setEndLocation(request.getDestination());
//        dystans = dirLeg.getDistance().set
//        dirLeg.setStartAddress("wrocław");
//        leg.setEndAddress(dokad.getText());
//        leg.setStartAddress(skad.getText());
//        System.out.println(dirLeg.getStartAddress() + dirLeg.getStartLocation());
//        dystans = new Distance();
//        dystans.getText();
//        System.out.println(dystans.getText());
        
    }
}

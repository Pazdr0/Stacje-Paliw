package pl.stacje;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.DirectionsLeg;
import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRoute;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.TravelMode;

import javax.swing.*;

public class MyMap extends MapView {

	private static final long serialVersionUID = 673L;
	private double dystans;

	public MyMap() {
		super();
		mapa();
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
//								System.out.println("Result: " + result[0].getGeometry().getLocation());
//								System.out.println("Start: " + dirLeg.getStartLocation());
							}
						}
						
					});
				}
			}
		});
	}
	
	public double getDystans() {
		return dystans;
	}
	
	public void calculateDirection(JTextField skad, JTextField dokad) {
		final Map map = getMap();
        DirectionsRequest request = new DirectionsRequest();
        // Ustawia miejsce początkowe i końcowe
        request.setOriginString(skad.getText());
        request.setDestinationString(dokad.getText());
        // Ustawia tryb na samochodowy
        request.setTravelMode(TravelMode.DRIVING);
        // Wyznacza trase oraz jej długość
        getServices().getDirectionService().route(request, new DirectionsRouteCallback(map) {
            @Override
            public void onRoute(DirectionsResult result, DirectionsStatus status) {
                if (status == DirectionsStatus.OK) {
                	//'Rysuje' trase
                    map.getDirectionsRenderer().setDirections(result);
                    //Oblicza odległość
                    DirectionsRoute[] routes = result.getRoutes();
                    if (routes.length > 0) {
                        dystans = 0;
                        for (DirectionsLeg leg : routes[0].getLegs()) {
                        	dystans += leg.getDistance().getValue();
                        }  
                    }
                } else {
                  JOptionPane.showMessageDialog(MyMap.this, "Błąd, Nie można wyznaczyć trasy, proszę o wprowadzenie poprawnych danych");
                } 
            }
        });
    }
}

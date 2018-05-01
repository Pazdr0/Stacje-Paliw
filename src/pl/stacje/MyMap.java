package pl.stacje;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;

import pl.stacje.test.DirectionsExample;

import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.TravelMode;

import java.awt.BorderLayout;

import javax.swing.*;

public class MyMap extends MapView{

	private static final long serialVersionUID = 673L;

	private JTextField skad;
	private JTextField dokad;

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
							}
						}
						
					});
				}
//				calculateDirection();
			}
		});
	}
	
	public void calculateDirection(JTextField skad, JTextField dokad) {
        // Getting the associated map object
		final Map map = getMap();
//        final Map map = getMap();
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
                } else {
                    JOptionPane.showMessageDialog(MyMap.this, "Błąd, Nie można wyznaczyć trasy, proszę o wprowadzenie poprawnych danych");
                }
            }
        });
    }
}

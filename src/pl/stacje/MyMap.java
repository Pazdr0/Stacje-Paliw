package pl.stacje;

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.*;
import java.awt.*;

public class MyMap extends MapView{

	private static final long serialVersionUID = 673L;
	
	public MyMap() {
		mapa();
	}
	private void mapa() {
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				if (status == MapStatus.MAP_STATUS_OK) {
					final Map map = getMap();
					map.setZoom(5.0);
					GeocoderRequest request = new GeocoderRequest();
					request.setAddress("Warszawa");

					getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
						@Override
						public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
							if (status == GeocoderStatus.OK) {
								map.setCenter(result[0].getGeometry().getLocation());
							}
						}
					});
				}
			}
		});
	}
	
	
	public void start(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	
//	public void calculateDirection() {
//        // Getting the associated map object
//
//        final Map map = getMap();
//        // Creating a directions request
//        DirectionsRequest request = new DirectionsRequest();
//        // Setting of the origin location to the request
//        request.setOriginString("Wrocław");
//        // Setting of the destination location to the request
//        request.setDestinationString("Kraków");
//        // Setting of the travel mode
//        request.setTravelMode(TravelMode.DRIVING);
//        // Calculating the route between locations
//        getServices().getDirectionService().route(request, new DirectionsRouteCallback(map) {
//            @Override
//            public void onRoute(DirectionsResult result, DirectionsStatus status) {
//                // Checking of the operation status
//                if (status == DirectionsStatus.OK) {
//                    // Drawing the calculated route on the map
//                    map.getDirectionsRenderer().setDirections(result);
//                } else {
//                    JOptionPane.showMessageDialog(MyMap.this, "Error. Route cannot be calculated.\nPlease correct input data.");
//                }
//            }
//        });
//    }
}

package pl.stacje;

import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.TravelMode;
import com.teamdev.jxmaps.swing.MapView;

import javax.swing.JOptionPane;

public class MyMap extends MapView{

	private static final long serialVersionUID = 673L;
	
	public MyMap() {
		calculateDirection();
	}
	
	private void calculateDirection() {
        // Getting the associated map object

        final Map map = getMap();
        // Creating a directions request
        DirectionsRequest request = new DirectionsRequest();
        // Setting of the origin location to the request
        request.setOriginString("Wrocław");
        // Setting of the destination location to the request
        request.setDestinationString("Kraków");
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
                    JOptionPane.showMessageDialog(MyMap.this, "Error. Route cannot be calculated.\nPlease correct input data.");
                }
            }
        });
    }
}

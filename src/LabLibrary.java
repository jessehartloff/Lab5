public class LabLibrary {


    /**
     * Computes the greater circle distance between two points on Earth given by lat/long coordinates in km.
     * For more information see http://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param latitude0  latitude of city 0
     * @param longitude0  longitude of city 0
     * @param latitude1  latitude of city 1
     * @param longitude1  longitude of city 1
     * @return the greater circle distance between city 1 and city 2 in kilometers
     */
    public static double greaterCircleDistance(double latitude0, double longitude0,
                                        double latitude1, double longitude1) {
        double radius = 6371000.0;
        
        double deltaLat = degreesToRadians(latitude1) - degreesToRadians(latitude0);
        double deltaLon = degreesToRadians(longitude1 - longitude0);
        
        double latPower = Math.pow(Math.sin(deltaLat / 2.0), 2);
        double longPower = Math.pow(Math.sin(deltaLon / 2.0), 2);
        double cosProduct = Math.cos(degreesToRadians(latitude0)) * Math.cos(degreesToRadians(latitude1));
        double intermediateValue = cosProduct * longPower + latPower;
        
        double normalDistance = 2.0 * Math.atan2(Math.sqrt(intermediateValue), Math.sqrt(1-intermediateValue));
        double distanceInMeters = normalDistance * radius;
        return distanceInMeters/1000.0;
    }

    
    private static double degreesToRadians(double degrees){
    	return degrees * Math.PI / 180.0;
    }



    // ** testing code ** //

    private static void testGCD(double latitude0, double longitude0,
                           double latitude1, double longitude1,
                           double expected){
        double epsilon = 0.00001;
        double computed = greaterCircleDistance(latitude0, longitude0, latitude1, longitude1);

        if(Math.abs(expected - computed) < epsilon){
            System.out.println("passed");
        }else{
            System.out.println("returned: " + computed + "\nshould be: " + expected);
        }
    }

    public static void main(String[] args) {
        testGCD(-65.82622902173087, -10.413995903142677, -57.06605394309698, -1.6852507608132896, 1076.6073820337647);
        testGCD(45.211842149799, -130.4891518150788, 47.069863176469966, 50.09396969714385, 9753.670896289728);
        testGCD(12.733424251291297, -156.4760478450378, -84.91802614609509, -26.574791183883207, 11782.25965654898);
        testGCD(-47.505668886687864, 65.67286957925029, -12.303554187002717, -132.77988602992914, 13117.215339890268);
        testGCD(43.1547222,-77.6158333, 42.8863889, -78.8786111,106.90519659246536);
        testGCD(28.6,77.2, 42.8863889, -78.8786111,11698.25293443148);
    }


}

public class Plane extends PlaneBase {

    public Plane(String planeNumber, String time) {
        super(planeNumber, time);
    }

    @Override
    public int compareTo(PlaneBase o) {

        String planeTime = this.getTime();
        String comparisonPlaneTime = o.getTime();
        int timeComparison = planeTime.compareTo(comparisonPlaneTime);
        
        // compares planes based on their time
        if (timeComparison > 0) {
            return 1;
        } else if (timeComparison < 0) {
            return -1; 
        }

        // if times of the planes are equal, then comapres based on their plane numbers. 
        String planeNumber = this.getPlaneNumber();
        String comparisonPlaneNumber = o.getPlaneNumber(); 
        int numberComparison = planeNumber.compareTo(comparisonPlaneNumber);

        if (numberComparison > 0) {
            return 1;
        } else if (numberComparison < 0) {
            return -1; 
        }

        return 0; // should not occur assuming all planes have unique plane numbers
    }   
}


public class Dispatcher extends DispatcherBase {

    OrderedLinkedList structure; 

    public Dispatcher() {
        this.structure = new OrderedLinkedList(); 
    }    

    @Override
    public int size() {
        return this.structure.size; 
    }

    @Override
    public void addPlane(String planeNumber, String time) {
        this.structure.addNode(new Plane(planeNumber, time));
    }

    @Override
    public String allocateLandingSlot(String currentTime) {
        return this.structure.removeFirstNode(currentTime);
    }

    @Override
    public String emergencyLanding(String planeNumber) {
        return this.structure.removeNode(planeNumber);
    }

    @Override
    public boolean isPresent(String planeNumber) {
        return this.structure.presentNode(planeNumber);
    }
}

// Node with data of plane Object - pseudocode from Data Structures and Algorithms in Java, page 126 
class Node {
    Plane plane;
    Node next;

    Node(Plane plane) {
        this.plane = plane;
        this.next = null;
    }
}

class OrderedLinkedList {

    Node head;
    int size = 0;

    public void addNode(Plane plane) {
        Node newPlane = new Node(plane);
        Node pointer = head;
        Node previousNodeOfPointer = null;

        // add first - pseudocode from Data Structures and Algorithms in Java, page 127
        if (pointer == null) {
            head = newPlane;
            size++;
            return;
        }

        // insert node at start - pseudocode from Introduction to Algorithms, page 238
        if (newPlane.plane.compareTo(pointer.plane) < 0) {
            newPlane.next = head;
            head = newPlane;
            size++;
            return;
        }

        // list search to find valid position - pseudocode from Introduction to Algoritms, page 239
        while (pointer != null && newPlane.plane.compareTo(pointer.plane) > 0) {
            previousNodeOfPointer = pointer;
            pointer = pointer.next;
        }

        previousNodeOfPointer.next = newPlane;
        newPlane.next = pointer;
        size++;
        return;
    }

    public String removeFirstNode(String currentTime) {
        Node pointer = head;

        // remove the first node of list - pseudocode from Data Structures and Algorithms in Java, page 125

        if (pointer == null) {
            return null;
        }

        if (pointer.plane.getTime().compareTo(currentTime) < 0) {
            head = pointer.next;
            size--;
            return pointer.plane.getPlaneNumber();
        }

        String firstPlaneTime = pointer.plane.getTime();
        boolean timeDifference = validTimeDifference(currentTime, firstPlaneTime);

        if (timeDifference == true) {
            head = pointer.next;
            size--;
            return pointer.plane.getPlaneNumber();
        }

        return null;
    }

    public boolean validTimeDifference(String currentTime, String earliestLandingTime) {

        String[] currTime = currentTime.split(":");
        String[] earliestTime = earliestLandingTime.split(":");

        String cTimeHours = currTime[0];
        String cTimeMinutes = currTime[1];
        String eTimeHours = earliestTime[0];
        String eTimeMinutes = earliestTime[1];

        int currentTimeHours;
        int currentTimeMinutes;
        int earliestTimeHours;
        int earliestTimeMinutes;

        if (cTimeHours.charAt(0) == '0') {
            currentTimeHours = Integer.parseInt(Character.toString(cTimeHours.charAt(1)));
        } else {
            currentTimeHours = Integer.parseInt(cTimeHours);
        }

        if (cTimeMinutes.charAt(0) == '0') {
            currentTimeMinutes = Integer.parseInt(Character.toString(cTimeMinutes.charAt(1)));
        } else {
            currentTimeMinutes = Integer.parseInt(cTimeMinutes);
        }

        if (eTimeHours.charAt(0) == '0') {
            earliestTimeHours = Integer.parseInt(Character.toString(eTimeHours.charAt(1)));
        } else {
            earliestTimeHours = Integer.parseInt(eTimeHours);
        }

        if (eTimeMinutes.charAt(0) == '0') {
            earliestTimeMinutes = Integer.parseInt(Character.toString(eTimeMinutes.charAt(1)));
        } else {
            earliestTimeMinutes = Integer.parseInt(eTimeMinutes);
        }

        if (currentTimeHours <= earliestTimeHours && (earliestTimeMinutes - currentTimeMinutes <= 5)) {
            return true;
        }

        return false;
    }

    public String removeNode(String planeNumber) {
        Node pointer = head;
        Node previousNodeOfPointer = null;

        if (pointer == null) {
            return null;
        }

        // remove the first node of list - pseudocode from Data Structures and Algorithms in Java, page 125
        if (pointer.plane.getPlaneNumber().equals(planeNumber)) {
            head = pointer.next;
            size--;
            return pointer.plane.getPlaneNumber();
        }

        // list search to find valid position - pseudocode from Introduction to Algoritms, page 239
        while (pointer != null && pointer.plane.getPlaneNumber() != planeNumber) {
            previousNodeOfPointer = pointer;
            pointer = pointer.next;
        }

        if (pointer == null) {
            return null;
        }

        previousNodeOfPointer.next = pointer.next;
        size--;

        return pointer.plane.getPlaneNumber();
    }

    public boolean presentNode(String planeNumber) {
        Node pointer = head;

        if (pointer == null) {
            return false;
        }

        // list search to find valid position - pseudocode from Introduction to Algorithms, page 239
        while (pointer != null) {
            if (pointer.plane.getPlaneNumber().equals(planeNumber)) {
                return true;
            }
            pointer = pointer.next;
        }

        return false;
    }
}

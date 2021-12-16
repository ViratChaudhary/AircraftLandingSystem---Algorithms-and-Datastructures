public class test {
    
    public static void main(String[] args) {

        /*
        plane 1: "ABC1233", "9:24"
        plane 2: "ENC3453", "8:23"
        => plane 1 is after plane 2

        plane 1: "ABC1233", "9:24"
        plane 2: "BAA1113", "9:24"
        => plane 1 is before plane 2

        plane 1: "ABC1233", "9:24"
        plane 2: "ABC1234", "9:24"
        => plane 1 is before plane 2
        */

        String[] test = { "BAA1113,09:24", "AAA0000,09:23", "ABC1234,09:22",
        "ABC1233,09:20", "ENC3453,08:20","ENC3452,08:20", "AAA111,01:51", "BCA1221,00:01"};

        String[] test2 = { "BAA1113,09:24", "AAA0000,14:46", "ABC1234,09:24", "ABC1233,09:24", "ENC3453,08:23",
                "ENC3454,08:23"};

        
        DisplayRandom random = new DisplayRandom(test); 
        Plane[] x = random.sort();
        for (Plane p : x) {
            System.out.println(p);
        }        
    }
}

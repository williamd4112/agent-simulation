import city.City;
import utils.SimTimer;
import utils.io.In;

public class TaxiCoordinator {



    public static void main(String[] args) {
        City vCity;
        int sourceNode = 41;
        int destinationNode = 40;


        In in = new In("src/main/resources/v_city.txt");
//        Intersection intersection = new Intersection(sourceNode, sourceNode, destinationNode);

        System.out.println("Init of file");
        System.out.println("Create City");

//        vCity = new City();
//        vCity.clear();
//        vCity.generateCity(in);
//        System.out.println(vCity.intersections.size());
//
//        for(Intersection e : vCity.intersections) {
//            System.out.println(e);
//        }


        System.out.println("Done creating city");
        System.out.println("Generate Random Call for one intersecctino");
//        double lambda = 1.0/(60.0 / 2.0 );
//        System.out.println(lambda);
//        int sum = 0 ;
//        for(int i=0; i<200;i++) {
//            double next = Helper.nextCall(lambda);
//            System.out.println(next);
//            sum += next;
//        }
//        System.out.println(sum/200);

        SimTimer c = new SimTimer(0,0,0,1);
        for(int t = 0; true; t++){
            c.tick();
            try { Thread.sleep(5); } catch(Exception e){}
            System.out.println(c.toString());
//            if(c.getHour() >= 7 || c.getHour() <= 9) {
//                System.out.println("Lambda = 3");
//            }
        }
    }
}

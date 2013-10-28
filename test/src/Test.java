import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jakub
 * Date: 10/28/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        Random rn = new Random();
//        int n = 12 - 4 + 1;
////        int random = rn.nextInt(12 - 4);
//
//        for(int i = 0; i < 10; i++){
//            System.out.println(rn.nextInt(12 - 4)+4);
//        }
////        float azimuth = 360 - 0 + 1;
////        float elevation = 90 - 0 + 1;
        double tab[] = {90, 75, 60, 45, 30, 0};

        for(int i = 0 ; i < 90; i++){
            System.out.println( Math.cos(Math.toRadians(i)));
        }
    }
}

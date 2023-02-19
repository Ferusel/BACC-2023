import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

//        try {
//            FileWriter fw = new FileWriter("Performance.txt");
//            int output = 0;
//            for (int i = 1; i <= 5; i++) {
//                for (int j = 1; j <= 5; j++) {
//                    Company.resetCompany(i, j);
//                    Simulation simulation = new BACCSimulation();
//                    output = new Simulator(simulation).run();
//
//                    fw.write(String.format("(%s, %s) = %s\n", i, j, output));
//                }
//            }
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Simulation simulation = new BACCSimulation();
        new Simulator(simulation).run();

    }
}

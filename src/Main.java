import java.util.Scanner;

/**
 * The main class for CS2030S Lab 1.
 *
 * @author Wei Tsang
 * @version CS2030S AY20/21 Semester 2
 */
class Main {
    public static void main(String[] args) {

        Simulation simulation = new BACCSimulation();
        new Simulator(simulation).run();
    }
}

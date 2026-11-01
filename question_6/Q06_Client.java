import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Q06_Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Q06_Calc calc = (Q06_Calc) registry.lookup("CalculatorService");

            System.out.println("Addition (10 + 5) = " + calc.add(10, 5));
            System.out.println("Subtraction (10 - 5) = " + calc.subtract(10, 5));
            System.out.println("Multiplication (10 * 5) = " + calc.multiply(10, 5));
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
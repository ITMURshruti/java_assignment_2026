import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Q06_Server {
    public static void main(String[] args) {
        try {
            Q06_Calc obj = new Q06_CalcImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", obj);
            System.out.println("Calculator Server is ready...");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
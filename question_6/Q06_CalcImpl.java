import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Q06_CalcImpl extends UnicastRemoteObject implements Q06_Calc {
    public Q06_CalcImpl() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }
}
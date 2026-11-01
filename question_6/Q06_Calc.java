


/*create function like multiply,addition, and substruction respectively. invoke these functions from remote system by using the concept of remote method invocationin java.*/

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Q06_Calc extends Remote {
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
}
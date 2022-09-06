import java.rmi.Remote;

public interface calcServer1 extends Remote {
    public int mensaje(int mensaje) throws java.rmi.RemoteException;
}

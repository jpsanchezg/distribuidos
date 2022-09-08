import java.rmi.Remote;
import java.util.List;

public interface calcServer extends Remote {
    public int mensaje(List<String> mensaje) throws java.rmi.RemoteException;
}
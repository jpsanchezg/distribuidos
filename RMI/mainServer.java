import java.rmi.Remote;

public interface mainServer extends Remote {
	public int getOperation(String mensaje) throws java.rmi.RemoteException;
}
import java.rmi.Remote;

public interface mainServer extends Remote {
	public int getTime(int mensaje) throws java.rmi.RemoteException;

}

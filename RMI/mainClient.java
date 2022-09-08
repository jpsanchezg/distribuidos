import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class mainClient {

	public static void main(String[] args) {
		
		try {
			
			// conectarse al servidor y cargar registro de objetos RMI
	        Registry registry = LocateRegistry.getRegistry("10.195.40.15", 5000);
			// buscar el objeto timeServer en el registro,
			// y si lo encuentra, crear el objeto local
			mainServer TS = (mainServer)registry.lookup("mainServer");
			System.out.println(args[0]);
			System.out.println(TS.getOperation(args[0]));
		}
		catch (NotBoundException e) {
			System.out.println("Time Server no se encontró en el registro");
		    System.exit(0);
		    }
		catch (RemoteException e) {
			System.out.println("Time error: " + e);
		    System.exit(0);
		    } 
	}

}
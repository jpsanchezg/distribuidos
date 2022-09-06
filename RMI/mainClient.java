// ************************************************************
// Cliente.
// Pide la ejecución del método getTime,
// del objeto TimeServer.
// Para el cliente una vez cargado el objeto, lo trata com local
// ************************************************************

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class mainClient {

	public static void main(String[] args) {
		
		// Variable que recibirá la hora del servidor
		int time = 0;

		
		try {
			
			// conectarse al servidor y cargar registro de objetos RMI
	        Registry registry = LocateRegistry.getRegistry("192.168.56.1", 1099);
	        
			// buscar el objeto timeServer en el registro,
			// y si lo encuentra, crear el objeto local
	        mainServer TS = (mainServer)registry.lookup("timeServer");
	        
	        // usar el método getTime del objeto conectado.
	        //time = TS.getTime(45);

			System.out.println(TS.getTime(45));

	        
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

// **************************************
// Servidor de Hora.
// Los clientes se conectan y ejecutan el método getTime()
// 
// ****************************************************************

import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class mainServerImpl extends UnicastRemoteObject implements mainServer {

	int numero = 0;

	protected mainServerImpl() throws RemoteException {
		System.out.println("Arrancando Servidor de calculo...");
	}

	public int getTime(int num) throws RemoteException {
		System.out.println("Mensaje recibido: " + num);
		System.out.println("operacion enviada a los servidores de operaciones...");
		try {
			Registry registrycalc = LocateRegistry.getRegistry("192.168.56.1", 1098);

			calcServer1 CS = (calcServer1) registrycalc.lookup("calcServer1");

			numero = CS.mensaje(num);
		} catch (NotBoundException e) {
			System.out.println("Time Server no se encontró en el registro");
			System.exit(0);
		}
		return numero;
	}

	// Arranque del Servidor de Hora

	public static void main(String[] args) {

		try {

			// Crear el objeto cuyos métodos el cliente podrá usar
			mainServerImpl TSI = new mainServerImpl();

			// Incluir el objeto en el registro del RMI en el puerto 1099,
			// para que el cliente lo pueda encontrar.

			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("timeServer", TSI);
			System.out.println("Objeto -timeServer- Registrado en el RMI");

		} catch (RemoteException e) {
			System.out.println("Error: " + e);
		}
	}
}

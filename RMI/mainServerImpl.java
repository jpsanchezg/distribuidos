import java.rmi.RemoteException;
import java.rmi.NotBoundException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class mainServerImpl extends UnicastRemoteObject implements mainServer {

	int num = 0;
	String numS;
	List<String> ms1 = new ArrayList<String>();
	List<String> ms2 = new ArrayList<String>();
	String[] mens;

	protected mainServerImpl() throws RemoteException {
		System.out.println("Arrancando Servidor de calculo...");
	}

	public int getOperation(String mens) throws RemoteException {
		System.out.println("Mensaje recibido main: " + mens);
		System.out.println("operacion enviada a los servidores de operaciones...");
		try {
			Registry registrycalc = LocateRegistry.getRegistry("10.43.100.228", 5005);
			calcServer CS = (calcServer) registrycalc.lookup("calcServer1");
			String[] numeros = mens.split("\\*");
			ms1.add(numeros[0]);
			String temporal = numeros[1];
			numeros = temporal.split("\\+");
			ms1.add(numeros[0]);
			num = CS.mensaje(ms1);
			numS = String.valueOf(num);
			try {
				Registry registrycalc2 = LocateRegistry.getRegistry("10.195.45.7", 5002);
				calcServer CS2 = (calcServer) registrycalc2.lookup("calcServer2");
				ms2.add(numS);
				ms2.add(numeros[1]);
				num = CS2.mensaje(ms2);
			} catch (NotBoundException e) {
				System.out.println("Calc Server no se encontró en el registro");
				System.exit(0);
			}

		} catch (NotBoundException e) {
			System.out.println("Calc Server no se encontró en el registro");
			System.exit(0);
		}
		return num;
	}

	// Arranque del Servidor de Hora

	public static void main(String[] args) {
		try {
			// Crear el objeto cuyos métodos el cliente podrá usar
			mainServerImpl TSI = new mainServerImpl();
			// Incluir el objeto en el registro del RMI en el puerto 1099,
			// para que el cliente lo pueda encontrar.
			Registry registry = LocateRegistry.createRegistry(5000);
			registry.rebind("mainServer", TSI);
			System.out.println("Objeto -mainServer- Registrado en el RMI");

		} catch (RemoteException e) {
			System.out.println("Error: " + e);
		}
	}
}
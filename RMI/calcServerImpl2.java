import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
//import java.rmi.NotBoundException;
import java.util.List;

public class calcServerImpl2 extends UnicastRemoteObject implements calcServer {

    protected calcServerImpl2() throws RemoteException {
		System.out.println("Arrancando Servidor de operacion 2...");
	}

    public int mensaje(List<String> numero) throws RemoteException {
        System.out.println("Mensaje recibido calc: " + numero);
        int valor = 0;
        valor = Integer.parseInt(numero.get(0)) + Integer.parseInt(numero.get(1));
        return valor;
    }

    // Arranque del Servidor de Hora

    public static void main(String[] args) {

        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            calcServerImpl2 TSI = new calcServerImpl2();

            // Incluir el objeto en el registro del RMI en el puerto 1099,
            // para que el cliente lo pueda encontrar.

            Registry registry = LocateRegistry.createRegistry(1097);
            registry.rebind("calcServer2", TSI);
            System.out.println("Objeto -calcServer2- Registrado en el RMI");
        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class calcServerImpl extends UnicastRemoteObject implements calcServer {

    protected calcServerImpl() throws RemoteException {
		System.out.println("Arrancando Servidor de operacion 1...");
	}

    public int mensaje(List<String> numero) throws RemoteException {
        System.out.println("Mensaje recibido calc: " + numero);
        int valor = 0;
        valor = Integer.parseInt(numero.get(0)) * Integer.parseInt(numero.get(1));
        return valor;
    }

    // Arranque del Servidor de Hora

    public static void main(String[] args) {

        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            calcServerImpl TSI = new calcServerImpl();

            // Incluir el objeto en el registro del RMI en el puerto 1099,
            // para que el cliente lo pueda encontrar.

            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("calcServer", TSI);
            System.out.println("Objeto -calcServer- Registrado en el RMI");
        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}
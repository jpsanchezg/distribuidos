import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;

public class calcServer1Impl extends UnicastRemoteObject implements calcServer1 {

    protected calcServer1Impl() throws RemoteException {
		System.out.println("Arrancando Servidor de operacion 1...");
	}

    public int mensaje(int numero) throws RemoteException {
        System.out.println("Mensaje recibido: " + numero);
        numero = numero + 1;
        return numero;
    }

    // Arranque del Servidor de Hora

    public static void main(String[] args) {

        try {

            // Crear el objeto cuyos métodos el cliente podrá usar
            calcServer1Impl TSI = new calcServer1Impl();

            // Incluir el objeto en el registro del RMI en el puerto 1099,
            // para que el cliente lo pueda encontrar.

            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("calcServer1", TSI);
            System.out.println("Objeto -timeServer- Registrado en el RMI");
        } catch (RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
}

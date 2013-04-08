package gamemanager;

import game.GestionJuegos;
import game.IGestionJuegos;
import game.ITriquiGame;
import game.TriquiGame;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class TriquiServer{
	
	/**
	 *Método local que no puede se invocado de manera remota.
	 *Las principales funciones de este método son:
	 *	-Crear e instalar un security manager. determina los permisos de 
	 *	operación de aplicaciones de diferentes maquinas virtuales. Si no está 
	 *	Implementada. RMI no descargará clases diferentes al classpath.
	 *	-Hacer los objetos remotos disponibles para los clientes.
	 */
	public static void main(String[] args) {
		// Creación del securuty manager.
		/*if (System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}*/
		try {
			//Se crea un nombre para el objeto
			String name = "gestor";
			String gameName = "game";
			
			//Hace los objetos disponibles para los clientes
			IGestionJuegos gestor = new GestionJuegos();
			ITriquiGame game = new TriquiGame();
			
			//Crea los stub
			IGestionJuegos stub = 
					(IGestionJuegos) UnicastRemoteObject.exportObject(gestor, 0);
			ITriquiGame stub2 =
					(ITriquiGame)UnicastRemoteObject.exportObject(game, 0);
			
			//Se agrega el nombre del RMI Registry en el servidor 
			Registry registry = LocateRegistry.getRegistry();
			
			/*Hace una referencia al localhost registry y el default registry 
			port que es 1099*/
			
			registry.rebind(name, stub);
			registry.rebind(gameName, stub2);
			/*La invocación del rebind hace una llamada remota al RMI Registry 
			en el localhost. Por razones de seguridad una aplicación solo puede 
			Hacer bind, rebind y unbind referencias de objetos remotos con un
			registry corriendo en el mismo host.*/
			
			System.out.println("gestor y game publicados");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

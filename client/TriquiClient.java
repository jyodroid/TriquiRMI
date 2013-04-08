package client;

import game.IGestionJuegos;
import game.ITriquiGame;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class TriquiClient {
	private IGestionJuegos myGestor =  null;
	private ITriquiGame myGame = null;
	
	public TriquiClient(String servidor) {
		/*if (System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}*/
		try {
			//Traemos el objeto juego
			String name = "gestor";
			Registry registry = LocateRegistry.getRegistry(servidor);
			IGestionJuegos gestor = (IGestionJuegos)registry.lookup(name);
			setMyGestor(gestor);
			
			name = "game";
			ITriquiGame game = (ITriquiGame)registry.lookup(name);
			setMyGame(game);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public IGestionJuegos getMyGestor() {
		return myGestor;
	}

	public void setMyGestor(IGestionJuegos gestor) {
		this.myGestor = gestor;
	}

	public ITriquiGame getMyGame() {
		return myGame;
	}

	public void setMyGame(ITriquiGame myGame) {
		this.myGame = myGame;
	}
}

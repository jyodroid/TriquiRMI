package game;

import java.rmi.RemoteException;
import java.util.ArrayList;

import client.TriquiClient;

public class GestionJuegos implements IGestionJuegos{
	
	private ArrayList<String> listaJugadores = new ArrayList<String>();
	private String contrincante;
	@SuppressWarnings("unused")
	private String servidor;
	private String jugador;
	private boolean juego = false;
	private boolean rechazo = false;
	private ITriquiGame game = null;
	
	@Override
	public boolean selecionado(String nick) throws RemoteException{ 
		if (!getListaJugadores().contains(nick)){
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> getListaJugadores() throws RemoteException{
		return listaJugadores;
	}
	
	@Override
	public void agregarAListaJugadores(String jugador) throws RemoteException{
		getListaJugadores().add(jugador);
	}
	
	@Override
	public void quitarDeListaJugadores(String jugador) throws RemoteException{
		getListaJugadores().remove(jugador);
	}
	
	@Override
	public ITriquiGame getGame() throws RemoteException{
		return game;
	}
	
	public void setGame(TriquiGame game) {
		this.game = game;
	}

	@Override
	public void juegoPactado(String jugador)
			throws RemoteException {
		this.contrincante = jugador;
		this.game.setContrincante(jugador);
	}
	
	@Override
	public String getContrincante() throws RemoteException{
		return contrincante;
	}
	
	@Override
	public boolean isJuego() throws RemoteException{
		return juego;
	}

	@Override
	public void setJuego(boolean myJuego) throws RemoteException{
		this.juego = myJuego;
	}

	@Override
	public boolean isRechazo() throws RemoteException {
		return rechazo;
	}

	@Override
	public void setRechazo(boolean myRechazo) throws RemoteException {
		this.rechazo = myRechazo;
	}

	@Override
	public void setJugador(String jugador) throws RemoteException {
		this.jugador = jugador;
		this.game.setJugador(jugador);
	}

	@Override
	public String getJugador() throws RemoteException {
		return jugador;
	}

	@Override
	public void setServidor(String string) throws RemoteException {
		this.servidor = string;
		game = new TriquiClient("localhost").getMyGame();
	}
}
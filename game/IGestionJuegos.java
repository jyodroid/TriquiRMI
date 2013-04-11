package game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGestionJuegos extends Remote{
	
	public boolean selecionado(String nick)  throws RemoteException;
	
	public ArrayList<String> getListaJugadores() throws RemoteException;
	
	public ITriquiGame getGame() throws RemoteException;

	public void agregarAListaJugadores(String jugador) throws RemoteException;

	public void quitarDeListaJugadores(String jugador) throws RemoteException;
	
	public void juegoPactado(String contrincante) throws RemoteException;
	
	public String getContrincante() throws RemoteException;

	public boolean isJuego() throws RemoteException ;

	public void setJuego(boolean juego) throws RemoteException;
	
	public boolean isRechazo() throws RemoteException ;

	public void setRechazo(boolean rechazo) throws RemoteException;
	
	public void setJugador(String jugador) throws RemoteException;
	
	public String getJugador() throws RemoteException;

	public void setServidor(String string) throws RemoteException;
}

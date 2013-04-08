package game;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emontoya
 */
public interface ITriquiGame extends Remote {
    /*
     * Inicia un juego
     */
    public void     start() throws RemoteException;
    
    /*
     * Realiza una jugada y alterna el turno.
     * retorna verdadero si la jugada es válida
     * retorna falso si la jugada es inválida
     * 
     * Esta son las posiciones que se utilizarán.
     * 
     * [1] [2] [3]
     * [4] [5] [6]
     * [7] [8] [9]
     */
    public boolean  play(int pos) throws RemoteException;
    
    
    
    
    
    /*
     * Devuelve el simbolo del jugador actual (X o O)
     */
    public String   player() throws RemoteException;
    
    
    
    
    
    
    /*
     * Devuelve una versión texto del tablero del triqui
     */
    public String   board() throws RemoteException;
    
    
    
    
    
    
    
    /*
     * verifica si hubo ganador, retorna el Simbolo del ganador, o "no hubo"
     */
    public String   testWinner() throws RemoteException;

	public void setContrincante(String jugador) throws RemoteException;

	public void setJugador(String jugador)throws RemoteException;
}

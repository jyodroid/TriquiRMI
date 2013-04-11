package game;

import java.rmi.RemoteException;


/**
 *
 * @author emontoya
 */

public class TriquiGame implements ITriquiGame{

	boolean turn = true;
    
    String X = "[X]";
    String O = "[O]";
    String E = "[ ]";
    private String jugador = "";
    private String contrincante = "";
    
    String[] board = new String[9];
    
    public void start() throws RemoteException{
        for (int x=0;x<9;x++) {
            board[x] = E; 
            }
        }
    
    public boolean play(int pos) throws RemoteException{
    	boolean result = true;
        if (pos < 9 && !board[pos].equals(X) && !board[pos].equals(O)) {
            if (turn) {
                board[pos] = X;
                turn=!turn;
                
            } else {
                board[pos] = O;
                turn=!turn;
            }
        } else
            result = false;
        return result;
    }
    
    public String player() throws RemoteException{
        String result = "";
            if (turn) {
                result = jugador;
                
            } else {
                result = contrincante;
            }
        return result;
    }
    
    public String board() throws RemoteException{
        StringBuffer sb = new StringBuffer();
        int pos = 0;
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++) {
                sb.append(board[pos++]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String testWinner() throws RemoteException{
        String result ="N ";
        
        if (board[0].equals(X) && board[1].equals(X) && board[2].equals(X))
            result = X;
        
        if (board[3].equals(X) && board[4].equals(X) && board[5].equals(X))
            result = jugador;
        
        if (board[6].equals(X) && board[7].equals(X) && board[8].equals(X))
            result = jugador;       
        
        if (board[0].equals(X) && board[3].equals(X) && board[6].equals(X))
            result = jugador;
        
        if (board[1].equals(X) && board[4].equals(X) && board[7].equals(X))
            result = jugador;
        
        if (board[2].equals(X) && board[5].equals(X) && board[8].equals(X))
            result = jugador;
        
        if (board[0].equals(X) && board[4].equals(X) && board[8].equals(X))
            result = jugador;
        
        if (board[2].equals(X) && board[4].equals(X) && board[6].equals(X))
            result = jugador;
        
        if (board[0].equals(O) && board[1].equals(O) && board[2].equals(O))
            result = contrincante;
        
        if (board[3].equals(O) && board[4].equals(O) && board[5].equals(O))
            result = contrincante;
        
        if (board[6].equals(O) && board[7].equals(O) && board[8].equals(O))
            result = contrincante;
        
        if (board[0].equals(O) && board[3].equals(O) && board[6].equals(O))
            result = contrincante;
        
        if (board[1].equals(O) && board[4].equals(O) && board[7].equals(O))
            result = contrincante;
        
        if (board[2].equals(O) && board[5].equals(O) && board[8].equals(O))
            result = contrincante;
        
        if (board[0].equals(O) && board[4].equals(O) && board[8].equals(O))
            result = contrincante;
        
        if (board[2].equals(O) && board[4].equals(O) && board[6].equals(O))
            result = contrincante;
        
        return result;
    }

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public String getContrincante() {
		return contrincante;
	}

	public void setContrincante(String contrincante) {
		this.contrincante = contrincante;
	}
}

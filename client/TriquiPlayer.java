package client;


import game.ITriquiGame;

import java.io.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emontoya
 */
public class TriquiPlayer{
    
	private ITriquiGame triqui = null;
    
    public TriquiPlayer(ITriquiGame game) {
        triqui = game;
    }

    /*
     * Lee un string del teclado
     */
    private String keyboard() {
        String cadena = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            cadena = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TriquiPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadena;
    }
    
    /*
     * Juega una partida
     */
    public void play() throws RemoteException {
        triqui.start();
        String winner = "N";
        int contplay = 0;
        boolean valid = false;

        while (winner.equals("N") && contplay < 9) {

            String p = null;
            String b = null;
            
            p = triqui.player();
            b = triqui.board();
            
            System.out.println("\nTURNO: " + p);
            System.out.println(b);
            do {
                System.out.print("Posicion = ");
                String pos = keyboard();

                valid = triqui.play(Integer.parseInt(pos)-1);
                if (!valid)
                    System.out.println(">>> Jugada invalida");
            } while (!valid);
            winner = triqui.testWinner();
            contplay++;
        }
        System.out.println(triqui.board());
        System.out.println("Ganador: " + winner);
    }
    
    public void run() throws RemoteException {
        String input = "y";
        while (input.equals("y")) {
            play();
            System.out.print("Continuar? (y/n) = ");
            input = keyboard();
        }
    } 
}

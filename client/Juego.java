package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import game.IGestionJuegos;

public class Juego{

	public static void main(String[] args) throws RemoteException {
		TriquiClient cliente = new TriquiClient(args[0]);
		IGestionJuegos gj = cliente.getMyGestor();
		gj.setServidor(args[0]);
		TriquiPlayer tp = null;
		InputStreamReader isr  = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int opcion = 0;
		
		try {
			do{
				System.out.println("Seleccione una opción:\n1. Crear Partida\n2. Unirse a partida\n3. Salir");
				opcion = Integer.parseInt(br.readLine());
				if (opcion<=0 || opcion >3){
					System.out.println("Opción no disponible");
				}else{
					switch (opcion) {
					case 1:
						System.out.println("Ingrese su nickname");
						String nick = br.readLine();
						gj.agregarAListaJugadores(nick);
						boolean seleccionado = false;
						System.out.println("...Esperando juego");
						do{
							seleccionado = gj.selecionado(nick);
						}while (!seleccionado);
						System.out.println("Desafio-> desea jugar con:"+gj.getContrincante()+" s/n");
						String a = br.readLine();
						if (a.compareToIgnoreCase("s")==0){
							gj.setJugador(nick);
							
							//Crear los jugadores
							System.out.println(nick+" vs "+gj.getContrincante());
							
							//empieza el juego
							gj.setJuego(true);
							gj.setRechazo(false);
							tp = new TriquiPlayer(gj.getGame());
							//FALTA Turnos para actualizar el objeto tp y compartirlo
							tp.run();
							
						}else{
							gj.setRechazo(true);
							gj.setJuego(false);
							System.out.println("Juego rechazado");
						}
						break;
					
					case 2:
						gj = cliente.getMyGestor();
						if (gj.getListaJugadores().size()==0){
							System.out.println("No hay jugadores disponibles");
							opcion = 0;
						}else{
							System.out.println("---Lista de Jugadores---");
							for (String jugadorListado : gj.getListaJugadores()) {
								System.out.println("- "+jugadorListado);
							}
							System.out.println("Ingrese el nombre del jugador de la lista con el que quiere jugar");
							String contrincante = br.readLine(); 
							if (gj.getListaJugadores().contains(contrincante)){
								System.out.println("Ingrese su nickname");
								String jugador = br.readLine();
								gj.juegoPactado(jugador);
								gj.quitarDeListaJugadores(contrincante);
								System.out.println("Esperando Respuesta");
								boolean aceptado = false;
								boolean rechazado = false;
								do{
									aceptado = gj.isJuego();
									rechazado = gj.isRechazo();
								}while (aceptado==rechazado);
								if(rechazado){
									System.out.println("Juego rechazado");
									gj.setJuego(false);
									gj.setRechazo(false);
								}else{
									//Crear los jugadores
									System.out.println(jugador+" vs "+contrincante);
									
									//pegarse al juego
									tp = new TriquiPlayer(gj.getGame());
									//FALTA Turnos para actualizar el objeto tp y compartirlo
									tp.run();
								}
							}else{
								opcion = 0;
								System.out.println("No existe ese contrincante");
							}
						}
						break;
						
					case 3:
						System.out.println("Fin del juego...");
						System.exit(0);
						break;

					default:
						System.out.println("Opciones 1, 2 o 3");
						opcion = 0;
						break;
					}
				}				
			}while(opcion<=0 || opcion >3);
		} 
		catch (IOException IOe) {
			System.out.println(IOe.getMessage());
			System.out.println("error ingresando los valores...Fin del juego");
		}
		catch (NumberFormatException NFe) {
				System.out.println("no puede ingresar caracteres en esta opicón solo números...Fin del juego");
		}
		catch (Exception e) {
			System.out.println("Error descnocido: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
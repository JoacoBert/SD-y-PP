package TP1_EJ3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerHilo implements Runnable{
	BufferedReader canalEntrada;
	PrintWriter canalSalida;
	Socket client;
	
	List<String> usuarios;
	
	Mailbox mailbox;
	
	int loggedUserId = -1;

	public ServerHilo(Socket client, List<String> users, Mailbox mailbox) {
		this.client = client;
		this.usuarios = users;
		this.mailbox = mailbox;
		try {
			canalSalida = new PrintWriter(this.client.getOutputStream(), true);
			canalEntrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	private void menuPrincipal() {
		try {
			
			canalSalida.println("||==============================================================||");
			canalSalida.println("||              Bienvenido al servidor de chat                  ||");
			canalSalida.println("||==============================================================||");
			canalSalida.println("Seleccione una opción del menú");
			canalSalida.println("1 -- Registrarse ");
			canalSalida.println("2 -- Login");
			canalSalida.println("0 -- Salir");
			int option = leerOpcion(0, 2);
			switch (option) {
			case 1:
				crearUsuario();
				break;
			case 2:
				login();
				break;
			case 0:
				salir();
				break;
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	private void crearUsuario() throws IOException {
		canalSalida.println("Ingrese el nombre de usuario");
		String nombreUsuario = canalEntrada.readLine();
		if(usuarios.contains(nombreUsuario)) {
			canalSalida.println("-----------------------------------------------------------");
			canalSalida.println("El usuario "+ nombreUsuario + " ya se encuentra registrado.");
			menuPrincipal();
		}
		else {
			usuarios.add(nombreUsuario);
			loggedUserId = usuarios.indexOf(nombreUsuario);
			canalSalida.println("-----------------------------------------------------------");
			canalSalida.println("Registro exitoso.");
			canalSalida.println("-----------------------------------------------------------");
			menuUsuario();
		}
	}

	private void menuUsuario() throws IOException {
		canalSalida.println("Bienvenido " + usuarios.get(loggedUserId));
		canalSalida.println("1 -- Enviar mensaje");
		canalSalida.println("2 -- Buzón de entrada");
		canalSalida.println("3 -- Cerrar sesión");
		int option = leerOpcion(1, 3);
		switch (option) {
		case 1:
			enviarMensaje();
			break;
		case 2:
			listarMensajes();
			break;
		case 3:
			cerrarSesion();
			break;
		}
	}

	private void enviarMensaje() throws IOException {
		if(usuarios.size() > 1) {
			listarContactos();
			int userIdTo = seleccionarDestinatario();
			canalSalida.println("Escriba el mensaje, para terminar pulse 0.");
			escribirMensaje(loggedUserId, userIdTo);
		}
		else {
			canalSalida.println("Por el momento no hay usuarios registrados para enviar mensajes.");
			menuUsuario();
		}
	}

	private void listarContactos() {
		canalSalida.println("Seleccione el destinatario");
		for(int i = 0; i < usuarios.size(); i++) 
		    if(i != loggedUserId)
			canalSalida.println(i + " -- " + usuarios.get(i));
	}


	private void listarMensajes() throws IOException {
		List<Mensaje> mensajes = mailbox.getMessagesTo(loggedUserId);
		if(mensajes.size() == 0) {
			canalSalida.println("No tienes ningún mensaje sin leer");
			canalSalida.println("-----------------------------------------------------------");
			menuMessages();				
		}else{
			mostrarBandejaEntrada(mensajes);
			canalSalida.println("-----------------------------------------------------------");
			menuMessages();
		} 
			
		
	}


	private void mostrarBandejaEntrada(List<Mensaje> mensajes) {
		ArrayList<Integer> idMensaje = new ArrayList<>();
		for(Mensaje mensaje : mensajes) {
			if(!idMensaje.contains(mensaje.getIdEmisor())) {
				listarMensajesRecibidos(mensaje.getIdEmisor(), mensaje.getNombreDestinatario(), mensajes);
				idMensaje.add(mensaje.getIdEmisor());
			}
		}
	}


	private void cerrarSesion() {
		loggedUserId = -1;
		menuPrincipal();
	}
	
	private void login() throws IOException {
		canalSalida.println("Ingrese su nombre de usuario");
		String nombreUsuario = canalEntrada.readLine();
		if(usuarios.contains(nombreUsuario)) {
			canalSalida.println("Login exitoso");
			canalSalida.println("-----------------------------------------------------------");
			loggedUserId = usuarios.indexOf(nombreUsuario);
			menuUsuario();
		}
		else {
			canalSalida.println("El usuario "+ nombreUsuario + " no está registrado.");
			canalSalida.println("-----------------------------------------------------------");
			menuPrincipal();
		}
	}

	private void menuMessages() throws IOException {
		canalSalida.println("1 -- Buzón de entrada");
		canalSalida.println("2 -- Vaciar buzón de entrada");
		canalSalida.println("3 -- Volver al menú anterior");
		int option = leerOpcion(1, 3);
		switch (option) {
		case 1:
			listarMensajes();
			break;
		case 2:
			eliminarMensajes();
			break;
		case 3:
			menuUsuario();
			break;
		}
	}

	private void eliminarMensajes() throws IOException {
		mailbox.eliminarMensaje(loggedUserId);
		canalSalida.println("Mensajes eliminados");
		canalSalida.println("-----------------------------------------------------------");
		menuUsuario();
	}
	
		
	private void salir() {
		try {
			String nombreUsuarioLogin = "";
			if(loggedUserId != -1)
				nombreUsuarioLogin = usuarios.get(loggedUserId);
				canalSalida.println("Cerrando sesión del usuario " + nombreUsuarioLogin);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private void listarMensajesRecibidos(int idEmisor, String nameEmisor, List<Mensaje> mensajes) {
		canalSalida.println("Mensaje de "+ nameEmisor+":");
		for (Mensaje mensaje : mensajes) {
			if(mensaje.getIdEmisor() == idEmisor)
			canalSalida.println(mensaje.getPayload());
		}
	}

	private int seleccionarDestinatario() {
		int userId = -1;
		while(userId < 0 || userId > usuarios.size() - 1 || userId == loggedUserId) {
			try {
				userId = Integer.valueOf(canalEntrada.readLine());

				if (userId >= usuarios.size() - 1) {
					canalSalida.println("Usuario inexistente");
					canalSalida.println("-----------------------------------------------------------");
					listarContactos();

				}

			} catch (Exception e) {
				userId = -1;
			}
		}
		return userId;
	}
	
	private void escribirMensaje(int idFrom, int idTo) throws IOException {
		String userMessage = canalEntrada.readLine();
		while(!userMessage.equals("0")) {
			mailbox.enviarMensaje(usuarios.get(loggedUserId), idFrom, idTo, userMessage);
			userMessage = canalEntrada.readLine();
		}
		canalSalida.println("Mensaje enviado");
		menuUsuario();
	}
	
	private int leerOpcion(int fromValue, int toValue) {
		int option = fromValue - 1;
		while(option < fromValue || option > toValue) {
			try {
				option = Integer.valueOf(canalEntrada.readLine());
			} catch (Exception e) {
				option = fromValue - 1;
			}
		}
		return option;
	}



	@Override
	public void run() {
		menuPrincipal();
	}

}

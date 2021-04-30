package TP1_EJ3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mailbox {
	
	List<Mensaje> mensajes;
	
	public Mailbox() {
		mensajes = Collections.synchronizedList(new ArrayList<Mensaje>());
	}
	
	public List<Mensaje> getMessagesTo(int idTo){
		ArrayList<Mensaje> result = new ArrayList<Mensaje>();
		for (Mensaje mensaje : mensajes) {
			if(mensaje.getIdDestinatario() == idTo)
				result.add(mensaje);
		}
		return result;
	}
	
	public void enviarMensaje(String nameFrom, int idFrom, int idTo, String payload) {
		mensajes.add(new Mensaje(nameFrom, idFrom, idTo, payload));
	}

	public void eliminarMensaje(int loggedUserId) {
		List<Mensaje> mensajesAEliminar = new ArrayList<>();
		for (Mensaje mensaje : mensajes) {
			if(mensaje.getIdDestinatario() ==loggedUserId)
				mensajesAEliminar.add(mensaje);
		}
		mensajes.removeAll(mensajesAEliminar);
	}
}

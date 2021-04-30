package TP1_EJ3;

public class Mensaje {
	
	int idEmisor;
	String nombreDestinatario;
	int idDestinatario;
	String payload;
	
	public Mensaje(String nombreDestinatario, int idEmisor, int idDestinatario, String payload) {
		this.idEmisor = idEmisor;
		this.nombreDestinatario = nombreDestinatario;
		this.idDestinatario = idDestinatario;
		this.payload = payload;
		
	}
	
	public int getIdEmisor() {
		return idEmisor;
	}
	
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	
	public int getIdDestinatario() {
		return idDestinatario;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public String toString(){
	    return "Mensaje para " + nombreDestinatario + ": " + payload;
	  }

}

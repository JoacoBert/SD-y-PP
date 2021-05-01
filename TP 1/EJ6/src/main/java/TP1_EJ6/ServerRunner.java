package TP1_EJ6;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.google.gson.Gson;

public class ServerRunner implements RemoteInt, Remote{

	Gson gson = new Gson();
	
	@Override
	public String suma(String jsonV1, String jsonV2) throws RemoteException {
		Vector v1 = gson.fromJson(jsonV1, Vector.class);
		Vector v2 = gson.fromJson(jsonV2, Vector.class);
		Vector v3 = v1.suma(v2);
		String jsonV3 = gson.toJson(v3, Vector.class);
		return jsonV3;
	}

	@Override
	public String resta(String jsonV1, String jsonV2) throws RemoteException {
		Vector v1 = gson.fromJson(jsonV1, Vector.class);
		Vector v2 = gson.fromJson(jsonV2, Vector.class);
		Vector v3 = v1.resta(v2);
		String jsonV3 = gson.toJson(v3, Vector.class);
		return jsonV3;
	}

	@Override
	public String modificarVector(String jsonV1) {
		Vector v1 = gson.fromJson(jsonV1, Vector.class);
		Vector v3 = v1.modificarVector(v1);
		String jsonV3 = gson.toJson(v3, Vector.class);
		return jsonV3;
	}
}
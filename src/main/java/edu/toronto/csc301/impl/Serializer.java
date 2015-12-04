package edu.toronto.csc301.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUser;
import com.google.gson.*;


public class Serializer implements ISerializer {

	public void serialize(IUser user, OutputStream output) throws Exception{
		if( user == null || output == null){
			throw new NullPointerException();
		}
		Gson gson = new Gson();
		String js = gson.toJson(user);
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream (output);
			out.writeObject(js);
		}catch (IOException e){
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}
	public IUser deserializeUser(InputStream input) throws Exception{
		if(input == null){
			throw new NullPointerException ();
		}
		String js= null;
		try{
			ObjectInputStream in = new ObjectInputStream(input);
			js = (String) in.readObject();
		}catch (IOException e){
			e.printStackTrace();
			throw new IOException (e.getMessage());
		}
		Gson gson = new Gson();
		IUser user = gson.fromJson(js, User.class);
		return user;
	}
	
}

package edu.toronto.csc301.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUser;

public class Serializer implements ISerializer
{
    @Override
    public void serialize(IUser user, OutputStream output) throws Exception
    {
        	if(user == null) throw new NullPointerException();
        	String object = JsonWriter.objectToJson((User)user);
            output.write(object.getBytes());
    }

    @Override
    public IUser deserializeUser(InputStream input) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            out.append(line);
        }
        try
        {
        	Object obj = JsonReader.jsonToJava(out.toString());
        	User user = (User)obj;
        	return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

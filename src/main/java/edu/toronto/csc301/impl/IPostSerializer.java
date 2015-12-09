package edu.toronto.csc301.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.toronto.csc301.IPost;

public class IPostSerializer extends JsonSerializer<Iterator<IPost>>
{
    @Override
    public void serialize(Iterator<IPost> arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException
    {
        List<Post> posts = new ArrayList<Post>();
        LinkedHashMap<Integer, Post> lhm = new LinkedHashMap<Integer, Post>();
        int index = 0;
        while (arg0.hasNext())
        {
            Post p = (Post)arg0.next();
            posts.add(p);
            lhm.put(index, p);
            index++;
        }
        arg1.writeObject(lhm);
    }
}

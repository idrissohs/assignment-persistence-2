package edu.toronto.csc301.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import edu.toronto.csc301.IPost;

public class IPostDeserializer extends JsonDeserializer<Collection<IPost>>
{
    @Override
    public Collection<IPost> deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException
    {
        LinkedList<Post> posts = new LinkedList<Post>();
        Object object = arg0.readValueAs(Object.class);
        @SuppressWarnings("unchecked")
        LinkedHashMap<Integer, Post> mapOfPosts = (LinkedHashMap<Integer, Post>)object;
        for (int x = 0; x < mapOfPosts.size(); x++)
        {
            posts.add(mapOfPosts.get(x));
        }
        Collection<IPost> iposts = new LinkedList<IPost>(posts);
        return iposts;
    }
}

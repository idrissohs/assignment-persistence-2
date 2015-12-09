package edu.toronto.csc301.impl;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class SerializableList extends ArrayList<User>
{
    private static final long serialVersionUID = -6694883943669171734L;
    @JsonBackReference
    private List<User>        posts            = new ArrayList<User>();

    public List<User> getPosts()
    {
        return posts;
    }

    public void setPosts(List<User> posts)
    {
        this.posts = posts;
    }

    public void addNext(User p)
    {
        posts.add(p);
    }
}

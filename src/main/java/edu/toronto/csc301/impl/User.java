package edu.toronto.csc301.impl;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;
public class User implements IUser
{
    private LocalDateTime    registrationTime;
    private Collection<Post> posts;
    private Collection<Post> likes;
    private String           username;
    private String           password;

    public User() throws IOException
    {
        this.posts = new LinkedList<Post>();
        this.likes = new LinkedList<Post>();
        for (int i = 0; i < 3; i++)
        {
            this.posts.add(new Post(this));
        }
        this.registrationTime = LocalDateTime.now();
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.posts = new LinkedList<Post>();
        this.likes = new LinkedList<Post>();
        this.registrationTime = LocalDateTime.now();
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public LocalDateTime getRegistrationTime()
    {
        return registrationTime;
    }

    @Override
    public Iterator<IPost> getPosts()
    {
        LinkedList<IPost> iposts = new LinkedList<>();
        for (Post nextPost : posts)
        {
            iposts.add(nextPost);
        }
        return iposts.iterator();
    }

    @Override
    public Iterator<IPost> getLikes()
    {
        LinkedList<IPost> ilikes = new LinkedList<>();
        for (Post nextPost : likes)
        {
            ilikes.add(nextPost);
        }
        return ilikes.iterator();
    }

    @Override
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public void setRegistrationTime(LocalDateTime registrationTime)
    {
    	this.registrationTime = registrationTime;
    }

    @Override
    public IPost newPost(RenderedImage image, String caption)
    {
    	if(image == null && caption == null) throw new IllegalArgumentException();
    	Post post = null;
        try
        {
            post = new Post();
            post.setImage(image);
            post.setCaption(caption);
            post.setPostedBy(this);
            posts.add(post);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void like(IPost post)
    {
        this.likes.add((Post)post);
        ((Post)post).addNextLike(this);
    }


	@Override
    public void unlike(IPost post)
    {
		for(Post nextLike:likes){
			if(nextLike.getCaption().equals(post.getCaption()))
				likes.remove(nextLike);
		}
		((Post)post).removeNextLike(this);
    }

    public void setUserName(String username)
    {
        this.username = username;

    }

    public void removePosts()
    {
        this.posts.clear();
    }

    public void newPost()
    {
        Post post = null;
        try
        {
            post = new Post(null);
            posts.add(post);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

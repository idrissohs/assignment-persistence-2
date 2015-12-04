package edu.toronto.csc301.impl;


import java.awt.image.RenderedImage;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IPost;

public class User implements IUser {
	
	private String username;
	private String password;
	private LocalDateTime registrationTime;
	private HashSet<IPost> posts = new HashSet<IPost>();
	private HashSet<IPost> likes = new HashSet<IPost>();
	

	public User (String username){
		this.username = username;
		this.registrationTime = LocalDateTime.now();
	}

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

	public LocalDateTime getRegistrationTime(){
		return this.registrationTime;
	}
	public void setRegistrationTime(LocalDateTime registrationTime){
		this.registrationTime=registrationTime;
	}


	// Users can create posts 

	public IPost newPost(RenderedImage image, String caption){
		if (image== null && caption == null){
			throw new IllegalArgumentException ();
		}
		IPost post = new Post ();
		post.setPostedAt(LocalDateTime.now());
		post.setPostedBy(this);
		post.setImage(image);
		post.setCaption(caption);
		this.posts.add(post);
		return post;
	}
	public Iterator<IPost> getPosts(){
		HashSet<IPost> p= new HashSet<IPost>(this.posts);
		return p.iterator();
		
	}


	// Users can like posts

	public void like(IPost post){
		post.addLike(this);
		 this.likes.add(post);
	}
	public void unlike(IPost post){
		post.addLike(this);
		this.likes.remove(post);
	}
	public Iterator<IPost> getLikes(){
		HashSet <IPost> l = new HashSet <IPost>(this.likes);
		return l.iterator();
	}

}

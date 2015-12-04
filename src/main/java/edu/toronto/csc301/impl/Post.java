package edu.toronto.csc301.impl;


import java.awt.image.RenderedImage;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;

public class Post implements IPost {
	
	private RenderedImage profilePic;
	private String caption;
	private IUser user;
	private LocalDateTime time;
	private HashSet<IUser> likes = new HashSet<IUser>();
	

	public RenderedImage getImage(){
		return this.profilePic;
	}
	public void setImage(RenderedImage profilePic){
		this.profilePic=profilePic;
		
	}
	
	public String getCaption(){
		return this.caption;
	}
	public void setCaption(String caption){
		this.caption=caption;
	}
	
	public IUser getPostedBy(){
		return this.user;
	}
	public void setPostedBy(IUser user){
		this.user=user;
	}
	
	public LocalDateTime getPostedAt(){
		return this.time;
	}
	public void setPostedAt(LocalDateTime time){
		this.time=time;
	}
	
	public Iterator<IUser> getLikes(){
		return this.likes.iterator();
	}
	public void addLike(IUser user){
		this.likes.add(user);
		Iterator <IPost> list =user.getLikes();
		IPost p=null;
		while(list.hasNext() || p.equals(this)){
			p= list.next();
			if(p.equals(this)){
				 user.unlike(this);
			}
		}
	}
	public void removeLike(IUser user){
		user.unlike(this);
		this.likes.remove(user);
	}
	
}

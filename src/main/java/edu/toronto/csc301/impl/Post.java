package edu.toronto.csc301.impl;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;
public class Post implements IPost
{
    IUser                    postedBy;
    byte[] imageAsByte;
    public byte[] getImageAsByte() {
		return imageAsByte;
	}

	public void setImageAsByte(byte[] imageAsByte) {
		this.imageAsByte = imageAsByte;
	}
    LocalDateTime            postedAt;
    String                   caption;

    private Collection<IUser> likes;

    public Post(IUser user) throws IOException
    {
        this.postedBy = user;
        // A BufferedImage cannot be serialized by Java's ObjectOutputStream.
        imageAsByte = getImageAsBytes(new BufferedImage(32, 48, BufferedImage.TYPE_INT_RGB));
        this.postedAt = LocalDateTime.now();
        this.likes = new LinkedList<IUser>();
    }

    public Post() throws IOException
    {
        this.likes = new LinkedList<IUser>();
        imageAsByte = getImageAsBytes(new BufferedImage(32, 48, BufferedImage.TYPE_INT_RGB));
        this.postedAt = LocalDateTime.now();
    }

    private byte[] getImageAsBytes(BufferedImage bufferedImage) throws IOException {
         ByteArrayOutputStream byteOut = new ByteArrayOutputStream(bufferedImage.getWidth() * bufferedImage.getHeight() * 4);
         ImageIO.write(bufferedImage, "BMP", byteOut);
         return byteOut.toByteArray();
	}

    @Override
    public RenderedImage getImage()
    {
        return getBufferedImage();
    }

    private RenderedImage getBufferedImage() {
    	try{
    		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageAsByte));
        return img;
    	}catch(Exception e){
    		
    	}
    	return null;
	}

	@Override
    public String getCaption()
    {
        return this.caption;
    }

    @Override
    public IUser getPostedBy()
    {
        return postedBy;
    }

    @Override
    public void setPostedBy(IUser postedBy)
    {
        this.postedBy = postedBy;
    }

    @Override
    public LocalDateTime getPostedAt()
    {
        return postedAt;
    }

    @Override
    public Iterator<IUser> getLikes()
    {
    	return likes.iterator();
    }

    public void setLikes(Collection<IUser> likes)
    {
    	this.likes = likes;
    }

    @Override
    public void setImage(RenderedImage profilePic)
    {
    	if(profilePic == null){
    		imageAsByte = null;
    		return;
    	}
    	try {
    		ByteArrayOutputStream byteOut = new ByteArrayOutputStream(profilePic.getWidth() * profilePic.getHeight() * 4);
			ImageIO.write(profilePic, "BMP", byteOut);
			imageAsByte = byteOut.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    @Override
    public void setPostedAt(LocalDateTime time)
    {
        this.postedAt = time;
    }

    @Override
    public void addLike(IUser user)
    {
        user.like(this);
    }
    public void addNextLike(IUser user){
    	likes.add(user);
    }
    @Override
    public void removeLike(IUser user)
    {
        ((User)user).unlike(this);
    }

	public void clearLikes() {
		this.likes.clear();
		
	}

	public void removeNextLike(User user) {
		for(IUser nextUser:likes){
			if(nextUser.getUsername().equals(user.getUsername()))
				likes.remove(nextUser);
		}
	}
}

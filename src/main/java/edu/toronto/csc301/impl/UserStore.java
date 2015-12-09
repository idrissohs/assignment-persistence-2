package edu.toronto.csc301.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;

public class UserStore implements IUserStore
{
	private Collection<User> users;
	public UserStore(){
		users = new LinkedList<User>();
	}
    @Override
    public IUser createUser(String username, String password) throws Exception
    {
    	if(username == null || password == null) throw new NullPointerException();
        if(username.trim().equals("")) throw new IllegalArgumentException();
        if(!isUnique(username)) throw new IllegalArgumentException();
    	User user = new User(username, password);
        user.setPassword(password);
        user.setUserName(username);
        users.add(user);
        return user;
    }

    private boolean isUnique(String username) {
    	for(User nextUser:users){
        	if(nextUser.getUsername().equals(username))
        		return false;
        }
		return true;
	}
	@Override
    public IUser getUser(String username)
    {
        if(username == null) throw new NullPointerException();
        for(User nextUser:users){
        	if(nextUser.getUsername().equals(username))
        		return nextUser;
        }
        return null;
    }

    @Override
    public Iterator<IUser> getAllUsers()
    {
    	Collection<IUser> iUsers = new LinkedList<IUser>();
        for(User nextUser:users){
        	iUsers.add(nextUser);
        }
    	return iUsers.iterator();
    }

}

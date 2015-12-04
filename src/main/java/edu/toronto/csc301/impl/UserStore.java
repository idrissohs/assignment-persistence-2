package edu.toronto.csc301.impl;


import java.util.HashSet;
import java.util.Iterator;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;


/**
 * A simple DAO.
 */
public class UserStore implements IUserStore {
	
	private HashSet<IUser> users = new HashSet<IUser> ();

	public IUser createUser(String username, String password) throws Exception{
		if(username == null || password == null){
			throw new NullPointerException ();
		}
		if (username.trim().length()==0|| password.trim().length()==0){
			throw new IllegalArgumentException();
		}
		if (getUser(username) != null){
			throw new IllegalArgumentException();
		}
		IUser user = new User (username);
		user.setPassword(password);
		users.add(user);
		return user;
	}
	
	public IUser getUser(String username){
		if(username == null){
			throw new NullPointerException();
		}
		Iterator<IUser> users = this.users.iterator();
		while (users.hasNext()){
			IUser curUser= users.next();
			if (curUser.getUsername()== username){
				return curUser;
			}
		}
		return null;
		
	}
	public Iterator<IUser> getAllUsers(){
		return this.users.iterator();
	}
}

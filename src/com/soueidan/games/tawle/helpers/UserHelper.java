package com.soueidan.games.tawle.helpers;

import java.util.List;

import com.smartfoxserver.v2.entities.User;

public class UserHelper {
	static public List<User> removeUserFromList(List<User> users, User user) {
		User u;
		for(int i=0;i<users.size();i++) {
			u = users.get(i);
			if ( u.getId() == user.getId() ) {
				users.remove(i);
				i--;
			}
		}
		return users;
	}
}

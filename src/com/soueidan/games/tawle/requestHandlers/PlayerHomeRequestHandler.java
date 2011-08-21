package com.soueidan.games.tawle.requestHandlers;

import java.util.List;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.soueidan.games.tawle.helpers.UserHelper;

public class PlayerHomeRequestHandler extends BaseClientRequestHandler {

	static public String PLAYER_IS_HOME = "player_is_home";
	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		
		UserVariable home = new SFSUserVariable("isHome", true);
		home.setHidden(true);
		try {
			user.setVariable(home);
		} catch (SFSVariableException err) {
			trace(err.getMessage());
		}
		
		Room room = getParentExtension().getParentRoom();
		
		List<User> users = UserHelper.removeUserFromList(room.getUserList(), user);
		
		getApi().sendExtensionResponse(PLAYER_IS_HOME, params, users, room, false);
		
	}

}

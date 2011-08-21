package com.soueidan.games.tawle.requestHandlers;

import java.util.List;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.soueidan.games.tawle.helpers.UserHelper;

public class ChipMovedReqestHandler extends BaseClientRequestHandler {

	static public String CHIP_MOVED = "chip_moved";
	
	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		trace("Send movement to other players");
		Room room = getParentExtension().getParentRoom();
		List<User> users = room.getUserList();
		users = UserHelper.removeUserFromList(users, user); 
		
		getApi().sendExtensionResponse(CHIP_MOVED, params, users, room, false);
	}

}

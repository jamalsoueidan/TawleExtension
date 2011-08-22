package com.soueidan.games.tawle.eventHandlers;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

public class PlayerLeaveRoomEventHandler extends BaseServerEventHandler {

	static public String END_GAME = "end_game";
	
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		Room currentRoom = getParentExtension().getParentRoom();
		User user = (User) event.getParameter(SFSEventParam.USER);
		
		trace(user.getName(), "disconnected from game");
		
		
		getApi().sendExtensionResponse(END_GAME, null, currentRoom.getUserList(), currentRoom, false);
	}

}

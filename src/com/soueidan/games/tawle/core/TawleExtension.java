package com.soueidan.games.tawle.core;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.soueidan.games.tawle.eventHandlers.*;
import com.soueidan.games.tawle.requestHandlers.*;

public class TawleExtension extends SFSExtension {
	
	static public int playerTurnId;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		trace("Loading TawleExtension");
		
		addEventHandler(SFSEventType.USER_JOIN_ROOM, PlayerJoinRoomEventHandler.class);
		
		addRequestHandler(ChipMovedReqestHandler.CHIP_MOVED, ChipMovedReqestHandler.class);
		addRequestHandler(PlayerFinishTurnRequestHandler.FINISHED, PlayerFinishTurnRequestHandler.class);
		addRequestHandler(PlayerHomeRequestHandler.PLAYER_IS_HOME, PlayerHomeRequestHandler.class);

		
	}

}

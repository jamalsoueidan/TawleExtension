package com.soueidan.games.tawle.core;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.soueidan.games.tawle.eventHandlers.*;
import com.soueidan.games.tawle.requests.*;

public class TawleExtension extends SFSExtension {
	
	static public int playerTurnId;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		trace("Loading TawleExtension");
		
		addEventHandler(SFSEventType.USER_JOIN_ROOM, PlayerJoinRoomEventHandler.class);
		addEventHandler(SFSEventType.USER_DISCONNECT, PlayerLeaveRoomEventHandler.class);
		
		addRequestHandler(ChipMovedRequest.CHIP_MOVED, ChipMovedRequest.class);
		addRequestHandler(PlayerFinishTurnRequest.FINISHED, PlayerFinishTurnRequest.class);
		addRequestHandler(PlayerIsHomeRequest.PLAYER_IS_HOME, PlayerIsHomeRequest.class);
		addRequestHandler(PlayerIsWinnerRequest.PLAYER_WIN_ROUND, PlayerIsWinnerRequest.class);
		
	}

}

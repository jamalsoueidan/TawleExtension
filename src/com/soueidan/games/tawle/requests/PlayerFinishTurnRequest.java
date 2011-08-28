package com.soueidan.games.tawle.requests;

import com.smartfoxserver.v2.entities.*;
import com.smartfoxserver.v2.entities.data.*;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.soueidan.games.tawle.core.TawleExtension;
import com.soueidan.games.tawle.helpers.DiceHelper;
import com.soueidan.games.tawle.helpers.UserHelper;

public class PlayerFinishTurnRequest extends BaseClientRequestHandler {

	static public String FINISHED = "player_finish_turn";
	static public String NEXT_PLAYER_TURN = "next_player_turn";
	
	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		// TODO Auto-generated method stub
		int playerTurnId = TawleExtension.playerTurnId;
		if ( user.getId() != playerTurnId ) {
			trace(user.getIpAddress(), "is cheating");
		}
		
		Room room = getParentExtension().getParentRoom();
		
		User playerTurn = UserHelper.getNextPlayer(room, playerTurnId);
		
		trace("from ", user.getName(), "next player turn is", playerTurn.getId());
		
		TawleExtension.playerTurnId = playerTurn.getId();
		
		ISFSObject playerData = DiceHelper.generateValues();
		playerData.putInt("turn", TawleExtension.playerTurnId);
		
		getApi().sendExtensionResponse(NEXT_PLAYER_TURN, playerData, room.getUserList(), room, false);
	}
}

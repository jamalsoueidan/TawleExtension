package com.soueidan.games.tawle.requestHandlers;

import java.util.Iterator;
import java.util.List;

import com.smartfoxserver.v2.entities.*;
import com.smartfoxserver.v2.entities.data.*;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.soueidan.games.tawle.core.TawleExtension;
import com.soueidan.games.tawle.helpers.DiceHelper;

public class PlayerFinishTurnRequestHandler extends BaseClientRequestHandler {

	static public String FINISHED = "player_finish_turn";
	static public String NEXT_PLAYER_TURN = "next_player_turn";
	
	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		// TODO Auto-generated method stub
		int playerTurnId = TawleExtension.playerTurnId;
		if ( user.getId() != playerTurnId ) {
			trace(user.getIpAddress(), "is cheating");
		}
		
		User playerTurn = getNextPlayer(playerTurnId);
		trace("next player turn is", playerTurn.getId());
		
		Room room = getParentExtension().getParentRoom();
		
		TawleExtension.playerTurnId = playerTurn.getId();
		
		ISFSObject playerData = DiceHelper.generateValues();
		playerData.putInt("turn", TawleExtension.playerTurnId);
		
		getApi().sendExtensionResponse(NEXT_PLAYER_TURN, playerData, room.getUserList(), room, false);
	}
	
	private User getNextPlayer(int playerTurnId) {
		Room room = getParentExtension().getParentRoom();
		List<User> players = room.getPlayersList();
		Iterator<User> itr = players.iterator();
		User user = null;
		while(itr.hasNext()) {
			user = itr.next();
			if ( playerTurnId != user.getId() ) {
				break;
			}
		}
		
		return user;
	}

}

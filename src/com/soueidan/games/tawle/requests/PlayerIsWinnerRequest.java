package com.soueidan.games.tawle.requests;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.soueidan.games.tawle.core.TawleExtension;

public class PlayerIsWinnerRequest extends BaseClientRequestHandler {

	static public String PLAYER_WIN_GAME = "player_win_game";
	static public String PLAYER_WIN_ROUND = "player_win_round";
	
	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		int playerTurnId = TawleExtension.playerTurnId;
		if ( user.getId() != playerTurnId ) {
			trace(user.getIpAddress(), "is cheating, it's not his play and he are winning");
		}
		
		if ( !user.getVariable("isHome").getBoolValue() ) {
			trace(user.getIpAddress(), "is cheating, he is not home, how can he be a winner");	
		}
		
		UserVariable score = user.getVariable("score");
		int total = score.getIntValue() + 1;
		
		score = new SFSUserVariable("score", total);
		try {
			user.setVariable(score);
		} catch (SFSVariableException err){
			trace(err.getMessage());
		}
		
		String action;
		if ( total >= 5 ) {
			trace("player won 5 times");
			action = PLAYER_WIN_GAME;
		} else {
			trace("player won the round");
			action = PLAYER_WIN_ROUND;
		}
		
		Room room = getParentExtension().getParentRoom();
		getApi().sendExtensionResponse(action, params, room.getUserList(), room, false);
		
	}

}

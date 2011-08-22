package com.soueidan.games.tawle.eventHandlers;

import java.util.Random;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.*;
import com.smartfoxserver.v2.entities.data.*;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.soueidan.games.tawle.core.TawleExtension;
import com.soueidan.games.tawle.helpers.DiceHelper;

public class PlayerJoinRoomEventHandler extends BaseServerEventHandler {

	static public String START_GAME = "start_game";
	
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		Room currentRoom = getParentExtension().getParentRoom();
		User user = (User) event.getParameter(SFSEventParam.USER);
		
		int totalUser = currentRoom.getSize().getTotalUsers();
		
		trace("Total users in the room:", totalUser);
		if ( totalUser == 1 || totalUser == 2 ) {
			setUserVariables(currentRoom, user);
		}
		
		if ( totalUser >= 2 ) {
			Random generator = new Random();
			int randomTurn = generator.nextInt(1) + 0;
			trace("RandomTurn", randomTurn);
			
			User playerTurn = currentRoom.getUserList().get(randomTurn);
			trace("playerTurn", playerTurn.getName());
			
			TawleExtension.playerTurnId = playerTurn.getId();
			
			ISFSObject playerData = DiceHelper.generateValues();
			playerData.putInt("turn", TawleExtension.playerTurnId);
			
			getApi().sendExtensionResponse(START_GAME, playerData, currentRoom.getUserList(), currentRoom, false);
		}

	}

	private void setUserVariables(Room room, User user) {
		trace("setUserVariables");
		
		user.isPlayer(room);
		
		UserVariable score = new SFSUserVariable("score", 0);
		score.setHidden(false);
		
		try {
			user.setVariable(score);
		} catch (SFSVariableException err) {
			trace(err.getMessage());
		}
	}

}

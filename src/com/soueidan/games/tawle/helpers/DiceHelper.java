package com.soueidan.games.tawle.helpers;

import java.util.Random;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class DiceHelper {
	
	static private int leftValue;
	static private int rightValue;
	
	static public ISFSObject generateValues() {
		Random generator = new Random();
		leftValue = generator.nextInt(6) + 1;
		rightValue = generator.nextInt(6) + 1;
		
		ISFSObject dice = new SFSObject();
		dice.putInt("leftValue", leftValue);
		dice.putInt("rightValue", rightValue);
		
		return dice;
	}
}

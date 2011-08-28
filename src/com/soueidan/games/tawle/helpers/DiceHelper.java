package com.soueidan.games.tawle.helpers;

import java.util.Random;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class DiceHelper {
	
	static private int leftValue;
	static private int rightValue;
	
	static public ISFSObject generateValues() {
		ISFSObject dice = new SFSObject();
		return assignValues(dice);
	}
	
	static public ISFSObject assignValues(ISFSObject params) {
		leftValue = generateValue();
		rightValue = generateValue();
		
		params.putInt("leftValue", leftValue);
		params.putInt("rightValue", rightValue);
		
		return params;
	}
	
	static public int generateValue() {
		Random generator = new Random();
		return generator.nextInt(6) + 1;
	}
}

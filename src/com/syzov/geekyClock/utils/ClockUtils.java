package com.syzov.geekyClock.utils;

import java.util.Arrays;
import java.util.Calendar;

public class ClockUtils {
	
	static public boolean [][] getLedsByTime(Calendar time){
		boolean[][] result;
		result = new boolean[3][10];
		
		short firstHourDigit = (short) Math.floor(time.get(Calendar.HOUR_OF_DAY)/10); 		
		boolean[][] firstHourDigitAsArray = getShortLedsByDigit(firstHourDigit);
		
		result[0][0] = firstHourDigitAsArray[0][0];
		result[1][0] = firstHourDigitAsArray[1][0];
		result[2][0] = firstHourDigitAsArray[2][0];
		
		short secondHourDigit = (short) (time.get(Calendar.HOUR_OF_DAY) % 10);
		boolean[][] secondHourDigitAsArray = getLedsByDigit(secondHourDigit);
		
		result[0][1] = secondHourDigitAsArray[0][0];
		result[1][1] = secondHourDigitAsArray[1][0];
		result[2][1] = secondHourDigitAsArray[2][0];
		
		result[0][2] = secondHourDigitAsArray[0][1];
		result[1][2] = secondHourDigitAsArray[1][1];
		result[2][2] = secondHourDigitAsArray[2][1];
		
		result[0][3] = secondHourDigitAsArray[0][2];
		result[1][3] = secondHourDigitAsArray[1][2];
		result[2][3] = secondHourDigitAsArray[2][2];
		
		short firstMinutesDigit = (short) Math.floor(time.get(Calendar.MINUTE)/10);
		boolean[][] firstMinutesDigitAsArray = getLedsByDigit(firstMinutesDigit);
		
		result[0][4] = firstMinutesDigitAsArray[0][0];
		result[1][4] = firstMinutesDigitAsArray[1][0];
		result[2][4] = firstMinutesDigitAsArray[2][0];
		
		result[0][5] = firstMinutesDigitAsArray[0][1];
		result[1][5] = firstMinutesDigitAsArray[1][1];
		result[2][5] = firstMinutesDigitAsArray[2][1];
		
		result[0][6] = firstMinutesDigitAsArray[0][2];
		result[1][6] = firstMinutesDigitAsArray[1][2];
		result[2][6] = firstMinutesDigitAsArray[2][2];
		
		short secondMinutesDigit = (short) (time.get(Calendar.MINUTE) % 10);
		boolean[][] secondMinutesDigitAsArray = getLedsByDigit(secondMinutesDigit);
		
		result[0][7] = secondMinutesDigitAsArray[0][0];
		result[1][7] = secondMinutesDigitAsArray[1][0];
		result[2][7] = secondMinutesDigitAsArray[2][0];
		
		result[0][8] = secondMinutesDigitAsArray[0][1];
		result[1][8] = secondMinutesDigitAsArray[1][1];
		result[2][8] = secondMinutesDigitAsArray[2][1];
		
		result[0][9] = secondMinutesDigitAsArray[0][2];
		result[1][9] = secondMinutesDigitAsArray[1][2];
		result[2][9] = secondMinutesDigitAsArray[2][2];
		
		System.out.println(Arrays.toString(result));
		
		return result;
	}
	
	/** This method returns 3*1 array of true/false booleans
	 * that show which LEDs supposed to be on/off for particular short (0,1,2) digit. */
	static private boolean [][] getShortLedsByDigit(short digit){
		boolean[][] result;
		
		switch (digit){
		case 0:			
			result = SHORT_DIGIT_0;
			break;
		case 1:
			result = SHORT_DIGIT_1;
			break;
		case 2:
			result = SHORT_DIGIT_2;
			break;
		default:
			result = null;
			break;
		}
		
		return result;		
	}
	
	/** This method returns 3*3 array of true/false booleans
	 * that show which LEDs supposed to be on/off for particular digit. */
	static private boolean [][] getLedsByDigit(short digit){
		boolean[][] result;
		
		switch (digit){
		case 0:			
			result = DIGIT_0;
			break;
		case 1:
			result = DIGIT_1;
			break;
		case 2:
			result = DIGIT_2;
			break;
		case 3:
			result = DIGIT_3;
			break;
		case 4:
			result = DIGIT_4;
			break;
		case 5:
			result = DIGIT_5;
			break;
		case 6:
			result = DIGIT_6;
			break;
		case 7:
			result = DIGIT_7;
			break;
		case 8:
			result = DIGIT_8;
			break;
		case 9:
			result = DIGIT_9;
			break;
		default:
			result = null;
			break;
		}
		
		return result;		
	}
	
	static final public boolean[][] SHORT_DIGIT_0 = {
														{false},
														{false},
														{false}
													};
	
	static final public boolean[][] SHORT_DIGIT_1 = {
														{false},
														{true},
														{false}
													};
	
	static final public boolean[][] SHORT_DIGIT_2 = {
														{true},
														{false},
														{true}
													};

	
	static final public boolean[][] DIGIT_0 = {
												{false, false, false},
												{false, false, false},
												{false, false, false}
											};
	
	static final public boolean[][] DIGIT_1 = {
												{false, false, false},
												{false, true, false},
												{false, false, false}
											}; 
	
	static final public boolean[][] DIGIT_2 = {
												{false, true, false},
												{false, false, false},
												{false, true, false}
											}; 

	static final public boolean[][] DIGIT_3 = {
												{false, true, false},
												{false, true, false},
												{false, true, false}
											}; 

	static final public boolean[][] DIGIT_4 = {
												{true, false, true},
												{false, false, false},
												{true, false, true}
											}; 

	static final public boolean[][] DIGIT_5 = {
												{true, false, true},
												{false, true, false},
												{true, false, true}
											}; 

	static final public boolean[][] DIGIT_6 = {
												{true, true, false},
												{true, true, false},
												{true, true, false}
											}; 

	static final public boolean[][] DIGIT_7 = {
												{true, true, false},
												{true, true, true},
												{true, true, false}
											}; 

	static final public boolean[][] DIGIT_8 = {
												{true, true, true},
												{true, false, true},
												{true, true, true}
											}; 

	static final public boolean[][] DIGIT_9 = {
												{true, true, true},
												{true, true, true},
												{true, true, true}
											}; 

}

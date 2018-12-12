/**create by liuhua at 2018年1月2日 上午11:02:09**/
package com.booting.common;

public class Temp {
	private double min;
	private double max;
	private int score;
	
	public Temp( double min, double max, int score){
		this.max = max;
		this.min = min;
		this.score = score;
	}
	
	public int getScore(double value){
		boolean bool = true;
		if (min > max) {
			bool = false;
//			double t = max;
//			max = min;
//			min = t;
		}
		if (bool && min <= value && value < max) {
			return score;
		}
		if (!bool && max < value && value <= min) {
			return score;
		}
		return 0;
	}
}

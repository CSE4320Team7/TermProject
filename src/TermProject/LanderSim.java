package TermProject;

import java.util.Random;
import java.awt.Color;
import java.awt.color.*;

public class LanderSim {
	
	private static LanderDisplay display;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			display = new LanderDisplay();
			display.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		start();
	}
	
	/**
	 * Begins the simulation. No controls are currently added
	 */
	private static void start() {
		
		Random rand = new Random();
		
		//Starting variables
		double G = 12.2; //feet/second^2
		int time = 0;
		
		double alt = 14318; //feet
		double vel = -366.667; //feet/second
		double fuel = 135; //gallons
		int landerAtt = display.getLanderAttitude();
		int groundAtt = rand.nextInt(21) - 10;
		int damage = 0;
		
		//TODO: Start in Up position when switch is working
		boolean podpos = false; //Up = false, Down = true

		//a = alarm, i = indicator, w = warning
		//Fuel <20, Pod OverSpeed, Ideal Pod Down Zone, Lander Crashed, 
		//Landed on Surface, Pod Cmd, Pos position
		boolean aFLT20 = false, aPOS = false, iIPDZ = false, aPDMG = false,
				wEPD = false, aLC = false, iLOS = false, iPODCMD = false, iPODPOS = false;
		
		//Run program until fuel is empty?
		while(true) {
			
			String MPD = "";
			double cGPS = 1;
			double cRate = 1;
			
			//Motor Programs
			if((6500 <= alt && alt < 15000) && fuel >= 1.25) {
				MPD = "RR1";
				cGPS = 1.25;
				cRate = 17;
			} else if((3500 <= alt && alt < 6500) && fuel >= 1.65) {
				MPD = "RR2";
				cGPS = 1.65;
				cRate = 19;
			} else if((0 < alt && alt < 3500) && fuel >= 1.8) {
				MPD = "RR3";
				cGPS = 1.8;
				cRate = 13.7;
			} else {
				MPD = "OFF";
				cGPS = 0.01;
				cRate = 0.01;
			}
			
			if(damage >= 20 && alt < 0) {
				int crash = rand.nextInt(10) + 1; //generate number between 1-10
				if(crash > 7 || landerAtt != groundAtt) { //if greater than 7 (30% chance)
					iLOS = true;
				} else {
					aLC = true;
				}
			} else if(alt < 0)
				iLOS = true;

			
			//Fuel 20 second alarm
			if(fuel/cGPS < 20) {
				aFLT20 = true;
			} else {
				aFLT20 = false;
			}
			
			//Damage Pod and Speed Alarms
			//Pod damage if vel over 100 for 20 seconds
			if(vel < -100 && podpos == true) {
				damage = damage + 1;
				aPOS = true;
			} else {
				aPOS = false;
			}
			if( damage >= 40 ) {
				aPDMG = true;
			} else {
				aPDMG = false;
			}
			
			//Emergency Pod Deploy Warning
			if(vel < -50) { 
				wEPD = true;
			} else
				wEPD = false;

			//Pod Position COmmand INdicator
			if(podpos == false & (display.getButtonStatus() == false | wEPD)) {//display.getPodCmd() == true) {
				iPODCMD = true; //DOWN
			} else if(podpos == true & (display.getButtonStatus() == true | !wEPD)) {//display.getPodCmd() == true) {
				iPODCMD = false; //UP
			}
				
			//Ideal Pod Down Zone Indicator
			if(-50 > vel && vel >= -100) {
				if(alt >- 0)
					if(podpos != true) {
						iIPDZ = true;
					} else
						iIPDZ = false;
			}
			
			time = time + 1;
			//calculate if pod is down(true)
			if(podpos == true) {
				fuel = fuel - cGPS;
				alt = alt - (-1 * vel);
				vel = vel + (cRate - G);
			} else {
				vel = vel + (cRate - G);
				alt = alt - ( (time-0.5) * (cRate-G) );
			}
			
			//aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS
			try {
				Thread.sleep(500);
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, podpos, MPD, fuel, alt, vel, landerAtt, groundAtt);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			
			//iPODPOS
			if(iPODCMD) {
				iPODPOS = true;
				podpos = true;
			} else {
				iPODPOS = false;
				podpos = false;
			}
			
			if(aLC == true || iLOS == true)
				break;
		}
		
	}
	
	/**
	 * Changes lighting and info display panels
	 * 
	 * @param aFLT20 Fuel Less Than 20, red if true
	 * @param aPOS Pod Over Speed, red if true
	 * @param iIPDZ Ideal Pod Down Zone, green if true
	 * @param aPDMG Pod Damage, red if true
	 * @param wEPD Emergency Pod Deploy, yellow if true
	 * @param aLC Lander Crashed, red if true
	 * @param iLOS Landed On Surface, green if true
	 * @param iPODCMD Pod Command, green if true
	 * @param iPODPOS Pod Position, green if true
	 * @param time the amount of time passed
	 * @param MPD Motor Display Program as a String
	 * @param fuel remaining fuel as double
	 * @param alt current altitude as double
	 * @param vel current velocity as double
	 * @param lAtt
	 * @param gAtt
	 */
	private static void LanderDisplay(boolean aFLT20, boolean aPOS, boolean iIPDZ, boolean aPDMG, 
			boolean wEPD, boolean aLC, boolean iLOS, boolean iPODCMD, boolean iPODPOS, 
			int time, boolean podpos, String MPD, double fuel, double alt, double vel, int lAtt, int gAtt) {
		
		//This would be the output panel showing alt, vel, fuel
		//System.out.printf("\nAt time %d\n   MPD: %s, Alt: %.3f, Vel: %.3f, Fuel: %.3f", time, MPD, alt, vel, fuel);
		
		display.textField_24.setText(String.format("%.3f", alt));
		display.textField_25.setText(String.format("%.3f", vel));
		display.textField_26.setText(MPD);
		if(podpos)
			display.textField_27.setText("UP");
		else
			display.textField_27.setText("DOWN");
		display.textField_28.setText(String.format("%.3f", fuel));
		display.textField_29.setText("" + lAtt);
		display.textField_30.setText("" + gAtt);
		
		//The following 3 sections would be the indicator panel
		if(aFLT20)
			//TODO:change color to green
			display.textFieldA1.setForeground(Color.RED);
		else
			display.textFieldA1.setForeground(Color.BLACK); //TODO:change color to white
		if(aPOS)
			display.textFieldB1.setForeground(Color.RED);
		else
			display.textFieldB1.setForeground(Color.BLACK);
		if(aPDMG)
			display.textFieldC1.setForeground(Color.RED);
		else
			display.textFieldC1.setForeground(Color.BLACK);
		if(aLC)
			display.textFieldD1.setForeground(Color.RED);
		else
			display.textFieldD1.setForeground(Color.BLACK);
		
		if(wEPD)
			display.textFieldA2.setForeground(Color.ORANGE);
		else
			display.textFieldA2.setForeground(Color.BLACK);
		
		if(iIPDZ)
			display.textFieldA3.setForeground(Color.GREEN);
		else
			display.textFieldA3.setForeground(Color.BLACK);
		if(iLOS)
			display.textFieldB3.setForeground(Color.GREEN);
		else
			display.textFieldB3.setForeground(Color.BLACK);
		if(iPODCMD)
			display.textFieldC3.setForeground(Color.GREEN);
		else
			display.textFieldC3.setForeground(Color.BLACK);
		if(iPODPOS)
			display.textFieldD3.setForeground(Color.GREEN);
		else
			display.textFieldD3.setForeground(Color.BLACK);
		

	}

}

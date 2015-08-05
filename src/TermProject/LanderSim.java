package TermProject;

import java.util.Random;

import java.awt.Color;
import java.awt.color.*;

public class LanderSim {
	
	private static LanderDisplay display;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startDisplay();
		start();
	}
	
	private static void startDisplay() {
		try{
			display = new LanderDisplay();
			display.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		int groundAtt = rand.nextInt(21) - 10;
		int landerAtt = 0;
		int damage = 0;
		
		//Start in Up position when switch is working
		boolean podSwitch = false, iPODCMD = false, iPODPOS = false; //Up = false, Down = true

		//Run program until lander detects its done
		while(true) {
			
			//a = alarm, i = indicator, w = warning
			//Fuel <20, Pod OverSpeed, Ideal Pod Down Zone, Lander Crashed, 
			//Landed on Surface, Pod Cmd, Pos position
			boolean aFLT20 = false, aPOS = false, iIPDZ = false, aPDMG = false,
					wEPD = false, aLC = false, iLOS = false;
			
			landerAtt = display.getLanderAttitude();
			
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
				cGPS = 0.000001;
				cRate = 0;
			}
			
			//Checking if altitude is on ground
			if(alt < 0) {
				//if pod is down (true) and attitudes are same
				if(iPODPOS && landerAtt == groundAtt) {
					//check to see if lander is damaged
					if(aPDMG) { //if pod is damaged
						int crash = rand.nextInt(10); //generate number between 0-9
						if(crash < 3) {//if less than 3 (30% chance)
							iLOS = true;
							aLC = false;
						} else {
							iLOS = false;
							aLC = true;
						}
					} else {
						iLOS = true;
						aLC = false;
					}
				} else {
					aLC = true;
					iLOS = false;
				}
			}

			
			//Fuel 20 second alarm
			if(fuel/cGPS < 20) {
				aFLT20 = true;
			} else {
				aFLT20 = false;
			}
			
			//Damage Pod and Speed Alarms
			if(vel < -100 && podSwitch == true) {
				damage = damage + 1;
				aPOS = true;
			} else {
				aPOS = false;
			}
			
			//PDMG if vel over 100 for 40 seconds
			if( damage >= 40 ) {
				aPDMG = true;
			} else {
				aPDMG = false;
			}
			
			//Emergency Pod Deploy Warning
			if(0 <= Math.abs(vel) && Math.abs(vel) < 50) { 
				wEPD = true;
			} else {
				wEPD = false;
			}
			
			podSwitch = display.getButtonStatus();
			iPODPOS = iPODCMD;

			//Pod Position COmmand INdicator
			if(wEPD) {
				iPODCMD = true; //DOWN
			} else {
				iPODCMD = podSwitch;
			}
				
			//Ideal Pod Down Zone Indicator
			if(-50 > vel && vel >= -100) {
				if(alt >= 0)
					if(podSwitch != true) {
						iIPDZ = true;
					} else
						iIPDZ = false;
			}

			//If we're landed or crashed, leave loop
			if(aLC == true || iLOS == true) {
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, podSwitch, MPD, fuel, alt, vel, landerAtt, groundAtt);
				break;
			}
			
			//calculate stuffS
			{
				time = time + 1;
				alt -= (time-0.5)*(cRate-G);
				vel += (cRate - G);
				fuel -= cGPS;
			}
			
			
			//aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS
			try {
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, iPODPOS, MPD, fuel, alt, vel, landerAtt, groundAtt);
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			
			/**pod buttons are calculated for the next second.
			podSwitch = display.getButtonStatus();
			
			//iPODPOS
			if(iPODCMD) {
				iPODPOS = true;
				podSwitch = true;
			} else {
				iPODPOS = podSwitch;
			}*/
			
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
		
		display.textFieldAltitude.setText(String.format("%.3f", alt));
		display.textFieldVelocity.setText(String.format("%.3f", vel));
		display.textFieldMotorProgram.setText(MPD);
		if(!podpos)
			display.textFieldPodPosition.setText("UP");
		else
			display.textFieldPodPosition.setText("DOWN");
		display.textFieldFuelRemaining.setText(String.format("%.3f", fuel));
		display.textFieldAttitude.setText("" + lAtt);
		display.textFieldGroundAttitude.setText("" + gAtt);
		
		//The following 3 sections would be the indicator panel
		if(aFLT20)
			//TODO:change color to green
			display.textFieldA1.setForeground(Color.RED);
		else
		if(aPOS)
		else
		if(aPDMG)
		else
		if(aLC)
		else
		
		if(wEPD)
		else
		
		if(iIPDZ)
		else
		if(iLOS)
		else

	}

}

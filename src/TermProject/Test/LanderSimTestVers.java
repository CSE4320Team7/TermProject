package TermProject.Test;

import java.util.ArrayList;
import java.util.Random;

import TermProject.Data.TestDataCSVReader;

import java.awt.Color;
import java.awt.color.*;

public class LanderSimTestVers {
	
	private static TermProject.LanderDisplay display;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startDisplay();
		new LanderSimTestVers();
	}

	private static void startDisplay() {
		try {
			display = new TermProject.LanderDisplay();
			display.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Begins the simulation. No controls are currently added
	 * @return 
	 */
	public LanderSimTestVers() {
		TestDataCSVReader reader = new TestDataCSVReader("Mars lander Test Case table.csv");
		ArrayList<TestDataCSVReader.DataItem> data = reader.getData();
		
		Random rand = new Random();
		//Starting variables
		double G = 12.2; //feet/second^2
		int time = 0;
		
		//TODO: Start in Up position when switch is working
		boolean podSwitch = display.getButtonStatus(); //Up = false, Down = true

		//a = alarm, i = indicator, w = warning
		//Fuel <20, Pod OverSpeed, Ideal Pod Down Zone, Lander Crashed, 
		//Landed on Surface, Pod Cmd, Pos position
		
		//Run program until test cases are exhausted.
		while(!data.isEmpty()){
			//iPODCMD and iPODPOS moved to inside loop
			boolean aFLT20 = false, aPOS = false, iIPDZ = false, aPDMG = false,
					wEPD = false, aLC = false, iLOS = false, iPODCMD = false, iPODPOS = false;
			
			//This is a section for test data.
			TestDataCSVReader.DataItem testData = data.remove(0);
				
			if(testData.strInputPODPOS.equalsIgnoreCase("u"))
				display.setButton(false);
			else
				display.setButton(true);
				
			podSwitch = display.getButtonStatus();
				
			double alt = testData.dblInputAltitude; //feet
			double vel = testData.dblInputVelocity; //feet/second
			double fuel = testData.dblInputFuel; //gallons
			int groundAtt = 0; //rand.nextInt(21) - 10;
			int landerAtt = display.getLanderAttitude();
			int damage = testData.intInputPodDamageCount;
			//End of section.
			
			String MPD = "";
			double cGPS = 1;
			double cRate = 1;
			
			//Motor Programs
			if((6500 <= alt && alt < 15000)) {
				MPD = "RR1";
				cGPS = 1.25;
				cRate = 17;
			} else if(3500 <= alt) {
				MPD = "RR2";
				cGPS = 1.65;
				cRate = 19;
			} else if(0 <= alt) {
				MPD = "RR3";
				cGPS = 1.8;
				cRate = 13.7;
			} else {
				MPD = "OFF";
				cGPS = 0.000001;
				cRate = 0;
			}
			
			if(alt >= 0) {
				time = 1;//time + 1;
				alt -= (time-0.5)*(cRate-G);
				vel += (cRate - G);
				fuel -= cGPS;
			}

			
			//Fuel 20 second alarm
			if(fuel/cGPS < 20) {
				aFLT20 = true;
			} else {
				aFLT20 = false;
			}
			
			//Damage Pod and Speed Alarms
			//Pod damage if vel over 100 for 40 seconds
			if(vel < -100 && podSwitch == true) {
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
			if(vel >= -50 && alt >= 0) { 
				wEPD = true;
			} else {
				wEPD = false;
			}
			
			podSwitch = display.getButtonStatus();

			//Pod Position COmmand INdicator
			if(wEPD) {
				iPODCMD = true; //DOWN
			} else {
				iPODCMD = podSwitch;
			}
			
			iPODPOS = iPODCMD;
				
			//Ideal Pod Down Zone Indicator
			if(alt >= 0) {
				if(-50 > vel && vel >= -100)
					if(podSwitch != true)
						iIPDZ = true;
					else
						iIPDZ = false;
			}

			
			//Checking if altitude is on ground
			if(alt < 0) {
				//if pod is down (true) and attitudes are within 5 degrees of each other
				if(iPODPOS && (landerAtt >= groundAtt-5 && landerAtt <= groundAtt+5)) {
					//check to see if lander is damaged
					if(aPDMG) { //if pod is damaged
						double crash = testData.dblInputRNG;//rand.nextInt(10) + 1; //generate number between 1-10
						if(crash < .3) {//if greater than 3 (30% chance)
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
			
			//If we're landed or crashed, leave loop
			if(aLC == true || iLOS == true) {
				try {
					LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
							time, iPODPOS, MPD, fuel, alt, vel, landerAtt, groundAtt);
					Thread.sleep(1000);
					continue;
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			
			
			//aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS
			try {
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, iPODPOS, MPD, fuel, alt, vel, landerAtt, groundAtt);
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
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
		if(iPODPOS) {
			display.textFieldC3.setText("PODPOS: DOWN");
			display.textFieldC3.setForeground(Color.GREEN);
		} else {
			display.textFieldC3.setText("PODPOS: UP");
			display.textFieldC3.setForeground(Color.BLACK);
		}
		if(iPODCMD) {
			display.textFieldD3.setText("PODCMD: DOWN");
			display.textFieldD3.setForeground(Color.GREEN);
		} else {
			display.textFieldD3.setText("PODCMD: UP");
			display.textFieldD3.setForeground(Color.BLACK);
		}

	}

}

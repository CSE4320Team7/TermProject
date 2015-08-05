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
		boolean podpos = display.getButtonStatus(); //Up = false, Down = true

		//a = alarm, i = indicator, w = warning
		//Fuel <20, Pod OverSpeed, Ideal Pod Down Zone, Lander Crashed, 
		//Landed on Surface, Pod Cmd, Pos position
		
		//Run program until fuel is empty?
		while(!data.isEmpty()){
			boolean aFLT20 = false, aPOS = false, iIPDZ = false, aPDMG = false,
					wEPD = false, aLC = false, iLOS = false, iPODCMD = false, iPODPOS = false;
			TestDataCSVReader.DataItem testData = data.remove(0);
			
			if(testData.strInputPODPOS.equalsIgnoreCase("u"))
				display.setButton(false);
			else
				display.setButton(true);
			
			podpos = display.getButtonStatus();
			
			double alt = testData.dblInputAltitude; //feet
			double vel = testData.dblInputVelocity; //feet/second
			double fuel = testData.dblInputFuel; //gallons
			int groundAtt = 0; //rand.nextInt(21) - 10;
			int landerAtt = display.getLanderAttitude();
			int damage = testData.intInputPodDamageCount;
			
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
				if(podpos && landerAtt == groundAtt) {
					//check to see if lander is damaged
					if(aPDMG) { //if pod is damaged
						double crash = testData.dblInputRNG;//rand.nextInt(10) + 1; //generate number between 1-10
						if(crash > 7) {//if greater than 7 (30% chance)
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
			//Pod damage if vel over 100 for 20 seconds
			if(vel < -100 && podpos == true) {
				//damage = damage + 1;
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
			if(Math.abs(vel) > 50) { 
				wEPD = true;
			} else
				wEPD = false;

			//Pod Position COmmand INdicator
			if(wEPD) {
				iPODCMD = true; //DOWN
			} else {
				iPODCMD = display.getButtonStatus();
			}
				
			//Ideal Pod Down Zone Indicator
			if(-50 > vel && vel >= -100) {
				if(alt >= 0)
					if(podpos != true)
						iIPDZ = true;
					else
						iIPDZ = false;
			}
			
			//iPODPOS
			if(iPODCMD) {
				iPODPOS = true;
				podpos = true;
			} else {
				iPODPOS = false;
				podpos = false;
			}

			//If we're landed or crashed, leave loop
			if(aLC == true || iLOS == true) {
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, podpos, MPD, fuel, alt, vel, landerAtt, groundAtt);
				continue;
			}
			
			//calculate stuff if pod is down(true)
			{
				time = 1;//time + 1;
				alt -= (time-0.5)*(cRate-G);
				vel += (cRate - G);
				fuel -= cGPS;
			}
			
			
			//aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS
			try {
				LanderDisplay(aFLT20, aPOS, iIPDZ, aPDMG, wEPD, aLC, iLOS, iPODCMD, iPODPOS,
						time, podpos, MPD, fuel, alt, vel, landerAtt, groundAtt);
				Thread.sleep(200);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			
			/**
			boolean testFLT = aFLT20 == testData.boolOutputFLT20;
			boolean testPOS = aPOS == testData.boolOutputPOS;
			boolean testIPDZ = iIPDZ == testData.boolOutputIPDZ;
			boolean testPDMG = aPDMG == testData.boolOutputPDMG;
			boolean testEPD = wEPD == testData.boolOutputEPD;
			
			System.out.println("\tflt " + testFLT + " pos" + testPOS + " ipdz" + testIPDZ + " dmg" + testPDMG + " epd " + testEPD);
			
			boolean test1 = (alt == testData.dblOutputAltitude), test2 = (vel == testData.dblOutputVelocity), test3 = (testData.strOutputPODPOS.equalsIgnoreCase("d") == podpos);
			
			System.out.println("\t Alt " + test1 + " Vel " + test2 + " PODPOS " + test3);
			*/
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
		if(!podpos)
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

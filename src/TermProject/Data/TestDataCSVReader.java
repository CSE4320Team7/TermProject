package TermProject.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


//How to use: Step 1, create class using filename/filepath as parameter.
//Step 2, access cases via method.attribute. Two ways to do that.
//GetCase, SetCase, and NextCase let you use an iterator I built into the class, but that's optional.
//If that's too much of a hassle and you want to do the iterator yourself, just use GetGivenCase(casenumber).attribute.
public class TestDataCSVReader {

	//This was to test the class independently.
/*	public static void main(String[] args){
		TestDataCSVReader asdf = new TestDataCSVReader("Mars lander Test Case table.csv");
		System.out.println("Done!");
	}*/
	
	
	
	public class DataItem{
		public int intTestCaseNumber;
		public int intInputCombination;
		public String strDisplayScenario;
		public double dblInputAltitude;
		public String strInputPODPOS;
		public double dblInputVelocity;
		public double dblInputFuel;
		public double dblInputRNG;
		//Always 0, why bother for test?
		//public int intInputAttitudeCmd;
		//public int intInputCumulativeAttitude;
		//public int intInputGroundAttitude;
		public int intInputPodDamageCount;
		
		public boolean boolOutputFLT20;
		public boolean boolOutputPOS;
		public boolean boolOutputIPDZ;
		public boolean boolOutputPDMG;
		public boolean boolOutputEPD;
		public boolean boolOutputLC;
		public boolean boolOutputLOS;
		public String strOutputMotorProgram;
		public double dblOutputAltitude;
		public double dblOutputVelocity;
		public double dblOutputFuel;
		public String strOutputPODPOS;
		public String strOutputPODCMD;
		//public String strOutputAttitude;
		public String strOutputPodCommand;
		
		
		//This is where a line of the given CSV is parsed out into the data structure.
		public DataItem(String[] arrstrData){
			intTestCaseNumber=Integer.parseInt(arrstrData[0]);
			intInputCombination=Integer.parseInt(arrstrData[1]);
			strDisplayScenario=arrstrData[2];
			dblInputAltitude=Double.parseDouble(arrstrData[3]);
			strInputPODPOS=arrstrData[4];
			dblInputVelocity=Double.parseDouble(arrstrData[5]);
			dblInputFuel=Double.parseDouble(arrstrData[6]);
			dblInputRNG=Double.parseDouble(arrstrData[7]);
			//Attitude stuff, which is always 0, could go here but would waste computer time.
			intInputPodDamageCount=Integer.parseInt(arrstrData[11]);
			
			if(arrstrData[12].equals("X")){
				boolOutputFLT20=true;
			}
			else{
				boolOutputFLT20=false;
			}
			
			if(arrstrData[13].equals("X")){
				boolOutputPOS=true;
			}
			else{
				boolOutputPOS=false;
			}
			
			if(arrstrData[14].equals("X")){
				boolOutputIPDZ=true;
			}
			else{
				boolOutputIPDZ=false;
			}
			
			if(arrstrData[15].equals("X")){
				boolOutputPDMG=true;
			}
			else{
				boolOutputPDMG=false;
			}
			
			if(arrstrData[16].equals("X")){
				boolOutputEPD=true;
			}
			else{
				boolOutputEPD=false;
			}
			
			if(arrstrData[17].equals("X")){
				boolOutputLC=true;
			}
			else{
				boolOutputLC=false;
			}
			
			if(arrstrData[18].equals("X")){
				boolOutputLOS=true;
			}
			else{
				boolOutputLOS=false;
			}
			
			strOutputMotorProgram=arrstrData[19];
			dblOutputAltitude=Double.parseDouble(arrstrData[20]);
			dblOutputVelocity=Double.parseDouble(arrstrData[21]);
			dblOutputFuel=Double.parseDouble(arrstrData[22]);
			strOutputPODPOS=arrstrData[23];
			strOutputPODCMD=arrstrData[24];
			strOutputPodCommand=arrstrData[26];
		}
		
	}
	
	BufferedReader CSVFile=null;
	int intActiveDataItem=1;
	int intLastDataItem;
	ArrayList<DataItem> TestData= new ArrayList<DataItem>();
	
	public TestDataCSVReader(String strFilename){
		String untokenStr="";
		
		intActiveDataItem=1;
		TestData=new ArrayList<DataItem>();
		
		
		try{
            CSVFile = new BufferedReader(new FileReader(strFilename));
        }
		catch(FileNotFoundException FNFE){
            System.out.println("Incorrect filename given or no file found, terminating program.");
            System.exit(0); //Fuck this, we're out!
        }
		
		try {
            untokenStr=CSVFile.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TestDataCSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		while(untokenStr!=null && untokenStr.length()!=0){
			
			String[] tokenedStr = untokenStr.split(",");
			//Here, we populate each line of the data.
			//This, like much of this, is a hack that will only work for this application.
			if(tokenedStr.length>0 && tokenedStr[0].length()<4 && tokenedStr[0].length()>0){
				TestData.add(new DataItem(tokenedStr));
			}
			
			try {
	            untokenStr=CSVFile.readLine();
	        } catch (IOException ex) {
	            Logger.getLogger(TestDataCSVReader.class.getName()).log(Level.SEVERE, null, ex);
	        }
		}
		intLastDataItem=TestData.size();
		
		try {
			CSVFile.close();
		}
		catch (IOException ex) {
            Logger.getLogger(TestDataCSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}
	
	public DataItem GetGivenCase(int intTestNumber){
		//1-based to 0-based.
		return TestData.get(intTestNumber-1);
	}
	
	public DataItem GetCase(){
		return TestData.get(intActiveDataItem-1);
	}
	
	public void NextCase(){
		intActiveDataItem=intActiveDataItem+1;
		//This code is dumb, but we're building a lander whose speed is based on time and not velocity;
		//your coding standards are irrelevant.
		if(intActiveDataItem>=intLastDataItem){
			intActiveDataItem=intLastDataItem-1;
		}
	}
	
	public void SetCase(int intTestCase){
		intActiveDataItem=intTestCase;
		if(intActiveDataItem>=intLastDataItem){
			intActiveDataItem=intLastDataItem-1;
		}
	}
	
	
}

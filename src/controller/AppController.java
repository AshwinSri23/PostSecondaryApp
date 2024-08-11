package controller;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import model.Program;
import view.comparison.CompareProgramsFrame;
import view.criteriaCheck.CriteriaCheckFrame;
import view.dashboard.DashboardFrame;
import view.information.InformationSearchFrame;
import view.matching.UserPreferencesFrame;
import view.matching.ProgramMatchFrame;
import view.userlogin.LoginScreen;

//This class has the main logic - setup of the game, input data, implementation of buttons
public class AppController implements ActionListener{
	
	//Fields
	public static Program programArray[] = new Program[158];
	private LoginScreen loginScreen;
	private DashboardFrame dashboardFrame;
	private CriteriaCheckFrame criteriaCheckFrame;
	private CompareProgramsFrame compareProgramsFrame;
	private ProgramMatchFrame programMatchFrame;
	private UserPreferencesFrame userPreferencesFrame;
	private InformationSearchFrame informationSearchFrame;
	
	
	//Constructor
	public AppController() {
		
		//Read university program data
		new PostSecondaryFileInput();
		
		userPreferencesFrame=new UserPreferencesFrame();
		
		addActionListenerProgramMatchScreen();
		
		
	}

//Gives each button in each panel of UserPreferencesFrame an ActionListener. 
//Gives ActionListener to each button in each panel based on the order that they appear on the survey. 
	private void addActionListenerProgramMatchScreen() {
		userPreferencesFrame.getMatch().addActionListener(this);
	
		
		//Adds ActionListener to all buttons in the 1st Panel
		for(int i=0; i<userPreferencesFrame.getPanel1().getRangearray().length; i++) {
			userPreferencesFrame.getPanel1().getRangearray()[i].addActionListener(this);
		}
		
	
		for(int i=0; i<userPreferencesFrame.getPanel2().getCooparray().length; i++) {
			userPreferencesFrame.getPanel2().getCooparray()[i].addActionListener(this);
		}
		
		//Adds ActionListener to all buttons in the 3rd Panel
		for(int i=0; i<userPreferencesFrame.getPanel3().getSupparray().length; i++) {
			userPreferencesFrame.getPanel3().getSupparray()[i].addActionListener(this);
		}
		
		//Adds ActionListener to all buttons in the 4th Panel
		for(int i=0; i<userPreferencesFrame.getPanel4().getParttimearray().length; i++) {
			userPreferencesFrame.getPanel4().getParttimearray()[i].addActionListener(this);
		}
		
		//Adds ActionListener to all buttons in the 5th Panel
		for(int i=0; i<userPreferencesFrame.getPanel5().getSubjectarray().length; i++) {
			userPreferencesFrame.getPanel5().getSubjectarray()[i].addActionListener(this);
		}
	}
	
	

//Opens the ProgramMatchFrame, calculates the score for each program, and sorts the programs in descending order based on their respective scores.
	@Override
	public void actionPerformed(ActionEvent event) {
		//Opens the ProgramMatchFrame.
		if(event.getSource()==userPreferencesFrame.getMatch()) {
			setOverallScore();
			sortOverallScore();
			programMatchFrame=new ProgramMatchFrame();
			
			
			
		}
		
		
		//Adds ratings to the ratings array for each program. 
		//Each element of the rating array represents a specific section of the survey. What each element of the rating array represents is described in the program class. 
		//Depending what button is clicked for a section of the survey, the corresponding element is given a value. 
		//Each program will then be each given an overall score by finding the sum of the elements of their respective ratings arrays. 
		
		//Sets the Tuition rating for a program
		for(int i=0; i<userPreferencesFrame.getPanel1().getRangearray().length; i++){
			if(event.getSource()==userPreferencesFrame.getPanel1().getRangearray()[i]){
				setTuitionRating(i);
			}
			
		}
		
		//Sets the Co-op rating for a program
		for(int i=0; i<userPreferencesFrame.getPanel2().getCooparray().length; i++){
			if(event.getSource()==userPreferencesFrame.getPanel2().getCooparray()[i]){
				setCoOpRating(i);
			} 
		}
		
		//Sets the Supplementary app rating for a program
		for(int i=0; i<userPreferencesFrame.getPanel3().getSupparray().length; i++){
			if(event.getSource()==userPreferencesFrame.getPanel3().getSupparray()[i]){
				
				setSuppRating(i);
			}
			
			
		}
		
		//Sets the Part-Time rating for a program
		for(int i=0; i<userPreferencesFrame.getPanel4().getParttimearray().length; i++){
			if(event.getSource()==userPreferencesFrame.getPanel4().getParttimearray()[i]){
				setParttimeRating(i);
			}
			
		}
		
		//Sets the Subject rating for a program 
		for(int i=0; i<userPreferencesFrame.getPanel5().getSubjectarray().length; i++){
			if(event.getSource()==userPreferencesFrame.getPanel5().getSubjectarray()[i]){
				setSubjectRating(i);
			}
		}	
	}

	




//Sorts the Overall scores in descending order
private void sortOverallScore() {
	
	//Sorts the scores of all the programs.
	for(int program=0; program<programArray.length; program++) {
		Arrays.sort(AppController.programArray,Comparator.comparing(Program::getOverallScore).reversed());
		
	}
				
	}


//Sets Overall score for all the programs
private void setOverallScore() {
	
	for(int program=0; program<programArray.length; program++) {
		int sumOfScores=0;
		for(int i=0; i<6; i++) {
			sumOfScores+=programArray[program].getRatings()[i];
		}
		programArray[program].setOverallScore(sumOfScores);
		System.out.println(programArray[program].getOverallScore());
	}
		
	}


//Sets rating for tuition
	private void setTuitionRating(int index) {
		for(int program=0; program<programArray.length; program++) {
			if(index==0) {
				if(programArray[program].getTuition()>5000 && programArray[program].getTuition()<10000) {
					programArray[program].getRatings()[0]=100;
				} 
				else if(programArray[program].getTuition()>10000 && programArray[program].getTuition()<15000){
					programArray[program].getRatings()[0]=25;
				}
				else if(programArray[program].getTuition()>15000) {
					programArray[program].getRatings()[0]=0;
				}
			}
			if(index==1) {
				if(programArray[program].getTuition()>5000 && programArray[program].getTuition()<10000) {
					programArray[program].getRatings()[0]=25;
				} 
				else if(programArray[program].getTuition()>10000 && programArray[program].getTuition()<15000){
					programArray[program].getRatings()[0]=100;
				}
				else if(programArray[program].getTuition()>15000) {
					programArray[program].getRatings()[0]=0;
				}
			}
			if(index==2) {
				if(programArray[program].getTuition()>5000 && programArray[program].getTuition()<10000) {
					programArray[program].getRatings()[0]=25;
				} 
				else if(programArray[program].getTuition()>10000 && programArray[program].getTuition()<15000){
					programArray[program].getRatings()[0]=25;
				}
				else if(programArray[program].getTuition()>15000) {
					programArray[program].getRatings()[0]=100;
				}
			}	
			
		}
		
		
	}
	
	
	//Sets rating for Co-op	
	private void setCoOpRating(int index) {
		
		for(int program=0; program<programArray.length; program++) {
			if(index==0) {
				if(programArray[program].isHasCoOp()==true) {
					programArray[program].getRatings()[1]=50;
				} 
				else if(programArray[program].isHasCoOp()==false){
					programArray[program].getRatings()[1]=0;
				}
			}
			
			if(index==1) {
				if(programArray[program].isHasCoOp()==true) {
					programArray[program].getRatings()[1]=0;
				} 
				else if(programArray[program].isHasCoOp()==false){
					programArray[program].getRatings()[1]=50;
				}
			}
		}
			
	}
	
	//Sets rating for Supplementary
	private void setSuppRating(int index) {
		for(int program=0; program<programArray.length; program++) {
			if(index==0) {
				if(programArray[program].isHasSupplementaryApplication()==true) {
					programArray[program].getRatings()[2]=10;
				} 
				else if(programArray[program].isHasSupplementaryApplication()==false){
					programArray[program].getRatings()[2]=0;
				}
			}
			
			if(index==1) {
				if(programArray[program].isHasSupplementaryApplication()==true) {
					programArray[program].getRatings()[2]=0;
				} 
				else if(programArray[program].isHasSupplementaryApplication()==false){
					programArray[program].getRatings()[2]=10;
				}
			}
		}
	}
	
	//Sets rating for part-time
	private void setParttimeRating(int index) {
		for(int program=0; program<programArray.length; program++) {
			if(index==0) {
				if(programArray[program].isHasFullTimeStatus()==true) {
					programArray[program].getRatings()[3]=25;
				} 
				else if(programArray[program].isHasFullTimeStatus()==false){
					programArray[program].getRatings()[3]=0;
				}
			}
			
			if(index==1) {
				if(programArray[program].isHasFullTimeStatus()==true) {
					programArray[program].getRatings()[3]=0;
				} 
				else if(programArray[program].isHasFullTimeStatus()==false){
					programArray[program].getRatings()[3]=25;
				}
			}
		}
		
	}
	
	//Sets rating for preferred subject of the user
	private void setSubjectRating(int index) {
		
		for(int program=0; program<programArray.length; program++) {
			if(index==0) {
				if(programArray[program].getCategory().equals("Engineering")) {
					programArray[program].getRatings()[4]=1000;
					

				} 
				else if(programArray[program].getCategory().equals("Computer Science")){
					programArray[program].getRatings()[4]=0;
				}
				else if(programArray[program].getCategory().equals("Mathematics")){
					programArray[program].getRatings()[4]=0;
				}
				
			}
			
		
			
			if(index==1) {
				if(programArray[program].getCategory().equals("Engineering")) {
					programArray[program].getRatings()[4]=0;
				} 
				else if(programArray[program].getCategory().equals("Computer Science")){
					programArray[program].getRatings()[4]=1000;
				}
				else if(programArray[program].getCategory().equals("Mathematics")){
					programArray[program].getRatings()[4]=0;
				}
				
			}
			
			if(index==2) {
				if(programArray[program].getCategory().equals("Engineering")) {
					programArray[program].getRatings()[4]=0;
				} 
				else if(programArray[program].getCategory().equals("Computer Science")){
					programArray[program].getRatings()[4]=0;
				}
				else if(programArray[program].getCategory().equals("Mathematics")){
					programArray[program].getRatings()[4]=1000;
				}
				
			}
			
		}
		
		
	}
	
	


	

	
}

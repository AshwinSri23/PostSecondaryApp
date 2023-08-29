package view.matching;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*Author: Ashwin
 *This class is the frame that contains and displays the top three universities that fit the preferences of the user
 */

@SuppressWarnings("serial")
public class ProgramMatchFrame extends JFrame{
	
	//Creates JLabels
	private JLabel title=new JLabel("Your Matches: ");
	private UniPanel1 uniPanel1=new UniPanel1();//Panel containing the best program that suits the preferences of the user
	private UniPanel3 uniPanel2=new UniPanel3();//Panel containing the 2nd best program that suits the preferences of the user
	private UniPanel2 uniPanel3=new UniPanel2();//Panel containing the 3rd best program that suits the preferences of the user
	
	public ProgramMatchFrame() {
		setTitle("preferences");
		setLayout(null);
		setSize(1920,1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Sets the Borders and adds the Panels.
		//Sets the location, font size, and font of the JLabel representing the Title. 
		title.setFont(new Font("Hevetica",Font.BOLD,19));
		title.setBounds(380,20,300,20);
		
		
		uniPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
		uniPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
		uniPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
		add(title);
		add(uniPanel1);
		add(uniPanel2);
		add(uniPanel3);
		
		setVisible(true);
		
		
	}

//Getters and Setters
	public UniPanel1 getUnipanel1() {
		return uniPanel1;
	}


	public void setUnipanel1(UniPanel1 unipanel1) {
		this.uniPanel1 = unipanel1;
	}
	
	

}

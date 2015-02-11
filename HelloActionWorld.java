package uk.ac.cam.rjm232.tick7;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class HelloActionWorld extends JFrame {
 	private JLabel label;
 	private int count = 0;
 	
 	//an "inner" class which handles events of type "ActionEvent"
 	private class ButtonAction implements ActionListener {
  		public void actionPerformed(ActionEvent e) {
  			count++;
  			//update text shown in "label"
   			label.setText("Button pressed " + count + " times");
		} 
	}
 	
 	HelloActionWorld() {
  		super("Hello Action");                   //create window & set title text
  		setDefaultCloseOperation(EXIT_ON_CLOSE); //close button on window quits app.
  		//configure the layout of the pane associated with this window as a "BoxLayout"
  		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
  		label = new JLabel("Button unpressed");  //create graphical text label
  		add(label);                              //associate "label" with window
  		JButton button = new JButton("Press me");//create graphical button
  		add(button);                             //associated "button" with window
  		//add a new instance of "ButtonAction" as an event handler for "button"
  		button.addActionListener(new ButtonAction());
  		setSize(320,240);                        //set size of window
 	}
 	
 	public static void main(String[] args) {
  		HelloActionWorld hello = new HelloActionWorld(); //create instance
  		hello.setVisible(true);                          //display window to user
	} 
}
package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SizeSectionDesigner {
	
	public SizeSectionDesigner(JLabel numLBL, JTextField num, JButton setNum, JFrame frame) {
		
        numLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        numLBL.setBounds(29, 22, 102, 20);
        frame.getContentPane().add(numLBL);
        
        num.setBounds(132, 23, 46, 20);
        num.setColumns(10);
        frame.getContentPane().add(num);
        
        setNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
        setNum.setBounds(207, 21, 62, 23);
        frame.getContentPane().add(setNum);
	}
}

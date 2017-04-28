package gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultSectionDesigner {

	public ResultSectionDesigner(JLabel deltaTotalLBL, JLabel deltaTotal, JLabel TF_LBL, JLabel TF, JFrame frame) {
		deltaTotalLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		deltaTotalLBL.setBounds(132, 507, 97, 20);
		frame.getContentPane().add(deltaTotalLBL);

		deltaTotal.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		deltaTotal.setBounds(227, 507, 103, 20);
		frame.getContentPane().add(deltaTotal);

		TF_LBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		TF_LBL.setBounds(381, 507, 100, 20);
		frame.getContentPane().add(TF_LBL);
		
		TF.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		TF.setBounds(481, 507, 103, 20);
		frame.getContentPane().add(TF);
	}
    
}

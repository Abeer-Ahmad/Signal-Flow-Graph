package gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameDesigner {

	public FrameDesigner(JPanel panel, JFrame frame) {
		panel.setLayout(null);
        panel.setVisible(true);
        
		frame.setBounds(100, 100, 1048, 579);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Signal Flow Graph Solver");       
        frame.setContentPane(panel);
        frame.getContentPane().setBackground(Color.white);
	}
}

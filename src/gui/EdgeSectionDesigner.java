package gui;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EdgeSectionDesigner {
	
	public EdgeSectionDesigner(JLabel fromLBL, JTextField from, JLabel toLBL,
			JTextField to, JLabel gainLBL, JTextField gain, JButton addEdge,
			JFrame frame) {
        fromLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        fromLBL.setBounds(28, 89, 103, 20);
        frame.getContentPane().add(fromLBL);

        from.setColumns(10);
        from.setBounds(132, 90, 46, 20);
        frame.getContentPane().add(from);

        toLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        toLBL.setBounds(207, 92, 32, 14);
        frame.getContentPane().add(toLBL);

        to.setColumns(10);
        to.setBounds(229, 90, 46, 20);
        frame.getContentPane().add(to);

        gainLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        gainLBL.setBounds(309, 92, 46, 14);
        frame.getContentPane().add(gainLBL);

        gain.setColumns(10);
        gain.setBounds(343, 90, 46, 20);
        frame.getContentPane().add(gain);

        addEdge.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addEdge.setBounds(421, 88, 62, 23);
        frame.getContentPane().add(addEdge);
	}

}

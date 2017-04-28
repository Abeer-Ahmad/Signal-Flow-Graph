package gui;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NodeSectionDesigner {

	public NodeSectionDesigner(JLabel nodeLBL, JTextField node,
			JButton addNode, JLabel sourceLBL, JTextField source,
			JLabel sinkLBL, JTextField sink, JFrame frame) {
		
        nodeLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        nodeLBL.setBounds(28, 59, 102, 14);
        frame.getContentPane().add(nodeLBL);      
        
        node.setBounds(132, 57, 46, 20);
        node.setColumns(10);
        frame.getContentPane().add(node);
        
        addNode.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addNode.setBounds(207, 55, 62, 23);
        frame.getContentPane().add(addNode);
        
        sourceLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        sourceLBL.setBounds(381, 22, 62, 20);
        frame.getContentPane().add(sourceLBL);
        
        source.setColumns(10);
        source.setBounds(437, 23, 46, 20);
        frame.getContentPane().add(source);
        
        sinkLBL.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        sinkLBL.setBounds(381, 56, 62, 20);
        frame.getContentPane().add(sinkLBL);
        
        sink.setColumns(10);
        sink.setBounds(437, 57, 46, 20);
        frame.getContentPane().add(sink);
	}	
}

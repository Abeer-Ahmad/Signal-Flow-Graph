package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Canvas;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

public class UI  extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 726, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(140, 36, 46, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(218, 34, 62, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Add New Node:");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblNewLabel.setBounds(28, 39, 102, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAddEdge = new JLabel("Add Edge From:");
		lblAddEdge.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblAddEdge.setBounds(28, 84, 103, 20);
		frame.getContentPane().add(lblAddEdge);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 85, 46, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblTo.setBounds(218, 87, 32, 14);
		frame.getContentPane().add(lblTo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(249, 85, 46, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblGain = new JLabel("Gain:");
		lblGain.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblGain.setBounds(332, 87, 46, 14);
		frame.getContentPane().add(lblGain);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(375, 85, 46, 20);
		frame.getContentPane().add(textField_3);
		
		JButton button = new JButton("Add");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(460, 84, 62, 23);
		frame.getContentPane().add(button);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSolve.setBounds(623, 385, 77, 23);
		frame.getContentPane().add(btnSolve);
		
		Graph graph = new MultiGraph("SFG");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		// Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		View view = viewer.addDefaultView(true);   // false indicates "no JFrame".
		// View view = viewer.getDefaultView();
		
		
		graph.addNode("A" );
        Node node = graph.getNode("A");
        node.setAttribute("xy", 1, 3);
        graph.addNode("B" );
        node = graph.getNode("B");
        node.setAttribute("xy", 6, 3);
        graph.addNode("C" );
        node = graph.getNode("C");
        node.setAttribute("xy", 12, 3);
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        // graph.display(false);
		
        // System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		frame.getContentPane().add((Component) view);
	}
}

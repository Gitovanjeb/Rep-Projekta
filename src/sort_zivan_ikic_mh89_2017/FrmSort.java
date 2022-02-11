package sort_zivan_ikic_mh89_2017;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Geometry.Point;
import Geometry.Rectangle;
import stack_zivan_ikic_mh89_2017.DlgStack;


public class FrmSort {
	private Stack<Rectangle> rectangles;
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	
	
	
	private JFrame frame;
	private JButton btnPop,btnPush;
	private JScrollPane scrPane;
	
	private JList<String> rectangleList;
	
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort window = new FrmSort();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}
	
	public FrmSort() {
		rectangles= new Stack<Rectangle>();
		initialize();
		frame.setVisible(true);
	}
	
	
	private void initialize() {
		// frejm
		frame = new JFrame("Sort");
		frame.setBounds(300, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// paneli
		JPanel selectPanel = new JPanel();
		frame.getContentPane().add(selectPanel, BorderLayout.SOUTH);
		selectPanel.setBackground(SystemColor.LIGHT_GRAY);
		selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBackground(SystemColor.activeCaption);
		
		//Grid lejaut 
		GridBagLayout gbl_pnlCentar = new GridBagLayout();
		gbl_pnlCentar.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentar.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentar.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCentar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_pnlCentar);
		
		
		//dugmad 
		btnPush = new JButton("PUSH");
		selectPanel.add(btnPush);
		
		btnPop = new JButton("POP");
		selectPanel.add(btnPop);
		
		//labela 
		JLabel lblRec=new JLabel("Rectangle stack : ");
		
		//Grid od scrola
		GridBagConstraints gbc_lblRec = new GridBagConstraints();
		gbc_lblRec.insets = new Insets(0, 0, 10, 10);
		gbc_lblRec.fill = GridBagConstraints.VERTICAL;
		gbc_lblRec.gridx = 7;
		gbc_lblRec.gridy = 8;
		centerPanel.add(lblRec, gbc_lblRec);
		
		
		scrPane = new JScrollPane();
		
		
		
		GridBagConstraints gbc_scrPane = new GridBagConstraints();
		gbc_scrPane.fill = GridBagConstraints.BOTH;
		gbc_scrPane.gridheight = 3;
		gbc_scrPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrPane.gridx = 7;
		gbc_scrPane.gridy = 9;
		centerPanel.add(scrPane, gbc_scrPane);
		
		
		//lista sa clanovima 
		rectangleList = new JList<String>();
		scrPane.setViewportView(rectangleList);
		rectangleList.setModel(dlm);
		
		try {
		listeners();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
					e.getMessage(), 
					"Greška", 
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	
	public void listeners() {
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlg = new DlgStack();
				dlg.setVisible(true);
				
				try {
					if (dlg.isPushed()){
						Rectangle newRectangle = dlg.getNewRectangle();
						rectangles.push(newRectangle);
						dlm.addElement(newRectangle.toString());
						sort();
						
					}else
						JOptionPane.showMessageDialog(null,
								"nije dobro uneto", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
				}catch(Exception e5) {
					JOptionPane.showMessageDialog(null,
							e5.getMessage(), 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rectangles.empty()) {
					try {
						DlgStack dlg = new DlgStack(rectangles.peek().getUpperLeft()
								,rectangles.peek().getHeight()
								,rectangles.peek().getWidth());
						dlg.setVisible(true);
						if(dlg.shouldRemove()) {
							rectangles.pop();
							dlm.remove(dlm.size()-1);
							sort();
							
						}
						
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage(), 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}else 
					JOptionPane.showMessageDialog(null,
							"stek je prazan", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
			}
		});
	}


	
	
	public void sort() {
		Object[] o = rectangles.toArray();
		Object temp;
		
		boolean sorted=false;
		 while(!sorted) {
		        sorted = true;
		        for (int i = 0; i < o.length - 1; i++) {
		            if ( ( (Rectangle)o[i]).compareTo(o[i+1]) > 0  ) {
		                temp = o[i];
		                o[i] = o[i+1];
		                o[i+1] = temp;
		                sorted = false;
		            }
		        }
		    }
		 
		 rectangles.clear();
		 dlm.clear();
		 for(int i =0 ;i < o.length;i++) {
			 rectangles.add((Rectangle)o[i]); 
			 dlm.addElement( ((Rectangle)o[i]).toString()   );
		 }
		
		
			 
		 
		 
		 
	}
	
	
	
	
	
	
	
	
	
	

}

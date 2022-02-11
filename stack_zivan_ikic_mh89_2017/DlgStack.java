package stack_zivan_ikic_mh89_2017;

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
import java.util.Stack;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Geometry.Point;
import Geometry.Rectangle;


public class DlgStack extends JDialog{


	private final JPanel contentPanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	
	private int x,y,h,w;  
	private Rectangle rec;
		
	private JButton btnPush;
	private JLabel xCor,yCor,height,width;
	private JTextField txtX,txtY,txtW,txtH;
	
	private boolean pushed,remove; 
	
	
	public boolean shouldRemove() {
		return remove;
	}
	
	public boolean isPushed() {
		return pushed;
	}
	
	public Rectangle getNewRectangle() {
		return rec;
	}
	
	private Rectangle getRectangle(Point p, int h2, int w2) throws Exception {
		
		return new Rectangle(p,h2,w2);
	}

	public static void main(String[] args) {
		
		try {
			
			DlgStack dialog = new DlgStack(new Point(1,2),3,4);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage(), 
					"Greška", 
					JOptionPane.ERROR_MESSAGE);
		}
			
	
		

	}
	
	public DlgStack(Point p,int h,int w) throws Exception {
		rec = getRectangle(p,h,w);
		initialize();
		listenerForTextFields(false);
		buttonsAndListeners(false);
		
		
		
	}
	

	public DlgStack() {
		x=y=h=w=-1;
		initialize();
		listenerForTextFields(true);
		buttonsAndListeners(true);
		
	}
	
	
	private void initialize() {
		
		
		// frejm  aplikacije
		setModal(true);
		setBounds(180, 180, 600, 200);
		getContentPane().setLayout(new BorderLayout());
		
		// panel sa sadrzajem
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.activeCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		//labele i text fildovi 
		
		//xcor
		xCor =new JLabel("X Cordinate ");
		txtX = new JTextField(10);
		
		
		//ycor
		yCor =new JLabel("Y Cordinate ");
		txtY = new JTextField(10);
		
		
		//visina
		height =new JLabel("height Cordinate ");
		txtH = new JTextField(10);
		
		
		//sirina
		width =new JLabel("width Cordinate ");
		txtW = new JTextField(10);
	
		
		//panel za dugmad
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(SystemColor.WHITE);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		
		gridBagsForContent();

		
	}
	
	public void gridBagsForContent() {
		
		// lejaut od sredisnjeg panela
		GridBagLayout gbl_pnlCentar = new GridBagLayout();
		gbl_pnlCentar.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentar.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCentar.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCentar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_pnlCentar);
				
		GridBagConstraints gbc_xCor = new GridBagConstraints();
		gbc_xCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_xCor.insets = new Insets(0, 0, 5, 5);
		gbc_xCor.gridx = 1;
		gbc_xCor.gridy = 1;
		contentPanel.add(xCor, gbc_xCor);
		
		GridBagConstraints gbc_txtX = new GridBagConstraints();
		gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtX.insets = new Insets(0, 0, 5, 5);
		gbc_txtX.gridx = 2;
		gbc_txtX.gridy = 1;
		contentPanel.add(txtX, gbc_txtX);
		
		GridBagConstraints gbc_yCor = new GridBagConstraints();
		gbc_yCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_yCor.insets = new Insets(0, 0, 5, 5);
		gbc_yCor.gridx = 5;
		gbc_yCor.gridy = 1;
		contentPanel.add(yCor, gbc_yCor);
		
		GridBagConstraints gbc_txtY = new GridBagConstraints();
		gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtY.insets = new Insets(0, 0, 5, 5);
		gbc_txtY.gridx = 6;
		gbc_txtY.gridy = 1;
		contentPanel.add(txtY, gbc_txtY);
		
		GridBagConstraints gbc_wCor = new GridBagConstraints();
		gbc_wCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_wCor.insets = new Insets(0, 0, 5, 5);
		gbc_wCor.gridx = 1;
		gbc_wCor.gridy = 4;
		contentPanel.add(width, gbc_wCor);
		
		GridBagConstraints gbc_txtw = new GridBagConstraints();
		gbc_txtw.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtw.insets = new Insets(0, 0, 5, 5);
		gbc_txtw.gridx = 2;
		gbc_txtw.gridy = 4;
		contentPanel.add(txtW, gbc_txtw);
		
		GridBagConstraints gbc_height = new GridBagConstraints();
		gbc_height.fill = GridBagConstraints.HORIZONTAL;
		gbc_height.insets = new Insets(0, 0, 5, 5);
		gbc_height.gridx = 5;
		gbc_height.gridy = 4;
		contentPanel.add(height, gbc_height);
		
		GridBagConstraints gbc_txtH= new GridBagConstraints();
		gbc_txtH.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtH.insets = new Insets(0, 0, 5, 5);
		gbc_txtH.gridx = 6;
		gbc_txtH.gridy = 4;
		contentPanel.add(txtH, gbc_txtH);
	}
	
	
	public void listenerForTextFields(boolean isPushing) {
		
		if(isPushing) {
			txtX.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							x=Integer.parseInt(txtX.getText());
							txtX.setText(Integer.toString(x));
						}
					}catch(Exception e2) {
						JOptionPane.showMessageDialog(null,
								"niste uneli broj", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
						
					
				}
			});
			
			txtY.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							y=Integer.parseInt(txtY.getText());
							txtY.setText(Integer.toString(y));
						}
					}catch(Exception e3) {
						JOptionPane.showMessageDialog(null,
								"niste uneli broj", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			txtH.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							h=Integer.parseInt(txtH.getText());
							txtH.setText(Integer.toString(h));
						}
					}catch(Exception e4) {
						JOptionPane.showMessageDialog(null,
								"niste uneli broj", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			
			txtW.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					try {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							w=Integer.parseInt(txtW.getText());
							txtW.setText(Integer.toString(w));
						}
					}catch(Exception e8) {
						JOptionPane.showMessageDialog(null,
								"niste uneli broj", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}else {
			txtX.setText(Integer.toString(rec.getUpperLeft().getX()));
			txtY.setText(Integer.toString(rec.getUpperLeft().getY()));
			txtH.setText(Integer.toString(rec.getWidth()));
			txtW.setText(Integer.toString(rec.getHeight()));
		}
		
		
	}
	
	public void buttonsAndListeners(boolean isPushing) {
		if (isPushing){
			//dugmad 
			btnPush = new JButton("PUSH");
			buttonPanel.add(btnPush);
			
			btnPush.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(x != -1 && y != -1 && h != -1 && w != -1) {
					try {
						rec = new Rectangle(new Point(x,y),h,w);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage(), 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
					pushed = true;
					x=y=w=h=-1;
					setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null,
								"niste uneli sve kordinate", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			});
		}
		else {
			btnPush = new JButton("CANCEL");
			buttonPanel.add(btnPush);
			
			btnPush.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove=false;
					setVisible(false);
				}
			});
			
			JButton btnPop = new JButton("POP");
			btnPop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					remove = true;
					setVisible(false);
				}
			});
			buttonPanel.add(btnPop);
		}
	}

	
}

	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	



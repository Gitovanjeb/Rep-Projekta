package drawing_zivan_ikic_mh89_2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Geometry.Circle;
import Geometry.Donut;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Shape;

public class DlgPaint extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private final JPanel buttonPanel = new JPanel();
	
	private boolean delete;
	private int param1,param2,param3,param4; 
	Color colorLine,colorInside;
	
	private JComboBox cbClrLine,cbClrInside;
	private JButton btnEnter,btnNo;
	private JLabel lbl1,lbl2,lblInsideColor,lblLineColor,lbl3,lbl4;
	private JTextField txt1,txt2,txt3,txt4;
	
	
	private Shape shape;
	private Actions action;
	
		
	
	
	
	public static void main(String[] args)  {
		
		try {
			
			DlgPaint dialog= new DlgPaint();
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//za crtanje
	public DlgPaint(Shapes shape) {
		param1 = param2 = -1;
		initialize();
		buttonsAndListeners(shape);
		textFieldsAndListener(shape);	
		this.setVisible(true);
	}
	//za modifikaciju
	public DlgPaint(Actions action,Shape shape) {
		this.shape = shape;
		this.action = action;
		param1 = param2=param3=param4=0;
		colorLine = Color.BLACK; colorInside = Color.lightGray;
		
		initialize();
		choseDlg();
		dlgGrid();
		this.setVisible(true);
	}
	//za brisanje
	public DlgPaint() {
		delete = false;
		deleteDlg();
		
		this.setVisible(true);
	}
	
	public void initialize() {
		// frejm  aplikacije
		setModal(true);
		setBounds(180, 180, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		
		
		// panel 
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.activeCaption);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		// za dugmad
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(SystemColor.GREEN);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		
		
	}
	
	
	
	//ZA MODIFIKACIJU
	
	public void choseDlg() {
		if(shape instanceof Point)
			pointDlgModifie();
		else if(shape instanceof Line)
			lineDlgModifie();
		else if(shape instanceof Rectangle)
			rectangleDlgModifie();
		else if(shape instanceof Donut)
			donutDlgModifie();
		else if(shape instanceof Circle)
			circleDlgModifie();
		else 
			System.out.println("greska u biranju dlg za shape");
		
	}
	
	public void pointDlgModifie() {
		
		setFirs2TxtListenersAndEvents();
		
		setColors();
		
		buttonsAndListeners();
		
	}
	
	public void lineDlgModifie() {
		setFirs2TxtListenersAndEvents();
		setColors();
		setSecond2TxtListenersAndEvents();
		buttonsAndListeners();
	}
	
	public void rectangleDlgModifie() {
		setFirs2TxtListenersAndEvents();
		setColors();
		setSecond2TxtListenersAndEvents();
		buttonsAndListeners();
	}
	
	public void circleDlgModifie() {
		setFirs2TxtListenersAndEvents();
		setSecond2TxtListenersAndEvents();
		setColors();
		buttonsAndListeners();
	}
	
	public void donutDlgModifie() {
		setFirs2TxtListenersAndEvents();
		setSecond2TxtListenersAndEvents();
		setColors();
		buttonsAndListeners();
	}
	
	//osluskivaci  i dugmad
	
	
	public void buttonsAndListeners() {
		btnEnter = new JButton("ENTER");
		
		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (action == Actions.MODIFIE)
					setModification();
				setVisible(false);
			}
		});
		
		buttonPanel.add(btnEnter);
    	
	}
	
	
	
	public void setFirs2TxtListenersAndEvents() {
		
		lbl1 = new JLabel("X cor : ");
		lbl2 = new JLabel("Y cor : ");
		txt1 = new JTextField(10);
		txt2 = new JTextField(10);
		
		
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param2=Integer.parseInt(txt2.getText());
						txt2.setText(Integer.toString(param2));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,
							e1.getMessage(), 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param1=Integer.parseInt(txt1.getText());
						txt1.setText(Integer.toString(param1));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,
							e1.getMessage(), 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	
	public void setSecond2TxtListenersAndEvents() {
		if (shape instanceof Line) 
			lbl3 = new JLabel("x2 : ");
		else if(shape instanceof Rectangle)
			lbl3 = new JLabel("W : ");
		else
			lbl3 = new JLabel("R : ");
		txt3 = new JTextField(10);
		

		
		txt3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param3=Integer.parseInt(txt3.getText());
						txt3.setText(Integer.toString(param3));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1) {
					e1.fillInStackTrace();
				}
			}
		});
		if (shape instanceof Line) 
			lbl4 = new JLabel("y2 : ");
		else if(shape instanceof Rectangle)
			lbl4 = new JLabel("H : ");
		else
			lbl4 = new JLabel("outerR : ");
		
		txt4 = new JTextField(10);
		
		
			
		txt4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param4=Integer.parseInt(txt4.getText());
						txt4.setText(Integer.toString(param4));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e1) {
					e1.fillInStackTrace();
				}
			}
		});

		
	}
	
	
	
	
	public void setColors() {
		lblInsideColor=new JLabel("inside color : ");
		cbClrInside = new JComboBox();
		cbClrInside.setModel(new DefaultComboBoxModel(new String[] {"black"	, "red", "green","gray"}));
		cbClrInside.setSelectedIndex(3);
		
		if(shape instanceof Circle || shape instanceof Rectangle) {
		cbClrInside.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	colorInside = getSelColor(cbClrInside.getSelectedIndex());
		    }
		});
		
		}
		lblLineColor=new JLabel("line color : ");
		cbClrLine = new JComboBox();
		cbClrLine.setModel(new DefaultComboBoxModel(new String[] {"black", "red", "green","grey"}));
		cbClrLine.setSelectedIndex(1);
		
		
		
		cbClrLine.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	colorLine = getSelColor(cbClrLine.getSelectedIndex());
		    }
		});
		
	}
	

	
	
	private Color getSelColor(int sel) {
		switch(sel) {
		case 0:
			return Color.black;
		case 1:
			return Color.red;
		case 2:
			return Color.green;
		case 3:
			return Color.lightGray;
		default:
			System.out.println("greska u odabiru boja");
			break;
		}
		return Color.black;
		
	}
	
	
	
	public void setModification() {
		
		try {
			
			shape.setColorLine(colorLine);
			
			if(shape instanceof Point) {
				setModificationParam1And2();
				
			}else if(shape instanceof Line) {
				if(param1 != 0 && param2 != 0) 
					((Line)shape).getStartPoint().moveTo(param1, param2);
				if(param3 != 0 && param4 != 0) 
					((Line)shape).getEndPoint().moveTo(param3, param4);
				
			}else if(shape instanceof Rectangle) {
				shape.setColorInside(colorInside);
				setModificationParam1And2();
				if (param3 != 0 && param4 != 0) {
					((Rectangle)shape).setWidth(param3);
					((Rectangle)shape).setHeight(param4);
				}
			}else if(shape instanceof Donut) {
				shape.setColorInside(colorInside);
				setModificationParam1And2();
				if (param3 != 0 && param4 != 0) {
					((Donut)shape).setRAndInnerR(param4,param3);
					
				}
				
			}else if(shape instanceof Circle) {
				shape.setColorInside(colorInside);
				setModificationParam1And2();
				if(param3 != 0)
					((Circle)shape).setR(param3);
				
			}else 
				System.out.println("greska u biranju modifikacije za shape");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage() ,
					"Greška", 
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void setModificationParam1And2() {
		if (param1 != 0 && param2 != 0) {
			shape.moveTo(param1, param2);
		}
	}
	

	
	
	
	//DIJALOG ZA BRISANJE
	
	public void deleteDlg() {
		// frejm 
		setModal(true);
		setBounds(180, 180, 400, 150);
		getContentPane().setLayout(new BorderLayout());
		
		
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(SystemColor.yellow);
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(SystemColor.activeCaption);
		getContentPane().add(buttonPanel, BorderLayout.CENTER);
				
		lbl1 = new JLabel("Do you want to remove : ");
		contentPanel.add(lbl1);
		btnEnter = new JButton("Yes");
		btnNo = new JButton("No");
		
		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete = true;
				setVisible(false);
			}
		});
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete = false;
				setVisible(false);
			}
		});
		buttonPanel.add(btnEnter);
		buttonPanel.add(btnNo);
		
	}
	
	
	
	
	
	
	//grid 
	public void dlgGrid() {
		GridBagLayout gbl_optionPanel = new GridBagLayout();
		gbl_optionPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_optionPanel.rowHeights = new int[]  {0, 0, 0, 0, 0, 0, 0, 0, 0,0};
		gbl_optionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_optionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};	
		contentPanel.setLayout(gbl_optionPanel);
		
		
		GridBagConstraints gbc_lbl1 = new GridBagConstraints();
		gbc_lbl1.insets = new Insets(10, 15, 10, 0);
		gbc_lbl1.fill = GridBagConstraints.VERTICAL;
		gbc_lbl1.gridx = 1;
		gbc_lbl1.gridy = 0;
		
		
		GridBagConstraints gbc_txt1 = new GridBagConstraints();
		gbc_txt1.insets = new Insets(10, 0, 10, 0);
		gbc_txt1.fill = GridBagConstraints.VERTICAL;
		gbc_txt1.gridx = 2;
		gbc_txt1.gridy = 0;
		
		
		GridBagConstraints gbc_lbl2 = new GridBagConstraints();
		gbc_lbl2.insets = new Insets(10, 100, 10, 0);
		gbc_lbl2.fill = GridBagConstraints.VERTICAL;
		gbc_lbl2.gridx = 3;
		gbc_lbl2.gridy = 0;
		
		
		GridBagConstraints gbc_txt2 = new GridBagConstraints();
		gbc_txt2.insets = new Insets(10, 0, 10, 0);
		gbc_txt2.fill = GridBagConstraints.VERTICAL;
		gbc_txt2.gridx = 4;
		gbc_txt2.gridy = 0;
		
		
		GridBagConstraints gbc_lbl3 = new GridBagConstraints();
		gbc_lbl3.insets = new Insets(10, 15, 10, 0);
		gbc_lbl3.fill = GridBagConstraints.VERTICAL;
		gbc_lbl3.gridx = 1;
		gbc_lbl3.gridy = 1;
		
		
		GridBagConstraints gbc_txt3 = new GridBagConstraints();
		gbc_txt3.insets = new Insets(10, 0, 10, 0);
		gbc_txt3.fill = GridBagConstraints.VERTICAL;
		gbc_txt3.gridx = 2;
		gbc_txt3.gridy = 1;
		
		
		GridBagConstraints gbc_lbl4 = new GridBagConstraints();
		gbc_lbl4.insets = new Insets(10, 100, 10, 0);
		gbc_lbl4.fill = GridBagConstraints.VERTICAL;
		gbc_lbl4.gridx = 3;
		gbc_lbl4.gridy = 1;
		
		
		GridBagConstraints gbc_txt4 = new GridBagConstraints();
		gbc_txt4.insets = new Insets(10, 0, 10, 0);
		gbc_txt4.fill = GridBagConstraints.VERTICAL;
		gbc_txt4.gridx = 4;
		gbc_txt4.gridy = 1;
		
		
		
		
		
		
		GridBagConstraints gbc_lblLineColor = new GridBagConstraints();
		gbc_lblLineColor.insets = new Insets(10, 15, 10, 0);
		gbc_lblLineColor.fill = GridBagConstraints.VERTICAL;
		gbc_lblLineColor.gridx = 1;
		gbc_lblLineColor.gridy = 2;
		
		
		GridBagConstraints gbc_cgClrLine = new GridBagConstraints();
		gbc_cgClrLine.insets = new Insets(10, 0, 10, 0);
		gbc_cgClrLine.fill = GridBagConstraints.VERTICAL;
		gbc_cgClrLine.gridx = 2;
		gbc_cgClrLine.gridy = 2;
		
		GridBagConstraints gbc_lblInsideColor = new GridBagConstraints();
		gbc_lblInsideColor.insets = new Insets(10, 100, 10, 0);
		gbc_lblInsideColor.fill = GridBagConstraints.VERTICAL;
		gbc_lblInsideColor.gridx = 3;
		gbc_lblInsideColor.gridy = 2;
		
		
		GridBagConstraints gbc_txt5 = new GridBagConstraints();
		gbc_txt5.insets = new Insets(10, 0, 10, 0);
		gbc_txt5.fill = GridBagConstraints.VERTICAL;
		gbc_txt5.gridx = 4;
		gbc_txt5.gridy = 2;
		
		
		if(shape instanceof Point) {
			contentPanel.add(lbl1, gbc_lbl1);
			contentPanel.add(txt1, gbc_txt1);
			contentPanel.add(lblLineColor, gbc_lblLineColor);
			contentPanel.add(cbClrLine,gbc_cgClrLine);
			contentPanel.add(lbl2, gbc_lbl2);
			contentPanel.add(txt2, gbc_txt2);
			
			
		}else if(shape instanceof Line) {
			contentPanel.add(lbl1, gbc_lbl1);
			contentPanel.add(txt1, gbc_txt1);
			contentPanel.add(lblLineColor, gbc_lblLineColor);
			contentPanel.add(cbClrLine,gbc_cgClrLine);
			contentPanel.add(lbl2, gbc_lbl2);
			contentPanel.add(txt2, gbc_txt2);
			
			contentPanel.add(txt3, gbc_txt3);
			contentPanel.add(lbl3, gbc_lbl3);
			contentPanel.add(lbl4, gbc_lbl4);
			contentPanel.add(txt4, gbc_txt4);
			
		}else if(shape instanceof Rectangle) {
			contentPanel.add(lbl1, gbc_lbl1);
			contentPanel.add(txt1, gbc_txt1);
			contentPanel.add(lblLineColor, gbc_lblLineColor);
			contentPanel.add(cbClrLine,gbc_cgClrLine);
			contentPanel.add(lbl2, gbc_lbl2);
			contentPanel.add(txt2, gbc_txt2);
			
			contentPanel.add(lbl3, gbc_lbl3);
			contentPanel.add(txt3, gbc_txt3);
			
			contentPanel.add(lbl4, gbc_lbl4);
			contentPanel.add(txt4, gbc_txt4);
			
			contentPanel.add(lblInsideColor, gbc_lblInsideColor);
			contentPanel.add(cbClrInside, gbc_txt5);
			
		}else if(shape instanceof Donut) {
			contentPanel.add(lbl1, gbc_lbl1);
			contentPanel.add(txt1, gbc_txt1);
			contentPanel.add(lblLineColor, gbc_lblLineColor);
			contentPanel.add(cbClrLine,gbc_cgClrLine);
			contentPanel.add(lbl2, gbc_lbl2);
			contentPanel.add(txt2, gbc_txt2);
			
			contentPanel.add(lbl3, gbc_lbl3);
			contentPanel.add(txt3, gbc_txt3);
			
			contentPanel.add(lbl4, gbc_lbl4);
			contentPanel.add(txt4, gbc_txt4);
			
			contentPanel.add(lblInsideColor, gbc_lblInsideColor);
			contentPanel.add(cbClrInside, gbc_txt5);
			
			}else if(shape instanceof Circle) {
			contentPanel.add(lbl1, gbc_lbl1);
			contentPanel.add(txt1, gbc_txt1);
			contentPanel.add(lblLineColor, gbc_lblLineColor);
			contentPanel.add(cbClrLine,gbc_cgClrLine);
			contentPanel.add(lbl2, gbc_lbl2);
			contentPanel.add(txt2, gbc_txt2);
			
			contentPanel.add(lbl3, gbc_lbl3);
			contentPanel.add(txt3, gbc_txt3);
			
			contentPanel.add(lblInsideColor, gbc_lblInsideColor);
			contentPanel.add(cbClrInside, gbc_txt5);
				
			}
		
		
	}
	
	
	
	
	
	//ZA CRTANJE
	public void buttonsAndListeners(Shapes shape) {
			btnEnter = new JButton("ENTER");
			buttonPanel.add(btnEnter);
			
			btnEnter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(param1 != -1 && (param2 != -1 || shape == shape.CIRCLE) )
						setVisible(false);
				}
			});
			
			buttonPanel.add(btnEnter);
		}
	
	public void textFieldsAndListener(Shapes shape) {
		switch(shape) {
		case RECTANGLE:
			rectangleTFAndEvents();
			break;
		case CIRCLE:
			circleTFAndEvent();
			break;
		case DONUT:
			donutTFAndEvent();
			break;
		default:
			System.out.println("Doslo je do greske pri stvaranju TF");
		}
		
		
		
	}
	
	public void donutTFAndEvent() {
		
		

		
		lbl1 =new JLabel("Inner Diameter");
		txt2 = new JTextField(10);
		contentPanel.add(lbl1);
		contentPanel.add(txt2);
		
		
		lbl2 =new JLabel("Outer Diameter");
		txt1 = new JTextField(10);
		contentPanel.add(lbl2);
		contentPanel.add(txt1);
		
		
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						param2=Integer.parseInt(txt2.getText());
						txt2.setText(Integer.toString(param2));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,
								"nisu unijeti brojevi", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}catch(Exception e3) {
						JOptionPane.showMessageDialog(null,
								e3.getMessage(), 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						param1=Integer.parseInt(txt1.getText());
						txt1.setText(Integer.toString(param1));
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,
								"nisu unijeti brojevi", 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}catch(Exception e4) {
						JOptionPane.showMessageDialog(null,
								e4.getMessage(), 
								"Greška", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	public void circleTFAndEvent() {
	
		lbl1 =new JLabel("Diametar");
		txt2 = new JTextField(10);
		contentPanel.add(lbl1);
		contentPanel.add(txt2);
		
		
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param1=Integer.parseInt(txt2.getText());
						txt2.setText(Integer.toString(param1));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
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
	}
	
	public void rectangleTFAndEvents() {
		
		lbl1 =new JLabel("height");
		txt2 = new JTextField(10);
		contentPanel.add(lbl1);
		contentPanel.add(txt2);
		
		lbl2 =new JLabel("width");
		txt1 = new JTextField(10);
		contentPanel.add(lbl2);
		contentPanel.add(txt1);
		
		txt2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param1=Integer.parseInt(txt2.getText());
						txt2.setText(Integer.toString(param1));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e6) {
					JOptionPane.showMessageDialog(null,
							e6.getMessage(), 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		txt1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						param2=Integer.parseInt(txt1.getText());
						txt1.setText(Integer.toString(param2));
					}
				}catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null,
							"nisu unijeti brojevi", 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}catch(Exception e7) {
					JOptionPane.showMessageDialog(null,
							e7.getMessage(), 
							"Greška", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	

	
	
	public int getParam1() {
		return param1;
	}


	public int getParam2() {
		return param2;
	}
	
	
	public boolean getShouldDelete() {
		return delete;
	}



			
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

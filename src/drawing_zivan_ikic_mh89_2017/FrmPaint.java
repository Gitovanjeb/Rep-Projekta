package drawing_zivan_ikic_mh89_2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Geometry.Circle;
import Geometry.Donut;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Shape;
import sort_zivan_ikic_mh89_2017.FrmSort;
import stack_zivan_ikic_mh89_2017.DlgStack;

public class FrmPaint {
	//Param
	private LinkedList<Object> ll = new LinkedList<Object>();
	
	private Shapes selShape;
	private Actions selAction;
	
	LineTupel lt;
	
	
	private JFrame frame;
	JPanel optionPanel;
	PnlDrawing paintPanel;
	private JButton btnR,btnC,btnD,btnL,btnP,btnDrw,btnMod,btnSel,btnClr;
	//

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPaint window = new FrmPaint();
					window.frame.setVisible(true);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
	public FrmPaint() {
		selShape = Shapes.NONE;
		selAction = Actions.DRAW;
	
		lt = new LineTupel();
		
		
		initalize();
		listenersForShapes();
		listenersForActions();
		mouseListener();
		gridLayout();
		
	}
	
	// init
	public void initalize() {
		// frejm  aplikacije
		frame = new JFrame("Paint");
		frame.setBounds(300, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		optionPanel = new JPanel();
		frame.getContentPane().add(optionPanel, BorderLayout.SOUTH);
		optionPanel.setBackground(SystemColor.LIGHT_GRAY);
		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
		
		//panel 
		paintPanel = new PnlDrawing();
		paintPanel.setBackground(Color.white);
		frame.getContentPane().add(paintPanel);
		
		
		//Dugmad
		btnR = new JButton("rectangle");
		btnC = new JButton("circle");
		btnD = new JButton("donut");
		btnP = new JButton("point");
		btnL = new JButton("line");
		
		btnSel = new JButton("selsect");
		btnDrw = new JButton("draw");
		btnDrw.setForeground(Color.BLUE);
		btnMod = new JButton("modifie");
		btnClr = new JButton("clear");
		btnMod.setForeground(Color.RED);
		btnClr.setForeground(Color.RED);
		
		optionPanel.add(btnR);
		optionPanel.add(btnC);
		optionPanel.add(btnD);
		optionPanel.add(btnP);
		optionPanel.add(btnL);
		
		optionPanel.add(btnDrw);
		optionPanel.add(btnSel);
		optionPanel.add(btnMod);
		optionPanel.add(btnClr);
		
		
		

		}
	
	
	//OSLUSKIVANJE 
	public void listenersForShapes() {
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selShape = Shapes.RECTANGLE; 
				buttonColor(Color.BLACK,Color.BLACK,
						Color.BLUE,Color.BLACK,Color.BLACK);
				
			}
		});
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selShape = Shapes.CIRCLE;
				buttonColor(Color.BLACK,Color.BLACK,
						Color.BLACK,Color.BLUE,Color.BLACK);
			}
		});
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selShape = Shapes.DONUT; 
				buttonColor(Color.BLACK,Color.BLACK,
						Color.BLACK,Color.BLACK,Color.BLUE);
				
			}
		});
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selShape = Shapes.LINE; 
				buttonColor(Color.BLACK,Color.BLUE,
						Color.BLACK,Color.BLACK,Color.BLACK);
				
			}
		});
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selShape = Shapes.POINT; 
				buttonColor(Color.BLUE,Color.BLACK,
						Color.BLACK,Color.BLACK,Color.BLACK);
				
			}
		});
		
		
	}
	
	public void listenersForActions() {
		btnDrw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selAction = Actions.DRAW;
				if(nothingIsSelected())
					buttonColor(Color.BLUE,Color.BLACK,Color.red,Color.red);
				else
					buttonColor(Color.BLUE,Color.BLACK,Color.green,Color.green);
					
			}
		});
		
		btnSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selAction = Actions.SELECT;
				if(nothingIsSelected())
					buttonColor(Color.BLACK,Color.BLUE,Color.red,Color.red);
				else
					buttonColor(Color.BLACK,Color.BLUE,Color.green,Color.green);
			}
		});
		
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nothingIsSelected()) {
					selAction = Actions.MODIFIE;
					buttonColor(Color.BLACK,Color.BLACK,Color.BLUE,Color.BLACK);
					modifieEvent();
					paintPanel.repaint();
					if(nothingIsSelected())
						buttonColor(Color.BLACK,Color.BLACK,Color.red,Color.red);
					else
						buttonColor(Color.BLACK,Color.BLACK,Color.green,Color.green);
				}
			}
		});
		
		btnClr.addActionListener(new ActionListener() {//brise
			public void actionPerformed(ActionEvent e) {
				if(!nothingIsSelected()) {
					selAction = Actions.DELETE;
					buttonColor(Color.BLACK,Color.BLACK,Color.BLACK,Color.BLUE);
					deleteEvent();
					if(nothingIsSelected())
						buttonColor(Color.BLACK,Color.BLACK,Color.red,Color.red);
					else
						buttonColor(Color.BLACK,Color.BLACK,Color.green,Color.green);
					paintPanel.repaint();
				}
			}
		});
	}
	
	
	//  boja dugmadi
	public void buttonColor(Color d,Color s, Color m,Color c) {
		btnDrw.setForeground(d);
		btnSel.setForeground(s);
		btnMod.setForeground(m);
		btnClr.setForeground(c);
		
	}
	
	public void buttonColor(Color p,Color l, Color r,Color c,Color d) {
		btnP.setForeground(p);
		btnL.setForeground(l);
		btnR.setForeground(r);
		btnC.setForeground(c);
		btnD.setForeground(d);
	}
	
	
	//modifikujemo i brisemo
    public void modifieEvent() {
    	Shape ps=(Shape) findeSelectedShape();
    	DlgPaint dlm = new DlgPaint(Actions.MODIFIE,ps);
    	
    	ps.setSelected(false);

    }
	
    public void deleteEvent() {
    	DlgPaint dpd = new DlgPaint();
    	if(dpd.getShouldDelete()) 
    		while(ll.remove(findeSelectedShape()));
    	
    }
    
    
	
	
	private void mouseListener() {
		// osluskivanje misa
		paintPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	
            	//bira  akciju 
				switch(selAction) {
				case DRAW:
	            	drawMouseAction(evt);
	            	paintPanel.repaint();
					break;
				case SELECT:
					selectShape(evt);
					if(findeSelectedShape() == null)
						buttonColor(Color.BLACK,Color.BLUE,Color.red,Color.red);
					else
						buttonColor(Color.BLACK,Color.BLUE,Color.green,Color.green);
					break;

					
				default :
					break;
					
				}
				
			}
		    
		});
		
		
	}
	
    
	public void drawMouseAction(MouseEvent evt) {
		try {
	    	if(selShape != Shapes.LINE)
	    		lt.reset();
	    	Point p = new Point(evt.getX(),evt.getY());
	    	
	    	switch (selShape) {
	    	case POINT:
	    		ll.add(p);
	    		break;
	    	case LINE:
	    		if (lt.pointSel) {
	    			ll.add(new Line(lt.frstP, p));
	    			lt.reset();
	    		}else {
	    			lt.pointSel=true;
	    			lt.frstP = p;
	    		}
	    		break;
	    	case RECTANGLE:
	    		DlgPaint dpr = new DlgPaint(selShape);
	    		ll.add(new Rectangle(p,dpr.getParam2(),
	    				dpr.getParam1()));
	    		break;
	    	case CIRCLE:
	    		DlgPaint dpc = new DlgPaint(selShape);
	    		ll.add(new Circle( p,dpc.getParam1() ) );
	    		
	    		break;
	    	case DONUT:
	    		DlgPaint dpd = new DlgPaint(selShape);
	    		ll.add(new Donut(p,dpd.getParam1(),
	    				dpd.getParam2()));
	    		break;
	    	case NONE:
	    		
	    		break;
			default:
				break;
	    	
	    	}
		}catch(NumberFormatException nfe) {
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage(), 
					"Greška", 
					JOptionPane.ERROR_MESSAGE);
		}
    	
    }
    
    public boolean selectShape(MouseEvent evt) {
    	int i = 0,selected=-1;
    
    	
    	for(Object o : ll) {
    		if(o instanceof Donut) {
				if ( ((Donut)o).contains(evt.getX(), evt.getY()) )
					selected=i;
    		}
			else if(o instanceof Line) {
				if ( ((Line)o).contains(evt.getX(), evt.getY()) )
					selected=i;
			}
			else if(o instanceof Rectangle) {
				if ( ((Rectangle)o).contains(evt.getX(), evt.getY()) )
					selected=i;
			}
			else if(o instanceof Circle) {
				if ( ((Circle)o).contains(evt.getX(), evt.getY()) )
					selected=i;
			}
			else if(o instanceof Point) 
				if ( ((Point)o).contains(evt.getX(), evt.getY()) ) 
					selected=i;
			++i;
		}
    	if(selected != -1) {
        	Shape s =(Shape) (ll.get(selected));
        	if(s.isSelected())
        		s.setSelected(false);
        	else
        		s.setSelected(true);
    		
    		paintPanel.repaint();
    		return true;
    	}return false;
    
    }
    
    
    //_____dodatne f-je
    
    public Object findeSelectedShape() {
    	for(Object o : ll) {
    		if(((Shape)o).isSelected())
    			return o;

		}return null;
    }
    
    public boolean nothingIsSelected() {
    	for(Object o : ll) {
    		if(((Shape)o).isSelected())
    			return false;

		}return true;
    }
    
    
    
	

	
	//PnlDrawing klasa
	
	
	class PnlDrawing extends JPanel {
		
		PnlDrawing(){
			
		}

		public void paint(Graphics g) {
			
			super.paint(g);
			
			drawing(g);
			
			}

		public void drawing(Graphics g) {
			
			for(Object o : ll) {
				if(o instanceof Point) {
					((Point)o).draw(g);}
				else if(o instanceof Line)
					((Line)o).draw(g);
				else if(o instanceof Rectangle)
					((Rectangle)o).draw(g);
				else if(o instanceof Circle)
					((Circle)o).draw(g);
				else if(o instanceof Donut)
					((Donut)o).draw(g);
			}

			
			
		}
		
		
	}
	

	
	class LineTupel{
		private Point frstP;
		private boolean  pointSel;
		
		LineTupel(){
			frstP = null;
			pointSel=false;
		}
		public void reset() {
			frstP = null;
			pointSel=false;
		}
	}
	//-----------------------

	
	public void gridLayout() {
		GridBagLayout gbl_optionPanel = new GridBagLayout();
		gbl_optionPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_optionPanel.rowHeights = new int[]  {0, 0, 0, 0, 0, 0, 0, 0, 0,0};
		gbl_optionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_optionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};	
		optionPanel.setLayout(gbl_optionPanel);
		
		GridBagConstraints gbc_btnP = new GridBagConstraints();
		gbc_btnP.insets = new Insets(10, 10, 10, 10);
		gbc_btnP.fill = GridBagConstraints.VERTICAL;
		gbc_btnP.gridx = 0;
		gbc_btnP.gridy = 0;
		optionPanel.add(btnP, gbc_btnP);
		
		GridBagConstraints gbc_btnL = new GridBagConstraints();
		gbc_btnL.insets = new Insets(10, 0, 10, 10);
		gbc_btnL.fill = GridBagConstraints.VERTICAL;
		gbc_btnL.gridx = 1;
		gbc_btnL.gridy = 0;
		optionPanel.add(btnL, gbc_btnL);
		
		GridBagConstraints gbc_btnR = new GridBagConstraints();
		gbc_btnR.insets = new Insets(10, 0, 10, 10);
		gbc_btnR.fill = GridBagConstraints.VERTICAL;
		gbc_btnR.gridx = 2;
		gbc_btnR.gridy = 0;
		optionPanel.add(btnR, gbc_btnR);
		
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.insets = new Insets(10, 0, 10, 10);
		gbc_btnC.fill = GridBagConstraints.VERTICAL;
		gbc_btnC.gridx = 3;
		gbc_btnC.gridy = 0;
		optionPanel.add(btnC, gbc_btnC);
		
		GridBagConstraints gbc_btnD = new GridBagConstraints();
		gbc_btnD.insets = new Insets(10, 0, 10, 10);
		gbc_btnD.fill = GridBagConstraints.VERTICAL;
		gbc_btnD.gridx = 4;
		gbc_btnD.gridy = 0;
		optionPanel.add(btnD, gbc_btnD);
		
		GridBagConstraints gbc_btnDrw = new GridBagConstraints();
		gbc_btnDrw.insets = new Insets(10, 0, 10, 150);
		gbc_btnDrw.fill = GridBagConstraints.VERTICAL;
		gbc_btnDrw.gridx = 9;
		gbc_btnDrw.gridy = 0;
		optionPanel.add(btnDrw, gbc_btnDrw);
		
		GridBagConstraints gbc_btnSel = new GridBagConstraints();
		gbc_btnSel.insets = new Insets(10, 10, 10, 0);
		gbc_btnSel.fill = GridBagConstraints.VERTICAL;
		gbc_btnSel.gridx = 9;
		gbc_btnSel.gridy = 0;
		optionPanel.add(btnSel, gbc_btnSel);
		
		GridBagConstraints gbc_btnMod = new GridBagConstraints();
		gbc_btnMod.insets = new Insets(10, -190, 10, 0);
		gbc_btnMod.fill = GridBagConstraints.VERTICAL;
		gbc_btnMod.gridx = 10;
		gbc_btnMod.gridy = 0;
		optionPanel.add(btnMod, gbc_btnMod);
		
		GridBagConstraints gbc_btnClr = new GridBagConstraints();
		gbc_btnClr.insets = new Insets(10, 0, 10, 10);
		gbc_btnClr.fill = GridBagConstraints.VERTICAL;
		gbc_btnClr.gridx = 10;
		gbc_btnClr.gridy = 0;
		optionPanel.add(btnClr, gbc_btnClr);
		
		
		 
	}
	
	
	
	
	

}

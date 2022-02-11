//package Geometry;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class Drawing extends JPanel {
//
//	public static void main(String[] args) {
//		//pravimo frejm
//		JFrame frame = new JFrame("Drawing");
//		frame.setSize(800, 600);
//		
//		//dodajemo crtez nasem frejmu
//		Drawing drawing = new Drawing();
//		frame.getContentPane().add(drawing);
//		frame.setVisible(true);
//		
//	}
//	
//	public void paint(Graphics g) {
//		//pravimo point  i crtamo ga
//		g.setColor(Color.BLUE);
//		Point p1 = new Point(50, 50);
//		p1.draw(g);
//		g.setColor(Color.RED);
//		
//		//crtamo liniju
//		Line l1 = new Line(new Point(100, 100), new Point(200, 200));
//		l1.draw(g);
//		g.setColor(Color.GREEN);
//		//crtamo pocetnu liniju
//		l1.getStartPoint().draw(g);
//		
//		//crtamo kocku
//		g.setColor(Color.BLUE);
//		Rectangle r1 = new Rectangle(l1.getEndPoint(), 100, 50);
//		//l1.getEndPoint().setY(10); !
//		r1.draw(g);
//
//		//crtamo krug
//		Circle c1 = new Circle(new Point(500, 100), 80); 
//		c1.draw(g);
//		c1.getCenter().draw(g);
//		g.setColor(Color.YELLOW);
//		
//		//crtamo krofnu
//		Donut d1 = new Donut(new Point(800, 100), 50, 25);
//		d1.draw(g);
//
//		//ctrtamo kocku
//		Rectangle k1 = new Rectangle(new Point(500, 500), 50, 50);
//		k1.draw(g);
//
//		//crtmo krofnu
//		int innerRD2 = (int)(k1.getHeight() * Math.sqrt(2)) / 2;
//		Donut d2 = new Donut(new Point(k1.getUpperLeft().getX() + k1.getHeight() / 2, k1.getUpperLeft().getY() + k1.getHeight() / 2)
//				, 80, innerRD2);
//		d2.draw(g);
////		
////		
////		// VEZBE 8
////		
////		ArrayList<Shape> shapes = new ArrayList<Shape>();
////		shapes.add(p1);
////		shapes.add(l1);
////		shapes.add(r1);
////		shapes.add(c1);
////		shapes.add(d1);
////
////		/*shapes.get(2).draw(g);
////		shapes.add(2, p1);
////		shapes.get(2).draw(g);
////		shapes.remove(2);
////		shapes.get(2).draw(g);*/
////		p1.setX(300);
////		Iterator<Shape> it = shapes.iterator();
////		while(it.hasNext()) {
////			Shape temp = it.next();
////			temp.setSelected(true);
////			temp.draw(g);
////			//System.out.println(temp);
////			// iscrtati samo krugove
////			/*if(temp instanceof Circle && !(temp instanceof Donut))
////				temp.draw(g);*/
////			// iscrtati povrsinske oblike
//////			if(temp instanceof Circle || temp instanceof Rectangle)
//////				temp.draw(g);
////		}
////
////		HashMap<String, Shape> hmShapes = new HashMap<String, Shape>();
////		hmShapes.put("point", p1);
////		hmShapes.put("line", l1);
////		System.out.println(hmShapes.get("point"));
////		//hmShapes.remove("point");
////		//System.out.println(hmShapes.get("point"));
////		//ArrayList<Shape> alHmShapes = (ArrayList<Shape>) hmShapes.values();
////
////		p1.setSelected(true);
////		p1.draw(g);
//	}
//
//}

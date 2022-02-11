package Geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {
	private int innerR;

	public Donut() {

	}

	public Donut(Point center, int r, int innerR) throws Exception {
		super(center, r);
		if(innerR < 0) 
			throw new Exception("radius ne moze biti manji od 0");
		else if(innerR> r) { 
			throw new Exception("unutrasnji radijus ne moze biti veci od spoljasnjeg");
		}
		this.innerR = innerR;
	}

	public Donut(Point center, int r, int innerR, boolean selected) throws Exception {
		this(center, r, innerR);
		if(innerR < 0) 
			throw new Exception("radius ne moze biti manji od 0");
		else if(innerR> r) { 
			throw new Exception("unutrasnji radijus ne moze biti veci od spoljasnjeg");
		}
		this.selected = selected;

	}

	@Override
	public double area() {
		return super.area() - innerR * innerR * Math.PI;
	}

	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y) && center.distance(x, y) >= innerR;
	}

	@Override
	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}

	
	public String toString() {
		return super.toString()+", inner radius = "+innerR;
	}

	public int getInnerR() {
		return innerR;
	}
	
	public void setRAndInnerR(int r,int innerR) throws Exception {
		if(innerR < 0 || r < 0) 
			throw new Exception("radius ne moze biti manji od 0");
		else if(innerR > r) { 
			throw new Exception("unutrasnji radijus ne moze biti veci od spoljasnjeg");
		}
		
		this.r=r;
		this.innerR= innerR;
	}
	
	public void setInnerR(int innerR) throws Exception{
		if(innerR < 0) 
			throw new Exception("radius ne moze biti manji od 0");
		else if(innerR > r) { 
			throw new Exception("unutrasnji radijus ne moze biti veci od spoljasnjeg");
		}
		this.innerR = innerR;
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.white);
		g.fillOval(center.getX()-innerR,center.getY()-innerR, 2*innerR, 2*innerR);
		
		g.setColor(colorLine);
		g.drawOval(center.getX()-innerR, center.getY()-innerR, 2*innerR, 2*innerR);
		if (selected) {
			g.setColor(Color.blue);
			g.drawRect(this.getCenter().getX() - innerR - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() + innerR - 2, this.getCenter().getY() - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() - innerR - 2, 4, 4);
			g.drawRect(this.getCenter().getX() - 2, this.getCenter().getY() + innerR - 2, 4, 4);
		}
	}

}

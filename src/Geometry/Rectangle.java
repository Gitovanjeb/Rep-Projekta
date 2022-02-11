package Geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	private Point upperLeft;
	private int width;
	private int height;
	
	public Rectangle() {

	}

	public Rectangle(Point upperLeft, int width, int height) throws Exception{
		if(width < 0 || height < 0) 
			throw new Exception("visina ili sirina ne moze biti manji od 0");
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeft, int width, int height, boolean selected)throws Exception {
		this(upperLeft, width, height);
		this.selected = selected;
	}

	public String toString() {
		return "Upper left point: "+upperLeft+", width = "+width+", height = "+height;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle temp = (Rectangle) obj;
			return upperLeft.equals(temp.upperLeft) && width == temp.width && height == temp.height;
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		return (upperLeft.getX() < x && x < upperLeft.getX() + width && upperLeft.getY() < y
				&& y < upperLeft.getY() + height);
	}

	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}

	public int area() {
		return width * height;
	}
	
	public int volume() {
		return 2 * width + 2 * height;
	}

	public Point getUpperLeft() {
		return upperLeft;
	}
	
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) throws Exception{
		if(width < 0) 
			throw new Exception("sirina ne moze biti manji od 0");
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height)throws Exception {
		if(height < 0) 
			throw new Exception("visina ne moze biti manji od 0");
		this.height = height;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(colorInside);
		g.fillRect(upperLeft.getX(),upperLeft.getY(), width, height);
		
		g.setColor(colorLine);
		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);

		if (selected) {
			g.setColor(Color.blue);
			g.drawRect(upperLeft.getX() - 2, upperLeft.getY() - 2, 4, 4);
			g.drawRect(upperLeft.getX() + width - 2, upperLeft.getY() - 2, 4, 4);
			g.drawRect(upperLeft.getX() - 2, upperLeft.getY() + height - 2, 4, 4);
			g.drawRect(upperLeft.getX() + width  - 2, upperLeft.getY() + height - 2, 4, 4);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		upperLeft.moveBy(byX, byY);
	}

	@Override
	public void moveTo(int x, int y) {
		upperLeft.moveTo(x, y);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle)o).area());
		}
		return 0;
	}
}

package Geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	private Point startPoint;
	private Point endPoint;
	
	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.selected = selected;
	}

	public String toString() {
		return startPoint+"-->"+endPoint;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line temp = (Line) obj;
			return startPoint.equals(temp.startPoint) && endPoint.equals(temp.endPoint);
		}
		return false;
	}
	
	public boolean contains(int x, int y) {
		return (startPoint.distance(x, y) + endPoint.distance(x, y)) - length() < 2;
	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(colorLine);
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if (selected) {
			g.setColor(Color.blue);
			g.drawRect(this.getStartPoint().getX() - 2, this.getStartPoint().getY() - 2, 4, 4);
			g.drawRect(this.getEndPoint().getX() - 2, this.getEndPoint().getY() - 2, 4, 4);
		}
	}

	@Override
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}

	@Override
	public void moveTo(int x, int y) {
		endPoint.moveTo(x-(startPoint.getX()-endPoint.getX()), y-(startPoint.getY()-endPoint.getY()));
		startPoint.moveTo(x, y);
		
		
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line)o).length());
		}
		return 0;
	}
}

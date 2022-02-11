package Geometry;


import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Moveable, Comparable {
	protected boolean selected;
	protected Color colorLine,colorInside;
	
	public Color getColorLine() {
		return colorLine;
	}

	public void setColorLine(Color colorLine) {
		this.colorLine = colorLine;
	}

	public Color getColorInside() {
		return colorInside;
	}

	public void setColorInside(Color colorInside) {
		this.colorInside = colorInside;
	}

	public Shape() {
		colorLine = Color.BLACK;
		colorInside = Color.white;
	}
	
	public Shape(boolean selected) {
		this.selected = selected;
		
	}
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}

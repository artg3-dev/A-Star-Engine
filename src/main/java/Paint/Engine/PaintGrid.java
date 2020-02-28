/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 11:50 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.Engine;

import Paint.Interfaces.Paintable;
import StandardUses.Standard2D.Grid2D;
import StandardUses.Standard2D.Node2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PaintGrid extends Grid2D implements Paintable {
	private final int scaleFactor;
	private Node2D start, end;
	
	public PaintGrid(int width, int height, int scaleFactor) {
		super(width, height);
		this.start = getNode(0, 0);
		this.end = getNode(width - 1, height - 1);
		this.scaleFactor = scaleFactor;
	}
	
	public void setStart(Node2D start) {
		if (!start.isObstacle()) {
			this.start = start;
		}
	}
	
	public void setEnd(Node2D end) {
		if (!end.isObstacle()) {
			this.end = end;
		}
	}
	
	public Node2D getScaledNode(int x, int y) {
		int scaledX = (int) Math.floor(x / scaleFactor);
		int scaledY = (int) Math.floor(y / scaleFactor);
		return getNode(scaledX, scaledY);
	}
	
	public Node2D getScaledNode(Point p) {
		return getScaledNode(p.x, p.y);
	}
	
	public void setObstacle(Node2D node, boolean isObstacle) {
		setObstacle(node.x, node.y, isObstacle);
	}
	
	@Override
	public void setObstacle(int x, int y, boolean isObstacle) {
		if (!getNode(x, y).equals(start) && !getNode(x, y).equals(end)) {
			super.setObstacle(x, y, isObstacle);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		for (int x = 0; x < width * scaleFactor; x += scaleFactor) {
			for (int y = 0; y < height * scaleFactor; y += scaleFactor) {
				if (getScaledNode(x, y).isObstacle()) {
					g.setColor(Color.black);
					g.fillRect(x, y, scaleFactor, scaleFactor);
				} else {
					g.drawRect(x, y, scaleFactor, scaleFactor);
				}
			}
		}
		
		// Start
		int startX = start.x * scaleFactor;
		int startY = start.y * scaleFactor;
		g.setColor(Color.green);
		g.fillRect(startX + 1, startY + 1,
				scaleFactor - 1, scaleFactor - 1);
		
		// End
		int endX = end.x * scaleFactor;
		int endY = end.y * scaleFactor;
		g.setColor(Color.blue);
		g.fillRect(endX + 1, endY + 1,
				scaleFactor - 1, scaleFactor - 1);
	}
}

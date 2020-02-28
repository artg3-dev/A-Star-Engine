/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 1:02 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.Listeners;

import Paint.DrawingBoard.DrawingBoard;
import Paint.Engine.PaintGrid;
import StandardUses.Standard2D.Node2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingMouseListener
		implements MouseListener, MouseMotionListener {
	private final DrawingBoard canvas;
	private final PaintGrid grid;
	private Node2D currentNode;
	private boolean currentState;
	
	public DrawingMouseListener(DrawingBoard canvas, PaintGrid grid) {
		this.canvas = canvas;
		this.grid = grid;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isControlDown()) {
			System.out.println("control down");
		}
		if (canvas.getMousePosition() != null) {
			currentNode = grid.getScaledNode(e.getPoint());
			currentState = currentNode.isObstacle();
			if (currentNode.equals(currentNode)) {
				grid.setObstacle(currentNode, ! currentState);
				canvas.repaint();
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (canvas.getMousePosition() != null) {
			try {
				Node2D node = grid.getScaledNode(e.getPoint());
				if (! node.equals(currentNode)) {
					grid.setObstacle(node, ! currentState);
					currentNode = node;
					canvas.repaint();
				}
			} catch (Exception ex) {
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
}

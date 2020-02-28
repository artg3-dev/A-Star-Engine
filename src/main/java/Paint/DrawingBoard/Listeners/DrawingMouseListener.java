/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 1:02 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.Listeners;

import Paint.DrawingBoard.DrawingBoard;
import Paint.DrawingBoard.PaintGrid;
import StandardUses.Standard2D.Node2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingMouseListener
		implements MouseListener, MouseMotionListener {
	private final DrawingBoard canvas;
	private Node2D currentNode;
	private boolean dragState;
	
	public DrawingMouseListener(DrawingBoard canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		PaintGrid grid = canvas.getGrid();
		currentNode = grid.getScaledNode(e.getPoint());
		
		if (canvas.getCurrentMode() == DrawingBoard.OBSTACLE_MODE) {
			dragState = e.getButton() == MouseEvent.BUTTON1;
			if (canvas.getMousePosition() != null) {
				if (currentNode.equals(currentNode)) {
					grid.clearMarkings();
					grid.setObstacle(currentNode, dragState);
					canvas.repaint();
				}
			}
		} else if (canvas.getCurrentMode() == DrawingBoard.START_MODE) {
			grid.clearMarkings();
			grid.setStart(currentNode);
			canvas.repaint();
		}
		 else if (canvas.getCurrentMode() == DrawingBoard.END_MODE) {
		    grid.clearMarkings();
		 	grid.setEnd(currentNode);
		    canvas.repaint();
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
		PaintGrid grid = canvas.getGrid();
		if (canvas.getCurrentMode() == DrawingBoard.OBSTACLE_MODE) {
			if (canvas.getMousePosition() != null) {
				try {
					Node2D node = grid.getScaledNode(e.getPoint());
					if (! node.equals(currentNode)) {
						grid.clearMarkings();
						grid.setObstacle(node, dragState);
						currentNode = node;
						canvas.repaint();
					}
				} catch (Exception ex) {
				}
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
}

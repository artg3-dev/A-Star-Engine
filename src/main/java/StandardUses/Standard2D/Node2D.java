/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 5:22 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package StandardUses.Standard2D;

import Engine.Abstracts.AbstractNode;

import java.awt.Point;

public class Node2D extends Point {
	private boolean isObstacle;
	
	public Node2D(int x, int y, boolean isObstacle) {
		super(x, y);
		this.isObstacle = isObstacle;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != this.getClass()) {
			return false;
		} else {
			Node2D compared = (Node2D) obj;
			return this.x == compared.x && this.y == compared.y;
		}
	}
	
	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}
	
	public boolean isObstacle() {
		return isObstacle;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Node2D: (");
		sb.append(x);
		sb.append(", ");
		sb.append(y);
		sb.append(")");
		return sb.toString();
	}
}

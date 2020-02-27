/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/25/20, 4:27 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine.Abstracts;

import java.util.Objects;

public abstract class AbstractNode {
	protected final double x;
	protected final double y;
	protected final double z;
	
	public AbstractNode(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
}

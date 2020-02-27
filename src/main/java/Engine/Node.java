/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/24/20, 6:16 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Engine;

import java.util.Objects;

public class Node {
    private final double x;
    private final double y;
    
    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    @Override
    public int hashCode() {
            return Objects.hash(x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            Node compared = (Node) obj;
            return this.x == compared.x && this.y == compared.y;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node: (");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(")");
        return sb.toString();
    }
}
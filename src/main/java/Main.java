
/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/23/20, 10:11 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

import Engine.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    private final static Node end = new Node(5, 5);

    public static void main(String[] args) {
        Node start = new Node(0, 0);
    
        List<Node> result = runAStar(start);
        for (Node node : result) {
            System.out.println(node);
        }
    }

    private static List<Node> runAStar(Node start) {
        List<Node> openSet = new LinkedList();
        List<Node> closedSet = new ArrayList();
        Map<Node, Double> gScores = new HashMap();
        Map<Node, Node> cameFrom = new HashMap();
        openSet.add(start);
        gScores.put(start, 0.0);

        // Loops while there are Nodes in openSet
        while (!openSet.isEmpty()) {
            // Defining current as the Node with the lowest fScore in openSet
            Node current = null;
            for (Node i : openSet) {
                if (current == null || getF(i, gScores.get(i)) <
                        getF(current, gScores.get(current))) {
                    current = i;
                }
            }

            // If the end has been reached
            if (current.equals(end)) {
                System.out.println("Done!");
                // Do the reconstruct function here
                List<Node> result = reconstruct(cameFrom);
                return result;
            }
            
            // Remove current from openSet and evaluate each neighbor
            openSet.remove(current);
            closedSet.add(current);
            
            // For each neighbor of current
            for (Node neighbor : getNeighbors(current)) {
                if (!closedSet.contains(neighbor)) {
                    double tempG = gScores.get(current) +
                            getDistance(current, neighbor);
                    if (!openSet.contains(neighbor) || gScores.get(neighbor) == null) {
                        cameFrom.put(neighbor, current);
                        gScores.put(neighbor, tempG);
                    } else if (tempG <= gScores.get(neighbor)) {
                        cameFrom.put(neighbor, current);
                        gScores.put(neighbor, tempG);
                    }
                    
                    // Add neighbor to openSet if not already
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        
        // If openSet has no more elements
        System.out.println("No solution.");
        return null;
    }

    private static double getHeuristic(Node node) {
        int a = end.getX() - node.getX();
        int b = end.getY() - node.getY();
        double cSq = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(cSq);
    }

    private static double getF(Node node, double gScore) {
        return gScore + getHeuristic(node);
    }
    
    private static double getDistance(Node n1, Node n2) {
        int a = n2.getX() - n1.getX();
        int b = n2.getY() - n1.getY();
        double cSq = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(cSq);
    }
    
    private static ArrayList<Node> getNeighbors(Node n) {
        ArrayList<Node> neighbors = new ArrayList();
        int x = n.getX();
        int y = n.getY();
        for (int i = -1; i <= 1; i ++) {
            for (int j = -1; j <= 1; j ++) {
                int neighborX = x + i;
                int neighborY = y + j;
                if ((neighborX >= 0 && neighborY >= 0) &&
                        !(neighborX == x && neighborY == y))  {
                    neighbors.add(new Node(neighborX, neighborY));
                }
            }
        }
        
        return neighbors;
    }
    
    private static List<Node> reconstruct(Map<Node, Node> cameFrom) {
        List<Node> r = new LinkedList();
        r.add(end);
        Node current = end;
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            r.add(current);
        }
        
        return r;
    }
}

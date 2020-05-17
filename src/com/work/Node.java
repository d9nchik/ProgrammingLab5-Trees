package com.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Node implements DistanceCalculable{
    private final static int MAXIMUM_POINTS = 50;//BTree is cool, is it?
    private DistanceCalculable[] abstractNodes = new DistanceCalculable[MAXIMUM_POINTS + 1];
    private DistanceCalculable centralNode;
    private boolean root = true;
    private double radius = 0;
    private int counter = 0;

    public Node() {
    }

    @SuppressWarnings("unused")
    public Node(DistanceCalculable[] nodes) {
        for (DistanceCalculable distanceCalculable : nodes)
            addNode(distanceCalculable);
        this.root = false;
    }

    protected Node(DistanceCalculable[] nodes, int from, int to) {
        for (int i = from; i < to; i++) {
            addNode(nodes[i]);
        }
        this.root = false;
    }


    public DistanceCalculable[] addNode(DistanceCalculable node) {
        if (counter == 0) {
            abstractNodes[counter++] = node;
            centralNode = node;
        } else {
            radius = Math.max(centralNode.distance(node), radius);
            if (!(abstractNodes[0] instanceof Node)) {
                abstractNodes[counter++] = node;
            } else {
                int positionOfChoice = 0;
                for (int i = 1; i < counter; i++) {
                    if (abstractNodes[i].distance(node) < abstractNodes[positionOfChoice].distance(node))
                        positionOfChoice = i;
                }

                DistanceCalculable[] nodes = ((Node) abstractNodes[positionOfChoice]).addNode(node);
                if (nodes != null) {
                    abstractNodes[positionOfChoice] = new Node(nodes, 0, 3);
                    abstractNodes[counter++] = new Node(nodes, 3, 5);
                }
            }
            if (counter > MAXIMUM_POINTS) {
                rebuild();
                if (!root)
                    return abstractNodes;
                else {
                    DistanceCalculable[] temp = new DistanceCalculable[MAXIMUM_POINTS + 1];
                    temp[0] = new Node(abstractNodes, 0, MAXIMUM_POINTS/2+1);
                    temp[1] = new Node(abstractNodes, MAXIMUM_POINTS/2+1, MAXIMUM_POINTS);
                    counter=2;
                    abstractNodes = temp;
                }
            }
        }
        return null;
    }

    private void rebuild() {
        Arrays.sort(abstractNodes, Comparator.comparingDouble(n -> n.distance(centralNode)));
    }

    @Override
    public double getLat() {
        if (centralNode != null)
            return centralNode.getLat();
        return 0;
    }

    @Override
    public double getLon() {
        if (centralNode != null)
            return centralNode.getLon();
        return 0;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    public ArrayList<DistanceCalculable> getValues(DistanceCalculable distanceCalculable) {
        ArrayList<DistanceCalculable> abstractNodeArrayList = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            if (abstractNodes[i] instanceof Node) {
                if (abstractNodes[i].distance(distanceCalculable) <= abstractNodes[i].getRadius() + distanceCalculable.getRadius())
                    abstractNodeArrayList.addAll(((Node) abstractNodes[i]).getValues(distanceCalculable));
            } else if (abstractNodes[i].distance(distanceCalculable) < distanceCalculable.getRadius())
                abstractNodeArrayList.add(abstractNodes[i]);
        }
        return abstractNodeArrayList;
    }
}

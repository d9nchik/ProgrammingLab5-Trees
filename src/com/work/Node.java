package com.work;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Node extends AbstractNode {
    private final static int MAXIMUM_POINTS = 4;
    private AbstractNode[] abstractNodes = new AbstractNode[MAXIMUM_POINTS + 1];
    private AbstractNode centralNode;
    private boolean root = true;
    private double radius = 0;
    private int counter = 0;

    public Node() {
    }

    public Node(AbstractNode[] nodes) {
        for (AbstractNode abstractNode : nodes)
            addNode(abstractNode);
        this.root = false;
    }

    protected Node(AbstractNode[] nodes, int from, int to) {
        for (int i = from; i < to; i++) {
            addNode(nodes[i]);
        }
        this.root = false;
    }


    public AbstractNode[] addNode(AbstractNode node) {
        if (counter == 0) {
            abstractNodes[counter++] = node;
            centralNode = node;
        } else {
            if (!(abstractNodes[0] instanceof Node)) {
                double tempRadius = centralNode.distance(node);
                radius = Math.max(tempRadius, radius);
                abstractNodes[counter++] = node;
            } else {
                int positionOfChoice = 0;
                for (int i = 1; i < counter; i++) {
                    if (abstractNodes[i].distance(node) < abstractNodes[positionOfChoice].distance(node))
                        positionOfChoice = i;
                }
                double tempRadius = centralNode.distance(node);
                radius = Math.max(tempRadius, radius);
                AbstractNode[] nodes = ((Node) abstractNodes[positionOfChoice]).addNode(node);
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
                    AbstractNode[] temp = new AbstractNode[MAXIMUM_POINTS + 1];
                    temp[0] = new Node(abstractNodes, 0, 3);
                    temp[1] = new Node(abstractNodes, 3, 5);
                    counter=2;
                    abstractNodes=temp;
                }
            }
        }
        return null;
    }

    private void rebuild() {
        Arrays.sort(abstractNodes, Comparator.comparingDouble(n -> n.distance(centralNode)));
    }

    @Override
    protected double getLat() {
        if (centralNode != null)
            return centralNode.getLat();
        return 0;
    }

    @Override
    protected double getLon() {
        if (centralNode != null)
            return centralNode.getLon();
        return 0;
    }
}

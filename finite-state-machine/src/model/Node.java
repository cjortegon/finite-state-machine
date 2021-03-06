package model;

import java.util.ArrayList;

public class Node implements Comparable {

	public String name;
	public int groupPointer, consecutive;
	public boolean acceptor;
	public ArrayList<Edge> outgoingEdges, incomingEdges;

	public Node(String name) {
		this.name = name;
		this.outgoingEdges = new ArrayList<Edge>();
		this.incomingEdges = new ArrayList<Edge>();
	}

	public void addAllConnectionsFrom(Node node) {

		// Changing the pointers
		for (Edge edge : node.incomingEdges)
			edge.head = this;
		for (Edge edge : node.outgoingEdges)
			edge.tail = this;

		// Removing the redundancy
		for (int i = 0; i < outgoingEdges.size(); i++) {
			for (int j = i+1; j < outgoingEdges.size();) {
				if(outgoingEdges.get(i).head == outgoingEdges.get(j).head) {
					outgoingEdges.get(i).mergeInputWords(outgoingEdges.get(j).inputWordsArray);
					outgoingEdges.remove(j);
				} else
					j++;
			}
		}
		for (int i = 0; i < incomingEdges.size(); i++) {
			for (int j = i+1; j < incomingEdges.size();) {
				if(incomingEdges.get(i).tail == incomingEdges.get(j).tail) {
					incomingEdges.get(i).mergeInputWords(incomingEdges.get(j).inputWordsArray);
					incomingEdges.remove(j);
				} else
					j++;
			}
		}
	}

	@Override
	public Object clone() {
		Node clone = new Node(name+"");
		clone.acceptor = acceptor;
		clone.consecutive = consecutive;
		return clone;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Object node) {
		return consecutive - ((Node)node).consecutive;
	}

}

package com.okunu.graph;

public class MatrixEdge {

	public Vertex v1;
	public Vertex v2;
	public int weight;
	
	@Override
	public String toString() {
		String str = v1.info.toString() + "   " + v2.info.toString() + "   " + weight;
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MatrixEdge) {
			MatrixEdge temp = (MatrixEdge)obj;
			if (temp.v1.info.equals(v1.info) && temp.v2.info.equals(v2.info) && temp.weight == weight) {
				return true;
			}
			if (temp.v1.info.equals(v2.info) && temp.v2.info.equals(v1.info) && temp.weight == weight) {
				return true;
			}
		}
		return false;
	}
	
	
}

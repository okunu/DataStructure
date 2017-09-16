package com.okunu.graph_matrix;

public class Vertex {

	public char label;
	public boolean isVisited;
	
	public Vertex(char lab){
		label = lab;
		isVisited = false;
	}

	@Override
	public String toString() {
		return label+"";
	}
	
	
}

package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;

public class BellmanFord {

	public MatrixGraph initDigraph(){
		MatrixGraph graph = new MatrixGraph(5);
		graph.setType(GRAPH_TYPE.digraph);
		
        Vertex s = new Vertex("s");
        Vertex t = new Vertex("t");
        Vertex x = new Vertex("x");
        Vertex y = new Vertex("y");
        Vertex z = new Vertex("z");
        
        graph.addVertex(s);
        graph.addVertex(t);
        graph.addVertex(x);
        graph.addVertex(y);
        graph.addVertex(z);
        
        graph.addEdge(s, t, 6);
        graph.addEdge(s, y, 7);
        
        graph.addEdge(t, x, 5);
        graph.addEdge(t, y, 8);
        graph.addEdge(t, z, -4);
        
        graph.addEdge(x, t, -2);
        
        graph.addEdge(y, z, 9);
        graph.addEdge(y, x, -3);
        
        graph.addEdge(z, x, 7);
        graph.addEdge(z, s, 2);
        
        return graph;
    }
	
	public void init_single_source(MatrixGraph graph, Vertex s){
		List<Vertex> list = graph.mList;
		for (Vertex vertex : list) {
			vertex.d = Integer.MAX_VALUE;
			vertex.pre = null;
		}
		s.d = 0;
	}
	
	public void relax(Vertex u, Vertex v, int w){
		if (v.d > u.d + w) {
			v.d = u.d + w;
			v.pre = u;
		}
	}
	
	public boolean bellman_ford(){
		MatrixGraph graph = initDigraph();
		Vertex s = graph.mList.get(0);
		init_single_source(graph, s);
		
		List<Vertex> list = graph.mList;
//		for (Vertex vertex : list) {
//			System.out.println(vertex);
//		}
		
		MatrixEdge[][] edges = graph.mEdges;
		List<MatrixEdge> edgeList = new ArrayList<>();
		int arrayLength = graph.maxLength;
		MatrixEdge edge = null;
		
		for (int i = 0; i < arrayLength; i++) {
			for (int j = 0; j < arrayLength; j++) {
				edge = edges[i][j];
				if (edge != null) {
					edgeList.add(edge);
				}
			}
		}
		//每条边都要循环 edgeList.size()-1 次，才能计算出正确答案。
		for (int i = 1; i < edgeList.size(); i++) {
			for (int j = 0; j < edgeList.size(); j++) {
				edge = edgeList.get(j);
				relax(edge.v1, edge.v2, edge.weight);
			}
		}
		
//		for (Vertex vertex : list) {
//			System.out.println(vertex);
//		}
		
		for (int i = 0; i < arrayLength; i++) {
			for (int j = 0; j < arrayLength; j++) {
				edge = edges[i][j];
				if (edge != null) {
					if (edge.v2.d > edge.v1.d + edge.weight) {
						return false;
					}
				}
			}
		}
		
		for (Vertex vertex : list) {
			System.out.println(vertex);
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		BellmanFord bellmanFord = new BellmanFord();
		bellmanFord.bellman_ford();
	}
}

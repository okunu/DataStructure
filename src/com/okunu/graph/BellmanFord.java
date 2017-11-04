package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;

/*
 * 单源最短路径bellman ford算法，计算权重可为负有环的算法
 */
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
		//因为s到最远的端点最多要经过 edgeList.size()-1 条边，如果松驰次数少了
		//某个前顶点的d值之前改变后，就不会得到反馈。
		for (int i = 1; i < edgeList.size(); i++) {
			for (int j = 0; j < edgeList.size(); j++) {
				edge = edgeList.get(j);
				relax(edge.v1, edge.v2, edge.weight);
			}
		}
		//第i个点经过i-1次数松驰就能得到最短距离
		//最远的点经过 edgeList.size()-1 次松驰也会得到最短距离。
		//所以，再次遍历，如果还存在能松驰的情况，那一定是有环路，这样的情况不存在最短距离。
		for (int i = 0; i < edgeList.size(); i++) {
			edge = edgeList.get(i);
			if (edge.v2.d > edge.v1.d + edge.weight) {
				return false;
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

package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import com.okunu.graph.Graph.GRAPH_TYPE;
import com.okunu.graph.Vertex.COLOR;

public class MatrixGraph {

	public List<Vertex> mList;
    public GRAPH_TYPE mType = GRAPH_TYPE.undigraph;
    public int maxLength;
    public MatrixEdge[][] mEdges;
    
    public MatrixGraph(int maxLength){
    	this.maxLength = maxLength;
    	mList = new ArrayList<>();
    	mEdges = new MatrixEdge[maxLength][maxLength];
    }
    
    public void addVertex(Vertex vertex){
    	mList.add(vertex);
    }
    
    public void addEdge(Vertex v1, Vertex v2, int weight){
    	int index1 = mList.indexOf(v1);
    	int index2 = mList.indexOf(v2);
    	if (index1 >=0 && index2 >= 0) {
    		MatrixEdge edge = new MatrixEdge();
    		edge.v1 = v1;
    		edge.v2 = v2;
    		edge.weight = weight;
    		mEdges[index1][index2] = edge;
    		if (mType == GRAPH_TYPE.undigraph) {
    			MatrixEdge edge2 = new MatrixEdge();
    			edge2.v1 = v2;
    			edge2.v2 = v1;
    			edge2.weight = weight;
        		mEdges[index2][index1] = edge2;
			}
		}
    }
    
    public void addEdge(Vertex v1, Vertex v2){
    	addEdge(v1, v2, 1);
    }
    
    public void initUndigraph(){
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        
        addVertex(v1);
        addVertex(v2);
        addVertex(v3);
        addVertex(v4);
        addVertex(v5);
        
        addEdge(v1, v2);
        addEdge(v1, v5);
        addEdge(v2, v4);
        addEdge(v2, v5);
        addEdge(v2, v3);
        addEdge(v5, v4);
        addEdge(v4, v3);
    }
    
    public void bfs(Vertex vertex){
    	if (vertex == null) {
			return;
		}
    	int index = mList.indexOf(vertex);
    	if (index == -1) {
			return;
		}
    	for (Vertex v : mList) {
			v.color = COLOR.WHITE;
		}
    	Queue<Vertex> queue = new LinkedBlockingDeque<>();
    	queue.add(vertex);
    	vertex.color = COLOR.GRAY;
    	vertex.d = 0;
    	System.out.println(vertex);
    	Vertex temp = null;
    	while (!queue.isEmpty()) {
			temp = queue.peek();
			int tempIndex = mList.indexOf(temp);
			for (int i = 0; i < maxLength; i++) {
				if (mEdges[tempIndex][i] != null && mEdges[tempIndex][i].v2.color == COLOR.WHITE) {
					mEdges[tempIndex][i].v2.color = COLOR.GRAY;
					mEdges[tempIndex][i].v2.d = temp.d + 1;
					System.out.println(mEdges[tempIndex][i].v2);
					queue.add(mEdges[tempIndex][i].v2);
				}
			}
			queue.remove(temp);
		}
    }
    
    public Vertex getVertex(int i){
        if (mList.size() > i) {
            return mList.get(i);
        }
        return null;
    }
    
    public void dfs(){
    	for (Vertex v : mList) {
			v.color = COLOR.WHITE;
		}
    	for (Vertex v : mList) {
    		if (v.color == COLOR.WHITE) {
    			dfsVisit(v);
			}
		}
    }
    
    int time = 0;
    public void dfsVisit(Vertex vertex){
    	time++;
    	vertex.color = COLOR.GRAY;
    	vertex.d = time;
    	int index = mList.indexOf(vertex);
    	Vertex v2 = null;
    	for (int i = 0; i < maxLength; i++) {
			v2 = mEdges[index][i] == null ? null : mEdges[index][i].v2;
			if (v2 != null && v2.color == COLOR.WHITE) {
				dfsVisit(v2);
			}
		}
    	time++;
    	vertex.color = COLOR.BLACK;
    	vertex.f = time++;
    	System.out.println(vertex);
    }
    
    public void initDigraph(){
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        
        addVertex(v1);
        addVertex(v2);
        addVertex(v3);
        addVertex(v4);
        addVertex(v5);
        
        addEdge(v1, v2, 1);
        addEdge(v1, v4, 1);
        
        addEdge(v2, v3, 1);
        addEdge(v2, v4, 1);
        
        addEdge(v3, v5, 1);
        
        addEdge(v4, v3, 1);
        addEdge(v4, v5, 1);
    }
    
    public void setType(GRAPH_TYPE type){
    	mType = type;
    }
    
    public static void main(String[] args) {
		MatrixGraph graph = new MatrixGraph(10);
//		graph.initUndigraph();
//		graph.bfs(graph.getVertex(0));
		
		graph.setType(GRAPH_TYPE.digraph);
		graph.initDigraph();
		graph.dfs();
	}
}

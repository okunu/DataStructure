package com.okunu.graph_matrix;

/**
 * 这个包里的内容是以前写的，用邻接矩阵存储数据
 * @author oukun.ok
 *
 */
public class Graph {
	private final int MAX_VERS = 20;
	private Vertex[] vertexList;
	private int adjMat[][];
	private int nVerts;
	private VertexStack mStack;
	private VertexQueue mQueue;
	
	public Graph(){
		vertexList = new Vertex[MAX_VERS];
		adjMat = new int[MAX_VERS][MAX_VERS];
		for (int i = 0; i < MAX_VERS; i++) {
			for (int j = 0; j < MAX_VERS; j++) {
				adjMat[i][j] = 0;
			}
		}
		nVerts = 0;
		mStack = new VertexStack();
		mQueue = new VertexQueue(10);
	}
	
	public void addVertex(char lab){
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void addEdge(int start,int end){
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	
	public void displayVertex(int v){
		System.out.print(vertexList[v].label+"\t");
	}
	
	public int getAdjUnvisitedVertex(int v){
		for (int i = 0; i < nVerts; i++) {
			if (adjMat[v][i] == 1 && !vertexList[i].isVisited) {
				return i;
			}
		}
		return -1;
	}
	
	public int getIndex(Vertex vertex){
		for (int i = 0; i < nVerts; i++) {
			if (vertexList[i] == vertex) {
				return i;
			}
		}
		return -1;
	}
	
	//深度优先算法
	public void dfs(){
		vertexList[0].isVisited = true;
		displayVertex(0);
		mStack.push(vertexList[0]);
		
		while (!mStack.isEmpty()) {
			int top = getIndex(mStack.peek());
			int v = getAdjUnvisitedVertex(top);
			if (v == -1) {
				mStack.pop();
			}else {
				vertexList[v].isVisited = true;
				displayVertex(v);
				mStack.push(vertexList[v]);
			}
		}
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	//最小生成树，使用深度优先算法
	public void mst(){
		while (!mStack.isEmpty()) {
			Vertex vertex = mStack.peek();
			int index = getIndex(vertex);
			int v = getAdjUnvisitedVertex(index);
			if (v == -1) {
				mStack.pop();
			}else {
				vertexList[v].isVisited = true;
				mStack.push(vertexList[v]);
				System.out.print(vertex + "--" + vertexList[v] + "   ");
			}
		}
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	//广度优先算法
	public void bfs(){
		vertexList[0].isVisited = true;
		displayVertex(0);
		mQueue.insert(vertexList[0]);
		
		while (!mQueue.isNull()) {
			Vertex temp = mQueue.remove();
			int index = getIndex(temp);
			int v1 = -1;
			while ((v1 = getAdjUnvisitedVertex(index)) != -1) {
				displayVertex(v1);
				vertexList[v1].isVisited = true;
				mQueue.insert(vertexList[v1]);
			}
		}
		for (int i = 0; i < nVerts; i++) {
			vertexList[i].isVisited = false;
		}
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex('a');
		graph.addVertex('b');
		graph.addVertex('c');
		graph.addVertex('d');
		graph.addVertex('e');
		graph.addVertex('f');
		graph.addVertex('g');
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(0, 6);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		
//		for (int i = 0; i < graph.nVerts; i++) {
//			for (int j = 0; j < graph.nVerts; j++) {
//				System.out.print(graph.adjMat[i][j]+"  ");
//			}
//			System.out.println();
//		}
		
		graph.dfs();
		System.out.println();
		graph.bfs();
		System.out.println();
		
		graph.vertexList[0].isVisited = true;
		graph.mStack.push(graph.vertexList[0]);
		graph.mst();
	}
	
}

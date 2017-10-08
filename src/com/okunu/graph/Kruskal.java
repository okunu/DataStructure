package com.okunu.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;
import com.okunu.graph.Vertex.COLOR;

public class Kruskal {

	public MatrixGraph initUndigraph(){
		MatrixGraph graph = new MatrixGraph(10);
		graph.setType(GRAPH_TYPE.undigraph);
		
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        Vertex i = new Vertex("i");
        
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);
        
        graph.addEdge(a, b, 4);
        graph.addEdge(a, h, 8);
        graph.addEdge(b, h, 11);
        graph.addEdge(b, c, 8);
        graph.addEdge(c, i, 2);
        graph.addEdge(h, i, 7);
        graph.addEdge(i, g, 6);
        graph.addEdge(h, g, 1);
        graph.addEdge(g, f, 2);
        graph.addEdge(c, f, 4);
        graph.addEdge(c, d, 7);
        graph.addEdge(d, f, 14);
        graph.addEdge(f, e, 10);
        graph.addEdge(d, e, 9);
        
        return graph;
    }
	
	/*
	 * 将边按权重从小到大排列
	 * 如果边的两个顶点在不同树中则将这两棵树合并，且加入森林
	 * 在森林中的顶点则是连通的，已连通的顶点在森林中，也不会重复添加边，达到无环要求
	 */
	public void kruskal(){
		MatrixGraph graph = initUndigraph();
		MatrixEdge[] array = getEdges(graph, null);
		//为边排序
		sort(array, 0, array.length-1);
		//构造森林，森林中的元素就是一棵棵树，初始树中只包含一个顶点
		ArrayList<HashSet<Vertex>> list = new ArrayList<>();
		for (int i = 0; i < graph.mList.size(); i++) {
			HashSet<Vertex> set = new HashSet<>();
			set.add(graph.mList.get(i));
			list.add(set);
		}
		MatrixEdge edge = null;
		for (int i = 0; i < array.length; i++) {
			edge = array[i];
			Vertex v1 = edge.v1;
			Vertex v2 = edge.v2;
			int count1 = -1;
			int count2 = -1;
			for (int j = 0; j < list.size(); j++) {
				HashSet<Vertex> set = list.get(j);
				//找到顶点在森林中的位置
				if (set.contains(v1)) {
					count1 = j;
				}
				if (set.contains(v2)) {
					count2 = j;
				}
			}
			if (count1 == -1 || count2 == -1) {
				return;
			}else {
				if (count1 != count2) {
					//如果顶点分别在不同的树中，则合并这两棵树并且删除之前的旧树
					//因为会合并树，所以已经连通的顶点就会在合并的树中，在同一棵树中
					//count1等于count2，所以能保证无环
					//最终森林中只有一棵树，且包含全部顶点
					HashSet<Vertex> set1 = list.get(count1);
					HashSet<Vertex> set2 = list.get(count2);
					set1.addAll(set2);
					if (count1 < count2) {
						list.remove(count1);
						list.remove(count2-1);
					}else {
						list.remove(count2);
						list.remove(count1-1);
					}
					list.add(set1);
					System.out.println(edge);
				}
			}
		}
	}
	
	public MatrixEdge[] getEdges(MatrixGraph graph, Vertex vertex){
		ArrayList<MatrixEdge> edges = new ArrayList<MatrixEdge>();
		for (int i = 0; i < graph.maxLength; i++) {
			for (int j = 0; j < graph.maxLength; j++) {
				if (graph.mEdges[i][j] != null && !edges.contains(graph.mEdges[i][j])) {
					edges.add(graph.mEdges[i][j]);
//					System.out.println(graph.mEdges[i][j]);
				}
			}
		}
		
		return (MatrixEdge[]) edges.toArray(new MatrixEdge[edges.size()]);
    }
	
	public int partion(MatrixEdge[] array, int start, int end){
		MatrixEdge temp = array[start];
		int i = start;
		int j = end;
		while (i < j) {
			while (i < j && array[j].weight > temp.weight) {
				j--;
			}
			if (i < j) {
				array[i] = array[j];
				i++;
			}
			while (i < j && array[i].weight < temp.weight) {
				i++;
			}
			if (i < j) {
				array[j] = array[i];
				j--;
			}
		}
		if (i < array.length) {
			array[i] = temp;
		}
		return i;
	}
	
	public void sort(MatrixEdge[] array, int start, int end){
		if (start < end) {
			int i = partion(array, start, end);
			sort(array, start, i-1);
			sort(array, i+1, end);
		}
	}
	
	public static void main(String[] args) {
		Kruskal kruskal = new Kruskal();
		kruskal.kruskal();
	}
}

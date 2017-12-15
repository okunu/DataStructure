package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;

public class Johnson {
    
    public static final int VERTEX_NUM = 6;
    
    public MatrixGraph initDigraph(){
        MatrixGraph graph = new MatrixGraph(VERTEX_NUM);
        graph.setType(GRAPH_TYPE.digraph);
        
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
        
        graph.addEdge(v1, v2, 3);
        graph.addEdge(v1, v3, 8);
        graph.addEdge(v1, v5, -4);
        
        graph.addEdge(v2, v4, 1);
        graph.addEdge(v2, v5, 7);
        
        graph.addEdge(v3, v2, 4);
        
        graph.addEdge(v4, v3, -5);
        graph.addEdge(v4, v1, 2);
        
        graph.addEdge(v5, v4, 6);
        
        return graph;
    }
    
    public void johnson(MatrixGraph graph){
        Vertex s = new Vertex("s");
        graph.addVertex(s);
        for (int i = 0; i < graph.mList.size(); i++) {
            graph.addEdge(s, graph.mList.get(i), 0);
        }
        //计算s点到其它点的最短距离
        ArrayList<Integer> h = bellman_ford(graph, s);
        //重新计算除s以外的其它点权重
        ArrayList<MatrixEdge> edges = new ArrayList<>();
        MatrixEdge temp = null;
        for (int i = 0; i < VERTEX_NUM; i++) {
            for (int j = 0; j < VERTEX_NUM; j++) {
                temp = graph.mEdges[i][j];
                if (temp != null && temp.v1 != s && temp.v2 != s) {
                    edges.add(temp);
                }
            }
        }
        
        System.out.println(" -------- ");
        
        for (int i = 0; i < edges.size(); i++) {
            temp = edges.get(i);
            temp.weight = temp.weight + h.get(graph.mList.indexOf(temp.v1)) - h.get(graph.mList.indexOf(temp.v2));
            System.out.print(temp + " | ");
        }
        System.out.println();
        System.out.println(" --------- ");
        
        graph.removeVertex(s);
        
        //根据重新计算的非负权重值，遍历调用dijkstra算法
        for (int i = 0; i < graph.mList.size(); i++) {
            dijkstra(graph, graph.mList.get(i));
        }
    }
    
    
    public void dijkstra(MatrixGraph graph, Vertex s){
        init_single_source(graph, s);
        
        Vertex[] datas = new Vertex[graph.mList.size()];
        for (int i = 0; i < graph.mList.size(); i++) {
            datas[i] = graph.mList.get(i);
        }
        MinPriorityQueue queue = new MinPriorityQueue(datas);
        Vertex u = null;
        MatrixEdge edge = null;
        while (queue.size() > 0) {
            u = (Vertex) queue.heapExtractMin();
            int index = graph.mList.indexOf(u);
            for (int i = 0; i < VERTEX_NUM; i++) {
                edge = graph.mEdges[index][i];
                if (edge != null) {
                    relax(edge.v1, edge.v2, edge.weight);
                    queue.checkMinHeap();
                }
            }
        }
        
      for (Vertex vertex : graph.mList) {
          System.out.println(vertex);
      }
      System.out.println(" ------ ");
    }
    
    
    public void relax(Vertex u, Vertex v, int w){
        if (v.d > u.d + w) {
            v.d = u.d + w;
            v.pre = u;
        }
    }
    
    public void init_single_source(MatrixGraph graph, Vertex s){
        List<Vertex> list = graph.mList;
        for (Vertex vertex : list) {
            vertex.d = 10000;//Integer.MAX_VALUE;
            vertex.pre = null;
        }
        s.d = 0;
    }
    
    public ArrayList<Integer> bellman_ford(MatrixGraph graph, Vertex s){
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
        for (int i = 1; i < graph.mList.size(); i++) {
            for (int j = 0; j < edgeList.size(); j++) {
                edge = edgeList.get(j);
                relax(edge.v1, edge.v2, edge.weight);
            }
        }
        
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength; j++) {
                edge = edges[i][j];
                if (edge != null) {
                    if (edge.v2.d > edge.v1.d + edge.weight) {
                        return null;
                    }
                }
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != s) {
                result.add(list.get(i).d);
                System.out.print(list.get(i).d + "   ");
            }
        }
        System.out.println();
        return result;
    }
    
    public static void main(String[] args) {
        Johnson johnson = new Johnson();
        MatrixGraph graph = johnson.initDigraph();
        johnson.johnson(graph);
    }
}

package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;

/*
 * 
 */
public class Dijkstra {

    public static final int VERTEX_NUM = 5;
    
    public MatrixGraph initDigraph(){
        MatrixGraph graph = new MatrixGraph(VERTEX_NUM);
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
        
        graph.addEdge(s, t, 10);
        graph.addEdge(s, y, 5);
        
        graph.addEdge(t, x, 1);
        graph.addEdge(t, y, 2);
        
        graph.addEdge(x, t, 4);
        
        graph.addEdge(y, z, 2);
        graph.addEdge(y, x, 9);
        graph.addEdge(y, t, 3);
        
        graph.addEdge(z, x, 6);
        graph.addEdge(z, s, 7);
        
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
    
    public void dijkstra(){
        MatrixGraph graph = initDigraph();
        Vertex s = graph.mList.get(0);
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
                    queue.getIndex(edge.v2);
                    queue.checkMinHeap();
                }
            }
        }
        
      for (Vertex vertex : graph.mList) {
          System.out.println(vertex);
      }
    }
    
    public void relax(Vertex u, Vertex v, int w){
        if (v.d > u.d + w) {
            v.d = u.d + w;
            v.pre = u;
        }
    }
    
    
    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra();
    }
}

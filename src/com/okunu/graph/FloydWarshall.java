package com.okunu.graph;

import com.okunu.graph.Graph.GRAPH_TYPE;

public class FloydWarshall {

    public static final int VERTEX_NUM = 5;
    
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
    
    public void floydWashall(MatrixEdge[][] edges){
        int n = edges.length;
        int[][] d = new int[n][n];
        
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (edges[i][j] != null) {
                    d[i][j] = edges[i][j].weight;
                }else {
                    if (i == j) {
                        d[i][j] = 0;
                    }else {
                        d[i][j] = 10000;
                    }
                }
            }
        }
        
        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d.length; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j] + "   ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        FloydWarshall fw = new FloydWarshall();
        MatrixGraph graph = fw.initDigraph();
        fw.floydWashall(graph.mEdges);
    }
}

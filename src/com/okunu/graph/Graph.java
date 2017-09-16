package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import com.okunu.graph.Vertex.COLOR;


public class Graph {

    public static enum GRAPH_TYPE{digraph, undigraph};
    public List<Vertex> mList;
    public GRAPH_TYPE mType = GRAPH_TYPE.undigraph;
    
    public Graph(GRAPH_TYPE type){
        mList = new ArrayList<>();
        mType = type;
    }
    
    public void addVertex(Vertex vertex){
        mList.add(vertex);
    }
    
    public void addArc(Vertex v, Vertex u){
        addArc(v, u, 1);
    }
    
    public void addArc(Vertex v, Vertex u, int weight){
        Arc arc = new Arc();
        arc.vertex = u;
        arc.weight = weight;
        arc.next = null;
        if (v.firstArc == null) {
            v.firstArc = arc;
        }else {
            Arc temp = v.firstArc;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = arc;
        }
        if (mType == GRAPH_TYPE.undigraph) {
            arc.vertex = v;
            arc.weight = weight;
            arc.next = null;
            if (u.firstArc == null) {
                u.firstArc = arc;
            }else {
                Arc temp = u.firstArc;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = arc;
            }
        }
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
        
        addArc(v1, v2);
        addArc(v1, v5);
        addArc(v2, v4);
        addArc(v2, v5);
        addArc(v2, v3);
        addArc(v5, v4);
        addArc(v4, v3);
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
        
        addArc(v1, v2, 1);
        addArc(v1, v4, 1);
        
        addArc(v2, v3, 1);
        addArc(v2, v4, 1);
        
        addArc(v3, v5, 1);
        
        addArc(v4, v3, 1);
        addArc(v4, v5, 1);
    }
    
    int time = 0;
    public void dfs(){
        for (Vertex vertex : mList) {
            vertex.color = COLOR.WHITE;
        }
        time = 0;
        for (Vertex vertex : mList) {
            if (vertex.color == COLOR.WHITE) {
                dfsVisit(vertex);
            }
        }
    }
    
    public Vertex getVertex(int i){
        if (mList.size() > i) {
            return mList.get(i);
        }
        return null;
    }
    
    public void dfsVisit(Vertex vertex){
        if (vertex == null) {
            return;
        }
        time++;
        vertex.color = COLOR.GRAY;
        vertex.d = time;
        Arc arc = vertex.firstArc;
        while (arc != null) {
            if (arc.vertex.color == COLOR.WHITE) {
                dfsVisit(arc.vertex);
            }
            arc = arc.next;
        }
        time++;
        vertex.f = time;
        System.out.println(vertex);
        vertex.color = COLOR.BLACK;
    }
    
    public void bfs(Vertex vertex){
        if (vertex == null) {
            return;
        }
        for (Vertex v : mList) {
            v.color = COLOR.WHITE;
        }
        int index = mList.indexOf(vertex);
        if (index == -1) {
            System.out.println("error vertex");
            return;
        }
        Queue<Vertex> queue = new LinkedBlockingDeque<Vertex>();
        queue.add(vertex);
        System.out.println(vertex);
        vertex.color = COLOR.GRAY;
        Arc arc = null;
        Vertex temp = null;
        while (!queue.isEmpty()) {
            temp = queue.peek();
            arc = temp.firstArc;
            while (arc != null) {
                if (arc.vertex.color == COLOR.WHITE) {
                    System.out.println(arc.vertex);
                    arc.vertex.color = COLOR.GRAY;
                    queue.add(arc.vertex);
                    break;
                }
                arc = arc.next;
            }
            if (arc == null) {
                temp.color = COLOR.BLACK;
                queue.remove(temp);
            }
            if (queue.isEmpty()) {
                for (Vertex vertex2 : mList) {
                    if (vertex2.color == COLOR.WHITE) {
                        queue.add(vertex2);
                        System.out.println(vertex2);
                        vertex2.color = COLOR.GRAY;
                        break;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
//        Graph graph = new Graph(GRAPH_TYPE.digraph);
//        graph.initDigraph();
////        graph.dfs();
//        
//        graph.bfs(graph.getVertex(4));
        
        Graph graph = new Graph(GRAPH_TYPE.undigraph);
        graph.initUndigraph();
        graph.bfs(graph.getVertex(1));
    }
}

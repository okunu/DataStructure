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
        	Arc arc2 = new Arc();
        	arc2.vertex = v;
        	arc2.weight = weight;
            arc2.next = null;
            if (u.firstArc == null) {
                u.firstArc = arc2;
            }else {
                Arc temp = u.firstArc;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = arc2;
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
    /*
     * 深度优先算法，下面的深度优先算法是最正确的，以栈来实现深度优先算法有可能有问题
     * 下面的计算方法最容易理解，访问完自己后立即访问自己的非访问后续结点
     * 同时深度优先算法，可以计算每个结点访问顺序，最终实现拓扑排序。
     * 即f最大的，放在前面
     */
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
    
    /*
     * 广度优先算法
     * 广度优先算法可以计算出到遍历起点的最短路径，因为最短路径一定是此顶点的前驱距离+1得到的
     * 有权值的后续讨论，如果无权图这个结论是对的
     */
    public void bfs(Vertex vertex){
        if (vertex == null) {
            return;
        }
        for (Vertex v : mList) {
            v.color = COLOR.WHITE;
        }
        int dis = 0;
        int index = mList.indexOf(vertex);
        if (index == -1) {
            System.out.println("error vertex");
            return;
        }
        Queue<Vertex> queue = new LinkedBlockingDeque<Vertex>();
        queue.add(vertex);
        vertex.color = COLOR.GRAY;
        vertex.d = dis;
        System.out.println(vertex);
        Arc arc = null;
        Vertex temp = null;
        while (!queue.isEmpty()) {
            temp = queue.peek();
            arc = temp.firstArc;
            while (arc != null) {
                if (arc.vertex.color == COLOR.WHITE) {
                	arc.vertex.color = COLOR.GRAY;
                	arc.vertex.d = temp.d + 1;
                    System.out.println(arc.vertex);
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
                        vertex2.color = COLOR.GRAY;
                        System.out.println(vertex2);
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
        graph.bfs(graph.getVertex(2));
    }
}

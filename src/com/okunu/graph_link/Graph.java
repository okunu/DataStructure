package com.okunu.graph_link;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import com.okunu.graph_link.GraphVertex.COLOR;

/**
 * 使用邻接链表存储数据
 * @author oukun.ok
 */
public class Graph {

    public GraphVertex[][] mVertexs;
    public int mMaxSize;
    
    public Graph(){
        mMaxSize = 5;
        mVertexs = new GraphVertex[mMaxSize][mMaxSize];
        init();
    }
    
    public void init(){
        GraphVertex v1 = new GraphVertex(1+"");
        GraphVertex v2 = new GraphVertex(2+"");
        GraphVertex v3 = new GraphVertex(3+"");
        GraphVertex v4 = new GraphVertex(4+"");
        GraphVertex v5 = new GraphVertex(5+"");
        
        mVertexs[0][0] = v1;
        mVertexs[0][1] = v2;
        mVertexs[0][2] = v5;
        
        mVertexs[1][0] = v2;
        mVertexs[1][1] = v1;
        mVertexs[1][2] = v5;
        mVertexs[1][3] = v3;
        mVertexs[1][4] = v4;
        
        mVertexs[2][0] = v3;
        mVertexs[2][1] = v2;
        mVertexs[2][2] = v4;
        
        mVertexs[3][0] = v4;
        mVertexs[3][1] = v2;
        mVertexs[3][2] = v5;
        mVertexs[3][4] = v3;
        
        mVertexs[4][0] = v5;
        mVertexs[4][1] = v4;
        mVertexs[4][2] = v1;
        mVertexs[4][3] = v2;
    }
    
    public int findIndex(GraphVertex vertex){
        for (int i = 0; i < mMaxSize; i++) {
            if(mVertexs[i][0] == vertex){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * 广度优先遍历，值得注意的是，遍历后v.d的值即是s到v的最短路径。
     * 广度优先遍历后得到的值d就是最短路径，定理
     */
    public void bfs(){
        for (int i = 0; i < mMaxSize; i++) {
            mVertexs[i][0].color = COLOR.WHITE;
            mVertexs[i][0].d = Integer.MAX_VALUE;
            mVertexs[i][0].pre = null;
        }
        Queue<GraphVertex> queue = new LinkedBlockingDeque<GraphVertex>();
        queue.add(mVertexs[0][0]);
        mVertexs[0][0].color = COLOR.GRAY;
        mVertexs[0][0].d = 0;
        mVertexs[0][0].pre = null;
        while (!queue.isEmpty()) {
            GraphVertex vertex = queue.poll();
            System.out.println(vertex.getName() + " d = " + vertex.d);
            int index = findIndex(vertex);
            GraphVertex v = null;
            for (int i = 1; i < mMaxSize; i++) {
                v = mVertexs[index][i];
                if (v != null && v.color == COLOR.WHITE) {
                    v.color = COLOR.GRAY;
                    v.d = vertex.d + 1;
                    v.pre = vertex;
                    queue.add(v);
                }
            }
            vertex.color = COLOR.BLACK;
        }
    }
    
    /*
     * 广度优先遍历,按起点来
     */
    public void bfs(GraphVertex s){
        for (int i = 0; i < mMaxSize; i++) {
            mVertexs[i][0].color = COLOR.WHITE;
            mVertexs[i][0].d = Integer.MAX_VALUE;
            mVertexs[i][0].pre = null;
        }
        Queue<GraphVertex> queue = new LinkedBlockingDeque<GraphVertex>();
        queue.add(s);
        s.color = COLOR.GRAY;
        s.d = 0;
        s.pre = null;
        while (!queue.isEmpty()) {
            GraphVertex vertex = queue.poll();
            System.out.println(vertex.getName() + " d = " + vertex.d);
            int index = findIndex(vertex);
            GraphVertex v = null;
            for (int i = 1; i < mMaxSize; i++) {
                v = mVertexs[index][i];
                if (v != null && v.color == COLOR.WHITE) {
                    v.color = COLOR.GRAY;
                    v.d = vertex.d + 1;
                    v.pre = vertex;
                    queue.add(v);
                }
            }
            vertex.color = COLOR.BLACK;
        }
    }
    
    public void dfs(){
        for (int i = 0; i < mMaxSize; i++) {
            mVertexs[i][0].color = COLOR.WHITE;
        }
        mVertexs[0][0].color = COLOR.GRAY;
        Stack<GraphVertex> stack = new Stack<>();
        stack.push(mVertexs[0][0]);
        System.out.println(stack.peek().name);
        while (!stack.isEmpty()) {
            GraphVertex vertex = stack.peek();
            int index = findIndex(vertex);
            GraphVertex v = null;
            for (int i = 1; i < mMaxSize; i++) {
                v = mVertexs[index][i];
                if (v != null && v.color == COLOR.WHITE) {
                    System.out.println(v.name);
                    v.color = COLOR.GRAY;
                    stack.push(v);
                    break;
                }
                if (i == 4) {
                    stack.pop();
                }
            }
        }
    }
    
    public void dfs2(){
        for (int i = 0; i < mMaxSize; i++) {
            mVertexs[i][0].color = COLOR.WHITE;
            mVertexs[i][0].pre = null;
        }
        for (int i = 0; i < mMaxSize; i++) {
            if (mVertexs[i][0].color == COLOR.WHITE) {
                dfsVisit(mVertexs[i][0]);
            }
        }
    }
    
    public void dfsVisit(GraphVertex u){
        u.color = COLOR.GRAY;
        System.out.println(u.name);
        int index = findIndex(u);
        for (int i = 1; i < mMaxSize; i++) {
            if (mVertexs[index][i] != null && mVertexs[index][i].color == COLOR.WHITE) {
                mVertexs[index][i].pre = u;
                dfsVisit(mVertexs[index][i]);
            }
        }
        u.color = COLOR.BLACK;
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph();
//        graph.bfs();
//        System.out.println("---------------------");
//        graph.bfs(graph.mVertexs[2][0]);
//        System.out.println("----------------------");
        graph.dfs();
//        graph.dfs2();
    }
}

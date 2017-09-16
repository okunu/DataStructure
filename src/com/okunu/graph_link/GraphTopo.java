package com.okunu.graph_link;

import java.util.Stack;

import com.okunu.graph_link.GraphVertex.COLOR;

public class GraphTopo {

    public GraphTopoVertex[] mVertexs;
    
    public void init(){
        mVertexs = new GraphTopoVertex[9];
        GraphTopoVertex neiku = new GraphTopoVertex("内裤");
        GraphTopoVertex kuzi = new GraphTopoVertex("裤子");
        GraphTopoVertex yaodai = new GraphTopoVertex("腰带");
        GraphTopoVertex chenyi = new GraphTopoVertex("衬衣");
        GraphTopoVertex lingdai = new GraphTopoVertex("领带");
        GraphTopoVertex jiake = new GraphTopoVertex("夹克");
        GraphTopoVertex wazi = new GraphTopoVertex("袜子");
        GraphTopoVertex xie = new GraphTopoVertex("鞋子");
        GraphTopoVertex shoubiao = new GraphTopoVertex("手表");
        
        neiku.next = new GraphTopoVertex[2];
        neiku.next[0] = xie;
        neiku.next[1] = kuzi;
        mVertexs[0] = neiku;
        
        kuzi.next = new GraphTopoVertex[2];
        kuzi.next[0] = xie;
        kuzi.next[1] = yaodai;
        mVertexs[1] = kuzi;
        
        yaodai.next = new GraphTopoVertex[1];
        yaodai.next[0] = jiake;
        mVertexs[2] = yaodai;
        
        chenyi.next = new GraphTopoVertex[2];
        chenyi.next[0] = yaodai;
        chenyi.next[1] = lingdai;
        mVertexs[3] = chenyi;
        
        lingdai.next = new GraphTopoVertex[1];
        lingdai.next[0] = jiake;
        mVertexs[4] = lingdai;
        
        jiake.next = null;
        mVertexs[5] = jiake;
        
        xie.next = null;
        mVertexs[6] = xie;
        
        wazi.next = new GraphTopoVertex[1];
        wazi.next[0] = xie;
        mVertexs[7] = wazi;
        
        shoubiao.next = null;
        mVertexs[8] = shoubiao;
    }
    
    public void init2(){
        GraphTopoVertex v1 = new GraphTopoVertex("1");
        GraphTopoVertex v2 = new GraphTopoVertex("2");
        GraphTopoVertex v3 = new GraphTopoVertex("3");
        GraphTopoVertex v4 = new GraphTopoVertex("4");
        GraphTopoVertex v5 = new GraphTopoVertex("5");
        
        mVertexs = new GraphTopoVertex[5];
        
        v1.next = new GraphTopoVertex[2];
        v1.next[0] = v4;
        v1.next[1] = v2;
        mVertexs[0] = v1;
        
        v2.next = new GraphTopoVertex[2];
        v2.next[0] = v4;
        v2.next[1] = v3;
        mVertexs[1] = v2;
        
        v3.next = new GraphTopoVertex[1];
        v3.next[0] = v5;
        mVertexs[2] = v3;
        
        v4.next = new GraphTopoVertex[2];
        v4.next[0] = v3;
        v4.next[1] = v5;
        mVertexs[3] = v4;
        
        v5.next = null;
        mVertexs[4] = v5;
    }
    
    public int findUnVisitVertex(GraphTopoVertex v){
        if (v.next != null) {
            for (int i = 0; i < v.next.length; i++) {
                if (v.next[i].color == COLOR.WHITE) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int getStartTime(){
        start++;
        return start;
    }
    
    int start = 0;
    public void dfs(){
        for (int i = 0; i < mVertexs.length; i++) {
            mVertexs[i].color = COLOR.WHITE;
        }
        mVertexs[0].color = COLOR.GRAY;
        mVertexs[0].d = getStartTime();
        
        Stack<GraphTopoVertex> stack = new Stack<>();
        stack.push(mVertexs[0]);
        GraphTopoVertex vertex = null;
        
        while (!stack.isEmpty()) {
            vertex = stack.peek();
            int i = findUnVisitVertex(vertex);
            if (i == -1) {
                vertex.f = getStartTime();
                vertex.color = COLOR.BLACK;
                System.out.println(vertex);
                stack.pop();
                if (stack.isEmpty()) {
                    for (GraphTopoVertex v : mVertexs) {
                        if (v.color == COLOR.WHITE) {
                            v.color = COLOR.GRAY;
                            v.d = getStartTime();
                            stack.push(v);
                            break;
                        }
                    }
                }
            }else {
                vertex.next[i].color = COLOR.GRAY;
                vertex.next[i].d = getStartTime();
                stack.push(vertex.next[i]);
            }
        }
    }
    
    int time = 0;
    public void dfsvisit(GraphTopoVertex u){
        time++;
        u.d = time;
        u.color = COLOR.GRAY;
        if (u.next != null) {
            for (GraphTopoVertex v : u.next) {
                if (v.color == COLOR.WHITE) {
                    v.pre = u;
                    dfsvisit(v);
                }
            }
        }
        u.color = COLOR.BLACK;
        time++;
        u.f = time;
        System.out.println(u);
    }
    
    /**
     * 最正确的深度遍历
     */
    public void dfs2(){
        for (int i = 0; i < mVertexs.length; i++) {
            mVertexs[i].color = COLOR.WHITE;
        }
        time = 0;
        for (GraphTopoVertex vertex : mVertexs) {
            if (vertex.color == COLOR.WHITE) {
                dfsvisit(vertex);
            }
        }
    }
    
    public static void main(String[] args) {
        GraphTopo graph = new GraphTopo();
        graph.init2();
        
        graph.dfs2();
    }
}

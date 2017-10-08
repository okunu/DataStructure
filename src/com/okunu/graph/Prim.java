package com.okunu.graph;

import java.util.ArrayList;
import java.util.List;

import com.okunu.graph.Graph.GRAPH_TYPE;

public class Prim {

	public Graph initUndigraph(){
		Graph graph = new Graph(GRAPH_TYPE.undigraph);
		
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
        
        graph.addArc(a, b, 4);
        graph.addArc(a, h, 8);
        graph.addArc(b, h, 11);
        graph.addArc(b, c, 8);
        graph.addArc(c, i, 2);
        graph.addArc(h, i, 7);
        graph.addArc(i, g, 6);
        graph.addArc(h, g, 1);
        graph.addArc(g, f, 2);
        graph.addArc(c, f, 4);
        graph.addArc(c, d, 7);
        graph.addArc(d, f, 14);
        graph.addArc(f, e, 10);
        graph.addArc(d, e, 9);
        
        return graph;
    }
	
	/*
	 * 构造新结点list，先从图中选中任意结点加入
	 * 不断遍历list中的结点，选择与结点相关的最短边，且将边的另一端加入到list中
	 * 选择的最短边的另一个结点之前不能在list中
	 * 最后list与图中顶点表相同则结束整个过程
	 */
	public void prim(){
		Graph graph = initUndigraph();
		Vertex start = graph.mList.get(0);
		//构建新list
		List<Vertex> oldList = graph.mList;
		List<Vertex> newList = new ArrayList<Vertex>();
		newList.add(start);
		Vertex v = null;
		Arc vArc = null;
		//最短边的权重、对应的弧、对应的弧起点
		int vMinW = Integer.MAX_VALUE;
		Arc vMinArc = null;
		Vertex vMinVertex = null;
		while (newList.size() < oldList.size()) {
			//查找出新list中最短边
			for (int i = 0; i < newList.size(); i++) {
				v = newList.get(i);
				vArc = v.firstArc;
				while (vArc != null) {
					if (vMinW > vArc.weight && !newList.contains(vArc.vertex)) {
						vMinW = vArc.weight;
						vMinArc = vArc;
						vMinVertex = v;
					}
					vArc = vArc.next;
				}
			}
			//输出结果
			if (vMinArc != null && vMinArc.vertex != null && !newList.contains(vMinArc.vertex)) {
				System.out.println(vMinVertex.info + "    " + vMinArc.weight + "    " + vMinArc.vertex.info);
				newList.add(vMinArc.vertex);
				vMinW = Integer.MAX_VALUE;
			}
		}
	}
	
	public static void main(String[] args) {
		Prim prim = new Prim();
		prim.prim();
	}
}

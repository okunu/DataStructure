package com.okunu.graph_matrix;

public class VertexStack {
	private Vertex[] array;
	public int mElems = 0;
	private int top = -1;
	
	public VertexStack(){
		array = new Vertex[20];
	}
	
	public boolean push(Vertex value){
		if (top < array.length-1) {
			top++;
			mElems++;
			array[top] = value;
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(){
		return mElems == 0;
	}
	
	public Vertex peek(){
		if (top >= 0) {
//			System.out.println(array[top]);
			return array[top];
		}
		System.out.println("this is null stack");
		return null;
	}
	
	public Vertex pop(){
		Vertex temp = array[top];
		array[top] = null;
		top--;
		mElems--;
		return temp;
	}
	
	public void display(){
		for (int i = mElems-1; i >= 0; i--) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}
}

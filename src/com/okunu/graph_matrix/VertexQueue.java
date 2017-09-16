package com.okunu.graph_matrix;

public class VertexQueue {
	private int maxSize = 0;
	private int mElems = 0;
	private int front;
	private int rear;
	private Vertex[] array;
	
	public VertexQueue(int size){
		maxSize = size;
		array = new Vertex[maxSize];
		rear = -1;
		front = 0;
	}
	//���в������ݣ���Ȼֻ�ܴ�β���������ݡ�
	//һ����ԣ�����β����index��ʼ��ʱΪ-1
	public void insert(Vertex value){
		if (rear == maxSize-1) {
			rear = -1;
		}
		array[++rear] = value;
		mElems ++;
	}
	//�����Ƴ�����ʱ��һ���ͷ����ʼ�Ƴ��������Ƴ�����֮����front��ֵ��1�ģ���Ϊ��������β��������Ƴ���frontֵҪ����
	public Vertex remove(){
		Vertex temp = array[front++];
		if (front == maxSize) {
			front = 0;
		}
		mElems --;
		return temp;
	}
	
	public Vertex peek(){
		System.out.println(array[front]);
		return array[front];
	}
	
	public boolean isFull(){
		return mElems==maxSize;
	}
	
	public boolean isNull(){
		return mElems==0;
	}
	
	public void display(){
		int tempFront = front;
		int tempRear = rear;
		if (isNull()) {
			return;
		}
		while (tempFront != tempRear) {
			System.out.print(array[tempFront]+"    ");
			tempFront ++;
			if (tempFront == maxSize) {
				tempFront = 0;
			}
		}
		System.out.print(array[tempFront]+"    ");
		System.out.println();
	}
}


/*
 * 2014/11/30 何建毅 f4050777@gmail.com
 * 
 * 0~9路徑所有排列組合
 * 利用樹狀圖來達到不重複數字效果
 * 每個路徑用arraylist方式儲存，並存於node上
 * 剩餘路線利用arraylist方式儲存
 * 再利用HashMap將剩餘路線與路徑做成表(路徑,剩餘路線)
 */


import java.io.*;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class Rainbow_Connection_Tree {
	
	//NodeHashMap用來紀錄node(路徑array)與剩下可走的(array)
	static HashMap<DefaultMutableTreeNode, ArrayList<Integer>> NodeHashMap =new HashMap<DefaultMutableTreeNode, ArrayList<Integer>>();
	static int visitcount=0;//拜訪次數
	static int parent=-1;//方便顯示目前運行到第幾個主要root下的child
	static long Starttime;//計時
	static DefaultMutableTreeNode Root =new DefaultMutableTreeNode("Root");//root node
	public static void main(String[] args) {
		
		Starttime = System.currentTimeMillis();//開始時間
		//起始設定 Indexlist 目前可走的還剩哪些path
		ArrayList<Integer>Indexlist = new ArrayList<Integer>();
		Integer[] nodeList = new Integer[] {9,8,7,6,5,4,3,2,1,0};
		Indexlist.addAll(Arrays.asList(nodeList));
		NodeHashMap.put(Root, Indexlist);
		
		//遞迴開始!!
		Create_Tree(Root,0);
		//計時結束與拜訪點數(10 node=> 10+(10*(10-1))+(10*(10-1)*(10-2))...=>9864101)
		System.out.println("visit="+(visitcount+1));
		System.out.println("Time：" + (float)(System.currentTimeMillis()-Starttime)/1000 + "秒");
	}
	
	public static void Create_Tree(DefaultMutableTreeNode node,int level){
		ArrayList<Integer>NodePath;
		//迴圈條件 將可走的路跑完
		for(int i=NodeHashMap.get(node).size()-1;i>=0;i--){
			visitcount++;
			if(node.getUserObject() != "Root"){
				//如果點不是Root則去NodeHashMap抓取剩下路徑
				NodePath=(ArrayList<Integer>) ((ArrayList<Integer>) node.getUserObject()).clone();
				
			}else{
				//root則將Hashmap clear 可減少記憶空間並加速
				NodePath = new ArrayList<Integer>();
				parent++;
				System.out.println("parent="+parent);
				if(parent>0){

					NodeHashMap.clear();	
					ArrayList<Integer>Indexlist = new ArrayList<Integer>();
					Indexlist.addAll(Arrays.asList(new Integer[] {9,8,7,6,5,4,3,2,1,0}));
					NodeHashMap.put(Root, Indexlist);

				}
			}
			//紀錄目前路徑，形態設為node
			NodePath.add(NodeHashMap.get(node).get(i));
			DefaultMutableTreeNode Child =new DefaultMutableTreeNode(NodePath);
			//復至剩下路徑給Childlist
			ArrayList<Integer>Childlist =(ArrayList<Integer>) NodeHashMap.get(node).clone();
			//刪除當下的node以防止Child重複
			Childlist.remove(i);
			NodeHashMap.put(Child, Childlist);
			node.add(Child);
			//顯示起始點、層數、當前路徑、childnode、childpath
			//System.out.println("parent="+parent+"	level="+level+"	Node="+node+"	Child="+Child+"	Childlist="+Childlist);
			level++;
			//若不為最後則進入下層
			if(Childlist.size()!=0){
				Create_Tree(Child,level);
			}
			level--;
		}
	}


}

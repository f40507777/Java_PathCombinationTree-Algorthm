/*
 * 2014/11/30 ��ؼ� f4050777@gmail.com
 * 
 * 0~9���|�Ҧ��ƦC�զX
 * �Q�ξ𪬹ϨӹF�줣���ƼƦr�ĪG
 * �C�Ӹ��|��arraylist�覡�x�s�A�æs��node�W
 * �Ѿl���u�Q��arraylist�覡�x�s
 * �A�Q��HashMap�N�Ѿl���u�P���|������(���|,�Ѿl���u)
 */


import java.io.*;
import java.util.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class Rainbow_Connection_Tree {
	
	//NodeHashMap�ΨӬ���node(���|array)�P�ѤU�i����(array)
	static HashMap<DefaultMutableTreeNode, ArrayList<Integer>> NodeHashMap =new HashMap<DefaultMutableTreeNode, ArrayList<Integer>>();
	static int visitcount=0;//���X����
	static int parent=-1;//��K��ܥثe�B���ĴX�ӥD�nroot�U��child
	static long Starttime;//�p��
	static DefaultMutableTreeNode Root =new DefaultMutableTreeNode("Root");//root node
	public static void main(String[] args) {
		
		Starttime = System.currentTimeMillis();//�}�l�ɶ�
		//�_�l�]�w Indexlist �ثe�i�����ٳѭ���path
		ArrayList<Integer>Indexlist = new ArrayList<Integer>();
		Integer[] nodeList = new Integer[] {9,8,7,6,5,4,3,2,1,0};
		Indexlist.addAll(Arrays.asList(nodeList));
		NodeHashMap.put(Root, Indexlist);
		
		//���j�}�l!!
		Create_Tree(Root,0);
		//�p�ɵ����P���X�I��(10 node=> 10+(10*(10-1))+(10*(10-1)*(10-2))...=>9864101)
		System.out.println("visit="+(visitcount+1));
		System.out.println("Time�G" + (float)(System.currentTimeMillis()-Starttime)/1000 + "��");
	}
	
	public static void Create_Tree(DefaultMutableTreeNode node,int level){
		ArrayList<Integer>NodePath;
		//�j����� �N�i�������]��
		for(int i=NodeHashMap.get(node).size()-1;i>=0;i--){
			visitcount++;
			if(node.getUserObject() != "Root"){
				//�p�G�I���ORoot�h�hNodeHashMap����ѤU���|
				NodePath=(ArrayList<Integer>) ((ArrayList<Integer>) node.getUserObject()).clone();
				
			}else{
				//root�h�NHashmap clear �i��ְO�ЪŶ��å[�t
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
			//�����ثe���|�A�κA�]��node
			NodePath.add(NodeHashMap.get(node).get(i));
			DefaultMutableTreeNode Child =new DefaultMutableTreeNode(NodePath);
			//�_�ܳѤU���|��Childlist
			ArrayList<Integer>Childlist =(ArrayList<Integer>) NodeHashMap.get(node).clone();
			//�R����U��node�H����Child����
			Childlist.remove(i);
			NodeHashMap.put(Child, Childlist);
			node.add(Child);
			//��ܰ_�l�I�B�h�ơB��e���|�Bchildnode�Bchildpath
			//System.out.println("parent="+parent+"	level="+level+"	Node="+node+"	Child="+Child+"	Childlist="+Childlist);
			level++;
			//�Y�����̫�h�i�J�U�h
			if(Childlist.size()!=0){
				Create_Tree(Child,level);
			}
			level--;
		}
	}


}

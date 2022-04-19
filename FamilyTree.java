package com.bleh.www;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
/**
 * Top level calss to display information on a family tree and answer some related questions.
 * @author connor
 *
 */
public class FamilyTree {
	/**
	 * Used to create a general tree from input. Input File organized in a preTraversal format
	 * to accommodate this input method.
	 * @param inScan is the scanner for the input file.
	 * @param parentNode is the top node of our tree.
	 * @return 
	 */
	public static GNode InputGNode(Scanner inScan, GNode parentNode) {
		String name = inScan.next();
		int numKids = inScan.nextInt();
		GNode tempNode = new GNode(name);
		GNode retVal = null;
		
		if(parentNode == null) {
			retVal = tempNode;
		}else {
			parentNode.getChildren().add(tempNode);
		}
		for(int i = 0; i < numKids; i++) {
			InputGNode(inScan, tempNode);
		}
		return retVal;
	}
	/**
	 * A method used to link the nodes of the General tree together.
	 * @param root
	 */
	public static void FixupGNodes(GNode root) {
		LinkedList<GNode> kids = root.getChildren();
		int nK = kids.size();
		int iLastK = nK-1;
		if(nK>0) {
			root.setFirstChild(kids.getFirst());
		}
		for(int iK=0; iK<nK; iK++) {
			GNode kidNode = kids.get(iK);
			if(iK<iLastK) {
				kidNode.setNextSibling(kids.get(iK+1));
			}
			FixupGNodes(kidNode);
		}
	}
	/**
	 * Used to convert the general tree into a binary tree.
	 * @param gRoot is root of the general tree.
	 * @return returns the the root node of our binary tree.
	 */
	public static BNode BNodeFromGNode(GNode gRoot) {
		if (gRoot == null) {
			return null;
		}
		BNode bReturn = new BNode(gRoot.getName());
		bReturn.setFirstChild(BNodeFromGNode(gRoot.getFirstChild()));
		bReturn.setNextSibling(BNodeFromGNode(gRoot.getNextSibling()));
		return bReturn;
	}
	/**
	 * A method used to display the binary tree (if need arises to test).
	 * @param bRoot is the root of the binary tree.
	 */
	public static void DisplayBNodes(BNode bRoot) {
		System.out.println(bRoot.getName());
		if (bRoot.getFirstChild() != null) {
			DisplayBNodes(bRoot.getFirstChild());
		}
		if (bRoot.getNextSibling() != null) {
			DisplayBNodes(bRoot.getNextSibling());
		}
	}
	/**
	 * A method used to get the parent of a Binary tree.
	 * @param bRoot is the root of the Binary tree.
	 * @param childName is the name of child to be tested.
	 * @param workingParent is the current parent of the node being tested.
	 * @return is the node to be returned containing information on parent node.
	 */
	public static BNode GetParent(BNode bRoot, String childName, BNode workingParent) {
		if (bRoot == null) {
			return null;
		}
		if (bRoot.getName().equals(childName)) {
			return workingParent;
		}
		BNode tmpNode = GetParent(bRoot.getFirstChild(), childName, bRoot);
		if(tmpNode != null) {
			return tmpNode;
		}
		tmpNode = GetParent(bRoot.getNextSibling(), childName, workingParent);
		return tmpNode;
	}
	/**
	 * This method serves as a general purpose method to retrieve a specific node.
	 * @param root is the root of the binary tree.
	 * @param nodeName is the information contained within that node.
	 * @return is node that contains the nodeName.
	 */
	public static BNode GetNode(BNode root, String nodeName) {
		if(root == null) {
			return null;
		}
		if (root.getName().equals(nodeName)) {
			return root;
		}
		BNode tmpNode = GetNode(root.getFirstChild(), nodeName);
		if(tmpNode!=null) {
			return tmpNode;
		}
		tmpNode = GetNode(root.getNextSibling(), nodeName);
		return tmpNode;
	}
	/**
	 * This method retrieves the names of the children of a specific node if it 
	 * has any.
	 * @param root is the root of the binary tree.
	 * @param nodeName is the name of the node being tested for children.
	 */
	public static void GetChildren(BNode root, String nodeName, PrintStream ps) {
		BNode theNode = GetNode(root, nodeName);
		if (theNode == null) {
			ps.println("Node "+nodeName+" not found");
		}else if (theNode.getFirstChild()==null) {
			ps.println("Node "+nodeName+" has no children.");
		}else {
			BNode currentChild = theNode.getFirstChild();
			ps.println("Node "+nodeName+"'s child: "+currentChild.getName());
			currentChild = currentChild.getNextSibling();
			while(currentChild != null) {
				ps.println("Node "+nodeName+"'s child: "+currentChild.getName());
				currentChild = currentChild.getNextSibling();
			}
		}
	}
	/**
	 * This method retrieves the name of the oldest child of a node if it has one.
	 * @param root is the root of the binary tree.
	 * @param nodeName is the name of the node being tested.
	 */
	public static void GetOldestChild(BNode root, String nodeName, PrintStream ps) {
		BNode theNode = GetNode(root, nodeName);
		if (theNode == null) {
			ps.println("Node "+nodeName+" not found");
		}else if (theNode.getFirstChild()==null) {
			ps.println("Node "+nodeName+" has no children.");
		}else {
			ps.println("Node "+nodeName+"'s oldest child is "+theNode.getFirstChild().getName());
		}
	}
	
	/**
	 * Prints original General Family Tree using indentations to mark the depth/generation
	 * of the tree.
	 * @param rootNode the father node of the tree.
	 * @param prefix used to make indentations in the tree.
	 */
	public static void DisplayGNodes(GNode rootNode, String prefix, PrintStream ps) {
		ps.println(prefix+rootNode.getName());
		for(int i=0; i<rootNode.getChildren().size(); i++) {
			DisplayGNodes(rootNode.getChildren().get(i), prefix+"  ", ps);
		}
	}
	/**
	 * The main method where command line arguments are put in and Input and Output is 
	 * controlled.
	 * @param args are command line arguments.
	 * @throws Exception for the I/O Exception.
	 */
	public static void main(String[] args) throws Exception {
		//Scanner for Family Tree file one
		Scanner fTree1 = new Scanner(new FileInputStream (new File ("FamilyTreeOne.txt")));
		//Scanner for Family Tree file two
		Scanner fTree2 = new Scanner(new FileInputStream (new File ("FamilyTreeTwo.txt")));
		//Scanner for Family Tree file three
		Scanner fTree3 = new Scanner(new FileInputStream (new File ("FamilyTreeThree.txt")));
		//Print Stream to be used for consolidated info file.
		PrintStream ps = new PrintStream(new FileOutputStream(new File("FamilyTreeOutput.txt")));
		
		//Tree1
		GNode rootGNode = InputGNode(fTree1, null);
		FixupGNodes(rootGNode);
		DisplayGNodes(rootGNode, "", ps);
		
		BNode rootBNode = BNodeFromGNode(rootGNode);
		
		String childOfInterest = "Milligan";
		BNode parentBNode = GetParent(rootBNode, childOfInterest, null);
		if(parentBNode != null) {
			ps.println(childOfInterest + " has parent " + parentBNode.getName());
		}else {
			ps.println(childOfInterest + " has no parent.");
		}
		
		GetOldestChild(rootBNode, "Rob", ps);
		GetChildren(rootBNode, "Jay", ps);
		
		//Tree2
		GNode rootGNode2 = InputGNode(fTree2, null);
		FixupGNodes(rootGNode2);
		DisplayGNodes(rootGNode2, "", ps);
		
		BNode rootBNode2 = BNodeFromGNode(rootGNode2);
		
		String childOfInterest2 = "Liz";
		BNode parentBNode2 = GetParent(rootBNode2, childOfInterest2, null);
		if(parentBNode2 != null) {
			ps.println(childOfInterest2 + " has parent " + parentBNode2.getName());
		}else {
			ps.println(childOfInterest2 + " has no parent.");
		}
		
		GetOldestChild(rootBNode2, "Robin", ps);
		GetChildren(rootBNode2, "Steiner", ps);
		
		//Tree3
		GNode rootGNode3 = InputGNode(fTree3, null);
		FixupGNodes(rootGNode3);
		DisplayGNodes(rootGNode3, "", ps);
		
		BNode rootBNode3 = BNodeFromGNode(rootGNode3);
		
		String childOfInterest3 = "Vlad";
		BNode parentBNode3 = GetParent(rootBNode3, childOfInterest3, null);
		if(parentBNode3 != null) {
			ps.println(childOfInterest3 + " has parent " + parentBNode3.getName());
		}else {
			ps.println(childOfInterest3 + " has no parent.");
		}
		
		GetOldestChild(rootBNode3, "Smith", ps);
		GetChildren(rootBNode3, "Stefani", ps);
	}

}

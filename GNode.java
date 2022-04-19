package com.bleh.www;
import java.util.LinkedList;
public class GNode {
	private String name_;
	private LinkedList<GNode> children_;
	private GNode firstChild_;
	private GNode nextSibling_;
	
	GNode(String name){
		name_ = name;
		children_ = new LinkedList<GNode>();
		firstChild_ = null;
		nextSibling_ = null;
	}
	
	public String getName() {
		return name_;
	}
	
	public LinkedList<GNode> getChildren(){
		return children_;
	}
	
	public GNode getFirstChild() {
		return firstChild_;
	}
	
	public void setFirstChild(GNode firstChild) {
		firstChild_ = firstChild;
	}
	
	public GNode getNextSibling() {
		return nextSibling_;
	}
	
	public void setNextSibling(GNode nextSibling) {
		nextSibling_ = nextSibling;
	}
}

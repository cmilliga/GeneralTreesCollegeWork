package com.bleh.www;

public class BNode {
	private String name_;
	private BNode firstChild_;
	private BNode nextSibling_;
	
	BNode(String name){
		name_ = name;
		firstChild_ = null;
		nextSibling_ = null;
	}
	
	public String getName() {
		return name_;
	}
	
	public BNode getFirstChild() {
		return firstChild_;
	}
	
	public void setFirstChild(BNode firstChild) {
		firstChild_ = firstChild;
	}
	
	public BNode getNextSibling() {
		return nextSibling_;
	}
	
	public void setNextSibling(BNode nextSibling) {
		nextSibling_ = nextSibling;
	}
}

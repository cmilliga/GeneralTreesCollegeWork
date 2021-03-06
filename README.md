# GeneralTreesCollegeWork

THIS IS THE PROMPT FOR THIS ASSIGNMENT

Write a complete program, using the node representation of Binary Trees (the nodes can be implemented using either an array or dynamic storage, your choice) to do the following

The program will read in a set of data representing a family tree. It will implement this as a binary tree in Java and then it will answer a series of questions about the family tree.

Here is an outline of what the program will do:

First it will do whatever initializations are necessary.

Then it will read in a set of data representing a family tree. The format of the data is described below. You should print the original data as it is read in. By hand you should draw the original family tree in it’s usual form.

The program will convert the data from a general to a binary tree (this can be done as you are reading the data in or using the algorithm to convert it afterwards).   By hand you should then draw the binary tree representing the original tree.

Next the program will answer a series of questions about the tree. Possible questions are:

1)	Who is the father of p?
2)	Who are all the sons of p?
3)	Who are all the brothers of p?
4)	Who is the oldest brother of p?
5)	Who is the youngest brother of p?
6)	Who is the oldest son of p?
7)	Who is the youngest son of p?
8)	Who are the uncles of p?
9)	Who is the grandfather of p?

Note:  P can be any node in the tree including the root or a leaf. The first thing to do is to locate p in the tree. (you might want to store some information on the way down to p.) 

You should determine what the question is then answer each question in a separate method.

Note the same question can be asked several times for a given tree, each time for a different node p. After you have answered a series of questions about this tree you should start the process all over again for a different family tree. Start the output for each tree on a new page and do at least 3 family trees worth of data.

Data - the set of data for each tree will look like this (you can decide how to separate sets of data for different trees).

A name up to 10 characters – this is the top node in the tree
An integer n (possibly 0) representing the number of sons this node has


Example

Jones     3 (the root Jones has 3 sons)
Bob       2 
Dan       0
Brian     1 (these are the 3 sons of Jones)    
Richard 0  
Jake       1 (these are the 2 sons of Bob)
Michael 1 (the one son of Brian)
Bill        0 (the one son of Jake)
Deville  0 (the one son of Michael)


Note:    You can use a different format as long as you explain what you did 

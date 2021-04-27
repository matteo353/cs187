package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }
  
  private T getFromTree(T t, BSTNode<T> node) {
    if (node == null) {
      return null;
    }
    if (t.compareTo(node.getData()) < 0) {
      return getFromTree(t, node.getLeft());
    }
    if (t == node.getData()) {
      return node.getData();
    }
    return getFromTree(t, node.getRight());
  }

  public boolean contains(T t) {
    // TODO
    if (t == null) {
      throw new NullPointerException("null pointer");
    }
    return (getFromTree(t, root) != null);
  }

  /**
   * remove the data from the tree.
   */
  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO
    if (t == null) {
      throw new NullPointerException();
    }
    return getFromTree(t, root);
  }


  /**
   * add data into the tree.
   */
  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }
  public T getMinHelper(BSTNode<T> node) {
    if (node.getLeft() == null) {
      return node.getData();
    }
    else {
    return (getMinHelper(node.getLeft()));
    }
  }
  @Override
  public T getMinimum() {
    // TODO
    if (root == null) {
      return null;
    }
    return getMinHelper(root);
  }

  private T getMaxHelper(BSTNode<T> node) {  
    if (node.getRight() != null) {
      return getMaxHelper(node.getRight());
    }
    else {
    return node.getData();
    }
  }
  

  @Override
  public T getMaximum() {
    // TODO
    if (root == null) {
      return null;
    }
    return getMaxHelper(root);
  }
  
  private int heightHelper(BSTNode<T> node) {
    if (node == null) {
      return -1;
    }
   
    else {
      return (1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())));
    }
  }


  @Override
  public int height() {
    // TODO
    if (root == null) {
      return -1;
    }
    return heightHelper(root);
  }


  public Iterator<T> preorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    preOrderTraverse(queue, root);
    return queue.iterator();
  }
  
  private void preOrderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      queue.add(node.getData());
      preOrderTraverse(queue, node.getLeft());
      preOrderTraverse(queue, node.getRight()); 
    }
  }
  /**
   * in-order traversal.
   */
  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }

  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    postOrderTraverse(queue, root);
    return queue.iterator(); 
  }
  
  public void postOrderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
    postOrderTraverse(queue, node.getLeft());
    postOrderTraverse(queue, node.getRight());
    queue.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO
    if (other == null) {
      throw new NullPointerException();
    }
    BSTNode<T> otherRoot = other.getRoot();
    return equalsHelper(root, otherRoot);
  }
  
  private boolean equalsHelper(BSTNode<T> other, BSTNode<T> another) {
    if (subtreeSize(other) != subtreeSize(another)) {
      return false;
  }
    if (other == null && another == null) {
      return true;
    }
    else if (!(other.getData()).equals(another.getData())) {
      return false;
    }
    else {
      return (equalsHelper(other.getLeft(), another.getLeft()) && (equalsHelper(other.getRight(),another.getRight())));
    }
  }


  @Override
  public boolean sameValues(BSTInterface<T> other) { 
    // TODO
    if (other == null) {
      throw new NullPointerException();
    }
    Iterator<T> AA =  this.inorderIterator();
    Iterator<T> BB =  other.inorderIterator();
    
    while(AA.hasNext() == true && BB.hasNext() == true) {
      if (!AA.next().equals(BB.next())) { //.next() updates the value even in if statements
        return false;
      }
    }
    return ( (!AA.hasNext()) && ((!BB.hasNext()) ));
  }
    
    
    //    Iterator<T> AA =  this.inorderIterator();
//    Iterator<T> BB =  other.inorderIterator();
//    while (AA.hasNext() == true) {
//      if (!(AA.next()).equals(BB.next())) {
//        return false;
//      }
//      AA.next(); dont need
//      BB.next(); dont need
//    }
//    return true;
//  }

  @Override
  public boolean isBalanced() {
    // TODO
   return (size() >= (Math.pow(2.0, height())) && (size() < (Math.pow(2.0, height() + 1)) )); 
  
  }

  @Override
  @SuppressWarnings("unchecked")

  public void balance() {
    // TODO
    Iterator<T> AA = this.inorderIterator();
    T[] array = (T[]) new Comparable[size()];
    int index = 0;
    while (AA.hasNext() == true) {
      array[index] = AA.next();
      index++;
    }
    root = balanceHelper(array, 0, array.length - 1);
  }
  
  private BSTNode<T> balanceHelper(T[] array, int low, int high) {
    if (low > high) {
      return null;
    }
    int midPoint = (low + high) / 2;
    BSTNode<T> newRoot = new BSTNode<T>(array[midPoint], balanceHelper(array, low, midPoint - 1), balanceHelper(array, midPoint + 1, high));
    return newRoot;
  }
  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  /**
   * toDotFormat.
   * @param root root of tree.
   * @return type T.
   */
  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> "
            + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot += cursor.getData().toString() + " -> "
            + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count
            + ";\n";
        count++;
      }

    }
    dot += "};";
    return dot;
  }
  
  public Iterator<T> inorderIterator(BSTNode<T> node){
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, node);
    return queue.iterator();
  }

  /**
   * main method.
   * @param args arguments.
   */
  public static void main(String[] args) {
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
      tree.add(r);
    }
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
   private Queue<File> beenTo = new Queue<File>();
   private Queue<File> goingTo = new Queue<File>();
  
  /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    // TODO 1
    if (!rootNode.exists() || rootNode == null) {
      throw new FileNotFoundException();
    }
    goingTo.enqueue(rootNode);
  }

  @Override
  public boolean hasNext() {
    // TODO 2
    return (!goingTo.isEmpty());
    
  }

  @Override
  public File next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    File myFile = goingTo.dequeue();
    System.out.println("\n" + myFile.getPath());
    beenTo.enqueue(myFile);
    if (myFile.isDirectory()) {
      File[] myFiles = myFile.listFiles();
      for (File file : myFiles) {
        goingTo.enqueue(file);
      }
    }
    
    return myFile;
  }

  @Override
  public void remove() {
    // Leave this one alone.
    throw new UnsupportedOperationException();
  }

}

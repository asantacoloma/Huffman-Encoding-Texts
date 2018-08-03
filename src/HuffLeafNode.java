//public class HuffLeafNode {
//}

//obtained from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html


// Huffman tree node implementation: Base class
interface HuffBaseNode {
    boolean isLeaf();
    int weight();

    //HuffBaseNode left();
    //HuffBaseNode right();

    //HuffBaseNode getLeftnode();
    //HuffBaseNode getRightnode();

}


// Huffman tree node: Leaf class
class HuffLeafNode implements HuffBaseNode {
    private char element;      // Element for this node
    private int weight;        // Weight for this node

    // Constructor
    HuffLeafNode(char el, int wt)
    { element = el; weight = wt; }

    // @return The element value
    public char value() { return element; }

    // @return The weight
    public int weight() { return weight; }

    // Return true
    public boolean isLeaf() { return true; }

   // public HuffBaseNode left(){return null;}

    //public HuffBaseNode right(){return null;}


}


// Huffman tree node: Internal class
class HuffInternalNode implements HuffBaseNode {
    private int weight;
    private HuffBaseNode left;
    private HuffBaseNode right;

    // Constructor
    HuffInternalNode(HuffBaseNode l, HuffBaseNode r, int wt)
    { left = l; right = r; weight = wt; }

    // @return The left child
    HuffBaseNode left() { return left; }

    // @return The right child
    HuffBaseNode right() { return right; }

    // @return The weight
    public int weight() { return weight; }

    // Return false
    public boolean isLeaf() { return false; }
}



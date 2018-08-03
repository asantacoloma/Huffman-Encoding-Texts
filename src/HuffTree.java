//public class HuffTree {
//}


//obtained from https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/Huffman.html


import java.util.PriorityQueue;


// A Huffman coding tree
class HuffTree implements Comparable {
//class HuffTree{
    public HuffTreeNode root;

    // Constructors
    HuffTree(char el, int wt)
    { root = new HuffTreeNode(el, wt); }
    HuffTree(HuffTreeNode l, HuffTreeNode r, int wt)
    { root = new HuffTreeNode(l, r, wt); }

    HuffTreeNode root() { return root; }

    int weight() // Weight of tree is weight of root
    { return root.weight; }

    public int compareTo(Object t) {
        HuffTree that = (HuffTree)t;
        if (root.weight() < that.weight()) return -1;
        else if (root.weight() == that.weight()) return 0;
        else return 1;
    }

}

    /*
    //class HuffTree{
    private HuffBaseNode root;

    // Constructors
    HuffTree(char el, int wt)
    { root = new HuffLeafNode(el, wt); }
    HuffTree(HuffBaseNode l, HuffBaseNode r, int wt)
    { root = new HuffInternalNode(l, r, wt); }

    HuffBaseNode root() { return root; }

    int weight() // Weight of tree is weight of root
    { return root.weight(); }

    public int compareTo(Object t) {
        HuffTree that = (HuffTree)t;
        if (root.weight() < that.weight()) return -1;
        else if (root.weight() == that.weight()) return 0;
        else return 1;
    }


}*/
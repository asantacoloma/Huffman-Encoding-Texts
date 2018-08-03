public class HuffTreeNode {

    public HuffTreeNode left;
    public HuffTreeNode right;
    public int weight;
    public char element;
    public boolean leaf;

    //constructor for a leaf node
    HuffTreeNode(char el,int wt ){
        element = el;
        weight = wt;
        leaf = true;
        left = null;
        right = null;
    }

    //constructor for an internal node
    HuffTreeNode(HuffTreeNode L, HuffTreeNode R, int wt){
        left = L;
        right = R;
        leaf = false;
        weight = wt;
    }



    boolean isLeaf(){
        /*
        if(left == null && right == null){
            return false;
        }
        else{
            return true;
        }*/
        return leaf;
    }

    int weight(){
        return weight;
    }


}

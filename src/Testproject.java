import java.io.*;

public class Testproject {

    public static void main(String[] args) {

        //run and test methods from huffman class

        //File testfile = new File("illiad.txt");
        //File testfile = new File("illiadclip.txt");
        //File testfile = new File("Test2.txt");
        File testfile = new File("randTest.txt");

        HuffmanEncoder hufftest = new HuffmanEncoder();

        String stringtest = hufftest.getFrequencies(testfile);
        System.out.println(stringtest);

        HuffTree Tree = hufftest.buildTree(testfile);

        String[] pathtable = new String[256];
        String path = "";

        //hufftest.mapTree(Tree.root(), path, pathtable);

        String traverse = hufftest.traverseHuffmanTree(Tree);
        System.out.println(traverse);

        String codetest = hufftest.encodeFile(testfile, Tree);
        System.out.println(codetest);
        String decodetest = hufftest.decodeFile(codetest, Tree);
        System.out.println(decodetest);

    }

}

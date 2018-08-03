//Alejandro Santacoloma
//COP3530 Project 3 HuffmanEncoder

import java.io.*;
import java.util.Scanner;
import java.util.PriorityQueue;


public class HuffmanEncoder implements HuffmanCoding {

    //take a file as input and create a table with characters and frequencies
    //print the characters and frequencies
    public String getFrequencies(File inputFile){
        //File list = new File(filename);
        //initialize file scanner
        Scanner filescan = null;
        try {
            //set filescan to be a scanner object for list file
            filescan = new Scanner(inputFile);
        }
        catch (IOException e) {
            System.out.println("something is wrong");
        }

        int[] frequency = new int[256];
        //While loop to read all lines from file and store in array named unsorted
        while (filescan.hasNextLine()== true) {
            String s = filescan.nextLine();
            for(char ch : s.toCharArray()){
                //check if character is in desired ascii range (may need to be 126??)
                //if(ch >= 32 && ch <= 125){
                if(ch < 256){
                    frequency[(ch)]++;
                }
            }
        }

        //create string to return with all frequencies
        String freqresults = "";

        //compile frequency results into a single string
        int i = 0;
        //while(i < frequency.length - 32 ) {
        while(i < frequency.length) {

            if(frequency[i] == 0){
                i++;
                continue;
            }

            //char tempchar = (char) (i + 32);
            char tempchar = (char) (i);

            //freqresults = freqresults + Character.toChars(i+65);
            freqresults = freqresults + tempchar;
            freqresults = freqresults + " ";

            freqresults = freqresults + Integer.toString(frequency[i]); //add character frequency to string

            freqresults = freqresults + ("\n"); //add a new line to string

            i++;
        };

        //System.out.println(freqresults);
        filescan.close();
        return freqresults;
    };

    //take a file as input and create a Huffman Tree
    public HuffTree buildTree(File inputFile) {

        String freq = getFrequencies(inputFile);

        PriorityQueue<HuffTree> priorityQ = new PriorityQueue<>(11);
        //PriorityQueue<HuffTreeNode> priorityQ = new PriorityQueue<>(11);

        //loop to get all frequency values into PriorityQueue priorityQ as individual huffTrees
        while(freq.length() > 0){

            //decode first part of string (char)

            //char ascii = freq.substring(0,1).toCharArray();

            //get character at this index
            char ascii = freq.charAt(0);

            //decode second part of string (frequency)
            int indexnextl = freq.indexOf("\n");

            // if string has  " | " start substring at 4, if only a space then start at 2
            String freqstr = freq.substring(2,indexnextl);

            //convert string version of frequency value to an int
            int freqnum = Integer.parseInt(freqstr);

            HuffTree temptree = new HuffTree(ascii, freqnum);

            //add new hufftree to priorityQ
            priorityQ.add(temptree);

            freq = freq.substring(indexnextl + 1, freq.length()); // shorten length of freq string before iterating on while loop
        }

        //logic to build Huffman tree, while loop runs until all individual nodes are one huffman tree

        HuffTree tmp1, tmp2, tmp3 = null;

        while (priorityQ.size() > 1) { // While two items left

            tmp1 = priorityQ.poll();
            //System.out.println(tmp1.root.weight);

            tmp2 = priorityQ.poll();
            //System.out.println(tmp2.root.weight);

            tmp3 = new HuffTree(tmp1.root(), tmp2.root(), (tmp1.root.weight + tmp2.root.weight) );
            priorityQ.add(tmp3);   // Return new tree to heap
            //System.out.println(tmp3.weight());
        }
        //System.out.println("final hufftree made");
        //System.out.println(tmp3.root.right.weight);
        //System.out.println(tmp3.root.right.element); // tested and huffman tree is being built properly
        return tmp3;            // Return the completed tree

    };


    //take a file and a HuffTree and encode the file.
    //output a string of 1's and 0's representing the file
    public String encodeFile(File inputFile, HuffTree huffTree) {
        //throws FileNotFoundException
        System.out.println("start encoding");

        String[] pathtable = new String[256];

        //generate array containg all encoded characters of huffman tree, index of array is ascii character value
        mapTree(huffTree.root(), "", pathtable);

        String totalpaths = traverseHuffmanTree(buildTree(inputFile));

        //initialize file scanner
        Scanner filescan = null;
        try {
            //set filescan to be a scanner object for list file
            filescan = new Scanner(inputFile);
        }
        catch (IOException e) {
            System.out.println("something is wrong");
        }

        //create string storing encoded text
        String encodedtext = "";

        //While loop to read all lines from file and store in array named unsorted
        while (filescan.hasNextLine()== true){
            String s = filescan.nextLine();
            //System.out.println(s);
            //for loop to go character by character of line stored in String s
            for(char ch : s.toCharArray()){
                //add next encoded character to encodedtext string
                encodedtext =  encodedtext + pathtable[ch];
                //System.out.print(ch);
            }
        }

        filescan.close();


        //return encoded text string
        // System.out.println("coded text up next");
        // System.out.println(encodedtext);
        return encodedtext;
    };

    //take a String and HuffTree and output the decoded words
    public String decodeFile(String code, HuffTree huffTree) {
        //throws Exception
        System.out.println("start decoding");

        //search for value by traversing hufftree
        String decoded = "";
        int start = 0;
        int end = 1;
        String pathtest = code.substring(0, 1);
        HuffTreeNode currentnode = huffTree.root();

        while(code.length() > 1) {
            //travel huffman tree
            //pathtest

            while (currentnode.isLeaf() == false) {
                if (pathtest.equals("1")) {
                    //System.out.println("go left");
                    currentnode = currentnode.right;
                } else if (pathtest.equals("0")) {
                    currentnode = currentnode.left;
                    //System.out.println("go right");
                }
                //remove first char from coded string
                if(code.length() > 1) {
                    code = code.substring(1);
                    pathtest = code.substring(0, 1);
                }
                else if(code.length() == 1){
                    code = code.substring(0,1);
                   // System.out.println(code);
                    pathtest = code;
                }
            }

            decoded = decoded + currentnode.element;
            //reset current node to be root of huffman tree
            currentnode = huffTree.root();
        }

        return decoded;
    };

    //print the characters and their codes
    public String traverseHuffmanTree(HuffTree huffTree) {
        //throws Exception;

        String[] pathtable = new String[256];
        String path = "";

        mapTree(huffTree.root(), path, pathtable);

        String pathresults = "";

        //compile frequency results into a single string
        int i = 0;
        while(i < pathtable.length) {

            if(pathtable[i] == null){
                i++;
                continue;
            }

            char tempchar = (char) (i);
            pathresults = pathresults + tempchar;
            pathresults = pathresults + " ";
            pathresults = pathresults + pathtable[i]; //add coded path of character to pathresults string
            pathresults = pathresults + ("\n"); //add a new line to string
            i++;

        };

       // System.out.println(pathresults);

        return pathresults;
    };


    //recursive method to map out all paths on tree
    public String[] mapTree(HuffTreeNode huffnode, String path, String[] pathtable){

        //internal node reached, add path to string and continue traversal
        if(huffnode.isLeaf()== false){
            String pathL = path;
            String pathR = path;

            //left node traversal
            if(huffnode.left != null){
                path = pathL + "0";
                mapTree(huffnode.left, path, pathtable);
            }
            //right node traversal
            if(huffnode.right != null){
                path = pathR + "1";

                mapTree(huffnode.right, path, pathtable);
            }
        }
        else if(huffnode.isLeaf() == true) {
            //reached leafnode, store path in array at spot specific to char
            pathtable[huffnode.element] = path;
            return pathtable;
        }

        return null;
    }

}



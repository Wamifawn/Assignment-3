package src;
import java.util.*;

import java.util.PriorityQueue;
import java.util.HashMap;
public class HuffmanCoding {
	public static void Encode(String message) {
		
		//For each instance of a unique character count how many times it shows up in the string

        HashMap<Character, Integer> charMap = new HashMap<>();
        
 		// Loop through every character in the message.
         for (int i = 0; i < message.length(); i++) {
 	        
	         char c = message.charAt(i);
         
	         // if map already has the char
	         if (charMap.containsKey(c)) {
	        	 // add 1 to its count
	        	 charMap.put(c, charMap.get(c) + 1);
	         } else {
	        	 // put it in the map
	        	 charMap.put(c, 1);
	         }
         }

		 //Create a queue where the lowest frequency characters will have the highest priority
	    PriorityQueue<HuffmanNode> pq =
	                new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
		
   
	    // Loop through all unique characters found in our frequency map.
	    // add nodes for each character
        for (char c : charMap.keySet()) {
            pq.add(new HuffmanNode(c, charMap.get(c)));
        }
	    
        // BUILD THE HUFFMAN TREE 
        while (pq.size() > 1) {
            // get the two smallest
            HuffmanNode node1 = pq.poll();
            HuffmanNode node2 = pq.poll();

            // create a new node
            // use char '$' for internal nodes
            HuffmanNode internalNode =
              new HuffmanNode('$', node1.frequency + node2.frequency);
           
            internalNode.left = node1;
            internalNode.right = node2;
           
            // add it back
            pq.add(internalNode);
        }

        
        // The remaining node is the root of the Huffman Tree
        HuffmanNode root = pq.poll();

     // Print codes
        System.out.println("Huffman Code Table:");
        // pass in empty string to build on
        generateCodeTable(root, "");        
	}
	
	  // Method to print
    public static void generateCodeTable(HuffmanNode root, String code) {
        // base case
        if (root == null) {
        return;
        }

        // if it's a leaf node (no children), print it
        if (root.left == null && root.right == null) {
            System.out.println(root.character + ": " + code);
        }
       
        // go left (add 0)
        generateCodeTable(root.left, code + "0");
       
        // go right (add 1)
        generateCodeTable(root.right, code + "1");
    }

}

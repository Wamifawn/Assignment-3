//package src;
class HuffmanNode {
 char character;           
 int frequency;       
 HuffmanNode left, right; 
 
 HuffmanNode(char character, int frequency) {
     this.character = character;
     this.frequency = frequency;
     left = right = null;
 }
}

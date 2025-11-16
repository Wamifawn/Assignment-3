//package src; fox servers cant use

public class Main {
	public static void main(String[] args) {
		String message = "AAAAAABBCCDDEEFFFFF";
		
		HuffmanCoding coder = new HuffmanCoding();
		coder.Encode(message);
		
		Scanner sc = new Scanner(System.in);
        int n = 7;
        int[] inputValues = new int[n];

        System.out.println("Enter 7 integers:");

        // enter 7 integers one by one
        for (int i = 0; i < n; ) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            if (sc.hasNextInt()) {
                int val = sc.nextInt();
                inputValues[i] = val;
                i++;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); 
            }
        }

        // heap type
        System.out.print("Enter heap type, max or min) : ");
        String heapType = "";
        while (true) {
            heapType = sc.next().trim().toLowerCase();
            if (heapType.equals("min") || heapType.equals("max")) {
                break;
            } else {
                System.out.print("Invalid input. Enter 'min' or 'max': ");
            }
        }

        boolean isMinHeap = heapType.equals("min");

        HeapTree heap = new HeapTree(n, isMinHeap);

        // Insert values
        for (int val : inputValues) {
            heap.insert(val);
        }

        System.out.println("\nHeap test:");
        System.out.print("Heap elements: ");
        heap.printHeap();

        System.out.println("Extracting all elements:");
        while (heap.getSize() > 0) {
            int extracted = heap.extractRoot();
            if (isMinHeap) {
                System.out.println("Extracted Min: " + extracted);
            } else {
                System.out.println("Extracted Max: " + extracted);
            }
        }

        sc.close();	
	}
}

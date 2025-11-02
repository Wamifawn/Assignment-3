import java.util.*; 
public class HeapTree {

    //Heap stored as an array 
    ArrayList<Integer> heap;
    boolean isMaxHeap; 

    //contructor: initialize heap & heap type, min or max 
    public HeapTree(boolean isMaxHeap) {
         
        heap = new ArrayList<>();
        this.isMaxHeap = isMaxHeap;
    }
    

    // method to insert values into the heap 
    public void insert(int value) { 
        heap.add(value); 
        int index = heap.size() - 1; 

        //caculate parent indexs, and swaps if needed 
        while(index > 0) { 
            int parent = (index - 1) /2; 
            if((isMaxHeap && heap.get(index) > heap.get(parent)) || 
            (!isMaxHeap && heap.get(index) < heap.get(parent))){ 
                int temp = heap.get(index); 
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);
                index = parent;
            } else { 
                break; 
            }
        }
    }

    // heapify staring at index
    private void heapify(int index) {
        int left = 2 * index + 1; // left child
        int right = 2 * index + 2; //right child
        int target = index;

        //compare with left 
        if (left < heap.size() &&
                ((isMaxHeap && heap.get(left) > heap.get(target)) ||
                        (!isMaxHeap && heap.get(left) < heap.get(target)))) {
            target = left;
        }

        // compare with right child
        if (right < heap.size() &&
                ((isMaxHeap && heap.get(right) > heap.get(target)) ||
                        (!isMaxHeap && heap.get(right) < heap.get(target)))) {
            target = right;
        }

        // swap if needed 
        if (target != index) {
            int temp = heap.get(index);
            heap.set(index, heap.get(target));
            heap.set(target, temp);
            heapify(target);
        }
    }
    
    //uses heapify method 
    // extract the root (max or min depending on heap type)
    public int extractRoot() {
        if (heap.isEmpty())
            return -1; 

        int rootValue = heap.get(0); // save root
        int lastValue = heap.remove(heap.size() - 1); // remove last value

        if (!heap.isEmpty()) {
            heap.set(0, lastValue); // move to the root
            heapify(0); // restore heap 
        }
        return rootValue;
    }

    //check if heap is empty 
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public static void main(String[] args)  {
        Scanner scnr = new Scanner(System.in); 
        ArrayList<Integer> numbers = new ArrayList<Integer>(); 

        System.out.println("Enter 7 integers (press Enter after each integer)"); 

        int i = 0;
        while (i < 7) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            int num = scnr.nextInt();

                // check for duplicates, dont allow 
            if (numbers.contains(num)) {
                System.out.println("Duplicate value ignored. Please enter a new integer.");
                continue; 
            }
            numbers.add(num);
            i++;
        }

        boolean isMaxHeap; 
        // choose the heap type 
        System.out.println("Choose Min or Max Heap, (1 = Min Heap, 2 = Max Heap):");
        int heapType = scnr.nextInt();
        if (heapType == 2) 
        isMaxHeap = true; 
        else 
        isMaxHeap = false; 

        HeapTree heap = new HeapTree(isMaxHeap);
        //insert all numbers into heap 
        for(int num : numbers) { 
            heap.insert(num); 
        }

        // Extracte and print heap elements in order depending on if min or max heap 
        System.out.println();
        while (!heap.isEmpty()) {
            if (isMaxHeap) {
                System.out.println("Extracted Max: " + heap.extractRoot());
            } else {
                System.out.println("Extracted Min: " + heap.extractRoot());
            }
        }
        
        scnr.close();

    }
}

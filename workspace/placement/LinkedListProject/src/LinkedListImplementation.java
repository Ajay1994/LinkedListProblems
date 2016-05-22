import java.util.*;

class Node{
	int data;
	Node next;
}

public class LinkedListImplementation {
	public static Node insert(int data, Node head){
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		if(head == null){
			head = node;
			return head;
		}
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = node;
		return head;
	}
	public static void printList(Node node){
		Node temp = node;
		while(temp != null){
			System.out.print(temp.data+ "-> ");
			temp = temp.next;
		}
		System.out.println("\n");
	}
	
	public static Node insertatPosition(int data, int position, Node head){
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		if(head == null || position == 0){
			node.next = head;
			head = node;
			return head;
		}
		
		Node temp = head;
		while(position > 1){
			temp = temp.next;
			position--;
		}
		node.next = temp.next;
		temp.next = node;
		
		return head;
	}
	
	public static int getLength(Node head){
		if(head == null)
			return 0;
		if(head.next == null) 
			return 1;
		return 1 + getLength(head.next);
	}
	
	public static boolean searchNode(int data, Node head){
	    if(head == null) return false;
		if(head.data == data) return true;
		return searchNode(data,head.next);
	}
	
	public static int searchNodePosition(int data, Node head){
		Node current = head;
		int position = 0;
		while(current != null && current.data != data){
			current = current.next;
			position = position + 1;
		}
		if(current == null) return -1;
		return position;
	}
	
	public static Node getNthNode(Node head, int n){
		if(n == 0) return head;
		else return getNthNode(head.next, n-1);
	}
	
	public static Node swapNode(int data1, int data2, Node head){
		if(head == null || head.next == null) return null;
		if(data1 == data2) return head;
		
		Node prevX = null, currX = head;
		Node prevY = null, currY = head;
		
		while(currX != null && currX.data != data1){
			prevX = currX;
			currX = currX.next;
		}
		while(currY != null && currY.data != data2){
			prevY = currY;
			currY = currY.next;
		}
		
		if(currX == null || currY == null) return null;
		
		//if X is in the begin 
		if(prevX == null) head = currY;
		else prevX.next = currY;
		
		
		//if Y is in begin
		if(prevY == null) head = currX;
		else prevY.next = currX;
		
		//swap the current pointers next.
		Node temp = currX.next;
		currX.next = currY.next;
		currY.next = temp;
		
		return head;
	}
	
	public static Node deleteNode(int data, Node head){
		
		if(head == null) return null;
		if(head.data == data) return head.next;
		if(head.next == null) return head;
		
		Node current = head.next;
		Node previous = head;
		
		while(current.next != null && current.data != data){
			previous = current;
			current = current.next;
		}
		if(current.data == data){
			previous.next = current.next;
		}
		return head;
	}
	
	public static Node insertSortedList(Node head, int data){
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		if(head == null){
			head = node;
			return head;
		}
		Node temp = head;
		Node tempPrev = null;
		while(temp != null && data > temp.data){
			tempPrev = temp;
			temp = temp.next;
		}
		System.out.println("Heello");
		if(tempPrev == null){
			//insertion in beginning
			node.next = head;
			head = node;
			return head;
		}else{
			tempPrev.next = node;
			node.next = temp;
		}

		return head;
		
	}
	public static Node reverseList(Node head){
		if(head == null) return head;
		if(head.next == null) return head;
		Node tempPrev = head;
		Node temp = head.next;
		tempPrev.next = null;
		while(temp != null){
			Node copy = temp.next;
			temp.next = tempPrev;
			tempPrev = temp;
			temp = copy;
		}
		return tempPrev;
	}
	
	public static Node removeDuplicates(Node head){
		if(head == null || head.next == null) return head;
		Node prev = head;
		Node curr = head.next;
		while(curr != null){
			if(prev.data == curr.data){
				prev.next = curr.next;
				curr = prev.next;
			}else{
				prev = curr;
				curr = curr.next;
			}
		}
		return head;
		
	}
	
	public static Node mergeSortedList(Node head1, Node head2){
		if(head1 == null) return head2;
		if(head2 == null) return head1;
		Node head = null;
		Node temp = null;
		if(head1.data <= head2.data){
			head = head1;
			head1 = head1.next;
			head.next = null; 
			
		}else{
			head = head2;
			head2 = head2.next;
			head.next = null;
			
		}
		temp = head;
		while(head1 != null && head2 != null){
			if(head1.data <= head2.data){
				Node copy = head1;
				head1 = head1.next;
				copy.next = null;
				temp.next = copy;
				temp = copy;
			}else{
				Node copy = head2;
				head2 = head2.next;
				copy.next = null;
				temp.next = copy;
				temp = copy;
			}
		}
		if(head1 == null) temp.next = head2;
		else temp.next = head1;
		
		return head;
	}
	
	public static Node mergeSort(Node head){
		int length = getLength(head);
		if(length > 1){
			int mid = length/2;
			Node left = head;
			
			while(mid > 1){
				head = head.next;
				mid--;
			}
			Node right = head.next;
			head.next = null;
			
			left = mergeSort(left);
			right = mergeSort(right);
			head = mergeSortedList(left,right);
			return head;
		}
		return head;
	}
	//Check for the pallindrome
	public static boolean isPallindrome(Node head){
		//Break the list in two equal parts and reverse one list now compare the equality of two list
		if(head == null) return true;
		if(head.next == null) return true;
		int length = getLength(head);
		
		if(length % 2 == 0){
			//list of even size
			int i = 1;
			Node temp = head;
			
			while(i < length/2){
				temp = temp.next;
				i = i+1;
			}
			Node temp2 = reverseList(temp.next);
			temp.next = null;
			temp = head;
			printList(head);
			printList(temp2);
			while(temp2 != null && temp2.data == temp.data){
				temp = temp.next;
				temp2 = temp2.next;
			}
			if(temp2 == null) return true;
			else return false;
			
		}else{
			//list of odd size
			int i = 1;
			Node temp = head;
			while(i <= length/2){
				temp = temp.next;
				i = i+1;
			}
			Node temp2 = reverseList(temp.next);
			temp.next = null;
			temp = head;
			printList(head);
			printList(temp2);
			while(temp2 != null && temp2.data == temp.data){
				temp = temp.next;
				temp2 = temp2.next;
			}
			if(temp2 == null) return true;
			else return false;
		}
	}
	//Swap the nodes pairwise
	public static Node swapPairWiseNode(Node head){
		if(head == null || head.next == null) return head;
		Node curr = head;
		while(curr != null && curr.next != null){
			int data = curr.data;
			curr.data = curr.next.data;
			curr.next.data = data;
			curr = curr.next.next;
		}
		return head;
	}
	//Intersection of two sorted Linked list
	public static Node intersectionTwoSortedList(Node head1, Node head2){
		Node head = new Node();
		head.data = Integer.MIN_VALUE;
		head.next = null;
		Node dummy = head;
		if(head1 == null || head2 == null) return head.next;
		while(head1 != null && head2 != null){
			if(head1.data == head2.data){
				Node temp = new Node();
				temp.data = head1.data;
				temp.next = null;
				dummy.next = temp;
				dummy = dummy.next;
				head1 = head1.next;
				head2 = head2.next;
			}else if(head1.data > head2.data){
				head2 = head2.next;
			}else{
				head1 = head1.next;
			}
		}
		return head.next;
	}
	
	//Check if two list are identical
	public static boolean isIdentical(Node head1, Node head2){
		if(head1 == null && head2 == null) return true;
		if(head1 == null || head2 == null) return false;
		if(head1.data == head2.data && isIdentical(head1.next, head2.next)) return true;
		else return false;
	}
	
	//Delete alternate elements
	public static Node deleteAlternateElements(Node head){
		if(head == null || head.next == null) return head;
		
		Node curr = head;
		while(curr.next.next != null){
			curr.next = curr.next.next;
			curr = curr.next;
		}
		return head;
	}
	
	//Alternating split of a list
	public static void alternatingSplit(Node head){
		if(head == null) return;
		
		Node list1 = new Node();
		list1.data = Integer.MIN_VALUE;
		list1.next = null;
		Node temp1 = list1;
		
		Node list2 = new Node();
		list2.data = Integer.MIN_VALUE;
		list2.next = null;
		Node temp2 = list2;
		
		Node curr = head;
		while(curr != null && curr.next != null){
			temp1.next = curr;
			temp1 = temp1.next;
			
			temp2.next = curr.next;
			temp2 = temp2.next;
			
			curr = curr.next.next;
		}
		if(curr != null){
			temp1.next = curr;
			temp1 = temp1.next;
		}
		temp1.next = null;
		temp2.next = null;
		printList(list1.next);
		printList(list2.next);
	}
	
	/***************************************************************************************/

	
	public static Node reverseKblock(Node head,int k){
		if(head == null || head.next == null) return head;
		Node prev = head;
		Node curr = head.next;
		int i = k;
		while(curr != null && i > 1){
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
			i--;
		}
		head.next = curr; // because in case if the current becomes null then this will terminate the lined list
		if(curr != null)
			head.next = reverseKblock(curr, k);
		return prev;
	}
	/***************************************************************************************/
	//Reverse list of size k alternating position
	public static Node reverseListAtK(Node head, int k){
		if(head == null || head.next == null) return head;
		Node prev = head;
		Node curr = head.next;
		int i = k;
		while(curr != null && i > 1){
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
			i--;
		}
		head.next = curr;
		int j = k;
		while(curr != null && j > 0){
			head = head.next;
			curr = curr.next;
			j--;
		}
		if(curr != null)
			head.next = reverseListAtK(curr, k);
		return prev;
	}
	
	//Delete Node with greater value on right side
	public static Node deleteNodeWithGreaterValue(Node head){
		if(head == null || head.next == null) return head;
		Node prev = head;
		Node curr = head.next;
		while(curr != null){
			if(prev.data < curr.data){
				prev.next = curr.next;
				curr = prev.next;
			}else{
				prev = curr;
				curr = curr.next;
			}
		}
		return head;
	}
	//Segregate odd Even - go to last and again start moving from begin and move odd node left side || split the list
	public static Node segregateOddEven(Node head){
		Node list1 = new Node();
		list1.data = Integer.MIN_VALUE;
		list1.next = null;
		Node temp1 = list1;
		
		Node list2 = new Node();
		list2.data = Integer.MIN_VALUE;
		list2.next = null;
		Node temp2 = list2;
		
		
		while(head != null){
			if(head.data % 2 == 0){
				temp1.next = head;
				temp1 = temp1.next;
				head = head.next;
				temp1.next = null;
			}else{
				temp2.next = head;
				temp2 = temp2.next;
				head = head.next;
				temp2.next = null;
			}
		}
		temp1.next = list2.next;
		return list1.next;
	}
	public static Node addNumberList(Node head1, Node head2){
		if(head1 == null) return head2;
		if(head2 == null) return head1;
		Node head = new Node();
		head.data = Integer.MIN_VALUE;
		head.next = null;
		Node temp = head;
		
		
		int carry = 0;
		while(head1 != null && head2 != null){
			int sum = head1.data + head2.data + carry;
			if(sum > 10){
				carry = sum / 10;
				sum = sum % 10;
			}else{
				carry = 0;
			}
			Node node = new Node();
			node.data = sum;
			node.next = null;
			temp.next = node;
			temp = temp.next;
			
			head1 = head1.next;
			head2 = head2.next;
		}
		if(head1 != null){
			while(head2 != null){
				int sum = head2.data + carry;
				if(sum > 10){
					carry = sum / 10;
					sum = sum % 10;
				}else{
					carry = 0;
				}
				Node node = new Node();
				node.data = sum;
				node.next = null;
				temp.next = node;
				temp = temp.next;
				
				head1 = head1.next;
				head2 = head2.next;
			}
		}else{
			while(head1 != null){
				int sum = head1.data + carry;
				if(sum > 10){
					carry = sum / 10;
					sum = sum % 10;
				}else{
					carry = 0;
				}
				Node node = new Node();
				node.data = sum;
				node.next = null;
				temp.next = node;
				temp = temp.next;
				
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		printList(head.next);
		return head.next;
	}
	//Rotate Linked List by an amount k
	public static Node rotateK(Node head, int k){
		if(head == null) return null;
		if(k == 0) return head;
		Node temp = head;
		while(temp.next != null &&  k > 1){
			temp = temp.next;
			k--;
		}
		if(temp.next == null) return head;
		if(k == 1){
			Node headCopy = head;
			head = temp.next;
			temp.next = null;
			Node temp1 = head;
			while(temp1.next != null){
				temp1 = temp1.next;
			}
			temp1.next = headCopy;
		}
		return head;
	}
	
	//add two no represented by lists
	public static int adjustLeftCarry(Node head, int carry, Node end){
		if(head == end) return carry;
		int updatedCarry = adjustLeftCarry(head.next, carry, end);
		int sum = head.data + updatedCarry;
		updatedCarry = 0;
		if(sum < 10) head.data = sum;
		else{
			updatedCarry = sum / 10;
			sum = sum % 10;
			head.data = sum;
		}
		return updatedCarry;
	}
	public static int add(Node head1, Node head2){
		if(head1 == null || head2 == null) return 0;
		int carry = add(head1.next, head2.next);
		int sum = head1.data + head2.data + carry;
		carry = 0;
		if(sum < 10) head1.data = sum;
		else{
			carry = sum / 10;
			sum = sum % 10;
			head1.data = sum;
		}
		return carry;
	}
	public static Node addTwoNumbers(Node head1, Node head2){
		int len1 = getLength(head1);
		int len2 = getLength(head2);
		if(len1 == len2){
			int carry = add(head1, head2);
			if(carry != 0){
				Node temp = new Node();
				temp.data = carry;
				temp.next = head1;
				head1 = temp;
			}
		}else{
			if(len1 < len2){
				Node temp = head1;
				head1 = head2;
				head2 = temp;
			}

			
			Node temp = head1;
			int k = Math.abs(len1 - len2);
			while(k > 0){
				temp = temp.next;
				k--;
			}
			
			int carry = add(temp, head2);
			System.out.println("Left carry "+carry);
			int leftCarry = adjustLeftCarry(head1, carry, temp);
			if(leftCarry > 0){
				temp = new Node();
				temp.data = leftCarry;
				temp.next = head1;
				head1 = temp;
			}
		}
		return head1;
	}
	//Delete n nodes after M nodes
	public static Node deleteNNodes(Node head, int n){
		if(head == null) return head;
		while(head != null && n > 0){
			head = head.next;
			n--;
		}
		return head;
	}
	public static Node deleteMandN(Node head, int M, int N){
		if(head == null) return null;
		int k = M;
		Node temp = head;
		while(temp != null){
			if(k == 1){
				temp.next = deleteNNodes(temp.next, N);
				k = M;
				temp = temp.next;
			}else{
				temp = temp.next;
				k--;
			}
		}
		return head;
	}
	
	//QuickSort implementation
	public static Node getTail(Node head){
		if(head == null || head.next == null) return head;
		while(head.next != null){
			head = head.next;
		}
		return head;
	}
	public static void swapData(Node node1, Node node2){
		int copy = node2.data;
		node2.data = node1.data;
		node1.data = copy;
	}
	public static Node partition(Node head){
		if(head == null || head.next == null) return head;
		Node prev = null;
		Node pivot = getTail(head);
		Node curr = head;
		while(curr != pivot){
			if(curr.data <= pivot.data){
				if(prev == null) prev = head;
				else prev = prev.next;
				swapData(prev, curr);
			}
			curr = curr.next;
		}
		if(prev == null){
			prev = head;
			swapData(prev, pivot);
			return prev;
		}
		swapData(prev.next, pivot);
		return prev.next;
	}
	public static Node quickSort(Node head){
		if(head == null || head.next == null) return head;
		Node pivot = partition(head);
		Node head1, head2;
		//check if the pivot is first element the first list is empty
		if(pivot == head){
			head1 = null;
			head2 = quickSort(head.next);
			return head;
		}else{
			Node list1 = head;
			while(list1.next != pivot) list1 = list1.next;
			list1.next = null;
			Node list2 = pivot.next;
			head1 = quickSort(head);
			head2 = quickSort(list2);
			Node tail = getTail(head1);
			tail.next = pivot;
		}
		return head;
	}
	//Merge LinkedList alternating position
	public static Node mergeAlternating(Node head1, Node head2){
		if(head1 == null) return head2;
		if(head2 == null) return head1;
		Node head = new Node();
		head.data = Integer.MAX_VALUE;
		head.next = null;
		Node temp = head;
		while(head1 != null && head2 != null){
			temp.next = head1;
			head1 = head1.next;
			
			temp = temp.next;
			
			temp.next = head2;
			head2 = head2.next;
			
			temp = temp.next;
		}
		if(head1 == null) temp.next = head2;
		if(head2 == null) temp.next = head1;
		return head.next;
	}
	//Rotate pairwise by changing links
	public static Node rotatePairwise(Node head){
		if(head == null || head.next == null) return head;
		Node prev = head;
		Node curr = head.next;
		
		Node temp = curr.next;
		curr.next = prev;
		prev = curr;
		curr = temp;
		
		head.next = curr;
		if(curr == null) 
			head.next = rotatePairwise(curr);
		return prev;
	}
	//Insertion sort in linked List
	public static Node insertSorted(Node head, Node node){
		if(head == null) return node;
		
		Node prev = null;
		Node curr = head;
		while(curr != null && curr.data < node.data){
			prev = curr;
			curr = curr.next;
		}
		if(prev == null){
			node.next = head;
			head = node;
			return head;
		}
		prev.next = node;
		node.next = curr;
		return head;
	}
	public static Node insertionSort(Node head){
		Node sortedHead = null;
		while(head != null){
			Node node = head;
			head = head.next;
			node.next = null;
			sortedHead = insertSorted(sortedHead, node);
		}
		return sortedHead;
	}
	
	public static void main(String[] args){
		Node head = null;
		Random rand = new Random();
		head = insert(500, head);
		for(int i=0; i<10; i++){
			head = insert(rand.nextInt(25), head);
		}
		head = insert(800, head);
		printList(head);
		System.out.println("\nInsertion after a certain point :");
		head = insertatPosition(100, 4, head);
		printList(head);
		System.out.println("\nLength of linkedList :"+getLength(head));
		System.out.println("Element 100 :"+searchNode(100, head) + " Element 200 :"+searchNode(200, head));
		
		//System.out.println(searchNodePosition(100, head));
		head = swapNode(500, 800, head);
		printList(head);
		swapNode(500, 100, head);
		printList(head);
		
		//Deletion Check
		System.out.println("\nAfter Deletion of 100 : ");
		head = deleteNode(100, head);
		printList(head);
		
		System.out.println("\nAfter Deletion of 500 : ");
		head = deleteNode(500, head);
		printList(head);
		
		System.out.println("\nAfter Deletion of 800 : ");
		head = deleteNode(800, head);
		printList(head);
		
		System.out.println("5th node is "+getNthNode(head, 5).data);
		
		System.out.println("_________________ Check for inserted sorted list _____________________");
		head = null;
		for(int i=1; i<10; i++){
			head = insert(i, head);
		}
		printList(head);
		head = insertSortedList(head, 0);
		printList(head);
		insertSortedList(head, 6);
		printList(head);
		head = insertSortedList(head, 12);
		printList(head);
		
		head = reverseList(head);
		printList(head);
		
		System.out.println("_________________ Check for Pallindrome _____________________");
		
		head = null;
		for(int i=1; i<5; i++){
			head = insert(i, head);
		}
		head = insert(6, head);
		for(int i=4; i>=1; i--){
			head = insert(i, head);
		}
		printList(head);
		System.out.println("IS Pallindrome :"+isPallindrome(head));
		
		System.out.println("_________________ Check for Duplicacy Removal _____________________");
		head = null;
		for(int i=1; i<5; i++){
			head = insert(i, head);
			head = insert(i, head);
		}
		printList(head);
		head = removeDuplicates(head);
		printList(head);
		
		
		System.out.println("_________________ Check for Merging/Merge Sort/Remove Duplicates arrays _____________________");
		
		head = null;
		head = insert(500, head);
		for(int i=0; i<10; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		head = mergeSort(head);
		printList(head);
		head = removeDuplicates(head);
		printList(head);
		

		System.out.println("_________________ Check for Pairwise Swap _____________________");
		head = null;
		head = insert(500, head);
		for(int i=0; i<12; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		head = swapPairWiseNode(head);
		printList(head);
		
		
		
		System.out.println("_________________ Check for Intersection of two Sorted List _____________________");
		Node head1 = null;
		Node head2 = null;
		for(int i=0; i<10; i++){
			if(i % 2 == 0)
				head1 = insert(i, head1);
		}
		for(int i=5; i<15; i++){
			if(i % 2 == 0)
				head2 = insert(i, head2);
		}
		printList(head1);
		printList(head2);
		head = intersectionTwoSortedList(head1, head2);
		printList(head);
		
		System.out.println("_________________ Check for Alternate delete elements _____________________");
		head = null;
		for(int i=0; i<10; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		head = deleteAlternateElements(head);
		printList(head);
		
		System.out.println("_________________ Check for Alternate Splits of list _____________________");
		head = null;
		for(int i=0; i<11; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		alternatingSplit(head);
		
		System.out.println("_________________ Check for Identical list _____________________");
		head1 = null;
		for(int i=0; i<11; i++){
			head1 = insert(i, head1);
		}
		printList(head1);
		head2 = null;
		for(int i=0; i<11; i++){
			head2 = insert(i, head2);
		}
		printList(head2);
		System.out.println("Is Identical: " + isIdentical(head1, head2));
		
		
		System.out.println("_________________ Check for alternating reverse K block patch_____________________");
		head = null;
		for(int i=1; i<10; i++){
			head = insert(i, head);
		}
		printList(head);
		head = reverseListAtK(head,3);
		printList(head);
		
		System.out.println("_________________ Check for reverse K block patch_____________________");
		head = null;
		for(int i=1; i<14; i++){
			head = insert(i, head);
		}
		printList(head);
		head = reverseKblock(head, 3);
		printList(head);
		
		System.out.println("_________________ Check for deleteion of node with greater right value_____________________");
		head = null;
		for(int i=1; i<14; i++){
			head = insert(rand.nextInt(15), head);
		}
		printList(head);
		head = deleteNodeWithGreaterValue(head);
		printList(head);
		
		System.out.println("_________________ Check for segregation of odd even nodes_____________________");
		head = null;
		for(int i=1; i<14; i++){
			head = insert(rand.nextInt(15), head);
		}
		printList(head);
		head = segregateOddEven(head);
		printList(head);
		
		System.out.println("_________________ Check for Addition of two list_____________________");
		head1 = null;
		for(int i=0; i<5; i++){
			head1 = insert(rand.nextInt(18), head1);
		}
		printList(head1);
		head2 = null;
		for(int i=0; i<5; i++){
			head2 = insert(rand.nextInt(18), head2);
		}
		printList(head2);
		head = addNumberList(head1, head2);
		
		System.out.println("_________________ Check for rotate k_____________________");
		head = null;
		for(int i=1; i<14; i++){
			head = insert(rand.nextInt(15), head);
		}
		printList(head);
		head = rotateK(head, 14);
		printList(head);
		
		System.out.println("_________________ Check for Addition of two Numbers_____________________");
		head1 = null;
		for(int i=0; i<4; i++){
			head1 = insert(rand.nextInt(18), head1);
		}
		printList(head1);
		head2 = null;
		for(int i=0; i<7; i++){
			head2 = insert(rand.nextInt(18), head2);
		}
		printList(head2);
		head = addTwoNumbers(head1, head2);
		printList(head);
		
		System.out.println("_________________ Check for deletion of M and N nodes _____________________");
		head = null;
		for(int i=0; i<11; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		head = deleteMandN(head, 2,2);
		printList(head);
		
		System.out.println("_________________ Check for quick sort _____________________");
		head = null;
		for(int i=0; i<12; i++){
			head = insert(rand.nextInt(25), head);
		}
		printList(head);
		head = quickSort(head);
		printList(head);
		
		System.out.println("_________________ Check for Merging two list alternating_____________________");
		head1 = null;
		for(int i=0; i<3; i++){
			head1 = insert(rand.nextInt(18), head1);
		}
		printList(head1);
		head2 = null;
		for(int i=0; i<5; i++){
			head2 = insert(rand.nextInt(18), head2);
		}
		printList(head2);
		head = mergeAlternating(head1, head2);
		printList(head);
		
		System.out.println("_________________ Check for pairwiase swap_____________________");
		head = null;
		for(int i=1; i<11; i++){
			head = insert(i, head);
		}
		printList(head);
		head = swapPairWiseNode(head);
		printList(head);
		
		System.out.println("_________________ Check for Insertion Sort_____________________");
		head = null;
		for(int i=1; i<11; i++){
			head = insert(rand.nextInt(20), head);
		}
		printList(head);
		
		head = insertionSort(head);
		printList(head);
	}
}

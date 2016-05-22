import java.util.Random;

class Dnode{
	int data;
	Dnode prev;
	Dnode next;
	Dnode(int data){
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}
public class DoubleLinkedList {
	public static void printList(Dnode node){
		Dnode temp = node;
		while(temp != null){
			System.out.print(temp.data+ "-> ");
			temp = temp.next;
		}
		System.out.println("\n");
	}
	public static void printListReverse(Dnode node){
		Dnode temp = node;
		while(temp.next != null){
			temp = temp.next;
		}
		while(temp != null){
			System.out.print(temp.data + "-> ");
			temp = temp.prev;
		}
		System.out.println("\n");
	}
	public static Dnode insertList(int data, Dnode head){
		Dnode node = new Dnode(data);
		if(head == null) return node;
		Dnode temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = node;
		node.prev = temp;
		return head;
	}
	public static void insertAfterGivenNode(int data, Dnode head){
		Dnode node = new Dnode(data);
		node.prev = head;
		node.next = head.next;
		node.next.prev = node;
		node.prev.next = node;
	}
	public static Dnode reverseList(Dnode head){
		if(head == null) return head;
		Dnode curr = head;
		Dnode temp = null;
		while(curr != null){
			temp = curr.prev;
			curr.prev = curr.next;
			curr.next = temp;
			curr = curr.prev;
		}
		if(temp != null)
			head = temp.prev;
		return head;
	}
	public static Dnode insertTree(int data, Dnode root){
		if(root == null){
			Dnode node = new Dnode(data);
			return node;
		}
		//System.out.print(root.data + " ");
		//printTree(root);
		if(data <= root.data){
			root.prev = insertTree(data, root.prev);
		}else{
			root.next = insertTree(data, root.next);
		}
		return root;
	}
	public static void printTree(Dnode root){
		if(root == null) return;
		printTree(root.prev);
		System.out.print(root.data + " ->");
		printTree(root.next);
	}
	static Dnode head1 = new Dnode(Integer.MAX_VALUE);
	static Dnode temp = head1;
	public static void treeToList(Dnode root){
		if(root == null) return;
		treeToList(root.prev);
		Dnode x = root.next;
		temp.next = root;
		root.prev = temp;
		root.next = null;
		temp = temp.next;
		treeToList(x);
	}
	public static Dnode merge(Dnode head1, Dnode head2){
		if(head1 == null) return head2;
		if(head2 == null) return head1;
		
		if(head1.data <= head2.data){
			head1.next = merge(head1.next, head2);
			head1.next.prev = head1;
			return head1;
		}else{
			head2.next = merge(head1, head2.next);
			head2.next.prev = head2;
			return head2;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dnode head = null;
		Random rand = new Random();
		System.out.println("_________________ Check for Insertion in Double Linked List _____________________");
		for(int i = 0; i < 10; i++){
			head = insertList(rand.nextInt(115), head);
		}
		printList(head);
		printListReverse(head);
		
		System.out.println("_________________ Check for Insertion After a Node in Double Linked List _____________________");
		insertAfterGivenNode(100, head.next.next.next);
		printList(head);
		
		System.out.println("_________________ Check for reversal of Double Linked List _____________________");
		for(int i = 0; i < 10; i++){
			head = insertList(rand.nextInt(115), head);
		}
		printList(head);
		head = reverseList(head);
		printList(head);
		
		System.out.println("_________________ Check for Flatting of tree to circular Linked List _____________________");
		head = null;
		for(int i = 10; i > 1; i--){
			head = insertTree(rand.nextInt(10), head);
		}
		printTree(head);
		treeToList(head);
		System.out.println();
		printList(head1);
		head = reverseList(head1);
		System.out.println();
		printList(head);
		
		System.out.println("_________________ Check for merge sort of two doubly Linked List _____________________");
		head1 = null;
		for(int i = 1; i < 5; i++){
			head1 = insertList(i, head1);
		}
		Dnode head2 = null;
		for(int i = 3; i < 7; i++){
			head2 = insertList(i, head2);
		}
		printList(head1);
		printList(head2);
		head = merge(head1, head2);
		printList(head);
		head = reverseList(head);
		printList(head);
	}

}

/*
 * Name: Thomas Cheng
 * 
 * Date: May 2, 2018
 * 
 * Dsscription: This is the LinkedList<T> class that implements the LinkedListADT<T> class.
 */

class LinkedList<T> implements LinkedListADT<T> {
    private ListNode<T> front = null;
    private int numberOfNodes = 0; 
    
    // Returns true if the linked list has no nodes, or false otherwise.
    @Override
    public boolean isEmpty() {
        return (front == null);
    }
    
    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    @Override
    public void clear() {
        front = null;
        numberOfNodes = 0;
    }
    
    // Returns the number of nodes in the linked list
    @Override
    public int size() {
        return numberOfNodes;
    }
    
    // Adds a node to the front of the linked list.
    @Override
    public void addFirst( T element ) {
        front = new ListNode<T>( element, front );
        numberOfNodes++;
    }
    
    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    public T peekFirst() {
        if ( isEmpty() ) 
            return null;
        
        return front.getData();
    }
    
    // Removes a node from the front of the linked list (if there is one).
    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    @SuppressWarnings("unchecked")
    public T removeFirst() {
        T tempData;
        // if the list is empty
        if ( isEmpty() ) 
            return null;
        // if the list is not empty
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    // Add an element to the end of the linked list
    @Override
    @SuppressWarnings("unchecked")
    public void addLast( T element ) {
        ListNode<T> node = front;
        ListNode<T> back = front;
        // if the list is empty
        if (front == null){
            addFirst( element );
            return;
        }
        else{
            // searches until the end of list
            while (node.getNext() != null){
                node = node.getNext();
                back = node.getNext();
            }
            // add the node to the end of the list
            back = new ListNode( element, null );
            node.setNext(back);
        }
        numberOfNodes++;
    }
    
    // Remove an element from the end of the list
    @Override
    @SuppressWarnings("unchecked")
    public T removeLast(){
        ListNode<T> node = front;
        ListNode<T> end = front;
        T element;
        
        // list is empty
        if (isEmpty())
            return null;
        // list has only 1 element
        if (size() == 1){
            element = node.getData();
            front = null;
            numberOfNodes--;
            return element;
        }
        // list has 2+ elements
        else{
            end = end.getNext();
            // searches for the end of the list
            while (end.getNext() != null){
                node = node.getNext();
                end = end.getNext();
            }
            // removing the last node from the list
            element = end.getData();
            node.setNext(null);
            numberOfNodes--;
            return element;
        }
    }
        
    
    // Returns true if the linked list contains a certain element, or false otherwise.
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains( T key ) {
        ListNode<T> searchNode;
        searchNode = front;
        while ( ( searchNode != null ) && ( !key.equals( searchNode.getData() ) ) ) {
            searchNode = searchNode.getNext();
        }
        return ( searchNode != null );
    }
    
    // Return String representation of the linked list.
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
}
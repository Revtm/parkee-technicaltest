public class SingleLinkedList {
    private Node head = null;

    public void insertAtEnd(int data){
        if(head == null){
            head = new Node(data);
        } else {
            head = insertToLastNode(data, head);
        }
    }

    private Node insertToLastNode(int data, Node node){
        if(node.getNext() != null){
            return insertToLastNode(data, node.getNext());
        }else{
            Node newNode = new Node(data);
            node.setNext(newNode);
            return node;
        }
    }

    public void insertAtBeginning(int data){
        if(head == null){
            head = new Node(data);
        }else{
            head = new Node(data, head);
        }
    }


    public void display(){
        if(head == null){
            System.out.println("[]");
        }else{
            System.out.print("[");
            display(head);
            System.out.println("]");
        }
    }

    private void display(Node node){
        if(node == null){
            System.out.print("");
        }else{
            System.out.print(node.getData());
            if(node.getNext() != null){
                System.out.print(",");
            }
            display(node.getNext());
        }
    }
}

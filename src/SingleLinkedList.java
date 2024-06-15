public class SingleLinkedList {
    private Node head = null;

    public void insertAtEnd(int data){
        if(head == null){
            head = new Node(data);
        } else {
            Node node = head;
            while(node.getNext() != null){
                node = node.getNext();
            }
            Node newNode = new Node(data);
            node.setNext(newNode);
        }
    }

    public void insertAtBeginning(int data){
        if(head == null){
            head = new Node(data);
        }else{
            Node newNode = new Node(data);
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void deleteByValue(int data){
        deleteByValue(data, null, head);
    }

    public void deleteByValue(int data, Node prev, Node current){
        if(current != null){
            if(current.getData() != data){
                deleteByValue(data, current, current.getNext());
            }else{
                Node next = current.getNext();
                if(prev != null){
                    prev.setNext(next);
                    current.setNext(null);
                }else{
                    head = next;
                }
            }
        }
    }

    public void display(){
        if(head == null){
            System.out.println("[]");
        }else{
            System.out.print("[");
            for(Node n = head ; n != null ; n = n.getNext()){
                System.out.print(n.getData());
                if(n.getNext() != null){
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}

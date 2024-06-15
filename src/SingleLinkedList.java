public class SingleLinkedList {
    private Node head = null;

    public void insertAtEnd(int data){
        if(head == null){
            head = new Node(data);
        } else {
            insertToLastNode(data, head);
        }
    }

    private void insertToLastNode(int data, Node node){
        if(node.getNext() != null){
            insertToLastNode(data, node.getNext());
        }else{
            Node newNode = new Node(data);
            node.setNext(newNode);
        }
    }

    public void insertAtBeginning(int data){
        if(head == null){
            head = new Node(data);
        }else{
            head = new Node(data, head);
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

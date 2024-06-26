
public class Main {
    public static void main(String[] args) {
        SingleLinkedList numbers = new SingleLinkedList();

        numbers.insertAtBeginning(3);
        numbers.insertAtEnd(5);
        numbers.insertAtEnd(7);
        numbers.insertAtBeginning(1);
        numbers.display(); // expected output : [1,3,5,7]
        numbers.deleteByValue(5);
        numbers.display(); // expected output : [1,3,7]
        numbers.deleteByValue(1);
        numbers.display(); // expected output : [3,7]
        numbers.deleteByValue(3);
        numbers.display(); // expected output : [7]
        numbers.deleteByValue(7);
        numbers.display(); // expected output : []
        numbers.insertAtBeginning(3);
        numbers.insertAtEnd(5);
        numbers.insertAtEnd(7);
        numbers.insertAtBeginning(1);
        numbers.display(); // expected output : [1,3,5,7]
    }
}
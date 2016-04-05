package Project4;

/**************************************************************************
 *
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 *
 *************************************************************************/
public class Node<E> {
    public E data;
    public Node next;

    public Node(E data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

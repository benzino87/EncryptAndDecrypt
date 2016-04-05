
package Project4;

/**************************************************************************
 *
 * @author Jason Bensel, Braedin Hendrickson
 * @version Project 4, Encrypt/Decrypt
 *
 *************************************************************************/
public class LinkedList<E> {
    protected Node<E> head;
    protected Node<E> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void insertTop(E data) {

        if (head == null)
            tail = head = new Node<>(data, head);
        else
            head = new Node<>(data, head);


    }

    public void insertBottom(E data) {

        if (head == null)
            tail = head = new Node<>(data, head);

        else {

            tail.setNext(new Node<>(data, null));
            tail = tail.getNext();


        }

    }

    public void clear(){
        head = null;
        tail = null;
    }

    public void display() {
        Node temp = head;
        int tempc = 1;
        while (temp != null) {
            System.out.println(tempc + " " + temp.getData());
            temp = temp.getNext();
            tempc++;
        }
    }
    public String getList(){
        if(head == null){
            return "";
        }
        Node temp = head;
        String strBuilder = "";
        while (temp != null) {
            strBuilder = strBuilder + temp.getData();
            temp = temp.getNext();
        }
        return strBuilder;
    }

    public void displayHead() {
        Node temp = head;

        System.out.println(temp.getData());


    }

    public void displayTail() {
        Node temp = tail;

        System.out.println(temp.getData());

    }

    public int count() {
        int count = 1;

        Node<E> temp = head;
        while (temp != tail) {
            count++;
            temp = temp.getNext();
        }


        return count;
    }

//    public boolean delete(int position) {
//
//        int tempPos = 1;
//        Node temp = head;
//        // check for empty list
//        if (head == null)
//            return false;
//
//
//
//        // check if top element is the target
//        if (head.getData().equals(data)) {
//            head = head.getNext();
//            if (head.getNext() == null)
//                tail = null;
//            return true;
//        }
//
//        Node<E> temp = head;
//        while (temp.getNext() != null) {
//            if (temp.getNext().getData().equals(data)) {
//                temp.setNext(temp.getNext().getNext());
//                if (temp.getNext() == null)
//                    tail = temp;
//                return true;
//            }
//            temp = temp.getNext();
//        }
//
//        return false;
//    }

    public boolean deleteNumber(int position) {


        if (head == null)
            return false;

        // check if top element is the target
        if (position == 1) {
            head = head.getNext();
            if (head.getNext() == null)
                tail = null;
            return true;
        }

        Node<E> temp = head;
        int tempc = 1;
        while (temp.getNext() != null) {
            if (tempc + 1 == position) {
                temp.setNext(temp.getNext().getNext());
                if (temp.getNext() == null)
                    tail = temp;
                return true;
            }
            temp = temp.getNext();
            tempc++;
        }

        return false;
    }

    public boolean insert(E c, int pos){


        if (head == null) {
            Node<E> inc = new Node(c , null);
            head = tail = inc;
            return true;
        }

        if(pos > count()){

            Node<E> inc = new Node(c , null);
            tail.setNext(inc);
            tail = inc;
            return true;
        }

        if(pos == 1){
            Node<E> inc = new Node(c , head);
            head = inc;
            return true;
        }

        Node<E> temp = head;
        int tempc = 1;


        while (temp.getNext() != null) {

                if (tempc == pos-1) {

                    Node<E> inc = new Node(c , temp.getNext());
                    temp.setNext(inc);

                    return true;
                }

            temp = temp.getNext();
            tempc++;
        }


        return false;
    }

    public boolean paste(LinkedList info, int pos){

        Node<E> temp = info.head;


        while (temp != null){

            this.insert(temp.getData(),pos);
            temp = temp.getNext();
            pos++;

        }

        return true;

    }


    public Node remove(int pos) {

        if ((pos > count())||(pos < 1)){
            Node<E> temp = new Node();
        return temp;
    }
        LinkedList<E> tempReturn =this.cut(pos, pos);

        return tempReturn.head;


    }

    public boolean Switch(int one,int two){


        if (one == two)
            return true;

        if (one > two){
            int tempInt =two;
            two = one;
            one = tempInt;
        }

        int tempCount = count();

        if((one<= tempCount)&&
                        (two<= tempCount) &&
                        (one>0)&&
                        (two>0)){

            Node<E> tempTwo = this.remove(two);
            Node<E> tempOne = this.remove(one);


            this.insert(tempTwo.getData(), one);
            this.insert(tempOne.getData(), two);

            return true;


        }

        return false;
    }

    public LinkedList dup(){


        LinkedList<E> tempReturn = new LinkedList();


        Node<E> temp0 = head;


        while (temp0 != null) {

            tempReturn.insertBottom(temp0.getData());
            temp0 = temp0.getNext();

        }

        return tempReturn;
    }

    public LinkedList copy(int one, int two){

        if (one > two){
            int tempInt =two;
            two = one;
            one = tempInt;
        }

        LinkedList<E> tempReturn = new LinkedList();

        int tempCount = count();

        if((one <= tempCount)&&
                (two<= tempCount) &&
                (one>0)&&
                (two>0)){

            tempReturn = this.dup();
            tempReturn = tempReturn.cut(one, two);

            return tempReturn;


        }

        return tempReturn;
    }

    public LinkedList cut(int start, int stop) {

        LinkedList<E> tempReturn = new LinkedList();

        if (head == null)
            return tempReturn;


        //check if top element is the target
        if (start == 1) {


            Node<E> temp = head;
            int tempc = 1;


            while (temp.getNext() != null) {
                if (tempc == stop) {

                    if (stop == count()) {
                        tempReturn.head=head;
                        tempReturn.tail=tail;
                        head = null;
                        tail = null;

                    } else {
                        tempReturn.head=head;
                        head = temp.getNext();
                        temp.setNext(null);
                        tempReturn.tail=temp;
                    }
                    break;
                }


                temp = temp.getNext();
                tempc++;
            }
        }

        if (start > 1) {


            Node<E> temp1 = head;
            int tempc = 1;


            while (temp1.getNext() != null) {
                if (tempc + 1 == start) {
                    tempReturn.head = temp1.getNext();
                    break;
                }
                temp1 = temp1.getNext();
                tempc++;
            }

            Node<E> temp2 = head;
            tempc = 1;
            while (temp2 != null) {
                if (tempc == stop) {
                    if (stop == count()) {
                        tail = temp1;
                        temp1.setNext(null);
                        tempReturn.tail=temp2;
                    }else {

                        temp1.setNext(temp2.getNext());
                        temp2.setNext(null);
                        tempReturn.tail=temp2;

                    }
                    break;
                }

                temp2 = temp2.getNext();
                tempc++;
            }



        }
        return tempReturn;
    }


    public static void main(String[] args) {
        LinkedList<String> list = new
                LinkedList<>();
        list.insert("1", 1);
        list.insert("2", 1);
        list.insert("3", 1);
        list.insert("4", 1);
        list.insert("5", 1);
        list.display();
        //    list.insertBottom("pizza5");
//        LinkedList<String> list2 = new
//                LinkedList<>();
//        list2.insertBottom("4");
//        list2.insertBottom("5");
        System.out.println("------sl------");
//        list.display();
//
//
//        System.out.println("------s2------");
//
//        list2.display();
//
//        System.out.println("------s2------");
////        list.paste(list2, 2);
////        list.display();
//
//        list.remove(1);
//        list.display();
//        System.out.println("------s2------");
//        list.remove(1);
//        list.display();
//        System.out.println("------s2------");
//        list.remove(1);
//        list.display();
//        System.out.println("------s2------");
//        list.remove(1);
//        list.display();
//        System.out.println("------s2------");
//        list.remove(1);
//        list.display();



//       list.insert("f", 1);
//
//        System.out.println("------l1------");
//        list.display();
//        System.out.println("------l2------");
//
//        System.out.println(list.tail.getData());
//        System.out.println("?");
//        list.delete("pizza1");
//        list.display();


        //		list.addAtEnd("pizza11");

        //		list.addfirst("pizza3");
        //		list.addfirst("pizza4");
        //		list.addfirst("pizza5");
        //		list.addfirst("pizza6");
        //		list.addfirst("pizza7");
        //		list.addfirst("pizza8");
        //		list.addAtEnd("pizza9");

        //list.display();

    }


}
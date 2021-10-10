import java.lang.*;

public class LinkStrand implements IDnaStrand{
    private class Node {
        String info;
        Node next;

        public Node(String s, Node n) {
            info = s;
            next = n;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;
    private char myLastChar;

    public LinkStrand(){
        this("");
    }

    public LinkStrand(String source){
        initialize(source);
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst=new Node(source,null);
        myLast=myFirst;
        myCurrent=myFirst;
        myIndex=0;
        myLocalIndex=0;
        mySize=source.length();
        myAppends=0;
        myLastChar = ' ';
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna, null);
        myLast.next=newNode;
        myLast=newNode;
        mySize+=dna.length();
        myAppends++;
        return this;
    }

    // write method to flip contents of nodes
    private static String flip(Node n){
        // get content of node passed to flip() and store in temp StringBuilder object
        StringBuilder temp = new StringBuilder(n.info);
        // reverse temp StringBuilder object
        temp.reverse();
        // return reversed StringBuilder as a String
        return temp.toString();
    }

    @Override
    public IDnaStrand reverse() {
        // Initialized LinkStrand "rev"
        //LinkStrand rev = new LinkStrand();

        // initialize "nextNode" to point to current node being evaluated from original LinkStrand
        Node nextNode = this.myFirst;

        LinkStrand rev = new LinkStrand(flip(this.myFirst));
        // Get first node in original LinkStrand, flip() its contents, and use to initialize "rev"
        // this initializes rev.myFirst and rev.myLast as well
        rev.initialize(flip(this.myFirst));
        //rev.myFirst = new Node(flip(this.myFirst), null);

        // while the node referenced by "nextNode" is pointing to another node
        while(nextNode.next != null){
            // create new node for "rev" has a value equal to flip(nextNode.next.info)
            // and points to rev.myFirst
            Node temp = new Node(flip(nextNode.next), rev.myFirst);

            // update rev.myFirst to be the new node
            rev.myFirst = temp;

            // update nextNode to point to current node being evaluated from original LinkStrand
            nextNode = nextNode.next;

        }
        // update rev.mySize
        rev.mySize = this.mySize;
        return rev;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        // if the index is less than zer throw and index out of bounds exception
        if(index < 0 || index>=this.mySize) {
            throw new IndexOutOfBoundsException();
        }

        // when charAt() is called for the first time or the index comes before current index
        // loop through each node and each element in each node ( O(N) )
        if(this.myIndex == 0 | index < this.myIndex) {
            // set myIndex instance variable to index
            this.myIndex = index;

            // initialize int "count" to count number of indices
            int overall_count = 0;
            int within_count = 0;

            // initialize current to the first node
            Node current = this.myFirst;

            // while "count" is not equal to the target index
            while (overall_count != index) {
                // increase within-node count by one
                within_count++;

                // increase overall count by one
                overall_count++;


                // if the within node count is equal to the length of the node
                if (within_count >= current.info.length()) {
                    // move to the next node; reset current to next node
                    current = current.next;
                    // reset within_count to zero
                    within_count = 0;
                }
            }

            // once overall_count == index..
            // set myLocalIndex to within_count
            this.myLocalIndex = within_count;
            // set myCurrent to the "current" node
            this.myCurrent = current;
            // return the character at that node's "within_count" index
            this.myLastChar = current.info.charAt(within_count);
            return this.myLastChar;
            //System.out.println(this.myLastChar);


        } else { // if the index is greater than or equal to this.myIndex

            int overall_count = this.myIndex;
            int within_count = this.myLocalIndex;
            Node current = this.myCurrent;

            while(overall_count != index){
                // increase within-node count by one; starts count at one
                within_count++;

                // increase overall count by one; starts count at one
                overall_count++;

                // if the within node count is equal to the length of the node
                if(within_count >= current.info.length()){
                    // move to the next node; reset current to next node
                    current = current.next;
                    // reset within_count to zero
                    within_count = 0;
                }
            }
            // once overall_count == index..
                // set myLocalIndex to within_count
            this.myLocalIndex = within_count;
                // set myCurrent to the "current" node
            this.myCurrent = current;
            // set myIndex instance variable to index
            this.myIndex = index;
                // return the character at that node's "within_count" index
            this.myLastChar = current.info.charAt(within_count);
            return this.myLastChar;
            //System.out.println(this.myLastChar);
        }
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("");
        Node index=myFirst;
        while(index != myLast){
            s.append(index.info);
            index=index.next;
        }
        s.append(myLast.info);
        return s.toString();
    }
}

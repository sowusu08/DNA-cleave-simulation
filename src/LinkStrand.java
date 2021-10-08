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
    public static String flip(Node n){
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
        return 0;
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

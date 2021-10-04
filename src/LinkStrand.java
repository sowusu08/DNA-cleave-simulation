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

    @Override
    public IDnaStrand reverse() {
        return null;
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

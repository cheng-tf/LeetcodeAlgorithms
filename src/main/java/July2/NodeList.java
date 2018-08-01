package July2;

public class NodeList{
    int val;
    public NodeList(int val){
        this.val = val;
    }
    public NodeList next;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodeList next = this.next;
        sb.append("[").append(this.val).append(",");
        while(next != null) {
            sb.append(next.val).append(",");
            next = next.next;
        }
        return sb.toString().substring(0,sb.length()-1)+"]";
    }
}

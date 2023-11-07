package Stack;

public class Node<T> {
    public T data;
    public Node nextNode;
    public Node prevNode;
    public Node(T data, Node prevNode, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Node getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    public Node getPrevNode() {
        return prevNode;
    }
    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }
    public Node(T data) {
        this.data = data;
    }
}

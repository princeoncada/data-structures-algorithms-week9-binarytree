public class Node{
    private Node left, right;
    private int data;

    public Node() {
        left = right = null;
        data = 0;
    }

    public Node(int data) {
        left = right = null;
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public int getData() {
        return data;
    }
} // end of inner class
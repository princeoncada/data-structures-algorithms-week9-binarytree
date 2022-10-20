import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node getRoot() {
        return root;
    }

    public void addNode(int data) {
        if (!search(data)) {
            root = add(root, data);
            return;
        }
        JOptionPane.showMessageDialog(null, "Value already in tree!", "Duplicate Error", JOptionPane.WARNING_MESSAGE);
    }

    private Node add(Node node, int data) {
        if (isEmpty()) {
            root = new Node(data);
        }else {
            Queue<Node> q = new LinkedList<>();
            q.add(node);

            while (!q.isEmpty()) {
                node = q.peek();
                q.remove();
                if (node.getLeft() == null) {
                    node.setLeft(new Node(data));
                    break;
                } else q.add(node.getLeft());

                if (node.getRight() == null) {
                    node.setRight(new Node(data));
                    break;
                } else q.add(node.getRight());
            }
        }return root;
    }

    public String traverseLevelOrder() {
        return levelOrder(root);
    }

    private String levelOrder(Node node) {
        StringBuilder hold = new StringBuilder();
        if (isEmpty()) hold = new StringBuilder("Tree is empty");
        else {
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            while (!q.isEmpty()) {
                Node newNode = q.poll();
                hold.append(newNode.getData()).append(" ");
                if (newNode.getLeft() != null)
                    q.add(newNode.getLeft());
                if (newNode.getRight() != null)
                    q.add(newNode.getRight());
            }
        }
        return hold.toString();
    }

    public String traverseInOrder() {
        return inOrder(root).equals("") ? "Tree is empty" : inOrder(root);
    }

    private String inOrder(Node node){
        if (node != null)
            return inOrder(node.getLeft()) + node.getData() + " " + inOrder(node.getRight());
        else return "";
    }

    public String traversePreOrder() {
        return preOrder(root).equals("") ? "Tree is empty" : preOrder(root);
    }

    private String preOrder(Node node){
        if (node != null)
            return node.getData() + " " + preOrder(node.getLeft()) + preOrder(node.getRight());
        else return "";
    }

    public String traversePostOrder() {
        return postOrder(root).equals("") ? "Tree is empty" : postOrder(root);
    }

    private String postOrder(Node node){
        if (node != null)
            return postOrder(node.getLeft()) + postOrder(node.getRight()) + node.getData() + " ";
        else return "";
    }

    public int count() {
        return count(root);
    }

    private int count(Node node) {
        if (node == null)
            return 0;
        else {
            int ctr = 1;
            ctr += count(node.getLeft());
            ctr += count(node.getRight());
            return ctr;
        }
    }

    public boolean search(int val) {
        if (isEmpty()) return false;
        else return search(root, val);
    }

    private boolean search(Node node, int val) {
        if (node.getData() == val) return true;
        if (node.getLeft() != null) if (search(node.getLeft(), val)) return true;
        if (node.getRight() != null) return search(node.getRight(), val);
        return false;
    }

    public String printParents() {
        if (printParents(root).equals("")) return "Tree has no internal nodes";
        return printParents(root);
    }

    private String printParents(Node node) {
        if (node == null) return "";
        if (node.getLeft() != null || node.getRight() != null)
            return printParents(node.getLeft()) + node.getData() + " " + printParents(node.getRight());
        return "";
    }

    public String printLeaves() {
        if (printLeaves(root).equals("")) return "Tree has no leaf nodes";
        return printLeaves(root);
    }

    private String printLeaves(Node node) {
        String hold = "";
        if (node == null) return hold;
        if (node.getLeft() == null && node.getRight() == null)
            hold += node.getData() + " ";
        if (node.getLeft() != null || node.getRight() != null)
            return printLeaves(node.getLeft()) + printLeaves(node.getRight());
        return hold;
    }

    // depth, height, and level of a tree are the same unless specified which leaf should start or end
    public int height(){
        return depth(root);
    }
    public int level() {
        return depth(root);
    }
    public int depth() {
        return depth(root);
    }

    private int depth(Node node)
    {
        if (node == null)
            return 0;
        else {
            int leftSubTreeHeight = depth(node.getLeft());
            int rightSubTreeHeight = depth(node.getRight());

            if (leftSubTreeHeight > rightSubTreeHeight)
                return (leftSubTreeHeight + 1);
            else
                return (rightSubTreeHeight + 1);
        }
    }

    public String treeType() {
        String hold = "";
        if (root == null) return "empty";
        if (fullTree(root)) hold += "full ";
        if (completeTree(root)) hold += "complete";
        return hold;
    }

    private boolean fullTree(Node node) {
        if(node == null) return true;
        if(node.getLeft() == null && node.getRight() == null ) return true;
        if((node.getLeft()!=null) && (node.getRight()!=null))
            return (fullTree(node.getLeft()) && fullTree(node.getRight()));
        return false;
    }

    public boolean completeTree(Node root) {
        int total = countNodes(root);
        return helper(root, 1, total);
    }

    private int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }

    private boolean helper(Node root, int idx, int total) {
        if (root == null) {
            return true;
        }
        if (idx > total) {
            return false;
        }
        return helper(root.getLeft(), idx * 2, total)
                && helper(root.getLeft(), idx * 2 + 1, total);
    }

    public void cut() {
        root = null;
    }

    public void delete(int value) {
        delete(root, value);
    }

    private void deleteDeepest(Node delNode) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        Node temp = null;
        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp == delNode) {
                temp = null;
                return;
            }

            if (temp.getRight()!=null) {
                if (temp.getRight() == delNode) {
                    temp.setRight(null);
                    return;
                } else q.add(temp.getRight());
            }

            if (temp.getLeft() != null) {
                if (temp.getLeft() == delNode) {
                    temp.setLeft(null);
                    return;
                } else q.add(temp.getLeft());
            }
        }
    }

    private void delete(Node root, int key) {
        if (root == null) return;

        if (root.getLeft() == null && root.getRight() == null) {
            if (root.getData() == key) {
                root = null;
            }
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node temp = null, keyNode = null;

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.getData() == key) keyNode = temp;
            if (temp.getLeft() != null) q.add(temp.getLeft());
            if (temp.getRight() != null) q.add(temp.getRight());
        }

        if (keyNode != null) {
            int x = temp.getData();
            deleteDeepest(temp);
            keyNode.setData(x);
        }
    }
}

package util.leetcode;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by songpanfei on 2020-05-26.
 */
public class BSTree<T extends Comparable<T>> {

    private Node<T> root;
    private AtomicInteger size = new AtomicInteger(0);


    public boolean insert(T data){
        return insert(root, data);
    }

    public boolean insert(Node<T> root, T data){
        if(root == null){
            this.root = new Node(data);
            size.incrementAndGet();
            return true;
        }
        Node<T> node = new Node<>(data);
        Node<T> x = root;
        Node<T> xp = null;
        while (x != null){
            xp = x;
            int cmp = data.compareTo(x.data);
            if(cmp<0){
                x = x.getLeft();
            }else if(cmp>0){
                x = x.getRight();
            }else {
                return false;
            }
        }
        node.parent = xp;
        int cmp = data.compareTo(xp.data);
        if(cmp<0){
            xp.left = node;
        }else {
            xp.right = node;
        }
        size.incrementAndGet();
        return true;
    }

    public boolean del( T data){
        return del(root, data);
    }

    public boolean del(Node<T> root,  T data){
        if(root == null) return false;
        Node<T> x = search(data);
        if(x==null) return false;
        Node<T> child = null;
        if(x.left!=null&&x.right!=null){
            //获取后继结点
            Node<T> successor = min(x.right);
            //当前后继节点值交换
            T tmp = x.data;
            x.data = successor.data;
            successor.data = tmp;
            //把要删除的当前节点设置为后继结点
            x = successor;
        }
        //经过前一步处理，下面只有前两种情况，只能是一个节点或者没有节点
        //不管是否有子节点，都获取子节点
        if(x.left!=null){
            child = x.left;
        }
        if(x.right!=null){
            child = x.right;
        }
        //如果 child != null，就说明是有一个节点的情况
        if(child!=null){
            child.parent = x.parent;
        }
        //如果当前节点没有父节点（后继情况到这儿时一定有父节点）
        //说明要删除的就是根节点
        if(x.parent == null){
            this.root = child;
        }else if(x==x.parent.left){
            //将父节点的左节点设置为 child
            x.parent.left = child;
        }else if(x==x.parent.right){
            //将父节点的右节点设置为 child
            x.parent.right=child;
        }
        //数量增加1
        size.decrementAndGet();
        return true;
    }

    //获取后继节点，即比它大的最小节点
    public Node<T> min(Node<T> node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public Node search(T data){
        return search(root, data);
    }

    public Node search(Node root, T data){
        if(data == null || root == null) return null;
        Node<T> p = root;
        while (p != null){
            int cmp = data.compareTo(p.data);
            if(cmp<0){
                p = p.getLeft();
            }else if(cmp>0){
                p = p.getRight();
            }else {
                return p;
            }
        }
        return null;
    }


    //前序遍历  中左右
    public void preOrder(Node root){
        if(root == null) return;
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        stack.push(curr);
        while (!stack.isEmpty()){
            curr = stack.pop();
            System.out.println(curr.data);
            if(curr.right!=null) stack.push(curr.right);
            if(curr.left!=null) stack.push(curr.left);
        }
    }

    //中序遍历  左中右
    public void inOrder(Node root){
        if(root == null) return;
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null){
            while (curr!=null){
                stack.push(curr);
                curr=curr.left;
            }
            curr=stack.pop();
            System.out.println(curr.data);
            if(curr.right!=null){
                curr=curr.right;
            }
        }
    }

    //后序遍历  左右中 == 中右左入栈然后出栈
    public void postOrder(Node root){
        if(root == null) return;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node curr = root;
        stack1.push(curr);
        while (!stack1.isEmpty()){
            curr = stack1.pop();
            stack2.push(curr);
            if(curr.left!=null) stack1.push(curr.left);
            if(curr.right!=null) stack1.push(curr.right);
        }
        while (!stack2.isEmpty()){
            System.out.println(stack2.pop().data);
        }
    }

    class Node<T extends Comparable<T>>{
        T data;
        Node left;
        Node right;
        Node parent;

        public Node(){}
        public Node(T data){this.data = data;}
        public Node(T data, Node parent, Node left, Node right){
            this.data = data;
            this.parent = parent;
            this.left  = left;
            this.right = right;
        }
        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public static final void main(String args[]){
        BSTree tree = new BSTree();
        System.out.println(tree.insert(2));
        System.out.println(tree.insert(1));
//        System.out.println(tree.insert(3));
//        System.out.println(tree.insert(4));
//        System.out.println(tree.insert(5));
//        System.out.println(tree.insert(6));
//        System.out.println(tree.insert(7));
//        System.out.println(tree.insert(8));
//        System.out.println(tree.insert(9));
//        System.out.println(tree.insert(10));
        System.out.println(tree.search(2).data);
        System.out.println(tree.del(2));
        System.out.println(tree.search(2));
//
//        ThreadDemo04 t4 = new ThreadDemo04();
//        t4.start();
//        t4.interrupt();
//        flag = false;

    }
}

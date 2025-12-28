public class BST {

    /* Node */
    static class Node {
        int data;
        Node left, right, parent;

        Node(int data) {
            this.data = data;
        }
    }

    /* Binary Search Tree */
    static class BinarySearchTree {
        Node root;
        int size = 0;

        /* insert(data) */
        void insert(int data) {
            root = insertRec(root, null, data);
        }

        Node insertRec(Node current, Node parent, int data) {
            if (current == null) {
                size++;
                Node n = new Node(data);
                n.parent = parent;
                return n;
            }
            if (data < current.data)
                current.left = insertRec(current.left, current, data);
            else
                current.right = insertRec(current.right, current, data);

            return current;
        }

        /* find_node(data) */
        Node find_node(int data) {
            Node current = root;
            while (current != null) {
                if (current.data == data) return current;
                if (data < current.data)
                    current = current.left;
                else
                    current = current.right;
            }
            return null;
        }

        /* delete(node) */
        void delete(Node node) {
            if (node != null) {
                root = deleteRec(root, node.data);
                size--;
            }
        }

        Node deleteRec(Node root, int data) {
            if (root == null) return null;

            if (data < root.data)
                root.left = deleteRec(root.left, data);
            else if (data > root.data)
                root.right = deleteRec(root.right, data);
            else {
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;

                Node min = LMC(root.right);
                root.data = min.data;
                root.right = deleteRec(root.right, min.data);
            }
            return root;
        }

        /* get_height() */
        int get_height() {
            return height(root);
        }

        int height(Node node) {
            if (node == null) return -1;
            return 1 + Math.max(height(node.left), height(node.right));
        }

        /* get_size() */
        int get_size() {
            return size;
        }

        /* LMC() */
        Node LMC(Node node) {
            while (node.left != null)
                node = node.left;
            return node;
        }

        /* parent(node) */
        Node parent(Node node) {
            return node != null ? node.parent : null;
        }

        /* Traversals */
        void print_preorder() {
            preorder(root);
            System.out.println();
        }

        void preorder(Node node) {
            if (node == null) return;
            System.out.print(node.data + " ");
            preorder(node.left);
            preorder(node.right);
        }

        void print_inorder() {
            inorder(root);
            System.out.println();
        }

        void inorder(Node node) {
            if (node == null) return;
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }

        void print_postorder() {
            postorder(root);
            System.out.println();
        }

        void postorder(Node node) {
            if (node == null) return;
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.data + " ");
        }
    }

    /* MAIN */
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("=== Insert ===");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.print("Inorder   : ");
        bst.print_inorder();

        System.out.print("Preorder  : ");
        bst.print_preorder();

        System.out.print("Postorder : ");
        bst.print_postorder();

        System.out.println("\nHeight: " + bst.get_height());
        System.out.println("Size  : " + bst.get_size());

        System.out.println("\n=== Find Node ===");
        Node n = bst.find_node(30);
        System.out.println("Found node: " + (n != null ? n.data : "Not Found"));

        System.out.println("\n=== Delete Node 30 ===");
        bst.delete(n);

        System.out.print("Inorder after delete: ");
        bst.print_inorder();

        System.out.println("Size after delete: " + bst.get_size());
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST.Node<K, V>> {

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private Node<K, V> root;
    private int size = 0;

    public void put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node<K, V> current = root;
        Node<K, V> parent = null;

        while (current != null) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                // Update value if key already exists
                current.value = value;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }

    public V get(K key) {
        Node<K, V> current = root;

        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public boolean remove(K key) {
        Node<K, V> current = root;
        Node<K, V> parent = null;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return false;

        // Case 1:
        if (current.left == null || current.right == null) {
            Node<K, V> newChild;
            if (current.left != null) {
                newChild = current.left;
            } else {
                newChild = current.right;
            }

            if (parent == null) {
                root = newChild;
            } else if (parent.left == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        } else {
            // Case 2:
            Node<K, V> successorParent = current;
            Node<K, V> successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != current) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            current.key = successor.key;
            current.value = successor.value;
        }

        size--;
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Node<K, V>> {
        private Stack<Node<K, V>> stack = new Stack<>();

        public BSTIterator() {
            Node<K, V> current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<K, V> node = stack.pop();
            Node<K, V> current = node.right;

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            return node;
        }
    }


    private static class Stack<E> {
        private java.util.LinkedList<E> list = new java.util.LinkedList<>();

        public void push(E value) {
            list.addFirst(value);
        }

        public E pop() {
            return list.removeFirst();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }
}

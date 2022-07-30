package com.witalis.jkit.usage.core.invoke.section.collection.context;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Slf4j
@Data
@ToString
public class Tree {
    private Node root;

    public Tree() {
        setRoot(null);
    }

    // ------------------- SEARCH -------------------

    // iterative
    public Node find(int value) {
        if (root == null) {
            return null;
        }
        Node currentNode = root;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    // recursive
    public Node findRecursive(int value) {
        return findRecursive(root, value);
    }

    private Node findRecursive(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == value) {
            return node;
        }
        if (node.getValue() > value) {
            return findRecursive(node.getLeft(), value);
        } else {
            return findRecursive(node.getRight(), value);
        }
    }

    // DFS (depth first search) - vertically
    public final Node dfsFind(int value) {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<> ();
        Node currentNode = root;
        while (currentNode != null || !stack.empty()) {
            if (!stack.empty()) {
                currentNode = stack.pop();
            }
            while (currentNode != null) {
                if (currentNode.getValue() == value) {
                    return currentNode;
                }
                if (currentNode.getRight() != null) {
                    stack.push(currentNode.getRight());
                }
                currentNode = currentNode.getLeft();
            }
        }
        return null;
    }

    // BFS (breadth first search) - horizontally
    public final Node bfsFind(int value) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.getValue() > 0 && currentNode.getValue() == value) {
                return currentNode;
            }
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
        return null;
    }

    // ------------------- TRAVERSE -------------------

    public void traverseInOrderRecursive() {
        traverseInOrderRecursive(root);
    }

    private void traverseInOrderRecursive(Node node) {
        if (node != null) {
            traverseInOrderRecursive(node.getLeft());
            visit(node.getValue());
            traverseInOrderRecursive(node.getRight());
        }
    }

    public void traversePreOrderRecursive() {
        traversePreOrderRecursive(root);
    }

    private void traversePreOrderRecursive(Node node) {
        if (node != null) {
            visit(node.getValue());
            traversePreOrderRecursive(node.getLeft());
            traversePreOrderRecursive(node.getRight());
        }
    }

    public void traversePostOrderRecursive() {
        traversePostOrderRecursive(root);
    }

    private void traversePostOrderRecursive(Node node) {
        if (node != null) {
            traversePostOrderRecursive(node.getLeft());
            traversePostOrderRecursive(node.getRight());
            visit(node.getValue());
        }
    }

    public void traverseInOrder() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            Node top = stack.pop();
            visit(top.getValue());
            current = top.getRight();
        }
    }

    public void traversePreOrder() {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        stack.push(root);
        while (current != null && !stack.isEmpty()) {
            current = stack.pop();
            visit(current.getValue());
            if (current.getRight() != null)
                stack.push(current.getRight());
            if (current.getLeft() != null)
                stack.push(current.getLeft());
        }
    }

    public void traversePostOrder() {
        Stack<Node> stack = new Stack<>();
        Node prev = root;
        Node current = root;
        stack.push(root);
        while (current != null && !stack.isEmpty()) {
            current = stack.peek();
            boolean hasChild = (current.getLeft() != null || current.getRight() != null);
            boolean isPrevLastChild = (prev == current.getRight() || (prev == current.getLeft() && current.getRight() == null));
            if (!hasChild || isPrevLastChild) {
                current = stack.pop();
                visit(current.getValue());
                prev = current;
            } else {
                if (current.getRight() != null) {
                    stack.push(current.getRight());
                }
                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                }
            }
        }
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            visit(node.getValue());
            if (node.getLeft() != null)
                nodes.add(node.getLeft());
            if (node.getRight() != null)
                nodes.add(node.getRight());
        }
    }

    // ------------------- CONTAINS -------------------

    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (current.getValue() == value) {
            return true;
        }
        return current.getValue() > value
            ? containsRecursive(current.getLeft(), value)
            : containsRecursive(current.getRight(), value);
    }

    // ------------------- INSERT -------------------

    // iterative
    public void add(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null) {
            root = newNode;
        } else {
            Node currentNode = root;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {
                    // such element already exists
                    return;
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeft();
                    if (currentNode == null) {
                        parentNode.setLeft(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    // recursive
    public void addRecursive(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (root == null) {
            root = newNode;
        } else {
            addRecursive(root, value);
        }
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            Node newNode = new Node();
            newNode.setValue(value);
            return newNode;
        }
        if (current.getValue() > value) {
            current.setLeft(addRecursive(current.getLeft(), value));
        } else if (current.getValue() < value) {
            current.setRight(addRecursive(current.getRight(), value));
        } else {
            // such value already exists
            return current;
        }
        return current;
    }

    // ------------------- DELETE -------------------

    public boolean remove(int value) {
        Node currentNode = root;
        Node parentNode = root;
        boolean isLeftChild = true;

        while (currentNode.getValue() != value) {
            parentNode = currentNode;
            if (value < currentNode.getValue()) {
                isLeftChild = true;
                currentNode = currentNode.getLeft();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null)
                // node is absent
                return false;
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            // no children - just remove node
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (currentNode.getRight() == null) {
            // no right child - replace with left sub-tree
            if (currentNode == root) {
                root = currentNode.getLeft();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }
        } else if (currentNode.getLeft() == null) {
            // no left child - replace with right sub-tree
            if (currentNode == root) {
                root = currentNode.getRight();
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }
        } else {
            // there are 2 children - algorithm (right -> left)
            Node heir = findSmallest(currentNode);
            if (currentNode == root) {
                root = heir;
            } else if (isLeftChild) {
                parentNode.setLeft(heir);
            } else {
                parentNode.setRight(heir);
            }
        }
        return true;
    }

    // recursive
    public boolean removeRecursive(int value) {
        root = removeRecursive(root, value);
        return true;
    }

    private Node removeRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (current.getValue() == value) {
            // no children
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }
            // only one child
            if (current.getRight() == null) {
                return current.getLeft();
            }
            if (current.getLeft() == null) {
                return current.getRight();
            }
            // two children
            int smallestValue = findSmallestRecursive(current.getRight());
            current.setValue(smallestValue);
            current.setRight(removeRecursive(current.getRight(), smallestValue));
            return current;
        }
        if (current.getValue() > value) {
            current.setLeft(removeRecursive(current.getLeft(), value));
            return current;
        }
        current.setRight(removeRecursive(current.getRight(), value));
        return current;
    }

    // ------------------- NEXT -------------------

    private Node findSmallest(Node node) {
        Node parentNode = node;
        Node heirNode = node;

        // start with the right child
        Node currentNode = node.getRight();
        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeft();
        }
        if (heirNode != node.getRight()) {
            parentNode.setLeft(heirNode.getRight());
            heirNode.setRight(node.getRight());
        }
        return heirNode;
    }

    private int findSmallestRecursive(Node root) {
        return root.getLeft() == null ? root.getValue() : findSmallestRecursive(root.getLeft());
    }

    // ------------------- SIZE -------------------

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node current) {
        return current == null ? 0 : getSizeRecursive(current.getLeft()) + 1 + getSizeRecursive(current.getRight());
    }

    // ------------------- OTHERS -------------------

    public boolean isEmpty() {
        return root == null;
    }

    private void visit(int value) {
        log.info(" " + value);
    }
}

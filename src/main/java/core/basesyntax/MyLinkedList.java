package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    @Override
    public void add(T value) {
        Node<T> last = tail;
        Node<T> node = new Node<>(last, value, null);
        tail = node;
        if (last == null) {
            head = node;
        } else {
            last.next = node;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        Node<T> currentNode = findByIndex(index);
        Node<T> newNode = new Node<>(currentNode.prev, value, currentNode);
        if (currentNode.prev == null) {
            head = newNode;
        } else {
            currentNode.prev.next = newNode;
        }
        currentNode.prev = newNode;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T item : list) {
            add(item);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> node = findByIndex(index);
        if (node != null) {
            return node.item;
        }
        return null;
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        Node<T> node = findByIndex(index);
        T oldValue = node.item;
        node.item = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Node<T> node = findByIndex(index);
        T item = node.item;
        unlink(node);
        return item;
    }

    @Override
    public boolean remove(T object) {
        Node<T> currentNode = head;
        for (int i = 0; i < size; i++) {
            T item = currentNode.item;
            if (item == object || (item != null && item.equals(object))) {
                unlink(currentNode);
                return true;
            }
            if (currentNode.next != null) {
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void unlink(Node<T> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.item = null;
        node.prev = null;
        node.next = null;

        size--;
    }

    private Node<T> findByIndex(int index) {
        if (index < size / 2) {
            Node<T> currentNode = head;
            for (int i = 0; i < size; i++) {
                if (index == i) {
                    return currentNode;
                }
                currentNode = currentNode.next;
            }
        } else {
            Node<T> currentNode = tail;
            for (int i = size - 1; i >= 0; i--) {
                if (index == i) {
                    return currentNode;
                }
                currentNode = currentNode.prev;
            }
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}

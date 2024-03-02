package iterator;

import java.util.Iterator;
import java.util.Optional;

public class CircularListWithIteratorImpl implements CircularListWithIterator {
    private Node head;
    private Node tail;
    private int size;
    private Node lastReturned;

    public CircularListWithIteratorImpl() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void add(int element) {
        if (head == null) {
            insertFirst(element);
        } else {
            insertLast(element);
        }
        size++;
    }

    private void insertLast(int element) {
        final Node l = tail;
        final Node newNode = new Node(l, element, head);

        head.prev = newNode;
        l.next = newNode;
        tail = newNode;
    }

    private void insertFirst(int element) {
        Node newNode = new Node(null, element, null);
        newNode.next = newNode.prev = newNode;
        head = tail = lastReturned = newNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<Optional> forwardIterator() {
        return new ForwardIterator();
    }

    @Override
    public Iterator<Optional> backwardIterator() {
        return new BackwardIterator();
    }


    private class Node {
        private int item;
        private Node next;
        private Node prev;

        Node(Node prev, int item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ForwardIterator implements Iterator<Optional> {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Optional<Integer> next() {
            if (isEmpty()) {
                return Optional.empty();
            } else {
                return navigateForward();
            }
        }

        private Optional<Integer> navigateForward() {
            if (lastReturned == null) {
                lastReturned = head;
            } else {
                lastReturned = lastReturned.next;
            }
            return Optional.of(lastReturned.item);
        }
    }

    private class BackwardIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Optional<Integer> next() {
            if (isEmpty()) {
                return Optional.empty();
            } else {
                return navigateBackwards();
            }
        }

        private Optional<Integer> navigateBackwards() {
            if (lastReturned == null) {
                lastReturned = head;
            } else {
                lastReturned = lastReturned.prev;
            }
            return Optional.of(lastReturned.item);
        }
    }
}

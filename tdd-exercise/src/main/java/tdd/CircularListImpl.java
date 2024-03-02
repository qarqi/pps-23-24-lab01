package tdd;

import java.util.Optional;
import java.util.function.Predicate;

public class CircularListImpl implements CircularList {
    private Node head;
    private Node tail;
    private int size;
    private Node lastReturned;

    public CircularListImpl() {
        head = tail = lastReturned = null;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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

    @Override
    public Optional<Integer> previous() {
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

    @Override
    public void reset() {
        lastReturned.prev.next = lastReturned.next;
        lastReturned.next.prev = lastReturned.prev;
        head.prev = lastReturned;
        lastReturned = new Node(tail, lastReturned.item, head);
        head = lastReturned;
    }

    @Override
    public Optional filteredNext(Predicate<Integer> condition) {
        for (int i = 1; i <= size; i++) {
            int nextElement = this.next().get();
            if (condition.test(nextElement)) {
                return Optional.of(nextElement);
            }
        }
        return Optional.empty();
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

}


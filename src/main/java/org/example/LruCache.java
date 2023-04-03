package org.example;

import java.util.HashMap;
import java.util.Map;

public class LruCache<T> {
    private LruCache.LinkedList list;
    private Map<Integer, Node> keyValueMap;
    private Map<Node, Integer> valueKeyMap;
    private Integer capacity;

    public LruCache(Integer capacity) {
        this.capacity = capacity;
        list = new LruCache.LinkedList<T>();
        keyValueMap = new HashMap<>();
        valueKeyMap = new HashMap<>();
    }

    public void put(Integer key, T val) {
        if (this.capacity > list.getLength()) {
            this.list.add(val);
            keyValueMap.put(key, list.head);
            valueKeyMap.put(list.head, key);
        } else {
            Node tail = this.list.tail;
            Integer keyToNode = valueKeyMap.get(tail);
            keyValueMap.remove(keyToNode);
            valueKeyMap.remove(tail);
            this.list.deleteLast();
            this.list.add(val);
            keyValueMap.put(key, list.head);
            valueKeyMap.put(list.head, key);
        }
    }

    public T get(Integer key) {
        Node node = keyValueMap.get(key);
        if (node == null) {
            Node<Integer> integerNode = new Node<>(-1);
            return null;
        }
        this.list.moveToHead(node);
        return (T) node.val;
    }

    public static class Node<T> {
        public T val;
        public LruCache.Node next;
        public LruCache.Node prev;

        public Node() {
        }

        public Node(T val) {
            this.val = val;
        }
    }

    public static class LinkedList<T> {
        public Node head;
        public Node tail;

        private Integer length;

        public LinkedList() {
            length = 0;
        }


        public Integer getLength() {
            return this.length;
        }

        public void deleteLast() {
            if (length >= 2) {
                // delete last
                // update head and tail
                // update length
                tail = tail.prev;
                tail.next = tail;
                length--;
            } else if (length == 1) {
                // delete the only node
                // update head and tail
                // update length
                head = null;
                tail = null;
                length--;
            } else {
                // return null
                return;
            }
        }

        public void add(T val) {
            Node newNode = new Node(val);
            // add the new node to the head of the list
            if (head != null) {
                head.prev = newNode;
                newNode.next = head;
                newNode.prev = newNode;
                head = newNode;
                length++;
            } else {
                head = newNode;
                tail = newNode;
                newNode.prev = newNode;
                newNode.next = newNode;
                length++;
            }

        }

        public void moveToHead(Node node) {
            if (node == head) {
                return;
            } else if (node == tail) {
                Node prev = node.prev;
                node.next = head;
                node.prev = node;
                head.prev = node;
                head = node;
                tail = prev;
                tail.next = tail;
            } else {
                Node prev = node.prev;
                Node next = node.next;
                node.next = head;
                head.prev = node;
                node.prev = node;
                prev.next = next;
                next.prev = prev;

            }
        }

    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(2);


        cache.put(1, 1);

        cache.put(2, 2);

        cache.get(1);

        cache.put(3, 3);

        cache.get(2);

        cache.put(1, 4);


    }
}
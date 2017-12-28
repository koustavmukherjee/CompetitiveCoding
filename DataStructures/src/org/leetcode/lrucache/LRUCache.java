package org.leetcode.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private Map<Integer, DLinkedList> cache;
	/*
	 * The most recently accessed element always resides at the tail
	 */
	private DLinkedList head = null, tail = null;
	private int capacity;

	public LRUCache(int capacity) {
		this.cache = new HashMap<>();
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			DLinkedList node = cache.get(key);
			int value = node.value;
			node.update();
			return value;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			DLinkedList node = cache.get(key);
			node.value = value;
			node.update();
			return;
		}

		if (this.capacity == cache.size()) {
			DLinkedList removedNode = this.remove();
			if (removedNode == null)
				return;
			else {
				cache.remove(removedNode.key);
			}
		}
		DLinkedList node = new DLinkedList();
		node.key = key;
		node.value = value;
		node.append();
		cache.put(key, node);
	}

	/**
	 * The remove operation is responsible for removing a node from the head
	 */
	public DLinkedList remove() {
		DLinkedList headNode = head;
		if (head != null) {
			if (head == tail)
				head = tail = null;
			else {
				head.right.left = null;
				head = head.right;
			}
		}
		return headNode;
	}

	class DLinkedList {
		int key;
		int value;
		DLinkedList left;
		DLinkedList right;

		/**
		 * The update operation is responsible for removing a node from its
		 * current position and adding it to the tail
		 */
		public void update() {
			// If this is the most recently accessed element, there is no need
			// to update it
			if (this == tail)
				return;
			if (this == head) {
				this.right.left = null;
				head = this.right;
			} else {
				this.left.right = this.right;
				this.right.left = this.left;
			}
			this.append();
		}

		/**
		 * The append operation is responsible for adding a new node at the tail
		 */
		public void append() {
			// adding the first node
			if (head == null && tail == null) {
				head = tail = this;
			} else {
				tail.right = this;
				this.left = tail;
				this.right = null;
				tail = this;
			}
		}
	}
}

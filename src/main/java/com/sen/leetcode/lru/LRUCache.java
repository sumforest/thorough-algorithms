package com.sen.leetcode.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LingSen
 * @date 2023/6/6 16:42
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * +----------------------------------------------+
 * |                                              |
 * |      key    MAP          key                 |
 * |                                              |
 * |    +---+               +-----+               |
 * |    |   |               |     |               |
 * |    +-+-+               +---+-+               |
 * |      |                     |                 |
 * |      |                     |                 |
 * +----------------------------------------------+
 * head              |                     |
 * |                     |                 tail
 * +                |                     |                  +
 * |                |                     |                  |
 * |                |                     |                  |
 * |                |                     |                  |
 * +----v----+        +--v-----+         +-----v----+       +-----v----+
 * |         +--------+   key  +---------+          +-------+          |
 * |  最新   |        +--------+         |          |       |   最旧   |
 * |         +--------+    val +---------+          +-------+          |
 * +---------+        +--------+         +----------+       +----------+
 */
public class LRUCache {

    private final int capacity;

    private int size;
    private final Map<Integer, Node> cache;

    private final DLinkdList dLinkdList = new DLinkdList();

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 更新双向链表
        dLinkdList.moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // 更新
        if (node != null) {
            node.val=value;
            dLinkdList.moveToHead(node);
            return;
        }
        // 新增
        if (size >= capacity) {
            // cache没有并且满了，调出最久没使用的
            removeLongVal();
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        dLinkdList.addHead(newNode);
        size++;
    }

    private void removeLongVal() {
        Node node = dLinkdList.removeTail();
        cache.remove(node.key);
    }

    class Node {
        private int key;
        private int val;

        private Node pre;

        private Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    class DLinkdList {

        /**
         * 头部为最新内容
         */
        private Node head = new Node();

        private Node tail = new Node();

        public DLinkdList() {
            this.head.next = tail;
            this.tail.pre = head;
        }

        private void addHead(Node node) {
            Node _next = head.next;
            head.next = node;
            node.pre = head;

            node.next = _next;
            _next.pre = node;
        }

        private void moveToHead(Node node) {
            // 删除当前节点
            remove(node);

            // 头部插入
            addHead(node);
        }

        private void remove(Node node) {
            Node _pre = node.pre;
            Node _next = node.next;
            _pre.next = _next;
            _next.pre = _pre;
        }

        private Node removeTail() {
            Node node = tail.pre;
            node.pre.next = tail;
            tail.pre = node.pre;
            return node;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}

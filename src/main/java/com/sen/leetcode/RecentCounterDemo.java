package com.sen.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LingSen
 * @date 2023/6/8 17:15
 * 写一个RecentCounter类来计算特定时间范围内最近的请求。
 * <p>
 * 请你实现 RecentCounter 类：
 * <p>
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 * <p>
 * 示例 1：
 * 输入：
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * 输出：
 * [null, 1, 2, 3, 3]
 * 解释：
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
 * recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-recent-calls
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RecentCounterDemo {

    public static void main(String[] args) {
        RecentCounterDemo recentCounterDemo = new RecentCounterDemo();
        RecentCounter recentCounter = new RecentCounter();
        for (int i = 1; i <= 10000; i++) {
            System.out.println(recentCounterDemo.ping(i));
//            System.out.println(recentCounter.ping(i));
        }

    }

    private int count;

    private final Queue queue;

    public RecentCounterDemo() {
        this.count = 0;
        this.queue = new Queue();
    }

    public int ping(int t) {
        if (count > 3000) {
            // 出队
            queue.poll();
            // 入队
            queue.offer(t);
        }else {
            queue.offer(t);
            count++;
        }

        return queue.count(t);
    }

    class Queue{
        private Node head = new Node();

        private Node tail = new Node();

        public Queue() {
            head.next = tail;
            tail.pre= head;
        }

        /**
         * 入队
         * @param t
         */
        public void offer(int t) {
            Node node = new Node(t);
            Node _next = head.next;
            head.next = node;
            node.pre = head;

            _next.pre = node;
            node.next=_next;
        }

        public void poll(){
            Node pre = tail.pre.pre;

            pre.next=tail;
            tail.pre = pre;
        }

        public int count(int t) {
            int count = 0;
            // 遍历查找返回t大于t-3000的节点
            Node cur = queue.head.next;
            while (cur.t >= (t - 3000) && cur != tail) {
                count++;
                cur = cur.next;
            }
            return count;
        }
    }

    class Node {
        private int t;

        private Node pre;
        private Node next;

        public Node() {
        }

        public Node(int t) {
            this.t = t;
        }
    }

    static class RecentCounter {
        java.util.Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<Integer>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}

package com.sen.data.structure.stack;

import java.util.Scanner;

/**
 * @Author: Sen
 * @Date: 2020/1/12 22:10
 * @Description: 栈(单向链表实现)
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("print:打印栈的信息");
            System.out.println("offer:压栈");
            System.out.println("pop:弹出栈顶元素");
            System.out.println("exit:退出程序");
            String command = scanner.next();
            switch (command) {
                case "print":
                    stack.print();
                    break;
                case "offer":
                    System.out.println("请输入值:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    flag = false;
                    break;
            }
        }
        System.out.println("程序已退出");
    }

    private static class Stack {
        /**
         * 头节点
         */
        Node head = new Node();

        public boolean isEmpty() {
            return head.next == null;
        }

        public void push(int data) {
            Node node = new Node(data);
            if (isEmpty()) {
                head.next = node;
                return;
            }
            //把压进来的的节点连接在头节点的后面
            node.next = head.next;
            head.next = node;
        }

        /**
         * 弹出栈顶元素:返回头节点的后一个节点
         *
         * @return
         */
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            int result = head.next.data;
            //移除栈顶节点
            head.next = head.next.next;
            return result;
        }

        public void print() {
            if (isEmpty()) {
                System.err.println("Stack is empty!");
            }
            Node current = head.next;
            while (current != null) {
                System.out.println(current);
                current = current.next;
            }
        }
    }

    /**
     * 节点用于存储栈数据
     */
    private static class Node {

        int data;

        Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}

package com.sen.data.structure.queue;

import java.util.Scanner;

/**
 * @Author: Sen
 * @Date: 2020/1/11 18:15
 * @Description: 数组模拟队列
 * 缺点：数组的空间无法重复利用
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("s:队列的情况");
            System.out.println("a:添加元素到队列");
            System.out.println("r:出队");
            System.out.println("p:查看队头元素");
            System.out.println("e:退出程序");
            String command = scanner.next();
            char ch = command.charAt(0);
            switch (ch) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    System.out.println("输入要添加的值（按回车键结束）:");
                    int value = scanner.nextInt();
                    arrayQueue.add(value);
                    break;
                case 'r':
                    try {
                        System.out.println(arrayQueue.remove());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.println("peek:" + arrayQueue.peek());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
            }
        }
        System.out.println("程序正在退出...");
    }
}

class ArrayQueue {
    /**
     * 栈的最大存放数量
     */
    private int maxSize;

    /**
     * 指向队列头部元素的前面，不含头部元素,初始值为-1
     */
    private int front;

    /**
     * 指向最为的最后一个元素包含最后一个元素，初始值为-1
     */
    private int rear;

    /**
     * 存储队列的数据
     */
    int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[this.maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        //是否达到数组最后一个元素
        return rear == maxSize - 1;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("Stack if full");
        } else {
            //把rear指针移到需要加的位置
            rear++;
            arr[rear] = value;
        }
    }

    /**
     * 出队
     */
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        //移动front指针到需要出队的元素下标
        front++;
        return arr[front];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, arr[i]);
            }
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return arr[front + 1];
    }
}

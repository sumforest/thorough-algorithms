package com.sen.data.structure.queue;

import java.util.Scanner;

/**
 * @Author: Sen
 * @Date: 2020/1/11 21:48
 * @Description: 环形队列
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //基于数组实现的循环队列，可用空间为maxSize - 1，预留一个空间动态变换
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
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
                    circleArrayQueue.show();
                    break;
                case 'a':
                    System.out.println("输入要添加的值（按回车键结束）:");
                    int value = scanner.nextInt();
                    circleArrayQueue.add(value);
                    break;
                case 'r':
                    try {
                        System.out.println(circleArrayQueue.remove());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.println("peek:" + circleArrayQueue.peek());
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
    }
}

class CircleArrayQueue {
    /**
     * 栈的最大存放数量
     */
    private int maxSize;

    /**
     * 指向队列头部元素包含含头部元素,初始值为0
     */
    private int front;

    /**
     * 指向最后一个元素的后一个位置，初始值为0
     */
    private int rear;

    /**
     * 存储队列的数据
     */
    int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        //是否达到数组最后一个元素,适配 rear = maxSize - 1 情况,front = 0；此时 rear + 1 = maxSize越界，取模处理rear范围在[0,maxSize-1]区间
        return (rear + 1) % maxSize == front;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("Stack if full");
        } else {
            //rear当前的指针就是添加的位置
            arr[rear] = value;
            //移动rear，考虑rear当前已经是在arr.length()-1的位置，在移动下次赋值就越界，所以移动后需要取模
            rear = (rear + 1) % maxSize;
        }
    }

    /**
     * 出队
     */
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        //front的当前位置就是队列头部元素的下标
        int result = arr[front];
        //当前是环形队列，当front在数组的最后一个下标在移动就会越界，所以移动后需要取模,让其循环
        front = (front + 1) % maxSize;
        return result;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
        } else {
            for (int i = front; i < front + getAvailableSize(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            }
        }
    }

    /**
     * 获取数组的有效长度
     * @return
     */
    private int getAvailableSize(){
        return (rear + maxSize - front) % maxSize;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return arr[front];
    }
}
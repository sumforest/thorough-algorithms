package com.sen.data.structure.stack;

import java.util.Scanner;

/**
 * @Author: Sen
 * @Date: 2020/1/12 21:25
 * @Description: 栈(数组实现)
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("print:打印栈的信息");
            System.out.println("push:压栈");
            System.out.println("pop:弹出栈顶元素");
            System.out.println("exit:退出程序");
            String command = scanner.next();
            switch (command) {
                case "print":
                    stack.print();
                    break;
                case "push":
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

    private static class ArrayStack {

        /**
         * 栈的最大容量
         */
        int maxSize;

        /**
         * 标记栈顶元素的指针
         */
        int top = -1;

        /**
         * 用于保存栈
         */
        int[] stack;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[maxSize];
        }

        /**
         * 判断是否栈满
         *
         * @return 满返回true
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }

        /**
         * 栈是否为空
         *
         * @return true 为空
         */
        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int value) {
            if (isFull()) {
                System.err.println("栈满,压栈失败");
                return;
            }
            //压栈
            //移动指针
            top++;
            stack[top] = value;
        }

        public int pop(){
            if (isEmpty()) {
                throw new RuntimeException("Stack is Empty!");
            }
            int temp = stack[top];
            //移动指针
            top--;
            return temp;
        }

        /**
         * 打印栈结构,从栈顶开始打印
         */
        public void print(){
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("Stack[%d]=%d\n",i,stack[i]);
            }
        }
    }
}

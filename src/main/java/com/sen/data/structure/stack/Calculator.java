package com.sen.data.structure.stack;

/**
 * @Author: Sen
 * @Date: 2020/1/12 23:08
 * @Description: 栈实现综合计算器(只支持 + - * /) 中缀表达式
 */
public class Calculator {

    public static void main(String[] args) {
        String expression = "30+2*6/2-20";
        //创建数栈
        ArrayStack numStack = new ArrayStack(10);
        //创建符号栈
        ArrayStack operatorStack = new ArrayStack(10);
        //记录遍历位置的指针
        int index = 0;

        int num1;
        int num2;
        int result;
        int operator;
        String temp = "";
        while (index != expression.length()) {
            char ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是否为操作符
            if (operatorStack.isOperator(ch)) {
                //判断符号栈是否为空
                if (operatorStack.isEmpty()) {
                    //符号栈为空直接入栈
                    operatorStack.push(ch);
                }
                //符号栈不为空
                else {
                    //比较符号栈顶的操作符和当前操作符的优先级
                    if (operatorStack.priority(ch) > operatorStack.priority(operatorStack.peek())) {
                        //优先级比栈顶的高直接入栈
                        operatorStack.push(ch);
                    }
                    //优先级小于或等于栈顶操作符
                    else {
                        //从数栈弹出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //从操符号栈中弹出一个符号
                        operator = operatorStack.pop();
                        result = numStack.calculator(num1, num2, operator);
                        numStack.push(result);
                        //把当前的符号压入符号栈
                        operatorStack.push(ch);
                    }
                }
            }
            //ch为数字
            else {
                //根据ASCII码表0的ASCII码为48
                // numStack.push(ch - 48);
                // 当前为位是数字时不能直接入栈,还需要往后探一位是否也是数字位,不是数字位时才入栈
                temp += ch;
                //如果当前位已经是最后以位时直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(temp));
                } else {
                    //后探一位是否位符号位
                    if (numStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(temp));
                        //把temp置空复用
                        temp = "";
                    }
                }
            }
            index++;
        }
        //表达式遍历完后把数栈和符号栈的元素依次弹出,计算
        while (!operatorStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            result = numStack.calculator(num1, num2, operator);
            //把结果压回栈中
            numStack.push(result);
        }
        //栈中的唯一元素就是最后结果
        System.out.printf("表达式:%s = %d", expression, numStack.peek());
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

        public int pop() {
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
        public void print() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("Stack[%d]=%d\n", i, stack[i]);
            }
        }

        public int peek() {
            return stack[top];
        }

        /**
         * 判断是否为操作符
         *
         * @param operator 操作符
         * @return true为操作符
         */
        public boolean isOperator(int operator) {
            return operator == '+' || operator == '-' || operator == '*' || operator == '/';
        }

        /**
         * 返回操作符的优先级
         *
         * @param operator 操作符
         * @return 数值越大优先级越高, 当是一个非法的操作符时返回-1
         */
        public int priority(int operator) {
            if (operator == '*' || operator == '/') {
                return 1;
            }
            if (operator == '+' || operator == '-') {
                return 0;
            }
            return -1;
        }

        /**
         * 计算两个数的结果
         *
         * @param num1     参数1
         * @param num2     参数2
         * @param operator 操作符
         * @return 结果
         */
        public int calculator(int num1, int num2, int operator) {
            int result = Integer.MIN_VALUE;
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    //注意先后顺序,用先入栈的数减去后入栈的数
                    result = num2 - num1;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    //先入栈的数除与后入栈的数
                    result = num2 / num1;
                    break;

            }
            return result;
        }
    }
}

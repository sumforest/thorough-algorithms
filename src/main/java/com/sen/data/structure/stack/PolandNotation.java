package com.sen.data.structure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Sen
 * @Date: 2020/1/13 01:35
 * @Description: 实现逆波兰表达式(后缀表达式)
 */
public class PolandNotation {

    public static void main(String[] args) {
        /*
        (3+4)*5-6
        (30+4)*5-6
        4*5-8+60+8/2 -> 逆波兰表达式 4 5 * 8 - 60 + 8 2 / +
        为了计算简便通过
        思路:1.将表达式存放在在集合中方便遍历
             2.遍历表达式,并将数字压入栈中
        */
       /* String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        List<String> stringList = toList(suffixExpression);
        System.out.println(calculate(stringList));*/

       /*
       中缀表达式转后缀表达式 1+((2+3)*4)-5
       思路:
            1.先把中缀表达式字符串转换成字符串集合方便操作
            2.将1+((2+3)*4)-5中缀表达式转换成后缀表达式1 2 3 + 4 * + 5 -
            3.把中缀表达式List：[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
              转换成后缀表达式List:[1,2,3,+,4,*,+,5,-]
       */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println(infixExpressionList);
        List<String> suffixExpression = toSuffixExpression(infixExpressionList);
        System.out.println(suffixExpression);
        System.out.println(calculate(suffixExpression));
    }

    /**
     * 返回表达式集合
     *
     * @param expression 逆波兰表达式
     * @return 表达式集合
     */
    private static List<String> toList(String expression) {
        String[] strings = expression.split(" ");
        return Arrays.asList(strings);
    }

    /**
     * 利用逆波兰表达式计算
     *  1+((2+3)*4)-5 -> 1,2,3,+,4,*,+,5,-
     * @param stringList 逆波兰表达式
     * @return
     */
    private static int calculate(List<String> stringList) {
        Stack<String> stack = new Stack<>();
        for (String str : stringList) {
            //正则表达式一个或多个数字
            if (str.matches("\\d+")) {
                //数字入栈
                stack.push(str);
            }
            //符号位,弹出栈里面的两个数字进行运算后出的数作为减数或者除数
            else {
                // 栈顶元素作为被减数/被除数
                int num2 = Integer.parseInt(stack.pop());
                // 磁钉元素作为减数/除数
                int num1 = Integer.parseInt(stack.pop());
                int result;
                //判断当前遍历到的符号的类型
                if ("+".equals(str)) {
                    result = num1 + num2;
                } else if ("-".equals(str)) {
                    result = num1 - num2;
                } else if ("*".equals(str)) {
                    result = num1 * num2;
                } else if ("/".equals(str)) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("Illegal Operator!");
                }
                //把运算结果压回栈中
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 生成中序表达式集合
     *
     * @param infix 中序表达式
     * @return 中序表达式字符串集合
     */
    private static List<String> toInfixExpression(String infix) {
        ArrayList<String> infixExpressionList = new ArrayList<>();
        //用于遍历表达式的指针
        int point = 0;
        do {
            char ch = infix.charAt(point);
            //当前字符为非数字时
            if (ch < 48 || ch > 57) {
                infixExpressionList.add(String.valueOf(ch));
                //后移指针
                point++;
            }
            //当前字符为数字
            else {
                String str = "";
                //循环把多个位的数字拼接到字符串中，比较ASCII码
                while (point < infix.length() && infix.charAt(point) >= 48 && infix.charAt(point) <= 57) {
                    // 把ASCII拼接称数字
                    str += String.valueOf(infix.charAt(point));
                    point++;
                }
                //加入数字
                infixExpressionList.add(str);
            }
        } while (point < infix.length());
        return infixExpressionList;
    }

    /**
     * 中缀表达式集合和转后缀表达式集合
     *
     * @param expressions 中缀表达式集合
     * @return 后缀表达式集合
     */
    private static List<String> toSuffixExpression(List<String> expressions) {
        //创建存放操作符的栈
        Stack<String> stack = new Stack<>();
        //第二个栈替换位集合，因为第二栈只有入栈的操作没有出栈的操作所以用集合替换方便操作
        List<String> suffix = new ArrayList<>();
        //遍历中缀表达式集合
        for (String expression : expressions) {
            //如果是操作数直接加入集合，一个或多个数字正则表达式
            if (expression.matches("\\d+")) {
                suffix.add(expression);
                //如果是左括号直接入栈
            } else if ("(".equals(expression)) {
                stack.push(expression);
                //如果是右括号把栈中的栈顶操作符弹出并加入集合中，直到遇到左括号
            } else if (")".equals(expression)) {
                while (!stack.isEmpty() && !"(".equals(stack.peek())) {
                    suffix.add(stack.pop());
                }
                //把栈顶的左括号弹出
                stack.pop();
                // 如果是操作符
            } else {
                //比较操作符的优先级，当前的操作符优先级小于或等于栈顶的操作符的优先级，把栈顶元素弹出并加入集合，直到
                // 当前操作符优先级比栈操作符顶优先级高或者栈空才能入栈
                while (!stack.isEmpty() && Operation.getPriority(expression) <= Operation.getPriority(stack.peek())) {
                    suffix.add(stack.pop());
                }
                //把当前操作符入栈
                stack.push(expression);
            }
        }
        //把栈的元素依次弹出并加入到集合中
        while (!stack.isEmpty()) {
            suffix.add(stack.pop());
        }
        //最后因为集合是先进先出的所以集合的表达式的顺序就是stack的逆序
        return suffix;
    }

    /**
     * 用于判断操作符的优先级
     */
    private static class Operation {
        private static final int ADD = 1;
        private static final int SUB = 1;
        private static final int MUL = 2;
        private static final int DIV = 2;

        static int getPriority(String operator) {
            int result = 0;
            switch (operator) {
                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
            }
            return result;
        }
    }
}

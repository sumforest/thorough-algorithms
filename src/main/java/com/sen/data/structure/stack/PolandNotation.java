package com.sen.data.structure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: Sen
 * @Date: 2020/1/13 01:35
 * @Description: 实现逆波兰表达式(后缀表达式)
 */
public class PolandNotation {

    public static void main(String[] args) {
        /*
        (3+4)*5-6
        (30+4)*5-6
        4*5-8+60+8/2 -> 逆波兰表达式 4 5 * 8 - 60 + 8 2 / +
        为了计算计算简便通过
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
       */
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpression(expression);
        System.out.println(infixExpressionList);
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
                int num2 = Integer.parseInt(stack.pop());
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
                }else {
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
     * @param infix 中序表达式
     * @return 中序表达式字符串集合
     */
    private static List<String> toInfixExpression(String infix) {
        ArrayList<String> infixExpressionList = new ArrayList<>();
        //用于遍历表达式的指针
        int point = 0;
        do {
            char ch = infix.charAt(point);
            //当前字符位非数字时
            if (ch< 48 || ch> 57) {
                infixExpressionList.add(String.valueOf(ch));
                //后移指针
                point++;
            }
            //当前字符位数字
            else {
                String str = "";
                //循环把多个位的数字加拼接到字符串中
                while (point < infix.length() && infix.charAt(point) >= 48 && infix.charAt(point) <= 57) {
                    str += String.valueOf(infix.charAt(point));
                    point++;
                }
                //加入数字
                infixExpressionList.add(str);
            }
        } while (point < infix.length());
        return infixExpressionList;
    }
}

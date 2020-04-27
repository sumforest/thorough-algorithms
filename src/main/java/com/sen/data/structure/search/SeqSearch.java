package com.sen.data.structure.search;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @Author: Sen
 * @Date: 2020/1/15 01:23
 * @Description: 线性查找
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {2, 7, 4, 11, 2, 8, -1, 11};
        int result = seqSearch(arr, 111);
        if (result != -1) {
            System.out.println("目标元素的下标：" + result);
        }else {
            System.out.println("目标元素不存在");
        }
    }

    /**
     * 这里找到一个即返回
     * @param arr 目标数组
     * @param target 目标值
     * @return 存在返回下标，不存在返回-1
     */
    private static int seqSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

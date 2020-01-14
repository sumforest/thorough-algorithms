package com.sen.data.structure.sort;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @Auther: Sen
 * @Date: 2020/1/13 22:55
 * @Description: 冒泡排序，时间复杂度n^2
 */
public class BubbleSort {

    public static void main(String[] args) throws InterruptedException {
        // int[] arr = {3, 9, -1, 10, -2};
        // int[] arr = {1,2,4,5,6,7};
        // 冒泡排序150000条数据花费时间：34S,时间复杂度O(n^2)
        testPerformance("冒泡排序", BubbleSort::bubbleSort);
    }

    public static void testPerformance(String sortName, Consumer<int[]> consumer) {
        int[] arr = new int[150000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000_000);
        }
        Instant start = Instant.now();
        consumer.accept(arr);
        Instant end = Instant.now();
        System.out.println(sortName + "150000条数据花费时间：" + Duration.between(start, end).get(ChronoUnit.SECONDS) + "S");
    }

    public static void testPerformance(String sortName, Consumer<int[]> consumer, int count,boolean isPrint) {
        int[] arr = new int[count];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(count * 10);
        }
        Instant start = Instant.now();
        consumer.accept(arr);
        Instant end = Instant.now();
        System.out.println(sortName + count + "条数据花费时间：" + Duration.between(start, end).get(ChronoUnit.SECONDS) + "S");
        if (isPrint) {
            System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * 冒泡排序
     *
     * @param arr int[]
     */
    private static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //如果进行一轮排序后没有任何数据交换则数据已经有序
            if (!flag) {
                break;
            } else {
                //重置标记
                flag = false;
            }
        }
    }
}

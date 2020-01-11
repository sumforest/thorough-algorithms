package com.sen.data.structure.sparse.array;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Auther: Sen
 * @Date: 2020/1/11 16:57
 * @Description: 创建稀疏数组并保存到硬盘，然后再从硬盘中恢复
 */
public class SparseArraySaveIntoDisk {

    public static void main(String[] args) {
        //创建一个11 * 11的棋盘，1代表黑棋，2代表蓝棋
        int[][] checkerBoard = new int[11][11];
        //给二维数组赋值
        checkerBoard[1][2] = 1;
        checkerBoard[2][3] = 2;
        checkerBoard[10][10] = 1;
        //遍历
        for (int[] ints : checkerBoard) {
            for (int j = 0; j < checkerBoard.length; j++) {
                System.out.printf("%s\t", ints[j]);
            }
            System.out.println();
        }

        //统计原始数组中不为零的总数
        int sum = 0;
        for (int[] ints : checkerBoard) {
            for (int chess : ints) {
                if (chess != 0) {
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //稀疏数组的第一行用与基于原始数组的行、列和有效数字的总数
        sparseArr[0][0] = checkerBoard.length;
        sparseArr[0][1] = checkerBoard.length;
        sparseArr[0][2] = sum;

        //用稀疏数组记录每个有效数字的下标和数值
        // 用于记录系数数组当前的行数,重第二行起
        int row = 1;
        for (int i = 0; i < checkerBoard.length; i++) {
            for (int j = 0; j < checkerBoard.length; j++) {
                //有效数字
                int available = checkerBoard[i][j];
                if (available != 0) {
                    sparseArr[row][0] = i;
                    sparseArr[row][1] = j;
                    sparseArr[row][2] = available;
                    row++;
                }
            }
        }
        System.out.println("-----------------遍历稀疏数组------------------");
        //遍历稀疏数组
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        //将创建文成的数组写入硬盘中保存
        try (
                FileOutputStream fos = new FileOutputStream("map.data");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(sparseArr);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //从硬盘中恢复稀疏数组
        int[][] recoverSparseArr = null;
        try (
                FileInputStream fis = new FileInputStream("map.data");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            recoverSparseArr = (int[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert recoverSparseArr != null;
        System.out.println("-----------------从稀疏数组中恢复原始数组------------------");
        //创建从稀疏数组的第一行获取数据创建原始数组
        int[][] recover = new int[recoverSparseArr[0][0]][recoverSparseArr[0][1]];
        //从稀疏数组中恢复数据
        for (int i = 1; i < recoverSparseArr.length; i++) {
            int re_row = recoverSparseArr[i][0];
            int re_col = recoverSparseArr[i][1];
            recover[re_row][re_col] = recoverSparseArr[i][2];
        }

        System.out.println("-----------------遍历恢复后的数组------------------");
        //遍历
        for (int[] ints : recover) {
            for (int j = 0; j < recover.length; j++) {
                System.out.printf("%s\t", ints[j]);
            }
            System.out.println();
        }
    }
}

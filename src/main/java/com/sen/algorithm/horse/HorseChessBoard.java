package com.sen.algorithm.horse;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Sen
 * @Date: 2020/2/3 18:29
 * @Description: 解决骑士周游问题
 */
public class HorseChessBoard {
    /**
     * 棋盘的列
     */
    private static int X;

    /**
     * 棋盘的行
     */
    private static int Y;

    /**
     * 记录是否被访问过
     */
    private static boolean[] isVisited;

    /**
     * 标记全部棋盘是否已经被访问过
     */
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法开始");
        X = 8;
        Y = 8;
        isVisited = new boolean[X * Y];
        int[][] chessBoard = new int[X][Y];
        //起始位置,从1开始计算
        int row = 1;
        int column = 1;
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共花费时间：" + (end - start) + " MS");
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 马踏棋盘（骑士周游算法的核心实现）
     *
     * @param chessBoard 棋盘
     * @param row        起始的行
     * @param column     起始的列
     * @param step       走了多少步，默认是1
     */
    private static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        //把棋盘上的值置为当前的步数
        chessBoard[row][column] = step;
        //把当前位置标记为已访问
        isVisited[row * X + column] = true;
        //获取马儿在当前位置可走的位置
        ArrayList<Point> points = next(new Point(column, row));
        //贪心算法优化
        sort(points);
        //遍历可走的位置
        while (!points.isEmpty()) {
            //取出并移除掉第一个位置
            Point removePoint = points.remove(0);
            //判断该点是否被访问过
            if (!isVisited[removePoint.y * X + removePoint.x]) {
                //递归
                traversalChessBoard(chessBoard, removePoint.y, removePoint.x, step + 1);
            }
        }
        //没有访问完所有的位置
        //满足step < X * Y条件的情况
        //1.没有访问完所有的位置
        //2.已经访问完所有的位置后回溯,所以需要增加访问完所有位置的标记finished
        if (step < X * Y && !finished) {
            //回溯
            chessBoard[row][column] = 0;
            isVisited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 贪心算法优化：
     * 对当前位置的下一个位置集合经行非递减排序
     * @param points
     */
    public static void sort(ArrayList<Point> points){
        points.sort(Comparator.comparingInt(p -> next(p).size()));
    }

    /**
     * 根据当前位置计算马儿还能走哪些位置
     *
     * @param cur 当前马儿的位置
     * @return 返回马儿还能走的位置的集合
     */
    private static ArrayList<Point> next(Point cur) {
        ArrayList<Point> points = new ArrayList<>();
        Point point = new Point();
        //可以走位置5
        if ((point.x = cur.x - 2) >= 0 && (point.y = cur.y - 1) >= 0) {
            points.add(new Point(point));
        }
        //可以走位置6
        if ((point.x = cur.x - 1) >= 0 && (point.y = cur.y - 2) >= 0) {
            points.add(new Point(point));
        }
        //可以走位置7
        if ((point.x = cur.x + 1) < X && (point.y = cur.y - 2) >= 0) {
            points.add(new Point(point));
        }
        //可以走位置0
        if ((point.x = cur.x + 2) < X && (point.y = cur.y - 1) >= 0) {
            points.add(new Point(point));
        }
        //可以走位置1
        if ((point.x = cur.x + 2) < X && (point.y = cur.y + 1) < Y) {
            points.add(new Point(point));
        }
        //可以走位置2
        if ((point.x = cur.x + 1) < X && (point.y = cur.y + 2) < Y) {
            points.add(new Point(point));
        }
        //可以走位置3
        if ((point.x = cur.x - 1) >= 0 && (point.y = cur.y + 2) < Y) {
            points.add(new Point(point));
        }
        //可以走位置4
        if ((point.x = cur.x - 2) >= 0 && (point.y = cur.y + 1) < Y) {
            points.add(new Point(point));
        }
        return points;
    }
}

package com.sen.data.structure.recursion;

/**
 * @Author: Sen
 * @Date: 2020/1/13 16:28
 * @Description: 利用递归回溯解决迷宫找路问题
 */
public class Maze {

    public static void main(String[] args) {
        //初始化迷宫地图，1 为墙或者,不能走
        int[][] map = new int[8][7];
        //给地图上下初始化墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //给地图的左右初始化墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置障碍
        map[3][1] = 1;
        map[3][2] = 1;
        // map[1][2] = 1;
        // map[2][2] = 1;
        //打印地图
        System.out.println("---------寻路前---------");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        //递归接解决迷宫寻路问题
        findWay(map, 1, 1);
        //打印地图
        System.out.println("---------寻路后---------");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    /**
     * 1.如果小球能找到map[6][5]位置说明寻路成功
     * 2.约定：当map[i][j] 为0表示该点没有走过，当为1表示墙；2表示已走过不能继续走；3表示点已经走过，但是走不通
     * 3.在走迷宫式，需要确定一个策略下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 地图
     * @param i   出发的行
     * @param j   出发的列
     * @return false寻路失败
     */
    private static boolean findWay(int[][] map, int i, int j) {
        //寻路成功
        if (map[6][5] == 2) {
            return true;
        } else {
            //该点可以走
            if (map[i][j] == 0) {
                //下假定该点可以走
                map[i][j] = 2;
                //先向下探索
                if (findWay(map, i + 1, j)) {
                    return true;
                }
                //再向右探索
                else if (findWay(map, i, j + 1)) {
                    return true;
                }
                //向上探索
                else if (findWay(map, i - 1, j)) {
                    return true;
                }
                //向右探索
                else if (findWay(map, i, j - 1)) {
                    return true;
                }
                //都走不通把当前位置标记为3
                map[i][j] = 3;
            }
            //当前位置的值为1或2或3都不能走
            return false;
        }
    }
}

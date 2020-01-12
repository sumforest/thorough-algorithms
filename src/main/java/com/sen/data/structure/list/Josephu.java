package com.sen.data.structure.list;

/**
 * @Auther: Sen
 * @Date: 2020/1/12 17:37
 * @Description: 解决约瑟夫/约瑟夫环问题（环形链表）
 */
public class Josephu {

    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.add(5);
        circleLinkedList.print();
        circleLinkedList.countBoy(1, 2, 5);
    }

    private static class CircleLinkedList{
        /**
         * 用于标记环形链表的第一个小孩
         */
        Boy first;

        /**
         * 更具参数创建环形链表
         *
         * @param nums 需要创建多少个节点的环形链表
         */
        public void add(int nums) {
            if (nums < 0) {
                System.err.println("输入参数有误，环形链表创建失败");
            }
            //用于标记最后一个节点的指针
            Boy cur = null;
            for (int i = 0; i < nums; i++) {
                Boy boy = new Boy(i + 1);
                //当创建的节点为第一个，直接赋值给first
                if (0 == i) {
                    first = boy;
                    boy.next = first;
                    //更新指针
                    cur = first;
                    continue;
                }
                //非首个节点创建
                cur.next = boy;
                //指向第一个节点
                boy.next = first;
                // 更新指针
                cur = boy;
            }
        }

        /**
         * 打印环形链表信息
         */
        public void print(){
            Boy cur = first;
            while (true) {
                System.out.printf("当前小孩的编号:[%d]\n", cur.no);
                if (cur.next == first) {
                    break;
                }
                cur = cur.next;
            }
        }

        /**
         * 解决约瑟夫问题,出列问题
         * @param k 第几个小孩开始数
         * @param m 每次数多少次
         * @param count 一共由多少个小孩
         */
        public void countBoy(int k, int m, int count) {
            if (k < 0 || m < 0 || m > count) {
                System.err.println("输入的参数有误,请重新输入!");
            }
            //创建辅助指针,让其指向最后一个节点
            Boy helper = first;
            while (helper.next != first) {
                helper = helper.next;
            }
            //往前移动k-1次first和helper指针
            for (int i = 0; i < k - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //开始报数
            while (helper != first) {
                //报数时移动m-1次helper和first指针
                for (int i = 0; i < m - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.printf("当前出队小孩编号:[%d]\n", first.no);
                //出队
                first = first.next;
                helper.next = first;
            }
            System.out.printf("最后留在队伍中的小孩编号:[%d]\n",first.no);
        }
    }

    private static class Boy{
        /**
         * 编号
         */
        int no;
        /**
         * 指向下一小孩
         */
        Boy next;

        public Boy(int no) {
            this.no = no;
        }
    }
}

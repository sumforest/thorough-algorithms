package com.sen.data.structure.list;

import java.util.Stack;
/**
 * @Author: Sen
 * @Date: 2020/1/11 22:50
 * @Description: 单项链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("第一个链表：");
        HeroNode heroNode1 = new HeroNode(5, "宋江", "及时雨");
        HeroNode heroNode3 = new HeroNode(10, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(18, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
       /*
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode4);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        */
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.print();
        /*
        System.out.println("------------------ 修改后的链表 ------------------");
        singleLinkedList.update(new HeroNode(2, "小卢", "玉麒麟~~~"));
        singleLinkedList.print();

        System.out.println("------------------ 删除后的链表 ------------------");
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.print();

        System.out.println("单链表中有效的节点的个数：" + countAvailableNode(singleLinkedList.head));

        System.out.printf("查找倒数第%d个节点为：[%s]", 2, findNodeAtLastIndex(singleLinkedList.head, 2));
        */
       /*
        System.out.println("------------------ 反转后的链表 ------------------");
        reverseLinkedList(singleLinkedList.head);
        singleLinkedList.print();
        */
        /*System.out.println("------------------ 逆序打印单链表 ------------------");
        reversePrint(singleLinkedList.head);*/
        System.out.println("第二个链表");
        HeroNode heroNode5 = new HeroNode(2, "鬼灭之刃", "灶门炭治郎");
        HeroNode heroNode6 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode heroNode7 = new HeroNode(6, "少年歌行", "萧老板");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(heroNode5);
        singleLinkedList2.addByOrder(heroNode6);
        singleLinkedList2.addByOrder(heroNode7);
        singleLinkedList2.print();
        System.out.println("------------------ 合并两个有序的单链表 ------------------");
        SingleLinkedList combine = combine(singleLinkedList.head, singleLinkedList2.head);
        combine.print();
    }

    /**
     * 求单链表中有效的节点的个数（排除头节点）
     *
     * @param head 链表头节点
     * @return
     */
    public static int countAvailableNode(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 新浪面试题：
     * 查找单链表的倒数第index个节点
     *
     * @param head  头节点
     * @param index 倒数的节点数
     * @return
     */
    public static HeroNode findNodeAtLastIndex(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //获取单链表的有效节点数
        int count = countAvailableNode(head);
        //验证index的有效性
        if (index < 1 || index > count) {
            return null;
        }

        //查找倒数的第index个节点转化为查找（有效节点数-index）位置的节点
        HeroNode temp = head.next;
        for (int i = 0; i < count - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 腾讯面试题：链表反转
     *
     * @param head 原链表头节点
     */
    public static void reverseLinkedList(HeroNode head) {
        //如果是一个空链表或者只有一个节点的链表直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //反转链表后的头节点
        HeroNode reverseHead = new HeroNode();
        HeroNode cur = head.next;
        //用于保存下一个节点指针
        HeroNode post = null;
        while (cur != null) {
            //先保存下一个节点的指针
            post = cur.next;
            //把当前节点的下一个节点指向reverseHead的下一个节点
            cur.next = reverseHead.next;
            //把reverseHead的下一个节点的指针指向当前节点
            reverseHead.next = cur;
            cur = post;
        }
        //把原链表的头节点head的下一个节点指向reverseHead的下一个节点next
        head.next = reverseHead.next;
    }

    /**
     * 百度面试题：逆序打印单链表
     * 实现方式1：链表反转后遍历（不推荐）破坏原链表结构
     * 实现方式2：利用栈先进后出的特点实现
     * @param head 链表根头点
     */
    public static void reversePrint(HeroNode head){
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个单链表，返回一个依然有序的单链表
     * @param head1 单链表1
     * @param head2 单链表2
     * @return 新链表
     */
    public static SingleLinkedList combine(HeroNode head1, HeroNode head2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode combineHead = singleLinkedList.head;
        combineHead.next = head1.next;
        HeroNode cur = head2.next;
        HeroNode next = null;
        while (cur != null) {
            next = cur.next;
            singleLinkedList.addByOrder(cur);
            cur = next;
        }
        return singleLinkedList;
    }

    private static class SingleLinkedList {

        HeroNode head = new HeroNode();

        public void add(HeroNode node) {
            HeroNode temp = head;
            //寻找最节点
            while (temp.next != null) {
                //移动指针到下一个节点
                temp = temp.next;
            }
            //在最后位置添加节点
            temp.next = node;
        }

        /**
         * 根据{@link HeroNode} 的属性no 顺序添加，当no已存在是不能添加，并且提示信息
         */
        public void addByOrder(HeroNode node) {
            //是否可以添加
            boolean flag = false;
            HeroNode temp = head;
            while (true) {
                //新加入的节点的位置是最后
                if (temp.next == null) {
                    break;
                }
                //找到了node的加入位置
                if (temp.next.no > node.no) {
                    break;
                }
                //存在相同的no，不能添加
                if (temp.next.no == node.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //判断是否可以添加
            if (flag) {
                System.err.printf("当前添加的Node的[%s]no已存在,不能添加\n", node.toString());
                return;
            }
            node.next = temp.next;
            temp.next = node;
        }

        /**
         * 更具{@link HeroNode}的no 属性来修改，no不能修改
         *
         * @param node
         */
        public void update(HeroNode node) {
            if (head.next == null) {
                System.err.println("当前链表为空，不能修改");
                return;
            }
            boolean flag = false;
            HeroNode temp = head.next;
            while (true) {
                //已经到达链表最后
                if (temp == null) {
                    break;
                }
                //找到了需要修改的节点
                if (temp.no == node.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                return;
            }
            System.err.printf("需要修改的节点[%s]没有找到，修改失败", node.toString());
        }

        /**
         * 根据{@link HeroNode}属性 no 来删除节点
         *
         * @param no
         */
        public void delete(int no) {
            if (head.next == null) {
                System.err.println("链表为空不能删除");
                return;
            }
            boolean flag = false;
            HeroNode temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                //找到了要被删除节点的上一个节点
                if (temp.next.no == no) {
                    flag = true;
                    break;
                }
                //移动指针，指向下一个节点
                temp = temp.next;
            }
            if (flag) {
                //删除链表，把要被删除链表的上一个节点的下一个节点指向被删除节点的下一个节点
                temp.next = temp.next.next;
                return;
            }
            System.err.println("没要找到对应的节点");

        }

        /**
         * 遍历打印所有节点信息
         */
        public void print() {
            if (head.next == null) {
                System.out.println("当前链表为空");
                return;
            }
            HeroNode temp = head.next;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    private static class HeroNode {

        int no;

        String name;

        String nickName;

        HeroNode next;

        public HeroNode() {
        }

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}

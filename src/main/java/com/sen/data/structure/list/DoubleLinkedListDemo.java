package com.sen.data.structure.list;

/**
 * @Author: Sen
 * @Date: 2020/1/12 15:06
 * @Description: 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(4, "卢俊义", "玉麒麟");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        /*
        linkedList.add(heroNode1);
        linkedList.add(heroNode2);
        linkedList.add(heroNode3);
        linkedList.add(heroNode4);
        linkedList.print();

        System.out.println("-------------------删除节点------------------");
        linkedList.remove(1);
        linkedList.print();

        System.out.println("-------------------更新节点------------------");
        linkedList.update(new HeroNode(2, "吴用", "没鬼用"));
        linkedList.print();
        */
        System.out.println("-------------------按顺序添加节点------------------");
        linkedList.addByOrder(heroNode1);
        linkedList.addByOrder(heroNode4);
        linkedList.addByOrder(heroNode3);
        linkedList.addByOrder(heroNode2);
        linkedList.print();
    }

    private static class DoubleLinkedList {
        /**
         * 头节点
         */
        HeroNode head = new HeroNode();

        public void print() {
            if (head.next == null) {
                System.err.println("当前节点为空！");
            }
            HeroNode cur = head.next;
            while (cur != null) {
                System.out.println(cur);
                cur = cur.next;
            }
        }

        /**
         * 在链表末尾添加元素
         *
         * @param node 添加的元素
         */
        public void add(HeroNode node) {
            HeroNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            //把当前节点的下一个节点执行新加入的节点
            cur.next = node;
            //把新加的节点的前一个指向当前节点
            node.pre = cur;
        }

        /**
         * 根据{@link HeroNode}no 属性顺序添加
         * 当Node的no重复时添加失败返回失败信息
         *
         * @param node
         */
        public void addByOrder(HeroNode node) {
            //标记是否由重复的no
            boolean flag = false;
            HeroNode cur = head;
            while (true) {
                //当前添加的节点位置时在链表末尾
                if (cur.next == null) {
                    break;
                }
                //找到了要加的位置
                if (cur.next.no > node.no) {
                    break;
                }
                //发现no重复
                if (cur.next.no == node.no) {
                    flag = true;
                    break;
                }
                cur = cur.next;
            }
            if (flag) {
                System.err.println("添加失败，发现重复的no！");
                return;
            }

            //添加节点的位置在链表末尾
            if (cur.next == null) {
                cur.next = node;
                node.pre = cur;
                return;
            }
            //当前节点的下一个节点的前一个指针指向指向新加节点
            cur.next.pre = node;
            //新加节点的后一个指针指向当前节点的下一节点
            node.next = cur.next;
            // 当前节点的下一个指针指向新节点
            cur.next = node;
            //新节点的前一个指针指向当前节点
            node.pre = cur;
        }

        /**
         * 根据{@link HeroNode}no 属性删除双向链表
         *
         * @param no
         */
        public void remove(int no) {
            if (head.next == null) {
                System.err.println("当前链表为空删除失败!");
                return;
            }
            //标记是否找到要删除的节点
            boolean flag = false;
            //因为是双向链表所以可以找到要删除的节点，删除自己
            HeroNode cur = head.next;
            while (true) {
                //没找找到要删除的节点，并且到达链表的末尾
                if (cur == null) {
                    break;
                }
                //找到了要删除的链表
                if (cur.no == no) {
                    flag = true;
                    break;
                }
                cur = cur.next;
            }
            if (flag) {
                //把当前节点的前一个节点的下一个节点的指针指向当前节点的下一个节点
                cur.pre.next = cur.next;
                //把当前节点的下一个节点的前一个节点的指针指向当前节点的上一个节点
                //删除节点为最后一个节点时没有此操作
                if (cur.next != null) {
                    cur.next.pre = cur.pre;
                }
                return;
            }
            System.err.println("没有找到要删除的节点！");
        }

        /**
         * 修改节点的属性
         *
         * @param node
         */
        public void update(HeroNode node) {
            if (head.next == null) {
                System.err.println("链表为空，不能修改");
                return;
            }
            boolean flag = false;
            HeroNode cur = head.next;
            while (true) {
                //没找到要修改的节点
                if (cur == null) {
                    break;
                }
                //找到要修改的节点
                if (cur.no == node.no) {
                    flag = true;
                    break;
                }
                cur = cur.next;
            }
            if (flag) {
                cur.name = node.name;
                cur.nickName = node.nickName;
                return;
            }
            System.err.println("没找到需要修改的链表！");
        }
    }

    private static class HeroNode {

        int no;

        String name;

        String nickName;

        /**
         * 指向当前列表的下一个节点
         */
        HeroNode next;

        /**
         * 指向当前链表的前一个节点
         */
        HeroNode pre;

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

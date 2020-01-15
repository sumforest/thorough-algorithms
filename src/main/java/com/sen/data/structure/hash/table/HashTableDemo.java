package com.sen.data.structure.hash.table;

import java.util.Scanner;

/**
 * @Auther: Sen
 * @Date: 2020/1/15 22:01
 * @Description: 实现Hash表
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("a:添加新员工");
            System.out.println("p:打印哈希表");
            System.out.println("f:查找员工");
            System.out.println("d:删除员工");
            System.out.println("e:退出程序");
            String command = scanner.next();
            switch (command) {
                case "a":
                    System.out.println("员工id：");
                    int id = scanner.nextInt();
                    System.out.println("员工姓名：");
                    String name = scanner.next();
                    hashTable.add(new Employee(id, name));
                    break;
                case "p":
                    hashTable.print();
                    break;
                case "f":
                    System.out.println("输入员工id：");
                    id = scanner.nextInt();
                    hashTable.find(id);
                    break;
                case "d":
                    System.out.println("输入员工id：");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "e":
                    scanner.close();
                    return;
            }
        }
    }

    /**
     * 员工信息节点
     */
    private static class Employee {
        int id;
        String name;
        Employee next;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * 员工信息链表
     */
    private static class EmployeeLinkedList {
        /**
         * 头节点，初始状态为null，表示没有任何节点
         */
        Employee head;

        /**
         * 说明：
         * 1.员工的id默认是自增的，直接添加到链表最后就可以达到有序
         *
         * @param node {@link Employee}
         */
        public void add(Employee node) {
            //如果链表为空直接添加到头节点
            if (head == null) {
                head = node;
                return;
            }
            //链表不为空添加到链表末尾
            Employee cur = head;
            //寻找最后的节点
            while (cur.next != null) {
                cur = cur.next;
            }
            //在最后的节点上添加新节点
            cur.next = node;
        }

        public void print(int no) {
            //说明该链表为空
            if (head == null) {
                System.err.println("List " + (no + 1) + " is Empty");
                return;
            }
            //遍历打印链表
            Employee cur = head;
            System.out.println("List " + (no + 1) + " info:");
            while (cur != null) {
                System.out.print(cur + "->");
                cur = cur.next;
            }
            System.out.println();
        }

        public Employee find(int id) {
            if (head == null) {
                return null;
            }
            Employee cur = head;
            while (cur != null) {
                if (cur.id == id) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }

        public void delete(int id) {
            if (head == null) {
                System.err.println("Current List is Empty");
                return;
            }
            Employee cur = head;
            //要删除的节点在第一个位置
            if (cur.id == id) {
                head = cur.next;
                return;
            }
            while (cur.next != null) {
                //没找到
                if (cur.next.id == id) {
                    cur.next = cur.next.next;
                    return;
                }
                cur = cur.next;
            }
            System.err.println("没找到");
        }
    }

    /**
     * 哈希表
     */
    private static class HashTable {
        /**
         * 用于保存的链表的数组
         */
        EmployeeLinkedList[] employeeLinkedListArr;

        int size;

        public HashTable(int size) {
            this.employeeLinkedListArr = new EmployeeLinkedList[size];
            //初始数组里面的链表
            for (int i = 0; i < size; i++) {
                employeeLinkedListArr[i] = new EmployeeLinkedList();
            }
            this.size = size;
        }

        /**
         * 调用链表添加节点
         *
         * @param node {@link Employee}
         */
        public void add(Employee node) {
            //通过散列函数确定调用哪个链表
            int index = hash(node.id);
            employeeLinkedListArr[index].add(node);
        }

        /**
         * 打印哈希表
         */
        private void print() {
            for (int i = 0; i < size; i++) {
                employeeLinkedListArr[i].print(i);
            }
        }

        private void find(int id) {
            int hashCode = hash(id);
            Employee employee = employeeLinkedListArr[hashCode].find(id);
            if (employee == null) {
                System.err.println("在哈希表中没有找到该员工");
            } else {
                System.out.printf("在第%d个链表找到员工：%s\n", hashCode + 1, employee.toString());
            }

        }

        private void delete(int id) {
            int hashCode = hash(id);
            employeeLinkedListArr[hashCode].delete(id);
        }

        /**
         * 生成散列表的函数
         *
         * @param id 用户id
         * @return 散列键
         */
        private int hash(int id) {
            return id % size;
        }
    }
}

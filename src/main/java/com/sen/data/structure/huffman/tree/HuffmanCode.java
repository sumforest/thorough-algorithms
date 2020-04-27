package com.sen.data.structure.huffman.tree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Sen
 * @Date: 2020/1/18 21:25
 * @Description: 赫夫曼编码表
 */
public class HuffmanCode {

    public static void main(String[] args) {
        // zipFile("f:/03.mp3", "f:/03.zip");
        // System.out.println("使用赫夫曼编码压缩文件完成");
        unZipFile("f:/03.zip", "f:/04.mp3");
        System.out.println("解压文件完毕");

        // unZipFile("f:/dst.zip", "f:/src2.bmp");
        // System.out.println("解压文件完毕");
        // zipFile("f:/src.bmp", "f:/dst.zip");
        // System.out.println("使用赫夫曼编码压缩文件完成");
        /*
        String message = "i like like like java do you like a java";
        byte[] messageBytes = message.getBytes();

        byte[] bytes = zipByHuffmanCode(messageBytes);
        byte[] decode = decode(huffmanCodes, bytes);
        System.out.println(new String(decode).length());
         */
        // System.out.println("压缩后的字节数组：" + Arrays.toString(bytes));
        // System.out.println("压缩后的字节数组长度：" + bytes.length);

        /*//将原数据字节数组转换成对应的节点
        List<Node> nodes = toNodeList(messageBytes);
        //将节点集合转换成赫夫曼树
        Node huffmanRoot = toHuffmanTree(nodes);
        preOrderPrint(huffmanRoot);
        //创建赫夫曼编码表
        createHuffmanCodes(huffmanRoot);
        System.out.println("生成的赫夫曼编码：" + huffmanCodes);
        //根据赫夫曼编码表压缩原数据字节数组
        byte[] bytesByHuffmanCode = zip(messageBytes, huffmanCodes);
        System.out.println(bytesByHuffmanCode.length);*/
    }

    /**
     * 解压文件
     *
     * @param unZipFile 需要解压的文件路径
     * @param dstFile   解压后的文件路径
     */
    private static void unZipFile(String unZipFile, String dstFile) {
        try (
                FileInputStream fis = new FileInputStream(unZipFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                FileOutputStream fos = new FileOutputStream(dstFile)
        ) {
            //读取压缩后的字节数组
            byte[] bytes = (byte[]) ois.readObject();
            //读取压缩文件中的赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解压问及那
            byte[] decode = decode(huffmanCodes, bytes);
            //写入文件到问及那
            fos.write(decode);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用赫夫曼编码表压缩文件
     *
     * @param srcFile 源文件地址
     * @param dstFile 输出文件地址
     */
    private static void zipFile(String srcFile, String dstFile) {
        try (
                //创建文件输入流
                FileInputStream fis = new FileInputStream(srcFile);
                //创建文件输出流
                FileOutputStream fos = new FileOutputStream(dstFile);
                //创建对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            byte[] bytes = new byte[fis.available()];
            //把输入流写入到byte数组
            fis.read(bytes);
            //使用赫夫曼编码压缩源文件字节数组
            byte[] zipBytes = zipByHuffmanCode(bytes);
            //使用对象输出流把压缩后的字节数组写入目标文件
            oos.writeObject(zipBytes);
            //把赫夫曼编码表写入目标文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据赫夫曼编码表解压压缩内容
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param encodeBytes  压缩后的字节数组
     * @return 解压后的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] encodeBytes) {
        StringBuilder builder = new StringBuilder();
        //获取压缩后的字节数组对应的二进制字符串
        for (int i = 0; i < encodeBytes.length; i++) {
            byte encode = encodeBytes[i];
            String code;
            if (i == encodeBytes.length - 1) {
                code = byteToBinaryString(false, encode);
            } else {
                //获取每个元素对应的二进制字符串
                code = byteToBinaryString(true, encode);
            }
            //拼接字符串
            builder.append(code);
        }
        //将赫夫曼编码表反转，由key->ASCII value->路径 反转成key->路径 value->ASCII
        HashMap<String, Byte> reverse = new HashMap<>();
        huffmanCodes.forEach((k, v) -> reverse.put(v, k));
        ArrayList<Byte> bytes = new ArrayList<>();
        //遍历压缩后的二进制字符串
        for (int i = 0; i < builder.length(); ) {
            //计数器
            int count = 0;
            boolean flag = true;
            //从i开始匹配
            while (flag && i+count < builder.length()) {
                String code = builder.substring(i, i + count);//92153210
                Byte data = reverse.get(code);
                if (data == null) {
                    //没有匹配到，移动count
                    count++;
                } else {
                    //匹配到对应编码
                    bytes.add(data);
                    flag = false;
                }
            }
            //更新i
            i += count;
        }
        byte[] target = new byte[bytes.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = bytes.get(i);
        }
        return target;
    }

    /**
     * 将利用赫夫曼编码产生的byte数字转化成二进制字符串
     *
     * @param flag 是否需要补全
     * @param code 需要转化成二进制字符串的数组
     * @return 二进制字符串
     */
    private static String byteToBinaryString(boolean flag, byte code) {
        int temp = code;
        if (flag) {
            //256对应的二进制 1 0000 0000 按位或 十进制1 0000 0001 --> 1 0000 0001
            //进行补全因为是正数时计算机不会对补码进行补全会导致下面截取字符串时下标越界
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);
        if (flag) {
            //截取后8个字符，因为是负数是计算机会进行补位，字符长度回超8
            string = string.substring(string.length() - 8);
        }
        return string;
    }

    /**
     * 利用赫夫曼编码压缩
     *
     * @param bytes 原数据的字节数组
     * @return 压缩后的字节数组
     */
    private static byte[] zipByHuffmanCode(byte[] bytes) {
        List<Node> nodes = toNodeList(bytes);
        Node huffmanTreeRoot = toHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = createHuffmanCodes(huffmanTreeRoot);
        return zip(bytes, huffmanCodes);
    }

    /**
     * 用赫夫曼编码表压缩
     * 赫夫曼编码表替换字符串数组之后：101010001011111111001000101111111100100010111111110010010100110111000111
     * 0000011011101000111100101000101111111100110001001010011011100
     * 说明：10101000（补码），计算机存储用的是补码-->10101000-1 = 10100111（反码）->反码除符号位各位取反
     * -->11011000（源码）-->转成十进制=-88
     * 即bytesByHuffmanCode[0]-->11011000
     * bytesByHuffmanCode对应的时机值是-88
     *
     * @param messageBytes 原字符串字节数组
     * @param huffmanCodes 根据原字节输出生成的赫夫曼编码表
     * @return 压缩后的字节数组
     */
    private static byte[] zip(byte[] messageBytes, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        //遍历原字符串字节数组，并用赫夫曼编码表替换对应的字符
        for (byte messageByte : messageBytes) {
            builder.append(huffmanCodes.get(messageByte));
        }
        //定义bytesByHuffmanCode[]的长度
        // 等价于len = (builder.length + 7) / 8
        int len;
        if (builder.length() % 8 == 0) {
            len = builder.length() / 8;
        } else {
            len = builder.length() / 8 + 1;
        }
        byte[] bytesByHuffmanCode = new byte[len];
        //记录byte数组的指针
        int index = 0;
        //把builder的内容切割成每8个字符一个byte-->8 Bit = 1 byte
        for (int i = 0; i < builder.length(); i += 8) {
            //当最后的builder不是8的整数倍的时候只取其实际长度，防止越界
            if (i + 8 > builder.length()) {
                bytesByHuffmanCode[index] = (byte) Integer.parseInt(builder.substring(i), 2);
            } else {
                bytesByHuffmanCode[index] = (byte) Integer.parseInt(builder.substring(i, i + 8), 2);
            }
            //移动指针
            index++;
        }
        return bytesByHuffmanCode;
    }

    /**
     * 存放生成的赫夫曼编码
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<>();

    public static Map<Byte, String> createHuffmanCodes(Node node) {
        if (node == null) {
            return null;
        }
        createHuffmanCodes(node.left, "0", new StringBuilder());
        createHuffmanCodes(node.right, "1", new StringBuilder());
        return huffmanCodes;
    }

    /**
     * 创建赫夫曼编码
     *
     * @param node          赫夫曼树根节点
     * @param code          向左遍历为0，向右遍历为1
     * @param stringBuilder 用于拼接字符串
     */
    private static void createHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        //只处理当前节点不为空的情况
        if (node != null) {
            StringBuilder builder = new StringBuilder(stringBuilder);
            //拼接当前路径
            builder.append(code);
            //处理叶子节点
            if (node.data == null) {
                //向左遍历左子树
                createHuffmanCodes(node.left, "0", builder);
                //向右遍历右子树
                createHuffmanCodes(node.right, "1", builder);
            } else {
                //到达了目标节点，结束遍历,把结果放进map
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    /**
     * 创建赫夫曼编码
     *
     * @param nodes 带权重和对应字符的节点
     * @return 赫夫曼树的根节点
     */
    private static Node toHuffmanTree(List<Node> nodes) {
        //当集合中剩余一个节点时，赫夫曼编码创建成功
        while (nodes.size() > 1) {
            //排序,升序排序
            Collections.sort(nodes);
            //取出并删除最后的节点
            Node left = nodes.remove(0);
            //取出次小的节点
            Node right = nodes.remove(0);
            //创建新节点
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            //把新创建的节点加入集合中
            nodes.add(parent);
        }
        //返回赫夫曼树的根节点
        return nodes.get(0);
    }

    private static void preOrderPrint(Node root) {
        if (root == null) {
            throw new RuntimeException("Current HuffmanTree is Empty");
        } else {
            root.preOrderPrint();
        }
    }

    /**
     * 生成统计每个字符出现的node节点
     *
     * @param Bytes 原信息对应的Byte数组
     * @return 节点集合
     */
    private static List<Node> toNodeList(byte[] Bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        //统计出现的每个字符的次数
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte data : Bytes) {
            //map中不存在该字符，说明是第一次添加
            //map中已存在该字符，再原来的基础上+1
            //since JDK 1.8
            map.merge(data, 1, Integer::sum);
        }
        map.forEach((k, v) -> nodes.add(new Node(k, v)));
        return nodes;
    }


    private static class Node implements Comparable<Node> {

        /**
         * 该节点代表的字符对应的ASCII码
         */
        Byte data;

        /**
         * 该节点的权,记录data出现的次数
         */
        int weight;

        Node left;

        Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public void preOrderPrint() {
            System.out.println(this);
            if (this.left != null) {
                //打印左子树
                this.left.preOrderPrint();
            }
            if (this.right != null) {
                //打印右子树
                this.right.preOrderPrint();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}

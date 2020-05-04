package com.sen.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author: Sen
 * @Date: 2020/1/28 23:37
 * @Description: 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> broadcast1 = new HashSet<>();
        broadcast1.add("北京");
        broadcast1.add("上海");
        broadcast1.add("天津");

        HashSet<String> broadcast2 = new HashSet<>();
        broadcast2.add("广州");
        broadcast2.add("北京");
        broadcast2.add("深圳");

        HashSet<String> broadcast3 = new HashSet<>();
        broadcast3.add("成都");
        broadcast3.add("上海");
        broadcast3.add("杭州");

        HashSet<String> broadcast4 = new HashSet<>();
        broadcast4.add("上海");
        broadcast4.add("天津");
        HashSet<String> broadcast5 = new HashSet<>();
        broadcast5.add("杭州");
        broadcast5.add("大连");

        broadcasts.put("K1", broadcast1);
        broadcasts.put("K2", broadcast2);
        broadcasts.put("K3", broadcast3);
        broadcasts.put("K4", broadcast4);
        broadcasts.put("K5", broadcast5);

        //保存未覆盖地区的集合
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //保存最大覆盖城市的电台的key
        String maxKey = null;
        //临时保存电台的所覆盖的地区
        HashSet<String> temp = new HashSet<>();
        //保存最优解的集合
        ArrayList<String> result = new ArrayList<>();
        //当未覆盖地区的集合大于0
        while (allAreas.size() > 0) {
            //遍历电台
            for (String key : broadcasts.keySet()) {
                temp.addAll(broadcasts.get(key));
                //求电台的覆盖范围和需要覆盖的范围的交集
                temp.retainAll(allAreas);
                // 获取电台的最大交集--覆盖最多未覆盖地区的电台
                HashSet<String> maxBroadcast;
                if (maxKey != null) {
                    maxBroadcast = broadcasts.get(maxKey);
                    maxBroadcast.retainAll(allAreas);
                }else {
                    maxBroadcast = new HashSet<>();
                }
                // 当前电台覆盖地区比原先假定的电台覆盖地区多，当前电台作为最多覆盖地区电台
                if (maxKey == null || temp.size() > maxBroadcast.size()) {
                    maxKey = key;
                }
                // 清空临时集合
                temp.clear();
            }
                //把电台保存到结果中
                result.add(maxKey);
                //把已覆盖的地区从未覆盖的地区移除
                HashSet<String> removeSet = broadcasts.get(maxKey);
                allAreas.removeAll(removeSet);
                //把maxKey置空用于下一次使用
        }

        //输出结果
        System.out.println(result);
    }
}

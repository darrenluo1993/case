package pers.darren.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

public class CalandriaSort {

    public static void main(String[] args) {
        List<Calandria> list = new ArrayList<>();
        list.add(new Calandria("埋管B-1-3-1"));
        list.add(new Calandria("埋管B-1-6-1"));
        list.add(new Calandria("埋管B-1-7"));
        list.add(new Calandria("埋管B-1-4-1"));
        list.add(new Calandria("埋管B-1-5"));
        list.add(new Calandria("埋管B-1-4-2"));
        list.add(new Calandria("埋管B-1-10-2"));
        list.add(new Calandria("埋管B-1-2-1"));
        list.add(new Calandria("埋管B-1-6-2"));
        list.add(new Calandria("埋管B-1-1-1"));

        List<String> modelLvlList = list.stream().peek(calandria -> {
            String model = calandria.getModel();
            // 截取出排管型号的层级字符串
            calandria.setModelLvl(model.substring(model.indexOf("-") + 1));
        }).map(Calandria::getModelLvl).collect(Collectors.toList());
        System.out.println("排管型号层级列表：");
        modelLvlList.forEach(System.out::println);

        Optional<Integer> maxLvl = modelLvlList.stream().map(modelLvl -> modelLvl.split("-").length).max(Integer::compareTo);
        System.out.println("排管的最大层级：" + maxLvl.get());

        // 计算出型号层级每个层级的最大长度
        int[] lvlMaxLengthArr = new int[maxLvl.get()];
        for (int i = 0; i < maxLvl.get(); i++) {
            final int fi = i;
            Optional<Integer> lvlMaxLength = modelLvlList.stream().map(modelLvl -> {
                String[] lvlArr = modelLvl.split("-");
                if (lvlArr.length > fi) {
                    return lvlArr[fi].length();
                }
                return 0;
            }).max(Integer::compareTo);
            lvlMaxLengthArr[fi] = lvlMaxLength.get();
        }
        System.out.println("型号层级每个层级的最大长度：");
        Arrays.stream(lvlMaxLengthArr).forEach(System.out::println);

        modelLvlList = list.stream().peek(calandria -> {
            String[] lvlArr = calandria.getModelLvl().split("-");
            for (int i = 0; i < lvlArr.length; i++) {
                String lvl = lvlArr[i];
                // 当前层级长度
                int lvlLength = lvl.length();
                // 当前层级最大长度
                int lvlMaxLength = lvlMaxLengthArr[i];
                if (lvlLength < lvlMaxLength) {
                    int diff = lvlMaxLength - lvlLength;
                    for (int j = 0; j < diff; j++) {
                        lvl = "0" + lvl;
                    }
                }
                lvlArr[i] = lvl;
            }
            calandria.setModelLvl(String.join("-", lvlArr));
        }).map(Calandria::getModelLvl).collect(Collectors.toList());
        System.out.println("补0后的型号层级：");
        modelLvlList.forEach(System.out::println);

        System.out.println("根据补0后的型号层级排序");
        System.out.println("排序之前：");
        list.forEach(System.out::println);
        System.out.println("排序之后：");
        list = list.stream().sorted(Comparator.comparing(Calandria::getModelLvl)).collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}

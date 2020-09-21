package pers.darren.hutool.pinyin;

import static cn.hutool.core.util.StrUtil.EMPTY;
import static cn.hutool.extra.pinyin.PinyinUtil.getPinyin;
import static net.sourceforge.pinyin4j.PinyinHelper.toHanYuPinyinString;
import static net.sourceforge.pinyin4j.format.HanyuPinyinCaseType.LOWERCASE;
import static net.sourceforge.pinyin4j.format.HanyuPinyinToneType.WITH_TONE_NUMBER;
import static net.sourceforge.pinyin4j.format.HanyuPinyinVCharType.WITH_U_UNICODE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinTest {

    private static final HanyuPinyinOutputFormat format;

    static {
        format = new HanyuPinyinOutputFormat();
        // 设置输出大小写格式/当前设置是小写格式
        format.setCaseType(LOWERCASE);
        // 设置输出拼音声母格式/当前设置是输出声母声码
        format.setToneType(WITH_TONE_NUMBER);
        // 设置拼音中“ü”的输出格式/当前设置是输出unicode编码ü
        format.setVCharType(WITH_U_UNICODE);
    }

    public static void main(final String[] args) throws Exception {
        System.out.println(getPinyin("Eclipse和IDEA哪个好用一些？~!@#$%^&*()_+{}|:\"<>?", EMPTY));
        System.out.println(getPinyin("绿水青山璧山四面山村低压线路路径图", EMPTY));
        System.out.println(toHanYuPinyinString("绿水青山璧山四面山村低压线路路径图", format, EMPTY, true));
        System.out.println(toHanYuPinyinString("JAVA中文转换中文拼音_qq_40083897的博客", format, EMPTY, true));

        final List<String> pinYinList = new ArrayList<>(6);
        pinYinList.add("abc");
        pinYinList.add("efg");
        pinYinList.add("lmn");
        pinYinList.add("bcd");
        pinYinList.add("hij");
        pinYinList.add("abd");
        Collections.sort(pinYinList, String::compareTo);
        pinYinList.stream().forEach(System.out::println);

        final List<TreeNode> treeNodeList = new ArrayList<>(6);
        treeNodeList.add(new TreeNode("张三2"));
        treeNodeList.add(new TreeNode("李四1"));
        treeNodeList.add(new TreeNode("王五3"));
        treeNodeList.add(new TreeNode("1赵六"));
        treeNodeList.add(new TreeNode("3吴七"));
        treeNodeList.add(new TreeNode("2刘八"));
        Collections.sort(treeNodeList, (o1, o2) -> {
            try {
                final String name1 = toHanYuPinyinString(o1.getName(), format, EMPTY, true);
                final String name2 = toHanYuPinyinString(o2.getName(), format, EMPTY, true);
                return name1.compareTo(name2);
            } catch (final BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return 0;
        });
        treeNodeList.stream().forEach(treeNode -> {
            System.out.println(treeNode.getName());
        });
    }

    private static final class TreeNode {

        public TreeNode(final String name) {
            this.name = name;
        }

        private String name;

        public String getName() {
            return this.name;
        }
    }
}
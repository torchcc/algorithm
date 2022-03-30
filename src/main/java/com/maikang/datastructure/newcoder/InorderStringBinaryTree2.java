package com.maikang.datastructure.newcoder;

import java.util.Scanner;

/*
 *a{b{d,e{g,h{,i}}},c{f}}
 *
 *
 * 作者：牛客741169438号
链接：https://www.nowcoder.com/discuss/732323?type=all&order=recall&pos=&page=1&ncTraceId=&channel=-1&source_id=search_all_nctrack&gio_id=A53C50C4C79B3D0EB8820339FBE79E0F-1644853546147
来源：牛客网

根据给定的二叉树结构描述字符串，输出该二叉树按照中序遍历结果字符串。中序遍历顺序为:左子树，根结点，右子树。

输入描述
由大小写字母、左右大括号、逗号组成的字符串:
1、字母代表一个节点值，左右括号内包含该节点的子节点。
2、左右子节点使用逗号分隔，逗号前为空则表示左子节点为空,没有逗号则表示右子节点
为空。
3、二叉树节点数最大不超过100。
注:输入字符串格式是正确的，无需考虑格式错误的情况。

输出描述
输出一个字符串，为二叉树中序遍历各节点值的拼接结果。

示例：
输入：a{b{d, e{g,h{,I}}},c{f}}
输出：dbgehiafc
 在线求助
 * c{f}
 * */


/*
*  https://www.codeleading.com/article/22176226272/
*
* 根据题目描述，可以将解决问题的过程分为两个步骤，首先解析字符串转存为树，然后就是实现中序遍历。

那么首先开始解析字符串。
* */


/**
 * 思路：这里以数组存储树结构，左子节点为2*i+1，右子节点为2*i+2，这里不做详细解析；
 *   这里重要的是注意三个字符“{”，“,”和“}”，当看见左花括号，那么下一个字母必为上一个
 *   字母的左子节点，而看见逗号，那么下一个字母必是右子节点，而遍历到右花括号时，应当
 *   将下标回退到父节点。
 */
public class InorderStringBinaryTree2 {
    private int p = 0;
    public static void main(String[] args) {

        String input = "a{b{d,e{g,h{,I}}},c{f}}";
        InorderStringBinaryTree2 m = new InorderStringBinaryTree2();
        char[] chars = m.str2tree(input);
        StringBuilder sb = new StringBuilder();
        m.inorder(chars, 0, sb);
        System.out.println(sb);


    }

    public char[] str2tree(String s) {
        char[] tree = new char[100];
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                tree[idx] = c;
            } else if (c == '{') {
                idx = 2 * idx + 1;
            } else if (c == ',') {
                idx++;
            } else if (c == '}') {
                idx = idx / 2 - 1;
            }
        }
        return tree;
    }

    private void inorder(char[] tree, int i, StringBuilder sb) {
        if (i >= tree.length) return;
        inorder(tree, 2 * i + 1, sb);
        sb.append(tree[i]);
        inorder(tree, 2 * i + 2, sb);

    }



}

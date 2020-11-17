package xyz.hcworld.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 32. 最长有效括号
 * @ClassName: LongestValidBracket
 * @Author: 张红尘
 * @Date: 2020/7/4 23:23
 * @Version： 1.0
 */
public class LongestValidBracket {

    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
    }

    private static int calc(char[] chars, int i, int flag, int end, char cTem) {
        int max = 0, sum = 0, currLen = 0, validLen = 0;
        for (; i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen++;
            if (sum < 0) {
                max = max > validLen ? max : validLen;
                currLen = validLen = sum = 0;
            } else if (sum == 0) {
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }


    public static void main(String[] args) {
        List<String> slist = new ArrayList<>();
        slist.add(")((()))((())");
        slist.add("())");
        slist.add("(()");
        slist.add("");
        slist.add("((())");

        for (String s : slist) {
            System.out.println(longestValidParentheses(s));
        }


    }

}

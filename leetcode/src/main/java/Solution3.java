import java.util.*;

/**
 * 3.无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution3 {

    public static void main(String[] args) {
        String test1 = "dvdf";
        System.out.println(lengthOfLongestSubstring(test1));
        System.out.println(answer(test1));

        String test2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring(test2));
        System.out.println(answer(test2));

        String test3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(test3));
        System.out.println(answer(test3));

        String test4 = " ";
        System.out.println(lengthOfLongestSubstring(test4));
        System.out.println(answer(test4));

        String test5 = "abbbbbbcc";
        System.out.println(lengthOfLongestSubstring(test5));
        System.out.println(answer(test5));

    }

    public static int lengthOfLongestSubstring(String s) {
        char[] charArr = s.toCharArray();
        Set<Character> resultSet = new HashSet<>();
        int maxLength = 0;
        for (int startIndex = 0; startIndex < charArr.length; startIndex++) {
            int tmpLength = 0;
            for (int endIndex = startIndex; endIndex < charArr.length; endIndex++) {
                if (!resultSet.contains(charArr[endIndex])) {
                    resultSet.add(charArr[endIndex]);
                    tmpLength++;
                } else {
                    resultSet.clear();
                    break;
                }
            }
            maxLength = Math.max(maxLength, tmpLength);
        }
        return maxLength;
    }

    public static int answer(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

package org.example;

import java.util.Arrays;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }

        int[][] inserted = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; i++) {//把所有区间复制并且新加进来一个区间
            inserted[i] = intervals[i];
        }
        inserted[intervals.length] = newInterval;
        Arrays.sort(inserted, (v1, v2) -> v1[0] - v2[0]);//将所有区间按照第一个元素大小排序
        return mergeIntervals(inserted);//合并所有区间
    }

    public int[][] mergeIntervals(int[][] intervals) {//区间intervals是按照第一个元素大小有序的
        int idx = -1;
        int i = 0;
        int[][] ans = new int[intervals.length][2];
        while (i < intervals.length) {
            if(idx == -1 || ans[idx][1] < intervals[i][0]) {//如果是第一个区间直接加入到ans里，如果ans里最后一个区间的尾，和即将加入的区间的头有重叠
                ans[idx + 1] = intervals[i];                //则ans里最后一个区间的尾改成即将加入的区间的尾巴
                idx++;
                i++;
            } else if (ans[idx][1] >= intervals[i][0]) {
                ans[idx][1] = Math.max(ans[idx][1], intervals[i][1]);//如果即将加入的区间和ans里最后的区间没有重叠，就正常加入区间
                i++;
            }
        }
        return Arrays.copyOf(ans, idx + 1);
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();

        int[][] intervals = {{1,2},{3,5}, {6,7},{8,10},{12,16}};
        int[] newInterval = {4, 8};
        int[][] inserted = mergeIntervals.insert(intervals, newInterval);
    }
}

package xyz.hcworld.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * @ClassName: ArrayStartAndEndElement
 * @Author: 张红尘
 * @Date: 2020/10/21 21:40
 * @Version： 1.0
 */
public class ArrayStartAndEndElement {
    /**
     * 使用二分查找
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] sum = {-1, -1};
        if (nums.length==0){
            return sum;
        }else if (nums.length==1){
            if(nums[0]==target){
                sum[0]=0;
                sum[1]=0;
            }
            return sum;
        }
        int length = nums.length;
        int frequency = (1 == length % 2) ? (length >> 1) + 1 : length >> 1;
        length -= 1;
        for (int i = 0; i <= frequency; i++) {
            if (target == nums[i]) {
                if (sum[0] == -1) {
                    sum[0] = i;
                } else if (sum[1] == -1 || sum[1] <= i) {
                    sum[1] = i;
                }
            }
            if (target == nums[length - i]) {
                if (sum[1] == -1) {
                    sum[1] = length - i;
                } else if (sum[0] == -1 || sum[0] > length - i) {
                    sum[0] = length - i;
                }
                if (i==frequency&&sum[0]>frequency){
                    sum[1]=frequency;
                }
            }
        }
        if (sum[0] != -1 && sum[1] == -1) {
            sum[1] = sum[0];
        } else if (sum[1] != -1 && sum[0] == -1) {
            sum[0] = sum[1];
        }
        return sum;
    }


}

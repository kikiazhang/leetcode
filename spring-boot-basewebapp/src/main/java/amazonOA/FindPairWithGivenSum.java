package amazonOA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindPairWithGivenSum {
	/**
	 * Given a list of positive integers nums and an int target, return indices of
	 * the two numbers such that they add up to a target - 30.
	 * 
	 * Conditions:
	 * 
	 * You will pick exactly 2 numbers. You cannot pick the same element twice. If
	 * you have muliple pairs, select the pair with the largest number. Example 1:
	 * 
	 * Input: nums = [1, 10, 25, 35, 60], target = 90 Output: [2, 3] Explanation:
	 * nums[2] + nums[3] = 25 + 35 = 60 = 90 - 30 Example 2:
	 * 
	 * Input: nums = [20, 50, 40, 25, 30, 10], target = 90 Output: [1, 5]
	 * Explanation: nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30 nums[1] + nums[5] =
	 * 50 + 10 = 60 = 90 - 30 You should return the pair with the largest number.
	 * 
	 */
	public static void main(String[] args) {
		int[] nums1 = { 1, 10, 25, 35, 60 };
		int target1 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums1, target1 - 30)));
		int[] nums2 = { 20, 50, 40, 25, 30, 10 };
		int target2 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums2, target2 - 30)));
		int[] nums3 = { 50, 20, 10, 40, 25, 30 };
		int target3 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums3, target3 - 30)));
		int[] nums4 = { 1, 2 };
		int target4 = 90;
		System.out.println(Arrays.toString(Find2Sum(nums4, target4 - 30)));
	}

	// corner case: [0,0] -> 30
	private static int[] Find2Sum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = Integer.MIN_VALUE;// max res index value
		int[] res = new int[] { -1, -1 };
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				if (nums[i] > max || nums[map.get(nums[i])] > max) {
					res[0] = map.get(nums[i]);
					res[1] = i;
					max = Math.max(nums[i], nums[map.get(nums[i])]);
				}
			}
			map.put(target - nums[i], i);
		}
		return res;
	}
}

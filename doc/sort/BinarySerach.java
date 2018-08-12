package com.wanghongen.demo.sort;

/**
 * 1 二分查找
 *
 * 二分查找是一个基础的算法，也是面试中常考的一个知识点。 二分查找就是将查找的键和子数组的中间键作比较， 如果被查找的键小于中间键，就在左子数组继续查找；
 * 如果大于中间键，就在右子数组中查找，否则中间键就是要找的元素。 Created by wang on 2018/7/10
 *
 * https://www.cnblogs.com/luoxn28/p/5767571.html
 */
public class BinarySerach {

  public static int find(int[] array, int key) {
    int left = 0;
    int right = array.length - 1;

    //这里必须是<=
    while (left <= right) {
      int mid = (left + right) / 2;
      if (array[mid] == key) {
        return mid;
      } else if (array[mid] < key) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] arr = {1,3,5,8,6,2,4,7,9};
    System.out.println(find(arr,1));
  }

}

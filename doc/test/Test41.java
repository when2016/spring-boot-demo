package com.code.test.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghongen
 * @date 7/31/18 7:30 PM
 */
public class Test41 {


  public static List<Integer> findNumbersWithSum(int[] data, int sum) {
    List<Integer> result = new ArrayList<>(2);

    if (data == null || data.length < 2) {
      return result;
    }
    int ahead = data.length - 1;
    int behind = 0;
    long curSum;
    while (behind < ahead) {
      curSum = data[behind] + data[ahead];

      if (curSum == sum) {
//        result.add(data[behind]);
//        result.add(data[ahead]);
//        break;
        System.out.println(data[behind] + "+" + data[ahead] + "=" + sum);
        behind++;
        ahead--;
        //break;

      } else if (curSum < sum) {
        behind++;
      } else {
        ahead--;
      }

    }

    return result;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    findNumbersWithSum(arr, 100);
  }

}

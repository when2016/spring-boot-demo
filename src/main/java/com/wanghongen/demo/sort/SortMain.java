package com.wanghongen.demo.sort;

import java.util.Arrays;

/**
 * Created by wang on 2018/7/4
 */
public class SortMain {

  // 排序原始数据
  private static final int[] NUMBERS =
      {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
          25, 53, 51};

  //插入排序---直接插入排序
  public static void insertSort(int[] array) {
    System.out.println(Arrays.toString(array) + " insertSort");
    for (int i = 1; i < array.length; i++) {
      int temp = array[i];
      int j = i - 1;
      for (; j >= 0 && array[j] > temp; j--) {
        //将大于temp的值整体后移一个单位
        array[j + 1] = array[j];
      }
      array[j + 1] = temp;
      System.out.println(Arrays.toString(array) + " insertSort");
    }
    System.out.println(Arrays.toString(array) + " insertSort");
  }

  public static void shellSort(int[] array) {
    System.out.println(Arrays.toString(array) + " shellSort");
    int i;
    int j;
    int temp;
    int gap = 1;
    int len = array.length;
    while (gap < len / 3) {
      gap = gap * 3 + 1;
    }
    for (; gap > 0; gap /= 3) {
      for (i = gap; i < len; i++) {
        temp = array[i];
        for (j = i - gap; j >= 0 && array[j] > temp; j -= gap) {
          array[j + gap] = array[j];
        }
        array[j + gap] = temp;
        System.out.println(Arrays.toString(array) + " shellSort");
      }
    }

  }

  public static void selectSort(int[] array) {
    int position = 0;
    for (int i = 0; i < array.length; i++) {
      int temp = array[i];
      position = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < temp) {
          temp = array[j];
          position = j;
        }
      }
      array[position] = array[i];
      array[i] = temp;
      System.out.println(Arrays.toString(array) + " selectSort");
    }
    System.out.println(Arrays.toString(array) + " selectSort");
  }


  public static void heapSort(int[] array) {
    /*
     *  第一步：将数组堆化
     *  beginIndex = 第一个非叶子节点。
     *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
     *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
     */
    int len = array.length - 1;
    int beginIndex = (len - 1) >> 1;
    for (int i = beginIndex; i >= 0; i--) {
      maxHeapify(i, len, array);
    }
    /*
     * 第二步：对堆化数据排序
     * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
     * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。
     * 直至未排序的堆长度为 0。
     */
    for (int i = len; i > 0; i--) {
      swap(0, i, array);
      maxHeapify(0, i - 1, array);
    }
    System.out.println(Arrays.toString(array) + " heapSort");
  }

  private static void swap(int i, int j, int[] arr) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * 调整索引为 index 处的数据，使其符合堆的特性。
   *
   * @param index 需要堆化处理的数据的索引
   * @param len 未排序的堆（数组）的长度
   */
  private static void maxHeapify(int index, int len, int[] arr) {
    int li = (index << 1) + 1; // 左子节点索引
    int ri = li + 1;           // 右子节点索引
    int cMax = li;             // 子节点值最大索引，默认左子节点。
    if (li > len) {
      return;       // 左子节点索引超出计算范围，直接返回。
    }
    if (ri <= len && arr[ri] > arr[li]) // 先判断左右子节点，哪个较大。
    {
      cMax = ri;
    }
    if (arr[cMax] > arr[index]) {
      swap(cMax, index, arr);      // 如果父节点被子节点调换，
      maxHeapify(cMax, len, arr);  // 则需要继续判断换下后的父节点是否符合堆的特性。
    }
  }

  public static void bubbleSort(int[] array) {

    for (int i = 0; i < array.length - 1; i++) {
      for (int j = 0; j < array.length - 1 - i; j++) {
        if (array[j] < array[j + 1]) {
          int temp = array[j + 1];
          array[j + 1] = array[j];
          array[j] = temp;
        }
//        if (array[j] > array[j + 1]) {
//          int temp = array[j];
//          array[j] = array[j + 1];
//          array[j + 1] = temp;
//        }

      }
    }

    System.out.println(Arrays.toString(array) + " bubbleSort");
  }

  private static void quickSort(int[] array) {
    _quickSort(array, 0, array.length - 1);
    System.out.println(Arrays.toString(array) + " quickSort");
  }

  private static void _quickSort(int[] list, int low, int high) {
    if (low < high) {
      int middle = getMiddle(list, low, high);  //将list数组进行一分为二
      _quickSort(list, low, middle - 1);      //对低字表进行递归排序
      _quickSort(list, middle + 1, high);      //对高字表进行递归排序
    }

  }

  private static int getMiddle(int[] list, int low, int high) {
    int tmp = list[low];    //数组的第一个作为中轴
    while (low < high) {
      while (low < high && list[high] >= tmp) {
        high--;
      }

      list[low] = list[high];   //比中轴小的记录移到低端
      while (low < high && list[low] <= tmp) {
        low++;
      }

      list[high] = list[low];   //比中轴大的记录移到高端
    }
    list[low] = tmp;              //中轴记录到尾
    return low;
  }

  //https://blog.csdn.net/qy1387/article/details/7752973
  //https://www.cnblogs.com/onepixel/articles/7674659.html
  public static void main(String[] args) {
    //insertSort(NUMBERS);
    //shellSort(NUMBERS);
    //selectSort(NUMBERS);
//    bubbleSort(NUMBERS);

    quickSort(NUMBERS);
  }


}

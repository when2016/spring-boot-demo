package com.code.test.anno;

import com.code.test.anno.FruitColor.Color;

/**
 * https://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 *
 * @author wanghongen
 * @date 8/24/18 6:14 PM
 */
public class Apple {

  @FruitName("Apple")
  private String appleName;

  @FruitColor(fruitColor = Color.RED)
  private String appleColor;

  public String getAppleName() {
    return appleName;
  }

  public void setAppleName(String appleName) {
    this.appleName = appleName;
  }

  public String getAppleColor() {
    return appleColor;
  }

  public void setAppleColor(String appleColor) {
    this.appleColor = appleColor;
  }


  public void displayName() {
    System.out.println("水果的名称是：苹果");
  }
}

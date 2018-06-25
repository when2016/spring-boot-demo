package com.wanghongen.demo;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by wang on 2018/6/25
 */
public class Test {

  public static void main(String[] args) {
    Instant ins = Instant.now();
    System.out.println(ins);
    OffsetDateTime time = ins.atOffset(ZoneOffset.ofHours(8));
    System.out.println(time);

    System.out.println(ZonedDateTime.now().toInstant());

    System.out.println(ZonedDateTime.now(ZoneId.systemDefault()));
    System.out.println(ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
    System.out.println("time===" + ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
  }

}

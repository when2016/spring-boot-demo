package com.wanghongen.demo.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SkuDTO {
    private Long skuId;
    private String skuCode;
    private Integer skuPrice;

    private Long itemId;
    private String itemName;

}

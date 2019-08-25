package com.wanghongen.demo.item;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class ItemConverterTest {
    public static void main(String[] args) {
        Item item = new Item(1L, "iPhone X");
        Sku sku = new Sku(2L, "phone12345", 1000000);
        SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);

        System.out.println(skuDTO);



//        assertNotNull(skuDTO);
//        assertEquals(skuDTO.getSkuId(),sku.getId());
//        assertEquals(skuDTO.getSkuCode(),sku.getCode());
//        assertEquals(skuDTO.getSkuPrice(),sku.getPrice());
//        assertEquals(skuDTO.getItemId(),item.getId());
//        assertEquals(skuDTO.getItemName(),item.getTitle());
    }
}

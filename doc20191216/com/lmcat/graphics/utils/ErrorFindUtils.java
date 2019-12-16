package com.lmcat.graphics.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author nayuan
 * @date 2018/6/22 11:05
 */
public class ErrorFindUtils {

    private static int size = 200;

    public static Point getErrorPoint(BufferedImage rightImage, BufferedImage userImage, int rgb) {
        int w = rightImage.getWidth();
        int h = rightImage.getHeight();

        Point temp = null;
        Map<String,Integer> cache = new HashMap();
        Map<String,Point> cachePoint = new HashMap();
        for(int x = 0; x < w; x++) {
            for(int y = 0; y < h; y++) {
                int rrgb = rightImage.getRGB(x,y);
                int urgb = userImage.getRGB(x,y);
                if(rrgb != urgb) {
                    String key = String.valueOf(x / size + y / size);
                    if(cache.containsKey(key)) {
                        cache.put(key, cache.get(key) + 1);
                    }else{
                        cache.put(key, 1);
                        cachePoint.put(key, new Point(x,y));
                    }
                }
                if(temp == null && urgb == rgb) {
                    temp = new Point(x,y);
                }
            }
        }

        int max = 0;
        String key = null;
        for(String k : cache.keySet()) {
            if(max < cache.get(k)) {
                max = cache.get(k);
                key = k;
            }
        }


        return cachePoint.get(key);
    }

}

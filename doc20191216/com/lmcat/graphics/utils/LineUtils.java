package com.lmcat.graphics.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO
 *
 * @author nayuan
 * @date 2018/6/22 00:08
 */
public class LineUtils {

    private static int stroke = 50;
    private static Color color = new Color(255,0,0,255);
    private static int rgb = color.getRGB();

    public static Point compare(JSONArray right, JSONArray user, int rate) {
        int minx = Integer.MAX_VALUE,maxx = Integer.MIN_VALUE,miny = Integer.MAX_VALUE,maxy = Integer.MIN_VALUE;

        for(int i = 0; i < right.size(); i++) {
            JSONObject it = right.getJSONObject(i);
            int x = it.getBigDecimal("x").intValue();
            int y = it.getBigDecimal("y").intValue();
            if(x < minx) {
                minx = x;
            }else if(x > maxx) {
                maxx = x;
            }
            if(y < miny) {
                miny = y;
            }else if(y > maxy) {
                maxy = y;
            }
        }

        for(int i = 0; i < user.size(); i++) {
            JSONObject it = user.getJSONObject(i);
            int x = it.getBigDecimal("x").intValue();
            int y = it.getBigDecimal("y").intValue();
            if(x < minx) {
                minx = x;
            }else if(x > maxx) {
                maxx = x;
            }
            if(y < miny) {
                miny = y;
            }else if(y > maxy) {
                maxy = y;
            }
        }

        int w = maxx - minx;
        int h = maxy - miny;
        BufferedImage rightImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage userImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        JSONObject prevPoint = null;

        Graphics2D g = (Graphics2D) rightImage.getGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        for(int i = 0; i < right.size(); i++) {
            JSONObject it = right.getJSONObject(i);
            if(prevPoint == null) {
                prevPoint = it;
                continue;
            }
            g.drawLine(
                    prevPoint.getBigDecimal("x").intValue() - minx,
                    prevPoint.getBigDecimal("y").intValue() - miny,
                    it.getBigDecimal("x").intValue() - minx,
                    it.getBigDecimal("y").intValue() - miny
            );
            prevPoint = it;
        }
        g.dispose();

        prevPoint = null;
        g = (Graphics2D) userImage.getGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        for(int i = 0; i < user.size(); i++) {
            JSONObject it = user.getJSONObject(i);
            if(prevPoint == null) {
                prevPoint = it;
                continue;
            }
            g.drawLine(
                    prevPoint.getBigDecimal("x").intValue() - minx,
                    prevPoint.getBigDecimal("y").intValue() - miny,
                    it.getBigDecimal("x").intValue() - minx,
                    it.getBigDecimal("y").intValue() - miny
            );
            prevPoint = it;
        }
        g.dispose();

        long rightSum = 0, userSum = 0;
        long sum = 0;
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++) {
                int rrgb = rightImage.getRGB(i,j);
                int urgb = userImage.getRGB(i,j);
                if(rrgb == urgb && rrgb == rgb) {
                    sum++;
                    rightSum++;
                    userSum++;
                }else if(rrgb == rgb) {
                    rightSum++;
                }else if(urgb == rgb) {
                    userSum++;
                }
            }
        }

        int userRate = (int)(sum * 2.0 / (rightSum + userSum) * 100);
        if(userRate > rate) {
            return null;
        }else{
            Point error = ErrorFindUtils.getErrorPoint(rightImage, userImage, rgb);
            error = new Point(error.getX() + minx, error.getY() + miny);
            return error;
        }
    }

    public static void main(String[] args) {
        JSONArray userPoints = JSONArray.parseArray("[{\"x\":348.8,\"y\":185.4},{\"x\":358.5,\"y\":173.4},{\"x\":378.6,\"y\":169},{\"x\":395.8,\"y\":162.3},{\"x\":429.3,\"y\":162.3},{\"x\":456.2,\"y\":160.8},{\"x\":470.3,\"y\":160.8},{\"x\":487.5,\"y\":161.5},{\"x\":525.5,\"y\":167.5},{\"x\":575.4,\"y\":174.2},{\"x\":618.6,\"y\":181.6},{\"x\":623.1,\"y\":184.6},{\"x\":649.2,\"y\":194.3},{\"x\":675.3,\"y\":199.5},{\"x\":705.1,\"y\":210},{\"x\":719.3,\"y\":219.7}]");
        JSONArray rightPoints = JSONArray.parseArray("[{\"x\":348.5,\"y\":184.3},{\"x\":361.4,\"y\":172.9},{\"x\":377.1,\"y\":168.6},{\"x\":397.1,\"y\":164.3},{\"x\":448.6,\"y\":158.6},{\"x\":474.3,\"y\":161.5},{\"x\":495.7,\"y\":164.3},{\"x\":538.6,\"y\":168.6},{\"x\":602.9,\"y\":180.1},{\"x\":661.5,\"y\":194.3},{\"x\":698.6,\"y\":208.6},{\"x\":720.1,\"y\":221.5}]");

        BufferedImage rightImage = new BufferedImage(1000,500, BufferedImage.TYPE_INT_RGB);
        JSONObject prevPoint = null;

        Graphics2D g = (Graphics2D) rightImage.getGraphics();
        g.setStroke(new BasicStroke(stroke));
        for(int i = 0; i < rightPoints.size(); i++) {
            JSONObject it = rightPoints.getJSONObject(i);
            if(prevPoint == null) {
                prevPoint = it;
                continue;
            }
            g.drawLine(
                    prevPoint.getBigDecimal("x").intValue(),
                    prevPoint.getBigDecimal("y").intValue(),
                    it.getBigDecimal("x").intValue(),
                    it.getBigDecimal("y").intValue()
            );
            prevPoint = it;


        }
        g.dispose();


        compare(rightPoints, userPoints, 95);

    }

}

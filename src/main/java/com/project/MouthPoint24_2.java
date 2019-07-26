package com.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MouthPoint24_2 {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\project\\1128");
        String filename = file.getName();
        List<File> fileList = getAllJsonFiles(file);
        int count = 0;
        for (File files : fileList) {
            count++;
            //D:\project\1128\bu\10651481-bu-a4-13(0).json   已完成： 181/10768
            //D:\project\1128\bu\10653068-bu-a4-19(0).json   已完成： 870/10768
            //D:\project\1128\吐舌\10719841-tu-a3-11.json   已完成： 10741/10768
            if (count <= 10741) {
                continue;
            }
            System.out.println(files.getAbsolutePath() + "   已完成： " + count + "/" + fileList.size());
            String name = files.getName().replace("json", "");
            File image = new File(files.getAbsolutePath().replace(filename, "images").replace("json", "jpg"));
            File prievew = new File(files.getAbsolutePath().replace(filename, "prievews").replace("json", "png"));
            File annotation = new File(files.getAbsolutePath().replace(filename, "annotations").replace("json", "txt"));
            if (prievew.exists() && image.exists() && annotation.exists()) {
                continue;
            }
            File txt = new File(files.getParent().replace(filename, "annotations"));
            if (!txt.exists()) txt.mkdirs();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    new File(txt, name + "txt"), false), "UTF-8"));
            writer.write(readJson(files, filename));
            writer.flush();
            writer.close();
        }
    }

    private static String readJson(File files, String filename) throws Exception {
        ArrayList points = new ArrayList();
        ArrayList<String> statespoints = new ArrayList<String>();
        ArrayList<String> states = new ArrayList<String>();
        String path = files.getParentFile().getName();
        String name = files.getName().replace("json", "jpg");
        String pathname = "/" + path + "/" + name;
        points.add(pathname);
        JSONObject object = readFileJSON(files);
        JSONObject markData = object.getJSONObject("answer").getJSONObject("markData");
        String src = markData.getString("src");
        BufferedImage image = ImageIO.read(new URL(src));
        outPutImages(files, filename, image, name);
        Graphics2D g = image.createGraphics();
        JSONArray counts = markData.getJSONArray("counts");
        JSONArray annotations = markData.getJSONArray("annotations");
        for (int a = 0; a < annotations.size(); a++) {
            JSONObject annotation = annotations.getJSONObject(a);
            JSONObject point = annotation.getJSONObject("point");
            float top = point.getFloat("top");
            float left = point.getFloat("left");
            float bottom = point.getFloat("bottom");
            float right = point.getFloat("right");
            points.add(top + "," + left + "," + bottom + "," + right);
            String labelType = annotation.getString("labelType");
            String state = null;
            if (labelType.equals("0.0")) {
                state = "0,1";
            } else {
                state = "1,0";
            }
            states.add(state + "," + labelType);
            int w = (int) (right - left);
            int h = (int) (bottom - top);
            g.setColor(Color.RED);
            g.drawRect((int) left, (int) top, w, h);
        }
        ArrayList<Integer> tonguePointx = new ArrayList<>();
        ArrayList<Integer> tonguePointy = new ArrayList<>();
        ArrayList<Integer> lipsoutx = new ArrayList<>();
        ArrayList<Integer> lipsouty = new ArrayList<>();
        ArrayList<Integer> lipsinx = new ArrayList<>();
        ArrayList<Integer> lipsiny = new ArrayList<>();
        for (int i = 0; i < counts.size(); i++) {
            JSONObject count = counts.getJSONObject(i);
            JSONObject point = count.getJSONObject("point");
            float x = point.getFloat("x");
            float y = point.getFloat("y");
            if (i > 8 && i < counts.size()) {
                points.add(x + "," + y);
            }
            if (i >= 0 && i < 9) {
                statespoints.add(x + "," + y);
            }
            g.setColor(Color.green);
            g.fillOval((int) x, (int) y, 3, 3);
            if (counts.getJSONObject(9).getInteger("orderNum") == 1) {
                if (i >= 0 && i < 9) {
                    tonguePointx.add((int) x);
                    tonguePointy.add((int) y);
                }
                if (i > 8 && i < 21) {
                    lipsoutx.add((int) x);
                    lipsouty.add((int) y);
                }
                if (i > 20 && i < counts.size()) {
                    lipsinx.add((int) x);
                    lipsiny.add((int) y);
                }
                if (i == 9) {
                    lipsoutx.add((int) x);
                    lipsouty.add((int) y);
                }
                if (i == 21) {
                    lipsinx.add((int) x);
                    lipsiny.add((int) y);
                }
            } else {
                if (i >= 0 && i < 12) {
                    lipsoutx.add((int) x);
                    lipsouty.add((int) y);
                }
                if (i > 11 && i < 24) {
                    lipsinx.add((int) x);
                    lipsiny.add((int) y);
                }
                if (i > 23 && i < counts.size()) {
                    tonguePointx.add((int) x);
                    tonguePointy.add((int) y);
                }
                if (i == 0) {
                    lipsoutx.add((int) x);
                    lipsouty.add((int) y);
                }
                if (i == 12) {
                    lipsinx.add((int) x);
                    lipsiny.add((int) y);
                }
            }
        }
        g.setColor(Color.yellow);
        int[] a = ListFormatArray(tonguePointx);
        int[] b = ListFormatArray(tonguePointy);
        g.drawPolyline(a, b, a.length);
//        for (int s =0;s<a.length;s++){
//            System.out.println(a[s]);
//        }

        g.setColor(Color.green);
        int[] a1 = ListFormatArray(lipsoutx);
        int[] b1 = ListFormatArray(lipsouty);
        g.drawPolygon(a1, b1, a1.length);

        g.setColor(Color.red);
        int[] a2 = ListFormatArray(lipsinx);
        int[] b2 = ListFormatArray(lipsiny);
        g.drawPolygon(a2, b2, a2.length);

        System.out.println(a.length + "," + a1.length + "," + a2.length);
        outPutPreviews(files, filename, image, name.replace("jpg", "png"));
        for (String s : statespoints) {
            points.add(s);
        }
        for (String s : states) {
            points.add(s);
        }

        String out = points.toString().replace(" ", "").replace(",", " ")
                .replace("[", "").replace("]", "");
//        System.out.println(out);
        return out;
    }

    /**
     * 获取所有json文件
     *
     * @param file
     * @return
     */
    public static List<File> getAllJsonFiles(File file) {
        List<File> allFile = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                allFile.addAll(getAllJsonFiles(f));
            } else if (f.getName().endsWith(".json")) {
                allFile.add(f);
            }
        }
        return allFile;
    }

    /**
     * 从文件中读取json
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static JSONObject readFileJSON(File file) throws Exception {
        if (!file.exists()) {
            return null;
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return JSON.parseObject(sb.toString());
    }

    /**
     * 输出原图
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static void outPutImages(File input, String filename, BufferedImage image, String imagename) throws IOException { //输出原图
        File outPutImage = new File(input.getParent().replace(filename, "image"));
        if (!outPutImage.exists()) outPutImage.mkdirs();
        String type = imagename.substring(imagename.lastIndexOf(".") + 1);
        String name = imagename.substring(0, imagename.lastIndexOf("."));
        if (!type.contains("png")) {
            ImageIO.write(image, "JPEG", new File(outPutImage, name + ".jpg"));
        } else {
            ImageIO.write(image, "PNG", new File(outPutImage, name + ".png"));
        }
    }

    /**
     * 输出效果图
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static void outPutPreviews(File input, String filename, BufferedImage image, String imagename) throws IOException { //输出效果图
        File outPutPreview = new File(input.getParent().replace(filename, "preview"));
        if (!outPutPreview.exists()) outPutPreview.mkdirs();
        String name = imagename.substring(0, imagename.lastIndexOf("."));
        ImageIO.write(image, "PNG", new File(outPutPreview, name + ".png"));
    }

    public static int[] ListFormatArray(ArrayList list) {

        int[] d = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            d[i] = (int) list.get(i);
        }
        return d;
    }
}

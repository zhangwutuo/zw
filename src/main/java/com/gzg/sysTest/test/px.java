package com.gzg.sysTest.test;

import java.util.*;

public class px {
    public static List<Map<String, Object>> getProductPriceGroup(List<Double> str1, Double min, Double max) {
        List<Map<String, Object>> pds = new ArrayList<>();
        diguiSum(arrayTransform(str1, max), min, max, pds);
        return pds;
    }

    public static void diguiSum(List<Double> str, Double min, Double max, List<Map<String, Object>> pds) {
        for (int i = 0; i < 10; i++) {
            Double[] cache = new Double[i + 1];
            int ceng = -1;
            int cengQuit = i;
            int startPiont = 0;
            cir(ceng, cengQuit, startPiont, str, cache, min, max, pds);
        }
    }

    // 递归求结果
    public static void cir(int ceng, int cengQuit, int startPiont, List<Double> array, Double[] cache, Double min, Double max, List<Map<String, Object>> pds) {
        ceng++;
        for (int i = startPiont; i < array.size(); i++) {
            cache[ceng] = array.get(i);
            if (ceng == cengQuit) {
                if ((getSum(cache) >= min) && (getSum(cache) <= max)) {
                    pds.add(printcache(cache));
                }
                if ((getSum(cache) < min) || (getSum(cache) > max)) {
                    continue;
                }
            }
            if (ceng < cengQuit) {
                startPiont = i;
                cir(ceng, cengQuit, startPiont, array, cache, min, max, pds);
            }
        }
    }

    // 获取组合数字之和
    public static Double getSum(Double[] cache) {
        Double sum = 0.00;
        for (int i = 0; i < cache.length; i++) {
            sum = sum + cache[i];
        }
        return sum;
    }

    // 打印组合的可能
    public static Map<String, Object> printcache(Double[] cache) {
        Map<String, Object> pd = new LinkedHashMap<>();
        Double sum = 0.00;
        String s = "";
        for (int i = 0; i < cache.length; i++) {
            if (i != cache.length - 1) {
                s += (cache[i] + ",");
                sum += cache[i];
            } else {
                s += cache[i];
                sum += cache[i];
            }
        }
        pd.put("price_sum", sum);
        pd.put("price_group", s);
        pd.put("group_size", cache.length);
        return pd;
    }

    public static List<Double> arrayTransform(List<Double> strArray, Double max) {
        for (int i = 0; i < strArray.size() - 1; i++) {
            for (int j = 1; j < strArray.size() - i; j++) {
                Double a;
                if ((strArray.get(j - 1)).compareTo(strArray.get(j)) > 0) { // 比较两个整数的大小
                    a = strArray.get(j - 1);
                    strArray.set((j - 1), strArray.get(j));
                    strArray.set(j, a);
                }
            }
        }
        for (int i = 0; i < strArray.size(); i++) {
            if (strArray.get(i) > max) {
                strArray.remove(i);
            }
        }
        return strArray;
    }

    public static void main(String[] args) {
        List<Double> str1 = new ArrayList<>();
        str1.add(0.18);
        str1.add(0.396);
        str1.add(0.4265);
        str1.add(0.4328);
        str1.add(0.2304);
        str1.add(0.6796);
        str1.add(0.568);
        str1.add(0.5936);
        str1.add(0.487);

        Double min = 1.10;
        Double max = 1.20;
        List<Map<String, Object>> result = px.getProductPriceGroup(str1, min, max);
        for (Map<String, Object> map : result) {
            System.out.println(map.get("price_group"));
        }
    }
}
package Similarity;

//java版编辑距离(字符串相似度)算法 levenshtein (edit distance)

public class levenshtein {  
    private levenshtein(){}  
      
    public static int ld(String s, String t) {  
        int d[][];  
        int sLen = s.length();  
        int tLen = t.length();  
        int si;   
        int ti;   
        char ch1;  
        char ch2;  
        int cost;  
        if(sLen == 0) {  
            return tLen;  
        }  
        if(tLen == 0) {  
            return sLen;  
        }  
        d = new int[sLen+1][tLen+1];  
        for(si=0; si<=sLen; si++) {  
            d[si][0] = si;  
        }  
        for(ti=0; ti<=tLen; ti++) {  
            d[0][ti] = ti;  
        }  
        for(si=1; si<=sLen; si++) {  
            ch1 = s.charAt(si-1);  
            for(ti=1; ti<=tLen; ti++) {  
                ch2 = t.charAt(ti-1);  
                if(ch1 == ch2) {  
                    cost = 0;  
                } else {  
                    cost = 1;  
                }  
                d[si][ti] = Math.min(Math.min(d[si-1][ti]+1, d[si][ti-1]+1),d[si-1][ti-1]+cost);  
            }  
        }  
        return d[sLen][tLen];  
    }  
      
    public static double similarity(String src, String tar) {  
        int ld = ld(src, tar);  
        return 1 - (double) ld / Math.max(src.length(), tar.length());   
    }  
      
      
    public static void main(String[] args) {  
//        String src = "hello world!";  
//        String tar = "hello";  
    	
    	   String src = "上海";  
           String tar = "上海市";  
           
        System.out.println("sim="+levenshtein.similarity(src, tar));  
    }  
}  
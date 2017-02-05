package Similarity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MainAddressSplit {
	public static NavigableMap<String, String> addressMap1 = new TreeMap<String,String>().descendingMap();
	public static NavigableMap<String, String> addressMap2 = new TreeMap<String,String>().descendingMap();
	
	
	public static String[] flag7list = {"特别行政区","自治区", "省"};  //考虑直辖市
	public static String[] flag6list = {"自治州", "地区", "市", "萌"};
	public static String[] flag5list = {"自治县", "自治旗", "林区", "特区", "县", "区", "旗"};
	public static String[] flag4list = {"乡", "镇", "街道", "民族乡", "苏木"};
	public static String[] flag3list = { "大道","大街", "胡同", "横路", "横街", "纵路", "纵街", "弄", "线","路", "街", "巷", "条"};
	public static String[] flag2list = {"小区","公寓", "村", "沟", "屯",  "里", "坊", "横", "队", "社",  
	                                    "大厦", "商场", "商城", "公司", "宾馆", "别墅", "商店", "所"};
	public static String[] flag1list = {"栋", "号楼", "楼", "座", "型", "阁", "号"};
	public static String[] flag0list = {"#", "楼", "层", "室", "房","组" ,"号"};

	public static String[][] FlagLevel = {flag0list, flag1list,flag2list,flag3list,flag4list,flag5list,flag6list,flag7list};
	public static HashSet<String> citySet = new HashSet<String>();
	 
	
    public static void main(String[] args){
    	LevelMap cdict = new LevelMap();
    	citySet = LevelMap.getCitySet();
    	
    	
    	//String add1 =  "南京市江宁区华府小区30栋401室";
    	 //String add1 =  "江苏省建湖县飞天小区30栋401室";
    	 //String add2 =   "江苏省无锡市新区旺庄街道新光村三墩巷";
    	// String add2 =   "江苏省南京市江宁区华府小区30栋401室";
    	 //String add2 =   "江苏省盐城市盐都区忠孝街道西大街华府小区401室";
    	String add1 =  "江苏省句容市华阳镇AA小区30号401号";
    	String add2 =  "江苏省句容市华阳镇AA小区30栋401室";
    	
    	//System.out.println("sim="+ levenshtein.similarity(add1, add2));  
    	 
    	addressMap1 = address_to_Map(add1,addressMap1);
    	 System.out.println(addressMap1);
    	
    	addressMap2 = address_to_Map(add2,addressMap2);
    	 System.out.println(addressMap2);
    	
    	 //System.out.println(Map_to_String(addressMap2));
    	
       //System.out.println("sim="+ levenshtein.similarity(Map_to_String(addressMap1), Map_to_String(addressMap2)));  
    
    	System.out.println(CompareAddress(addressMap1,addressMap2));
    }
    
    
    
    public static NavigableMap<String, String> address_to_Map(String address, NavigableMap<String, String> addressMap22){
    	String leftinfo = address;

        int i=0;
        int curfind =0;
        int lastfind = 0;
  
        
    	while(i<8){
    	  String[] temp_list = FlagLevel[i];
 
    	  for(String flag: temp_list){
    		if(leftinfo.contains(flag)){
    		 	
            //  if(flag.equals("区") && leftinfo.contains("地区")&& leftinfo.contains("自治区"))
//    			if(flag.equals("街")){
//    			 if( String.valueOf(leftinfo.charAt(leftinfo.indexOf("街")+1)).equals("道") )
//    				 break;
//    			}
    			
    			
    			if(i==0){
    			  if(leftinfo.split(flag).length == 1)
    				leftinfo = leftinfo.split(flag)[0];
    			 
    			}
    			else if(leftinfo.split(flag).length == 2){
    			  String item = leftinfo.split(flag)[1];
        		  addressMap22.put("key"+ lastfind, item);
        		  leftinfo = leftinfo.split(flag)[0];
//        		  System.out.println(i);
//        		  System.out.println(leftinfo);
    		    }
    			else{
    			  String[] itemList = leftinfo.split(flag);
    			  int k=0;
     
    			  leftinfo = itemList[0];
    			  while(k < itemList.length-1){
    				  leftinfo = leftinfo + flag + itemList[k];
    				  k++;
    			  }  
          		  addressMap22.put("key"+ lastfind, itemList[itemList.length-1]);
    			}
			   lastfind = i;
    		   break; // 只要匹配到，则该轮查找结束，进入下一个while循环
    		}
    		else{
    			addressMap22.put("key"+ i, "");
    			}
    	  } 
    	  
    	  // 一轮查找结束进入下一个while循环
    	  i++;
    	}
     
    	boolean isprovince = true;
    	if(leftinfo.length()>2) {     //减少查询次数
    		for(String city: citySet){
    			String[] templist;
				if(leftinfo.contains(city)){
				   isprovince = false;
    			   templist = leftinfo.split(city);
				   if(templist.length>1){
					   addressMap22.put("key7", templist[0]);
					   addressMap22.put("key6", city);
					   addressMap22.put("key5", templist[1]);
				   }
				   else{
					   addressMap22.put("key7", templist[0]);
					   addressMap22.put("key6", city);
				   }
				   break;
				}
    		}
    		
    		if(isprovince)   //黑龙江这些
    			addressMap22.put("key8", leftinfo);
    			
    	}
    	else
    		addressMap22.put("key8", leftinfo);
    		

    	return 	addressMap22; 
    }
	 
    public static String Map_to_String(NavigableMap<String, String> addressMap22){
    	String result = "";
    	for(String v: addressMap22.values())
    		result= result+ v;
    	return result;
    }

//    public static boolean CompareDistrict{
//    	
//    	
//    }

    
    
    public static Boolean CompareAddress(NavigableMap<String, String> A, NavigableMap<String, String> B){
        Iterator<String> ita = A.keySet().iterator(); 
        Iterator<String> itb = B.keySet().iterator(); 
        Boolean YFlag = false;  //有相似的
        Boolean NFlag = false;  //出现不相似的
        Boolean LackFlag = false; //其中一个缺少部分
        Boolean MatchFlag = true;
        
        String StrA = "";
        String StrB = "";
        
        while(ita.hasNext() && itb.hasNext()){
        	String keyA = ita.next();
        	String keyB = itb.next();
        	if(!A.get(keyA).isEmpty() && A.get(keyA).equals(B.get(keyB))){
        		StrA = StrA + A.get(keyA); 
        		StrB = StrB + B.get(keyB); 
        		YFlag = true;
        	}
        	else if((A.get(keyA).isEmpty() && !B.get(keyB).isEmpty())
        			||(!A.get(keyA).isEmpty() && B.get(keyB).isEmpty())){  //其中有一个空
        		LackFlag = true;
        		
        	}
        	else if(!A.get(keyA).isEmpty() && !B.get(keyB).isEmpty() 
        			&& !A.get(keyA).equals(B.get(keyB))) {
        		NFlag = true;
        		MatchFlag = false;
        		StrA = StrA + A.get(keyA); 
        		StrB = StrB + B.get(keyB); 
        		break;
        	}
        }

        System.out.println(StrA);
   	 System.out.println(StrB);
   	 
         double sim = levenshtein.similarity(Map_to_String(addressMap1), Map_to_String(addressMap2));
            if(MatchFlag && !LackFlag){
            	System.out.println(sim);
            	return true;
            }
             else if(LackFlag && StrA.equals(StrB)){
            	
            	 return true;
            }
            else if(!MatchFlag && sim<0.7){
            	System.out.println(sim);
            	return false;
            }
            else{
            	System.out.println(sim);
            	return false;
            }
            
            	 
    	
    	 
    }

}

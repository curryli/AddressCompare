package Similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Test {
	public static String[] flag0list = {"省", "自治区", "特别行政区"};  //考虑直辖市
	public static String[] flag1list = {"市", "地区", "自治州", "萌"};
	public static String[] flag2list = {"县", "区", "旗", "自治县", "自治旗", "林区", "特区"};
	public static String[] flag3list = {"乡", "镇", "街道", "民族乡", "苏木"};
	public static String[] flag4list = {"路", "街", "大道", "巷", "横路", "横街", "纵路", "纵街", "弄", "线"};
	public static String[] flag5list = {"小区", "村", "沟", "屯",  "里", "坊", "横", "队", "社"};
	public static String[] flag6list = {"大厦", "商场", "商城", "城", "公司", "宾馆", "别墅", "商店", "所"};
	public static String[] flag7list = {"栋", "座", "型", "阁", "号"};
	public static String[] flag8list = {"#", "楼", "层", "室", "房","组"};
	
	public static <T> T[] concatAll(T[] first, T[]... rest) {  
		  int totalLength = first.length;  
		  for (T[] array : rest) {  
		    totalLength += array.length;  
		  }  
		  T[] result = Arrays.copyOf(first, totalLength);  
		  int offset = first.length;  
		  for (T[] array : rest) {  
		    System.arraycopy(array, 0, result, offset, array.length);  
		    offset += array.length;  
		  }  
		  return result;  
		}  
	
	public static String[] districtList = concatAll(flag0list, flag1list, flag2list, flag3list);
	public static String[] detailList = concatAll(flag4list, flag5list, flag6list, flag7list, flag8list);
	public static String[] AllFlagList = concatAll(districtList,detailList);
	
	 public static void main(String[] args){
		 String AddressBefore = "上海市浦东新区远兰小区1000号";
         new ReverseSplit(AddressBefore, AllFlagList).start();
		 
		 
		  
	 }

}

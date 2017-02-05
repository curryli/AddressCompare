package Similarity; 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RABI {
	public static String[] flag0list = {"省", "自治区", "特别行政区"};  //考虑直辖市
	public static String[] flag1list = {"市", "地区", "自治州", "萌"};
	public static String[] flag2list = {"县", "区", "旗", "自治县", "自治旗", "林区", "特区"};
	public static String[] flag3list = {"乡", "镇", "街道", "民族乡", "苏木"};
	public static String[] flag4list = {"路", "街", "大道", "巷", "横路", "横街", "纵路", "纵街", "弄", "线"};
	public static String[] flag5list = {"小区", "村", "沟", "屯",  "里", "坊", "横", "队", "社"};
	public static String[] flag6list = {"大厦", "商场", "商城", "城", "公司", "宾馆", "别墅", "商店", "所"};
	public static String[] flag7list = {"栋", "座", "型", "阁", "号"};
	
	public static HashSet<String> MajorFlagSet = new HashSet<String>(){
		{ add("省");add("自治区");add("特别行政区");add("市");add("地区");add("自治州");add("萌");add("县");
		  add("区");add("旗");add("自治县");add("自治旗");add("林区");add("特区");add("乡");add("镇");
		  add("街道");add("民族乡");add("苏木");add("路"); add("街");add("大道");add("巷");add("横路");
		  add("横街");add("纵路");add("纵街");add("弄");add("线");add("小区"); add("村");add("沟");
		  add("屯");add("里");add("坊");add("横");add("队");add("社");add("大厦");add("商场");add("商城");
		  add("城");add("公司");add("宾馆");add("别墅");add("商店");add("所");add("栋");add("座");
		  add("型");add("阁");add("号");
		}
	};
	
	public static HashSet<String> MinorFlagSet = new HashSet<String>(){
		{ add("东");add("西");add("南");add("北");add("前");add("后");add("内");add("中");
		 add("大");add("小");add("新");add("旧");add("老");
		}
	};
		
	public static HashSet<String> RuleSet = new HashSet<String>(){
		{ add("010");add("0120");add("0122");add("09");add("19");
		  add("320101");add("32012");add("329");
		}
	};	
		
	
	 public static void main(String[] args){
		 String AddressBefore = "上海市普陀区中山北路5800号";
		 //String AddressBefore = "建邺区应天路业余村村部";

	 /////////////////////////////////
		 String NumbersBefore = "";
		 for(int i=0;i<AddressBefore.length();i++){
			  if(MajorFlagSet.contains(String.valueOf(AddressBefore.charAt(i)))){
				  if(i>2 && MajorFlagSet.contains(String.valueOf(AddressBefore.charAt(i-1)))){
					  NumbersBefore += "1";
					  NumbersBefore += "3";
				  }
				  else  
			          NumbersBefore += "1";
			  }
			  else if(MinorFlagSet.contains(String.valueOf(AddressBefore.charAt(i))))
			    NumbersBefore += "2";
			  else
			    NumbersBefore += "0";   
		 }
		 NumbersBefore += "9";
		 System.out.println(NumbersBefore);
		 
		 NumbersBefore = NumbersBefore.replaceAll("0+", "0");
		 System.out.println(NumbersBefore);
    //////////////////////////////////		 
		 
		 ArrayList<String> addItem = new ArrayList<String>();
		 
//		 for(int m=0;m<NumbersBefore.length();m++){
//			 if()
//		 }
		 
		 String NumbersLeft = "";
		 String NumbersRight = NumbersBefore;
		 String LastNumbersRight = "";
		 
		 while(!NumbersRight.equals(LastNumbersRight)){
			 LastNumbersRight = NumbersRight;
			 for(String rule: RuleSet){
	            Pattern p = Pattern.compile("^"+ rule);
			    Matcher m = p.matcher(NumbersRight);
			    
			    if (m.find()){
			    	NumbersLeft = m.group(1);
			    	addItem.add(NumbersLeft);
			    	NumbersRight = NumbersRight.replace(m.group(1), "");
			    	break;
			    } 
		     }
		 }
		 addItem.add(NumbersRight);
	 
		 System.out.println(addItem);
	 
	 } 
	 
}

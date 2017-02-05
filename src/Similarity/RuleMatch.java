package Similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class RuleMatch {
	public static HashSet<String> DistictFlagSet = new HashSet<String>(){
		{ add("省");add("自治区");add("特别行政区");add("市");add("地区");add("自治州");add("萌");add("县");
		  add("区");add("旗");add("自治县");add("自治旗");add("林区");add("特区");add("乡");add("镇");
		  add("街道");add("民族乡");add("苏木");
		}
	};
	
	public static HashSet<String> DetailFlagSet = new HashSet<String>(){
		{ add("路"); add("街");add("大道");add("巷");add("横路");
		  add("横街");add("纵路");add("纵街");add("弄");add("线");add("小区"); add("村");add("沟");
		  add("屯");add("里");add("坊");add("横");add("队");add("社");add("大厦");add("商场");add("商城");
		  add("城");add("公司");add("宾馆");add("别墅");add("商店");add("所");add("栋");add("座");
		  add("型");add("阁");add("号");
		}
	};
	
	
//	public static HashSet<String> MinorFlagSet = new HashSet<String>(){
//		{ add("东");add("西");add("南");add("北");add("前");add("后");add("内");add("中");
//		 add("大");add("小");add("新");add("旧");add("老");
//		}
//	};
	
	
	 public static void main(String[] args){
		 String AddressBefore = "上海市浦东新区远兰小区1000号";
		 
		 String Item = "";

		 Stack<String> DistrictStack = new Stack<String>();
		 Stack<String> DetailStack = new Stack<String>();
		 
		 for(int i=0;i<AddressBefore.length();i++){
			  if(DistictFlagSet.contains(String.valueOf(AddressBefore.charAt(i)))){
			      DistrictStack.push(Item);
			      Item="";
			  }
//			  else if(MinorFlagSet.contains(String.valueOf(AddressBefore.charAt(i))))
//			    NumbersBefore += "2";
			  else if(DetailFlagSet.contains(String.valueOf(AddressBefore.charAt(i))))
			  {
//				   NumbersBefore += "1";
				  DetailStack.push(Item);
			      Item="";
		      }
			  else{
				  Item = Item + AddressBefore.charAt(i);
				  
			  }
		 }
//		 NumbersBefore += "9";
		 System.out.println(DistrictStack);
		 System.out.println(DetailStack);
		 
		 String LastDistictItem = DistrictStack.pop();
		 System.out.println(LastDistictItem);
		 System.out.println(DistrictStack);
		 
		 
		  
	 }

}

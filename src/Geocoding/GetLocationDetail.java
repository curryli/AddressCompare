package Geocoding;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.ClientProtocolException;


/**
 * @author 
 * 
 */
public class GetLocationDetail {
    
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String Cstring = GetLocationString("31.237324047250146", "121.67010793418429");
		System.out.println(Cstring);
        
    }
    
    
    public static String GetLocationString(String lat, String lng) throws IOException, IOException{
    	String ak = "FpGacYmfVbQhNHO51YYGS4v3LR7cgS8P";
    	   
        //逆地理编码,即根据经度纬度来查地址
        //String url = "http://api.map.baidu.com/geocoder/v2/?ak=" + ak + "&location=32.0021400000,119.6102790000&output=json&pois=1";
        
    	String url = "http://api.map.baidu.com/geocoder/v2/?ak=" + ak + "&location=" + lat + "," + lng + "&output=json&pois=1";
        
    	
    	String qr = Parser_Tool.do_get(url);
        System.out.println("接收到的字符串：" + qr);
        
        //将json字符串转成json对象
        JSONObject jsonObject = JSONObject.fromObject(qr);
        
        JSONObject result = jsonObject.getJSONObject("result");
        
        String formatted_address = result.getString("formatted_address");
        
          
        return formatted_address;
    }
     

}
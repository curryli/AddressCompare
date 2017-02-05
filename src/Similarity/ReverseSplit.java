package Similarity;
 
public class ReverseSplit {
    private String[] dict = null;  //词典
    private String input = null;
     
    public ReverseSplit(String input, String[] dict) {
        this.input = input;
        this.dict = dict;
    }
    
   
    public void start() {
        String temp = null;
        for(int i=0;i<this.input.length();i++) {
            temp = this.input.substring(i);  // 每次从字符串的首部截取一个字，并存到temp中

            // 如果该词在字典中， 则删除该词并在原始字符串中截取该词
            if(this.isInDictionary(temp)) {
                System.out.println(temp);
                this.input = this.input.replace(temp, "");
                i = -1;  // i=-1是因为要重新查找， 而要先执行循环中的i++
            }
        }
         
        // 当前循环完毕，词的末尾截去一个字，继续循环， 直到词变为空
        if(null != this.input && !"".equals(this.input)) {
            this.input = this.input.substring(0,this.input.length()-1);
            this.start();
        }
    }
    
    
    
    public void splitByDict() {
        String temp = null;
        for(int i=0;i<this.input.length();i++) {
            temp = this.input.substring(i);  // 每次从字符串的首部截取一个字，并存到temp中

            // 如果该词在字典中， 则删除该词并在原始字符串中截取该词
            if(this.isInDictionary(temp)) {
                System.out.println(temp);
                this.input = this.input.replace(temp, "");
                i = -1;  // i=-1是因为要重新查找， 而要先执行循环中的i++
            }
        }
         
        // 当前循环完毕，词的末尾截去一个字，继续循环， 直到词变为空
        if(null != this.input && !"".equals(this.input)) {
            this.input = this.input.substring(0,this.input.length()-1);
            this.start();
        }
    }
    
    
    
    
    
     
    //判断当前词是否在字典中
    public boolean isInDictionary(String temp) {
        for(int i=0;i<this.dict.length;i++) {
            if(temp.equals(this.dict[i])) {
                return true;
            }
        }
        return false;
    }
}
package ArraysAdHoc;

public class Rotation {
	
	  static boolean isRotation(String palin, String rot){
	        
	        if(palin.length() != rot.length() || palin.isEmpty() || rot.isEmpty()){
	            return false;
	        }
	        char last = palin.charAt(palin.length()-1);
	        char first = rot.charAt(rot.charAt(0));
	        
	        if(last == first){
	            for(int i = 0; i < palin.length() -1; i++){
	                for(int j = 1; j < rot.length(); j++){
	                    if(palin.charAt(i) != rot.charAt(j)){
	                        return false;
	                    }
	                }
	                
	                
	            }
	            return true;
	        }
	        
	        return false;
	    }
    

}

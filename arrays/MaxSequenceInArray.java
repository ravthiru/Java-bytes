// finds longest sequence of 0 in an array. 
public class MaxSequenceInArray {

	public static void main(String[] args) {
        int arr[] = { 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1 }; 
            System.out.println("Max Sequence is "+getLongestSeq(arr)); 
	  }  
		 
		    static int getLongestSeq(int a[]) 
		    { 
		    	int len = a.length;
		        int maxIdx = 0, maxLen = 0, currLen = 0, currIdx = 0; 
		      
		        for (int i = 0; i < len; i++)  
		        { 
		            if (a[i] == 0) 
		            { 
		                currLen++; 
		                // New sequence, store 
		                // beginning index. 
		                if (currLen == 1)  
		                    currIdx = i;          
		            } 
		            else
		            { 
		                if (currLen > maxLen)  
		                { 
		                    maxLen = currLen; 
		                    maxIdx = currIdx; 
		                } 
		                currLen = 0; 
		            } 
		        } 
		            
		        return maxLen; 
		    } 
	}

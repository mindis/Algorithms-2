import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogParser {
    public static void main(String[] args)
            throws FileNotFoundException, IOException, ParseException {
        String filename = "input_1.txt";
        if (args.length > 0) {
        	filename = args[0];
        }

        String answer = parseFile(filename);
        System.out.println(answer);
    }

    static String parseFile(String filename)
            throws FileNotFoundException, IOException, ParseException {
        /*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();

        return parseLines(allLines.toArray(new String[allLines.size()]));
    }

    static String parseLines(String[] lines) throws ParseException {
        /*
         *
         * Your code goes here
         *
         */
    	Date startDate = null;
    	Date endDate = null;
    	long totalConnect = 0;
    	Date buffer1 = null;
    	Date buffer2 = null;
    	for(String line:lines){
    		String[] str=line.split(" :: ");
    		if(str[1].contains("START")){
    			startDate = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)").parse(str[0]);
    			continue;
    		}
    		if(str[1].contains("SHUTDOWN")){
    			endDate = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)").parse(str[0]);
    			continue;
    		}
    		
    		if(str[1].contains("DISCONNECTED")){
    			if(buffer1!=null){
	    			buffer2 = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)").parse(str[0]);
	    			totalConnect += getDiff(buffer1, buffer2);
	    			buffer1 = null;
	    			buffer2 = null;
    			}
    			continue;
    			
    		} else if(str[1].contains("CONNECTED")){
    			buffer1 = new SimpleDateFormat("(MM/dd/yyyy-HH:mm:ss)").parse(str[0]);
    			buffer2 = null;
    			continue;
    		}
    		
    	}
    	if(buffer1!=null){
    		totalConnect += getDiff(buffer1, endDate);
    	}
    	
    	double ret = totalConnect * 1.0 / getDiff(startDate, endDate);
    	String retString = (int)(ret*100) +"%";
        return retString;
    }
    
    public static long getDiff(Date d1, Date d2){
    	return (d2.getTime()-d1.getTime())/1000;
    }
}

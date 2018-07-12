/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebcdic.to.ascii.converter;
/**
 *
 * @author 257592
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

public class FixedwidthToDelimited {

    public void  FixedwidthToDelimited(File destFile,String schema) throws FileNotFoundException, IOException {
        // String S1="NHJAMES TURNER M123-45-67890004224345";
        String FixedLengths = schema;

        List<String> items = Arrays.asList(FixedLengths.split("\\s*,\\s*"));
        // File file = new File("src/sample.txt");
        File fout=new File(destFile+"_converted.txt");
        FileOutputStream fos = new FileOutputStream(fout); 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
 
       // System.out.println("Reading input File");
        try (BufferedReader br = new BufferedReader(new FileReader(destFile))) 
        {
        String line1;
            while ((line1 = br.readLine()) != null) {
                // process the line.

                int n = 0;
                String line = "";
                for (String i : items) 
                {
                    // System.out.println("Items:"+i);
                    if (!i.contains(".") && i == items.get(items.size() - 1))
                    {
                        line = line + line1.substring(n, n + Integer.parseInt(i));
                    } 
                    else if (i.contains(".") && i == items.get(items.size() - 1))
                    {
                        // System.out.println("point Encounterd"+i);
                        String split = ".";
                        String before = i.substring(0, i.indexOf(split));
                        // System.out.println("point Encounterd-"+before);
                        String after = i.substring(i.indexOf(split) + split.length());                     
                       //  System.out.println("point Encounterd-"+after);
                        int total = Integer.parseInt(before) + Integer.parseInt(after) ;
                        // System.out.println(total);
                        String value = line1.substring(n,n+total);
                        // System.out.println(value);
                        line = line + value.substring(0, Integer.parseInt(before)) + "."  +value.substring(Integer.parseInt(before));      
                        // System.out.println("point -"+line);
                        i=String.valueOf(total);
                    }
                    else if (!i.contains(".") && i != items.get(items.size() - 1))
                    {
                        line = line + line1.substring(n, n + Integer.parseInt(i)) + ",";
                    }
                    else
                    {
                        //System.out.println("point Encounterd"+i);
                        String split = ".";
                        String before = i.substring(0, i.indexOf(split));
                       // System.out.println("point Encounterd-"+before);
                        String after = i.substring(i.indexOf(split) + split.length());                     
                       // System.out.println("point Encounterd-"+after);
                        int total = Integer.parseInt(before) + Integer.parseInt(after) ;
                        // System.out.println(total);
                        String value = line1.substring(n,n+total);
                       // System.out.println(value);
                        line = line + value.substring(0, Integer.parseInt(before)) + "."  +value.substring(Integer.parseInt(before))+ ",";      
                       // System.out.println("point -"+line);
                        i=String.valueOf(total);
                        
                    }
                    // System.out.println(
                    // S1.substring(n,n+Integer.parseInt(i)));
                    n = n + Integer.parseInt(i);
                    // System.out.println("After"+n);
                }
                bw.write(line);
                bw.newLine();
            }
        }
        bw.close();
    }
}
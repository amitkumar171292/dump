
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
// import java.util.Scanner;


public final class Main 
{

  private static final String INPUT_CHARSET_DEFAULT = "CP1047";
  private static final int FIXED_LENGTH_DEFAULT = -1;

    private static File File(String cabcfoldertextfiletxt) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  private Charset input = charsetForName(INPUT_CHARSET_DEFAULT);
  private Charset output = Charset.defaultCharset();
  private int fixedLength = FIXED_LENGTH_DEFAULT;
  private File source = null;
  private File destination = null;
  
 




  

  public static void main(String[] args) throws IOException {

 
    new Main().parseArguments(args).convert();
    
      
    
  }

  private Main() {
        // this.filepath = br.readLine();
  }

  private Main parseArguments(String[] args) 
  {
    for (int i = 0; i < args.length; i++) 
    {
      String arg = args[i];
      if ("-h".equals(arg) || "--help".equals(arg)) 
      {
        printUsage();
      } 
      else if ("-f".equals(arg)) {
        i++;
        checkOptionValueDefinition(args, i);
        input = charsetForName(args[i]);
      } 
      else if ("-t".equals(arg)) {
        i++;
        checkOptionValueDefinition(args, i);
        output = charsetForName(args[i]);
      }
      else if ("-l".equals(arg)) {
        i++;
        checkOptionValueDefinition(args, i);
        try {
          fixedLength = Integer.parseInt(args[i]);
        } catch (NumberFormatException e) {
          printError("Expected number for option -l, but got: " + args[i]);
        }
      } 
      else 
      {
        File dir = new File(args[i]);
        if (source == null) 
        {
          source = dir;
          if (!source.isDirectory()) {
            printError("No such directory: " + source);
          }
        } 
        else if (destination == null) 
        {
          destination = dir;
        } 
        else 
        {
          printError("Unknown option: " + args[i]);
        }
      }
    }

    if (source == null) 
    {
      printError("Missing source.");
    }
    if (destination == null) 
    {
      printError("Missing destination.");
    }
    return this;
  }

  private void checkOptionValueDefinition(String[] args, int i) {
    if (i >= args.length) {
      printError("Missing argument for option -f");
    }
  }

  private void convert() throws IOException 
  {
      Properties prop = new Properties();  
        try {  
            // load a properties file for reading  
            File file = new File("myConfig.properties");
            // System.out.println("Path : " + file.getAbsolutePath());
            prop.load(new FileInputStream(file.getAbsolutePath()));  
            // get the properties and print  
            // prop.list(System.out);  
            //Reading each property value  
            // System.out.println(prop.getProperty("Entire_Directory_processing"));  
            // System.out.println(prop.getProperty("Source_Folder"));  
            // System.out.println(prop.getProperty("Destination_Folder"));  
            //System.out.println(prop.getProperty("File_Name"));  
        } catch (IOException ex) 
        {  
            ex.printStackTrace();  
        }  
      
      
      //Scanner input1 = new Scanner(System.in);
      //System.out.println("Enter Y or N");
      
 // char  indicator =input1.next().charAt(0);
        String indicator=prop.getProperty("Entire_Directory_processing");
        // System.out.println(indicator);
 
 // input1.close();
    try 
    {
      FileConverter converter = new FileConverter(input, output);
      // System.out.println("Am i Calling");
      
      converter.setFixedLength(fixedLength);
      if ("Y".equals(indicator))
            
      {
      List<String> files = listFiles(source);
      for (String s : files) 
      {
        File sourceFile = new File(source, s);
        File destFile = new File(destination, s);
        log("Converting " + sourceFile + " into " + destFile);
        destFile.getParentFile().mkdirs();
        converter.convert(sourceFile, destFile);
        String path5=prop.getProperty("Fixed_Width");
        String path6=prop.getProperty("Schema");
       // System.out.println(path5);
        if("N".equals(path5))
        {
        // System.out.println("Fixed To Delimitsed Conversion");
        FixedwidthToDelimited FTD = new FixedwidthToDelimited();
        FTD.FixedwidthToDelimited(destFile,path6);
        }
      }
      }
      else
      {
 //System.out.println("Enter Source File Path");
 //Scanner input2 = new Scanner(System.in);
 String path2 = prop.getProperty("Source_Folder");
 //System.out.println("Enter Destination File Path");
 //Scanner input3 = new Scanner(System.in);
 String path3 = prop.getProperty("Destination_Folder");
 //System.out.println("Enter FileName");
 //Scanner input4 = new Scanner(System.in);
 String path4 = prop.getProperty("File_Name");
 File sourceFile = new File(path2, path4);
 File destFile = new File(path3, path4);
 log("Converting " + sourceFile + " into " + destFile);
 destFile.getParentFile().mkdirs();
 converter.convert(sourceFile, destFile);
 String path5=prop.getProperty("Fixed_Width");
 String path6=prop.getProperty("Schema");
  if("N".equals(path5))
        {
        // System.out.println("Fixed To Delimited Conversion");
        FixedwidthToDelimited FTD = new FixedwidthToDelimited();
        FTD.FixedwidthToDelimited(destFile,path6);
        }
 
      }
      
      log("SUCCESS");
    } 
    catch (EbcdicToAsciiConverterException e) 
    {
      log("Unable to convert files", e);
      log("FAILURE");
    }
  }

  private static Charset charsetForName(String charsetName) {
    try {
      return Charset.forName(charsetName);
    } catch (Exception e) {
      printError("Unknown charset: " + charsetName);
      throw new EbcdicToAsciiConverterException("'" + charsetName + "' is an unknown charset.", e);
    }
  }

  private static void printUsage() {
    log("");
    log("Usage: [options] source destination");
    log("");
    log("Convert source into destination.");
    log("  source         relative or absolute path to the directory containing files to be converted");
    log("  destination    relative or absolute path to the directory, which will contain result of conversion");
    log("");
    log("Options:");
    log("  -h, --help     display help information and exit");
    log("  -f encoding    encoding of original text (" + INPUT_CHARSET_DEFAULT + " by default)");
    log("  -t encoding    encoding for output (by default the one of the OS: " + Charset.defaultCharset().displayName() + ")");
    log("  -l length      number of characters to split output by lines (disabled by default)");
    System.exit(0);
  }

  private static List<String> listFiles(File dir) {
    List<String> files = new ArrayList<String>();
    recursivelyListFiles(dir, "", files);
    return files;
  }

  private static void recursivelyListFiles(File dir, String relativePath, List<String> files) {
    for (String s : dir.list()) 
    {
      String path = relativePath + File.separator + s;
      File file = new File(dir, s);
      if (file.isFile() && !file.isHidden()) 
      {
        files.add(path);
      } 
      else if (file.isDirectory() && !file.isHidden()) 
      {
        recursivelyListFiles(file, path, files);
      }
    }
  }

  private static void printError(String message) {
    log("");
    log(message);
    printUsage();
  }

  private static void log(String message) {
    System.out.println(message);
  }

  private static void log(String message, Throwable e) {
    System.out.println(message);
    e.printStackTrace();
  }

}

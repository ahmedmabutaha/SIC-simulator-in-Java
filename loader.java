
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class loader {
   reg g =new reg();
   private Scanner inputFile;
   String line;
   String Ram[];
   int hstart,hsize;
   String [] array;
   String l;
   String startexe="";
    public String []  openfile  (String filename)
    {
        try
	{
            line = new Scanner(new File(filename)).useDelimiter("\\A").next();
            array=line.split("\n");
       
            for(int i=0;i<array.length;i++)
            {
                String x=array[i];
                
               if(x.charAt(0)=='H')
               {
                   Head(x);
               }else if(x.charAt(0)=='T')
               {
                   Text(x);
                 
               
               }else if(x.charAt(0)=='E')
               {
                  End(x);  
               }
            }
	}
	catch ( FileNotFoundException fileNotFoundException )
	{
	System.err.println( "Error opening or creating file." );
 	System.exit( 1 );	
        
      
	}
        return Ram;
    }
    public void closeReadFile()
	 {
                if ( inputFile != null )
                    inputFile.close();
	 }
    public void Head( String c)
    {
            String z=c.substring(8, 14);
            hstart=Integer.parseInt(z, 16);
            String x=c.substring(14, 19);
            hsize=Integer.parseInt(x, 16);
            Ram=new String[hsize];

    }
    public void Text(String c)
    {        
           String fadd=c.substring(1,7).trim();
           int arrf=Integer.parseInt(fadd, 16);
           String size=c.substring(7,9).trim();
           int arrs=Integer.parseInt(size, 16);
           int count=11;
           int count1=9;

           for(int i=arrf;i<arrf+arrs;i++){
              l=c.substring(count1,count);
              count=count+2;count1=count1+2;             

              Ram[i]=l;
           }

    }

    public void End(String c)
    {

     

    }

}

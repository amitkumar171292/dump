/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfgenerator;

/**
 *
 * @author 610968
 */
import java.io.*;
import java.util.*;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.h2.jdbcx.JdbcConnectionPool;
import static pdfgenerator.logo.IMAGE;


public class pdflogo 
{  
    public static void main(String[] args) throws IOException, DocumentException, Exception
    {
        final String IMAGE = "D:\\AMIT KUMAR\\Google.png";
        String DEST = "D:\\AMIT KUMAR\\file1.pdf";
        class HeaderTable extends PdfPageEventHelper 
        {
            protected PdfPTable table;
            protected float tableHeight;
            public HeaderTable() 
            {
                table = new PdfPTable(1);
                table.setTotalWidth(523);
                table.setLockedWidth(true);
                table.addCell("Bravo Test Execution Summary");
                tableHeight = table.getTotalHeight();
            }
         
            public float getTableHeight() 
            {
                return tableHeight;
            }
         
            public void onEndPage(PdfWriter writer, Document document) 
            {
                table.writeSelectedRows(0, -1,document.left(),
                            document.top()+ ((document.topMargin() + tableHeight) / 2),
                            writer.getDirectContent());
            }
        }
        
        class FooterTable extends PdfPageEventHelper 
        {
            protected PdfPTable footer;
            public FooterTable(PdfPTable footer) 
            {
                this.footer = footer;
            }
            public void onEndPage(PdfWriter writer, Document document) 
            {
                footer.writeSelectedRows(0, -1, 20, 100, writer.getDirectContent());
            }
        }

        
        //creating connection to the database
             
        Class.forName("org.h2.Driver");
        String RepoPath="jdbc:h2:D:\\AMIT KUMAR\\Project\\JAVA\\repo\\repo\\bravo.repo;AUTO_SERVER=TRUE;IFEXISTS=TRUE";
        Connection conn = DriverManager.getConnection(RepoPath,"bravo","");
        Statement stmt = conn.createStatement();
        String sql = "select TS.TS_NO,TS.TSET_NAME,TC.TC_NO,TC.TC_ID,TC.TC_NAME from BRAVO_REPO.TEST_SETS TS inner join BRAVO_REPO.TEST_CASES TC on (TS.TS_NO=TC.TS_NO) where TS.TSET_NAME='Mapr' order by TC.TC_NO";
        ResultSet query_set = stmt.executeQuery(sql);         
        Document my_pdf_report = new Document(PageSize.A4, 36, 36, 36, 72);     /* Step-2: Initialize PDF documents - logical objects */
            
        HeaderTable event1 = new HeaderTable();
        // step 1
        Document document = new Document(PageSize.A4, 36, 36, 20 + event1.getTableHeight(), 36);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(my_pdf_report, new FileOutputStream(DEST));
        writer.setPageEvent(event1);
        // step 3
        my_pdf_report.open();  
         
        PdfPTable my_report_table = new PdfPTable(5);           //we have five columns in our table
            //create a cell object
        PdfPCell table_cell;
        table_cell=new PdfPCell(new Phrase("TS_NO"));
        my_report_table.addCell(table_cell);                               // give column names
        table_cell=new PdfPCell(new Phrase("TSET_NAME"));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("TC_NO"));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("TC_ID"));
        my_report_table.addCell(table_cell);
        table_cell=new PdfPCell(new Phrase("TC_NAME"));
        my_report_table.addCell(table_cell);
          
        while (query_set.next()) 
        {                
            String TS_NO = query_set.getString("TS_NO");
            table_cell=new PdfPCell(new Phrase(TS_NO));
            my_report_table.addCell(table_cell);
            String TSET_NAME=query_set.getString("TSET_NAME");
            table_cell=new PdfPCell(new Phrase(TSET_NAME));
            my_report_table.addCell(table_cell);
            String TC_NO=query_set.getString("TC_NO");
            table_cell=new PdfPCell(new Phrase(TC_NO));
            my_report_table.addCell(table_cell);
            String TC_ID=query_set.getString("TC_ID");
            table_cell=new PdfPCell(new Phrase(TC_ID));
            my_report_table.addCell(table_cell);
            String TC_NAME=query_set.getString("TC_NAME");
            table_cell=new PdfPCell(new Phrase(TC_NAME));
            my_report_table.addCell(table_cell);
        }

                /* Attach report table to PDF */
        my_pdf_report.add(my_report_table);  
        
        PdfPTable table = new PdfPTable(1);
        table.setTotalWidth(523);
        PdfPCell cell = new PdfPCell(new Phrase("This is a test document"));
        cell.setBackgroundColor(BaseColor.ORANGE);
        table.addCell(cell);
        FooterTable event = new FooterTable(table);
        writer.setPageEvent(event);

        
        
        
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance(IMAGE);
        //image.scaleAbsolute(PageSize.A4.rotate());
        image.setAbsolutePosition(0, 0);
        canvas.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.8f);
        canvas.setGState(state);
        canvas.addImage(image);
        canvas.restoreState();
        
        
        
        my_pdf_report.close();
                
                /* Close all DB related objects */
        query_set.close();
        stmt.close(); 
        conn.close();               
        System.out.print("Completed"); 
    }
}


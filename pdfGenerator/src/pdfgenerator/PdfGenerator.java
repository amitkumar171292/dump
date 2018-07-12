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
import java.io.FileOutputStream;

import java.io.*;
import java.util.*;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class PdfGenerator 
{  
    public static void main(String[] args) throws Exception
    {
        Connection conn=null;
        Statement stmt=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); /* Create Connection objects */
            conn=DriverManager.getConnection("jdbc:mysql://10.237.36.111:3306/jupiter", "root", "Bravo123"); 
            stmt = conn.createStatement();
            /* Define the SQL query */  
            ResultSet query_set = stmt.executeQuery("SELECT DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID FROM DEPARTMENTS");
            Document my_pdf_report = new Document();     
            /* Step-2: Initialize PDF documents - logical objects */
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("D:\\AMIT KUMAR\\Project\\JAVA\\pdf_report_from_sql_using_java.pdf"));
            my_pdf_report.open();            

            PdfPTable my_report_table = new PdfPTable(4);           //we have four columns in our table
                    //create a cell object
            PdfPCell table_cell;

            table_cell=new PdfPCell(new Phrase("DEPARTMENT_ID"));
            my_report_table.addCell(table_cell);                                                    
            table_cell=new PdfPCell(new Phrase("DEPARTMENT_NAME"));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase("MANAGER_ID"));
            my_report_table.addCell(table_cell);
            table_cell=new PdfPCell(new Phrase("LOCATION_ID"));
            my_report_table.addCell(table_cell);

            while (query_set.next()) 
            {                
                String dept_id = query_set.getString("DEPARTMENT_ID");
                table_cell=new PdfPCell(new Phrase(dept_id));
                my_report_table.addCell(table_cell);
                String dept_name=query_set.getString("DEPARTMENT_NAME");
                table_cell=new PdfPCell(new Phrase(dept_name));
                my_report_table.addCell(table_cell);
                String manager_id=query_set.getString("MANAGER_ID");
                table_cell=new PdfPCell(new Phrase(manager_id));
                my_report_table.addCell(table_cell);
                String location_id=query_set.getString("LOCATION_ID");
                table_cell=new PdfPCell(new Phrase(location_id));
                my_report_table.addCell(table_cell);
            }
                    /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);                       
            my_pdf_report.close();

                    /* Close all DB related objects */
            query_set.close();
            stmt.close(); 
            conn.close();               
            System.out.println("Completed");
        }
        catch(SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {
            //finally block used to close resources
            try
            {
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2)
            {
            }// nothing we can do
            try
            {
                if(conn!=null)
                conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");        
    }
}
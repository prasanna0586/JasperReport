package com.sample.jasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.db.connection.DBConnection;

public class CreateJasperReport {

	public static void main(String[] args)  {
		String sourceFileName = "D:\\dev\\jasperreports\\JasperReport\\input\\Sample2.jrxml";
		String destFileName = "D:\\dev\\jasperreports\\JasperReport\\output\\Output.pdf";
		Connection connection = DBConnection.getConnection();	
		Map<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("empAge", new Integer(28));
		JasperPrint jasperPrint = null;
		JasperReport jasperReport;
		OutputStream output = null;
		try {
			jasperReport = JasperCompileManager.compileReport(sourceFileName);
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
			output = new FileOutputStream(new File(destFileName));
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("End Of Report...");
	}

}

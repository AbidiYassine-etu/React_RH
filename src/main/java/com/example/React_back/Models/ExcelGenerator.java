package com.example.React_back.Models;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelGenerator {

    public static byte[] generateCongesExcel(List<Conges> congesList) {
        try {
            // Create a new workbook and a sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Congés");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nom");
            headerRow.createCell(1).setCellValue("Département");
            headerRow.createCell(2).setCellValue("Type");
            headerRow.createCell(3).setCellValue("Date Début");
            headerRow.createCell(4).setCellValue("Date Fin");
            headerRow.createCell(5).setCellValue("Statut");

            // Add data rows
            int rowNum = 1;
            for (Conges conge : congesList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(conge.getEmployee().getNom());
                row.createCell(1).setCellValue(conge.getEmployee().getDepartement().toString());
                row.createCell(2).setCellValue(conge.getType().toString());
                row.createCell(3).setCellValue(conge.getDate_Debut()); // String value
                row.createCell(4).setCellValue(conge.getDate_Fin());   // String value
                row.createCell(5).setCellValue(conge.getStatus().toString());
            }

            // Resize columns to fit content
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write the output to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            workbook.close();

            return baos.toByteArray(); // Return the generated Excel file as a byte array
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

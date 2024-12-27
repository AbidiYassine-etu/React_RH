package com.example.React_back.Models;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ExcelGenerator {

    /**
     * Generates an Excel file for a list of Presence records.
     *
     * @param presenceList List of Presence records to be included in the Excel file.
     * @return A byte array containing the Excel file.
     * @throws IOException if an error occurs while writing the Excel file.
     */
    public static byte[] generatePresenceExcel(List<Presence> presenceList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Presence Data");

        // Create Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Employee ID", "Employee Name", "Login Time"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Populate Data Rows
        int rowIndex = 1;
        for (Presence presence : presenceList) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(presence.getEmployee().getId());
            row.createCell(1).setCellValue(presence.getEmployee().getNom() + " " + presence.getEmployee().getPrenom());
            row.createCell(2).setCellValue(presence.getLoginTime().toString());
        }

        // Resize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }

    /**
     * Generates an Excel file for a list of Conges records.
     *
     * @param congesList List of Conges records to be included in the Excel file.
     * @return A byte array containing the Excel file.
     */
    public static byte[] generateCongesExcel(List<Conges> congesList) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Congés");

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Nom", "Département", "Type", "Date Début", "Date Fin", "Statut"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Date format to display in Excel
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Add data rows
            int rowIndex = 1;
            for (Conges conge : congesList) {
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(conge.getEmployee().getNom());
                row.createCell(1).setCellValue(String.valueOf(conge.getEmployee().getDepartement()));
                row.createCell(2).setCellValue(conge.getType().toString());

                // Handle Date conversion
                try {
                    // Parse dates from String to Date
                    String dateDebutStr = conge.getDate_Debut();
                    String dateFinStr = conge.getDate_Fin();

                    // Convert String to Date using SimpleDateFormat
                    String formattedDateDebut = formatDate(dateDebutStr, dateFormat);
                    String formattedDateFin = formatDate(dateFinStr, dateFormat);

                    row.createCell(3).setCellValue(formattedDateDebut);
                    row.createCell(4).setCellValue(formattedDateFin);

                } catch (ParseException e) {
                    // Log the error and handle invalid date format
                    e.printStackTrace();
                    row.createCell(3).setCellValue("Invalid Date");
                    row.createCell(4).setCellValue("Invalid Date");
                }

                row.createCell(5).setCellValue(conge.getStatus().toString());
            }

            // Resize columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to ByteArrayOutputStream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating Congés Excel file: " + e.getMessage(), e);
        }
    }

    /**
     * Generates an Excel file for a list of Feuille Temps records.
     *
     * @param feuilleTempsList List of Feuille Temps records to be included in the Excel file.
     * @return A byte array containing the Excel file.
     */
    public static byte[] generateFeuilleTempsExcel(List<Feuille_Temps> feuilleTempsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Feuille Temps");

        // Create Header Row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Employee ID", "Date", "Work Time", "Task Description", "Start Time", "End Time", "Status", "Validated"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(createHeaderCellStyle(workbook));
        }

        // Date and Time format
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

        // Add data rows
// Add data rows
        int rowIndex = 1;
        for (Feuille_Temps feuilleTemps : feuilleTempsList) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(feuilleTemps.getEmployeeId());  // Employee ID
            row.createCell(1).setCellValue(feuilleTemps.getDate());  // Date
            row.createCell(2).setCellValue(String.valueOf(feuilleTemps.getTime()));  // Work Time
            row.createCell(3).setCellValue(feuilleTemps.getTaskDescription());  // Task Description

            // Start Time with null check
            if (feuilleTemps.getStartTime() != null) {
                row.createCell(4).setCellValue(feuilleTemps.getStartTime().toString());  // Start Time
            } else {
                row.createCell(4).setCellValue("");  // or some default value like "N/A"
            }

            // End Time with null check
            if (feuilleTemps.getEndTime() != null) {
                row.createCell(5).setCellValue(feuilleTemps.getEndTime().toString());  // End Time
            } else {
                row.createCell(5).setCellValue("");  // or some default value like "N/A"
            }

            row.createCell(6).setCellValue(feuilleTemps.getStatus().toString());  // Status
            row.createCell(7).setCellValue(feuilleTemps.isValidated() ? "Yes" : "No");  // Validated (boolean)
        }


        // Resize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }

    /**
     * Converts a String date to a formatted String using the given SimpleDateFormat.
     *
     * @param dateStr The date string to be converted.
     * @param dateFormat The date format to apply.
     * @return The formatted date string.
     * @throws ParseException If the date string cannot be parsed.
     */
    private static String formatDate(String dateStr, SimpleDateFormat dateFormat) throws ParseException {
        if (dateStr != null && !dateStr.isEmpty()) {
            // Parse the string to a Date object and format it
            return dateFormat.format(dateFormat.parse(dateStr));
        }
        return "Invalid Date";  // Return this if date is invalid or null
    }

    /**
     * Creates a header cell style with bold text.
     *
     * @param workbook The workbook where the style will be used.
     * @return A CellStyle with bold font.
     */
    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}

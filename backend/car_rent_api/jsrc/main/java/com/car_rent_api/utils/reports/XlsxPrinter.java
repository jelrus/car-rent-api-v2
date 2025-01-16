package com.car_rent_api.utils.reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class XlsxPrinter {

    private String sheetName;
    private List<String> headers;
    private Object[][] data;

    private XlsxPrinter() {
    }

    public static Builder builder() {
        return new XlsxPrinter().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder sheetName(String sheetName) {
            XlsxPrinter.this.sheetName = sheetName;
            return this;
        }

        public Builder headers(List<String> headers) {
            XlsxPrinter.this.headers = headers;
            return this;
        }

        public Builder data(List<Object> cells) {
            XlsxPrinter.this.data = new Object[cells.size()][headers.size()];

            try {
                for (int i = 0; i < cells.size(); i++) {
                    Field[] fields = cells.get(i).getClass().getDeclaredFields();
                    Object cell = cells.get(i);

                    for (int j = 0; j < fields.length; j++) {
                        fields[j].setAccessible(true);
                        data[i][j] = fields[j].get(cell);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            return this;
        }

        public byte[] toByteArray() {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet(sheetName);
                XSSFRow headerRow = sheet.createRow(0);
                String[] tableHeaders = headers.toArray(new String[0]);
                generateHeaderCells(headerRow, tableHeaders);
                generateDataCells(sheet, data);
                sheet.createTable(calculateTableArea(tableHeaders, workbook));
                workbook.write(byteArrayOutputStream);
                workbook.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Error writing data to XLSX");
            }
        }

        private void generateHeaderCells(XSSFRow headerRow, String[] headers) {
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
        }

        private void generateDataCells(XSSFSheet sheet, Object[][] data) {
            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    setDataCell(i, j, data, cell);
                }
            }
        }

        private AreaReference calculateTableArea(String[] tableHeaders, XSSFWorkbook workbook) {
            int rowCount = data.length + 1;
            int colCount = tableHeaders.length;

            return new AreaReference(
                    new CellReference(0, 0),
                    new CellReference(rowCount - 1, colCount - 1),
                    workbook.getSpreadsheetVersion()
            );
        }

        private void setDataCell(int v, int h, Object[][] data, Cell cell) {
            if (data[v][h] instanceof Double) {
                cell.setCellValue((Double) data[v][h]);
            } else if (data[v][h] instanceof Integer) {
                cell.setCellValue((Integer) data[v][h]);
            } else if (data[v][h] instanceof String) {
                cell.setCellValue((String) data[v][h]);
            }
        }
    }
}
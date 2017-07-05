package common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 读取excel公共utils
 * @author zhq  2017年7月3日
 */
public class ImportExcelUtils {

    /**
     * 读取Excel的内容，第一维数组存储的是多少行，二维数组存储的每一行是多少列。<br>
     * 兼容Excel 2003（后缀名：xls）及 2007（后缀名：xlsx）的文件，同时还支持读取csv格式的文件
     *
     * @param filePath
     *            文件完整路径
     * @param startRows
     *            读取数据忽略的行数，例：行头不需要读入，忽略的行数为1，那么将ignoreRows设为1即可
     * @return
     * @throws Exception
     */
    public static String[][] importExcelFile(String filePath, int startRows) throws IOException {

        /** 验证文件是否存在 */
        if (!validateFileExit(filePath)) {
            throw new IOException(filePath + "文件不存在");
        }

        /** 验证文件是否合法 */
        if (!validateExcel(filePath)) {
            throw new RuntimeException("不是Excel格式的文件");
        }
        List<String[]> result = new ArrayList<String[]>();
        Workbook workbook = null;
        InputStream inputStream = null;
        int rowSize = 0;
        try {
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            if (isExcel2003(filePath)) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                for (int rowIndex = startRows; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    String[] values = new String[rowSize];
                    Arrays.fill(values, "");
                    boolean hasValue = false;
                    for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                        String value = "";
                        Cell cell = row.getCell(columnIndex);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        if (date != null) {
                                            value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                        } else {
                                            value = "";
                                        }
                                    } else {
                                        value = new DecimalFormat("0").format(cell.getNumericCellValue());
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_FORMULA:
                                    // 导入时如果为公式生成的数据则无值
                                    if (!cell.getStringCellValue().equals("")) {
                                        value = cell.getStringCellValue();
                                    } else {
                                        value = cell.getNumericCellValue() + "";
                                    }
                                    break;
                                case HSSFCell.CELL_TYPE_BLANK:
                                    break;
                                case HSSFCell.CELL_TYPE_ERROR:
                                    value = "";
                                    break;
                                case HSSFCell.CELL_TYPE_BOOLEAN:
                                    value = (cell.getBooleanCellValue() == true ? "Y" : "N");
                                    break;
                                default:
                                    value = "";
                            }
                        }
                        if (columnIndex == 0 && value.trim().equals("")) {
                            break;
                        }
                        values[columnIndex] = rightTrim(value);
                        hasValue = true;
                    }
                    if (hasValue) {
                        result.add(values);
                    }
                }
            }
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    inputStream = null;
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    workbook = null;
                    e.printStackTrace();
                }
            }
        }
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * 验证excel文件
     *
     * @param filePath
     *            　文件完整路径
     * @return boolean
     */
    public static boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    /**
     * 检查文件是否存在，存在返回true，不存在返回false
     *
     * @param filePath
     * @return
     */
    public static boolean validateFileExit(String filePath) {
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是2003的excel，返回true是2003
     *
     * @param filePath
     *            文件完整路径
     * @return boolean
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel，返回true是2007
     *
     * @author Jiang <br>
     *         2016年9月19日
     * @param filePath
     *            文件完整路径
     * @return boolean
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param str
     *            要处理的字符串
     * @return 处理后的字符串
     */
    private static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String[][] result = importExcelFile("D:/excel/user_hospital.xlsx", 0);
        int rowLength = result.length;
        List list = new ArrayList();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < result[i].length; j++) {
                String[] obj = result[i];
                System.out.print(obj[j] + "\t\t");
            }
        System.out.println();
        }
    }

}

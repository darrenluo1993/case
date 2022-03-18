package pers.darren.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateWorkbook {

    public static void main(String[] args) {
        System.out.println(createWorkbook());
    }

    private static Workbook createWorkbook() {
        File file = new File("/home/darren/Downloads/华融闪贷合伙人推荐需求变更申请单.xlsx");

        try (FileInputStream fis = new FileInputStream(file)) {
            return new HSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("指定文件不存在！", e);
        } catch (IOException e) {
            throw new RuntimeException("文件处理出错，请重试或联系管理员！", e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            try (FileInputStream fis = new FileInputStream(file)) {
                return new XSSFWorkbook(fis);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException("指定文件不存在！", ex);
            } catch (IOException ex) {
                throw new RuntimeException("文件处理出错，请重试或联系管理员！", ex);
            } catch (Exception ex) {
                throw new RuntimeException("文件处理出错，请检查文件格式！", ex);
            }
        }
    }
}
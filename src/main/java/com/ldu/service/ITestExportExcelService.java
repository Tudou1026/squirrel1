package com.ldu.service;

import javax.servlet.ServletOutputStream;

public interface ITestExportExcelService {
    public void exportExcel(String [] titles, ServletOutputStream outputStream);
}

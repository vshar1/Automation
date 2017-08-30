package com;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class DirectoryUtilTest {
    private static String CURRENT_PRJ_DIRECTORY = System.getProperty("user.dir");
    private static String CURRENT_TEST_DIRECTORY = CURRENT_PRJ_DIRECTORY + "/resources";

    @Test
    public void testExtractFileDetailsForCSV() {
        DirectoryUtil directoryUtil = new DirectoryUtil();
        directoryUtil.populateFileDetails(CURRENT_TEST_DIRECTORY, SupportedFileFormat.CSV);
        Assert.assertTrue(directoryUtil.getFilesDetail().size() == 1);
        System.out.println("Total Number of files of CSV types =" + directoryUtil.getFilesDetail().size());
    }

    @Test
    public void testExtractFileDetailsForExcel() {
        DirectoryUtil directoryUtil = new DirectoryUtil();
        directoryUtil.populateFileDetails(CURRENT_TEST_DIRECTORY, SupportedFileFormat.Excel);
        Assert.assertTrue(directoryUtil.getFilesDetail().size() == 1);
        System.out.println("Total Number of files of XLS types =" + directoryUtil.getFilesDetail().size());
    }

    @Test
    public void testExtractFileDetailsForAny() {
        DirectoryUtil directoryUtil = new DirectoryUtil();
        directoryUtil.populateFileDetails(CURRENT_PRJ_DIRECTORY, SupportedFileFormat.ANY);
        Assert.assertTrue(directoryUtil.getFilesDetail().size() > 2);
        System.out.println("Total Number of files of any types =" + directoryUtil.getFilesDetail().size());
    }

    @Test
    public void testgetVehicleCSVData() throws IOException {
        DirectoryUtil directoryUtil = new DirectoryUtil();
        directoryUtil.populateFileDetails(CURRENT_PRJ_DIRECTORY, SupportedFileFormat.CSV);
        String csvfileName = ((FileDetail) directoryUtil.getFilesDetail().get(0)).getFileName();
        ArrayList<VehicleDetails> vehLst = directoryUtil.getVehicleCSVData(CURRENT_TEST_DIRECTORY + "/" + csvfileName);
        Assert.assertTrue(vehLst.size() == 2);

    }

}
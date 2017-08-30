package com;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This will scan configured directory in file system which will return fileDetails
 *
 * @see FileDetail
 */
public class DirectoryUtil {

    private ArrayList<FileDetail> filesDetail = new ArrayList<>();

    public void populateFileDetails(String path, SupportedFileFormat fileFormat) {
        File directory = new File(path);

        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile() && isSupportedFileType(file, fileFormat)) {
                populateFilesDetails(file);
            } else if (file.isDirectory()) {
                populateFileDetails(file.getAbsolutePath(), fileFormat);
            }
        }
    }

    private boolean isSupportedFileType(File file, SupportedFileFormat fileFormat) {
        return file.getName().endsWith(fileFormat.toString());
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private void populateFilesDetails(File file) {
        FileDetail fileDetail = new FileDetail();
        fileDetail.setFileName(file.getName());
        fileDetail.setSize(file.length());
        fileDetail.setMineType(new MimetypesFileTypeMap().getContentType(file));
        fileDetail.setFileExtension(getFileExtension(file));
        filesDetail.add(fileDetail);
    }

    public ArrayList<FileDetail> getFilesDetail() {
        return filesDetail;
    }

    public ArrayList<VehicleDetails> getVehicleCSVData(String fileName) throws IOException {
        ArrayList<VehicleDetails> vehList = new ArrayList<>();
        VehicleDetails vehicleDetails = null;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<List<String>> values = lines.skip(1).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
            for (List<String> line : values) {
                vehicleDetails = new VehicleDetails();
                vehicleDetails.setRegistration_number(line.get(0));
                vehicleDetails.setVehicle_make(line.get(1));
                vehicleDetails.setRegistration_date(line.get(2));
                vehicleDetails.setManufacturer_year(line.get(3));
                vehList.add(vehicleDetails);
            }
        }
        return vehList;
    }
}

package com.niyo;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * Handler for requests to Lambda function.
 */
public class App {

    AmazonS3 amazonS3 = AmazonS3Client.builder().withRegion(Regions.AP_SOUTHEAST_1).build();

    public void getInterestRate() throws IOException {
        ReadExcelDataFromS3();
    }

    private void ReadExcelDataFromS3() throws IOException {
        /*S3Object s3object = amazonS3.getObject("shweta35", "master-data/employee_occupation_data.json");
        S3ObjectInputStream inputStream = s3object.getObjectContent();*/

        S3Object fullObject = amazonS3.getObject(new GetObjectRequest("shweta35", "master-data/employee_occupation_data.json"));
        System.out.println("Downloading file done...!!");

        S3ObjectInputStream s3ObjectInputStream = fullObject.getObjectContent();

        XSSFWorkbook workbook = new XSSFWorkbook(s3ObjectInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

    }



}

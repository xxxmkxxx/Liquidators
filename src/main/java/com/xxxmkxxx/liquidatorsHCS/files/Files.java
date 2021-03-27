package com.xxxmkxxx.liquidatorsHCS.files;

import javafx.beans.property.IntegerProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Files {
    public static String pathToFile;
    public static Properties properties = new Properties();

    //Метод для записи содержимого файла в массив
    public List <String> readFileToArray(String path){
        File file = new File(path);
        ArrayList <String> listStr = new ArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while(bufferedReader.ready()){
                listStr.add(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listStr;
    }

    public static Properties connectProperties(String path) {
        pathToFile = path;

        try(FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static void safeChanges(String path) {
        try {
            properties.store(new FileOutputStream(path), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void safeChanges() {
        try {
            properties.store(new FileOutputStream(pathToFile), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
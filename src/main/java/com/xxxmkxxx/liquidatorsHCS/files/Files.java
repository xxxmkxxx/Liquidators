package com.xxxmkxxx.liquidatorsHCS.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Files {
    private String path;
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

    //Метод для записи информации в фаил
    public void writeInfoToFile(String path, List <String> info){
        File file = new File(path);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for(int i = 0; i < info.size(); i++){
                bufferedWriter.write(info.get(i));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Запись строки в файл с заменой старой строки
    public void writeLineToFile(String pathToFile, String line) {
        try(FileWriter fileWriter = new FileWriter(pathToFile, false)) {
            fileWriter.write(line);
            fileWriter.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Properties connectProperties(String path) {
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
}
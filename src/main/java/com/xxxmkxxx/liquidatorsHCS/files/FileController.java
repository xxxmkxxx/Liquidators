package com.xxxmkxxx.liquidatorsHCS.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {
    public static String pathToFile;

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
}
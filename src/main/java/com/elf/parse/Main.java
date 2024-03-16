package com.elf.parse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class Main {

    public static class Person {
        private String name;
    }



    public static void main(String[] args) {

        byte[] bytes = readFile("/Users/juneleo/Desktop/libcall.so");

        ElfHeaderParse elfHeaderParse = new ElfHeaderParse();
        elfHeaderParse.parse(0, bytes);

    }
    //122592

    private static byte[] readFile(String path) {
        try {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileInputStream fileInputStream = new FileInputStream(path);
            int length = 0;
            while ((length = fileInputStream.read(bytes)) > 0) {
                byteArrayOutputStream.write(bytes, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
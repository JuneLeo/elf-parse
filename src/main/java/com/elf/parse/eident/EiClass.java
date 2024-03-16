package com.elf.parse.eident;

public enum EiClass {
    ELFCLASSNONE,
    ELFCLASS32,
    ELFCLASS64,
    ELFCLASSNUM;


    public static EiClass getEiClass(int value) {
        switch (value) {
            case 0:
                return ELFCLASSNONE;
            case 1:
                return ELFCLASS32;
            case 2:
                return ELFCLASS64;
            case 3:
                return ELFCLASSNUM;
        }
        return null;
    }
}

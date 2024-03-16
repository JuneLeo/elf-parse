package com.elf.parse.eident;

public enum EiData {
    ELFDATANONE,
    ELFDATA2LSB,
    ELFDATA2MSB,
    ELFDATANUM;


    public static EiData getEiData(int value) {
        switch (value) {
            case 0:
                return ELFDATANONE;
            case 1:
                return ELFDATA2LSB;
            case 2:
                return ELFDATA2MSB;
            case 3:
                return ELFDATANUM;
        }
        return null;
    }
}

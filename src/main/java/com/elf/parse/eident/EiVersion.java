package com.elf.parse.eident;

public enum EiVersion {
    E_NONE,
    E_CURRENT,
    E_NUM;

    public static EiVersion getEiVersion(int value) {
        switch (value) {
            case 0:
                return E_NONE;
            case 1:
                return E_CURRENT;
            case 2:
                return E_NUM;

        }
        return null;
    }
}

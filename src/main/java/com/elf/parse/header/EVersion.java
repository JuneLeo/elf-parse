package com.elf.parse.header;

public enum EVersion {
    EV_NONE, // 非法版本
    EV_CURRENT; // 当前版本


    public static EVersion getEVersion(int value) {
        switch (value) {
            case 0x0:
                return EV_NONE;
            case 0x1:
                return EV_CURRENT;
        }

        return null;
    }
}

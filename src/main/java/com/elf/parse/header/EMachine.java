package com.elf.parse.header;

public enum EMachine {
    EM_AARCH64;

    public static EMachine getEmachine(int value) {
        switch (value) {
            case 183:
                return EM_AARCH64;
        }
        return null;
    }
}

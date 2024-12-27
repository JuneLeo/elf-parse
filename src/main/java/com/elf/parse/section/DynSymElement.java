package com.elf.parse.section;

public class DynSymElement {
    public int symNameOff;//4

    public String name;

    public SymInfo symInfo; // 1

    public int symOther; //1

    public int symShndx;//2

    public long symValue;//8

    public long symSize;//8


    @Override
    public String toString() {
        return "DynSymElement{" +
                "symNameOff=" + symNameOff +
                ", name='" + name + '\'' +
                ", symInfo=" + symInfo +
                ", symOther=" + symOther +
                ", symShndx=" + symShndx +
                ", symValue=" + symValue +
                ", symSize=" + symSize +
                '}';
    }
}

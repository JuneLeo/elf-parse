package com.elf.parse.program;

public class SegmentElement {
    public PHType pType;
    public PHFlag pFlags;

    public long pOffset;

    public long pVirtualAddr;

    public long pPhysicalAddr;

    public long pFiles;

    public long pMems;

    public long pAlign;

    @Override
    public String toString() {
        return "SegmentElement{" +
                "pType=" + pType.name() +
                ", pFlags=" + pFlags.name() +
                ", pOffset=" + pOffset +
                ", pVirtualAddr=" + pVirtualAddr +
                ", pPhysicalAddr=" + pPhysicalAddr +
                ", pFiles=" + pFiles +
                ", pMems=" + pMems +
                ", pAlign=" + pAlign +
                '}';
    }
}

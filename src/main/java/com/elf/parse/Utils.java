package com.elf.parse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public final class Utils {

    public static int eiData = 1;

    public static int getU1Int(int start, byte[] bytes) {
        byte[] versionCode = new byte[1];
        System.arraycopy(bytes, start, versionCode, 0, 1);
        return Byte.toUnsignedInt(versionCode[0]);
    }

    public static int getU2Int(int start, byte[] bytes) {
        byte[] versionCode = new byte[2];
        System.arraycopy(bytes, start, versionCode, 0, 2);
        int high;
        int low;
        //04 00
        // 0004
        // 0400
        if (eiData == 1) {
            low = Byte.toUnsignedInt(versionCode[0]);
            high = Byte.toUnsignedInt(versionCode[1]);
        } else {
            // 04 00
            // 0400

            high = Byte.toUnsignedInt(versionCode[0]);
            low = Byte.toUnsignedInt(versionCode[1]);
        }
        return high << 8 | low;
    }

    public static int getU4Int(int start, byte[] bytes) {
        int length = 4;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        if (eiData == 1) {
            return buffer.order(ByteOrder.LITTLE_ENDIAN).getInt();
        }
        return buffer.order(ByteOrder.BIG_ENDIAN).getInt();
    }


    public static long getU8Long(int start, byte[] bytes) {
        int length = 8;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        if (eiData == 1) {
            return buffer.order(ByteOrder.LITTLE_ENDIAN).getLong();
        }
        return buffer.order(ByteOrder.BIG_ENDIAN).getLong();
    }


    public static float getU4Float(int start, byte[] bytes) {
        int length = 4;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        if (eiData == 1) {
            return buffer.order(ByteOrder.LITTLE_ENDIAN).getFloat();
        }
        return buffer.order(ByteOrder.BIG_ENDIAN).getFloat();
    }


    public static double getU8Double(int start, byte[] bytes) {
        int length = 8;
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.put(bytes, start, length);
        buffer.flip();
        if (eiData == 1) {
            return buffer.order(ByteOrder.LITTLE_ENDIAN).getDouble();
        }
        return buffer.order(ByteOrder.BIG_ENDIAN).getDouble();
    }


    public static String getString(int start, byte[] bytes) {
        int end = start;
        byte b = bytes[end];
        while (b != 0 && b < bytes.length) {
            end++;
            b = bytes[end];
        }
        int length = end - start;
        if (length == 0) {
            return null;
        }
        byte[] strBytes = new byte[length];
        System.arraycopy(bytes, start, strBytes, 0, length);
        return new String(strBytes, StandardCharsets.ISO_8859_1);
    }

    public static void log(String tag, String msg) {
        System.out.println(tag + " : " + msg);
    }

    public static void log(String msg) {
        log("elf-parse", msg);
    }
}

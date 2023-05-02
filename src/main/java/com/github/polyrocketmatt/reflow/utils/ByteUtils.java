package com.github.polyrocketmatt.reflow.utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class ByteUtils {

    public static ClassNode parseBytesToClassNode(byte[] bytes) {
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();

        reader.accept(node, 0);

        return node;
    }

    public static byte[] parseClassNodeToBytes(ClassNode node) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        node.accept(writer);

        return writer.toByteArray();
    }

    public static String byteArrayToString(byte[] bytes) {
        return new String(bytes);
    }

}
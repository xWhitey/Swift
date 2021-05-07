package team.swift.javassist.transformer;

import team.swift.javassist.transformer.exceptions.PatcherException;
import team.swift.javassist.util.ClassData;

public abstract class Transformer {

    public abstract String getTransformingClass();
    public abstract byte[] transform(final ClassData classData) throws PatcherException;

    public String getJVMTransformingClass() {
        return getTransformingClass().replace(".", "/");
    }
}

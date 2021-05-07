package team.swift.javassist.transformer.impl;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import team.swift.javassist.transformer.Transformer;
import team.swift.javassist.transformer.exceptions.PatcherException;
import team.swift.javassist.util.ClassData;

import java.io.IOException;

public class DisplayClassTransformer extends Transformer {

    @Override
    public String getTransformingClass() {
        return "org.lwjgl.opengl.Display";
    }

    @Override
    public byte[] transform(ClassData classData) throws PatcherException {
        try {
            CtClass display = ClassPool.getDefault().get(getTransformingClass());

            display.getDeclaredMethod("setTitle").setBody("{ " +
                    "title = \"Swift | Minecraft 1.12.2 (~katch & xWhitey) \";\n" +
                    "if (isCreated()) display_impl.setTitle(title);\n" +
                    "}");

            return display.toBytecode();
        } catch(NotFoundException | CannotCompileException | IOException ex) {
            throw new PatcherException(ex);
        }
    }
}

package team.swift.javassist;

import team.swift.javassist.transformer.Transformer;
import team.swift.javassist.transformer.impl.DisplayClassTransformer;
import team.swift.javassist.transformer.impl.NetworkManagerClassTransformer;
import team.swift.javassist.util.ClassData;
import team.swift.javassist.util.Logger;

import javax.swing.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.ArrayList;

public class AgentClassTransformer implements ClassFileTransformer {

    public static AgentClassTransformer INSTANCE = new AgentClassTransformer();

    private final ArrayList<Transformer> transformers = new ArrayList<>();

    public AgentClassTransformer() {
        transformers.add(new NetworkManagerClassTransformer());
        transformers.add(new DisplayClassTransformer());
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        final ClassData classData = new ClassData(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);

        for (Transformer transformer : transformers) {
            if (transformer.getJVMTransformingClass().equals(className)) {
                Logger.info("Applying transformations on " + className);

                try {
                    return transformer.transform(classData);
                } catch(Throwable t) {
                    Logger.error("Failed to patch " + className);
                    t.printStackTrace();

                    JOptionPane.showMessageDialog(null, "Failed to patch " + className + "\nThe error was: " + t.getCause().getClass().getName(), "Serious error", JOptionPane.ERROR_MESSAGE);
                    Runtime.getRuntime().halt(-2);
                }
            }
        }

        return classfileBuffer;
    }
}

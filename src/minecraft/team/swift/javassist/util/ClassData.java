package team.swift.javassist.util;

import java.security.ProtectionDomain;

public class ClassData {

    private final ClassLoader classLoader;
    private final String className;
    private final Class<?> classBeingRedefined;
    private final ProtectionDomain protectionDomain;
    private final byte[] classfileBuffer;

    public ClassData(ClassLoader classLoader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        this.classLoader = classLoader;
        this.className = className;
        this.classBeingRedefined = classBeingRedefined;
        this.protectionDomain = protectionDomain;
        this.classfileBuffer = classfileBuffer;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public String getClassName() {
        return className;
    }

    public Class<?> getClassBeingRedefined() {
        return classBeingRedefined;
    }

    public ProtectionDomain getProtectionDomain() {
        return protectionDomain;
    }

    public byte[] getClassfileBuffer() {
        return classfileBuffer;
    }


}

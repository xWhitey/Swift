package team.swift.javassist.transformer.impl;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import team.swift.javassist.transformer.Transformer;
import team.swift.javassist.transformer.exceptions.PatcherException;
import team.swift.javassist.util.ClassData;
import team.swift.javassist.util.Logger;

import java.lang.reflect.Method;

public class NetworkManagerClassTransformer extends Transformer {
    @Override
    public String getTransformingClass() {
        return "gw"; // net.minecraft.network.NetworkManager
    }

    @Override
    public byte[] transform(final ClassData classData) throws PatcherException {
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass networkManager = ClassPool.getDefault().get(getTransformingClass());

            Logger.info(networkManager.getDeclaredMethods());
            Logger.info(networkManager.getDeclaredConstructors());

            return networkManager.toBytecode();
        } catch (Throwable ex) {
            if (!ex.getMessage().contains("frozen"))
                throw new PatcherException(ex);

            return classData.getClassfileBuffer();
        }
    }
}

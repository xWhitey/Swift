package team.swift.javassist.transformer.exceptions;

public class PatcherException extends RuntimeException {

    public PatcherException(String cause) {
        super(cause);
    }

    public PatcherException(Throwable cause) {
        super(cause);
    }
}

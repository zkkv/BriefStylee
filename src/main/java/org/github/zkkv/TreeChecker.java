package org.github.zkkv;

import spoon.Launcher;
import spoon.reflect.CtModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public abstract class TreeChecker {
    private final Launcher launcher;

    public TreeChecker() {
        launcher = new Launcher();
    }

    protected String convertPath(String filePath) throws FileNotFoundException {
        if (filePath == null) {
            throw new FileNotFoundException();
        }

        String absolute = FileSystems.getDefault().getPath(filePath).normalize().toAbsolutePath().toString();

        if (!(new File(absolute).isFile())) {
            throw new FileNotFoundException();
        }
        return absolute;
    }

    protected CtModel getModel(String filePath) {
        launcher.addInputResource(filePath);
        launcher.buildModel();
        return launcher.getModel();
    }
}

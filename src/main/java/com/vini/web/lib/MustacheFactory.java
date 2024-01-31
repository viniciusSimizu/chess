package com.vini.web.lib;

import com.github.mustachejava.DefaultMustacheFactory;
import com.vini.web.routes.controller.PublicFileHandler;

import java.io.File;

public class MustacheFactory {
    private static DefaultMustacheFactory INSTANCE;
    private static final String TEMPLATES_PATH = "/src/main/resources/templates";

    private MustacheFactory() {}
    ;

    public static DefaultMustacheFactory getInstance() {
        if (MustacheFactory.INSTANCE == null) {
            String path = PublicFileHandler.WORKSPACE_DIRECTORY + MustacheFactory.TEMPLATES_PATH;
            File file = new File(path);
            MustacheFactory.INSTANCE = new DefaultMustacheFactory(file);
        }
        return MustacheFactory.INSTANCE;
    }
}

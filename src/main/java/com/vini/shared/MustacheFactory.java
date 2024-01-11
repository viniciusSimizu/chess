package com.vini.shared;

import com.github.mustachejava.DefaultMustacheFactory;
import java.io.File;

public class MustacheFactory {
  private static DefaultMustacheFactory INSTANCE;

  private MustacheFactory(){};

  public static DefaultMustacheFactory getInstance() {
    if (MustacheFactory.INSTANCE == null) {
      String WORKSPACE_DIRECTORY = System.getProperty("user.dir");
      String TEMPLATE_ROOT_DIRECTORY = "/assets/templates";
      File path = new File(WORKSPACE_DIRECTORY + TEMPLATE_ROOT_DIRECTORY);
      MustacheFactory.INSTANCE = new DefaultMustacheFactory(path);
    }
    return MustacheFactory.INSTANCE;
  }
}

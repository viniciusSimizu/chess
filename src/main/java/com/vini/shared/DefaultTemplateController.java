package com.vini.shared;

import com.github.mustachejava.DefaultMustacheFactory;
import com.sun.net.httpserver.HttpHandler;

public abstract class DefaultTemplateController implements HttpHandler {
  protected final DefaultMustacheFactory mustacheFactory;

  protected DefaultTemplateController() {
    this.mustacheFactory = MustacheFactory.getInstance();
  }
}

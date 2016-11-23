package com.github.bmsantos.isomorphic.app.views;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.bmsantos.isomorphic.app.js.JSLoader;
import io.vertx.rxjava.core.Future;

import static java.util.Objects.nonNull;

public enum RenderView {
  renderView;

  public Future<String> render(final JSLoader jsLoader, final String view, final Map<String, Object> model) {
    Future<String> future = Future.future();

    InputStream in = this.getClass().getClassLoader().getResourceAsStream("webroot/templates/" + view + ".ejs");
    if (nonNull(in)) {
      try (BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
        String template = buffer.lines().collect(Collectors.joining("\n"));
        final Object result = jsLoader.invokeFunction("render", template, model);
        future.complete((String)result);
      } catch (final Exception e) {
       future.fail(e);
      }
    }

    return future;
  }

}

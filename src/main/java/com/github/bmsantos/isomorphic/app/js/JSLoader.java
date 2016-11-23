package com.github.bmsantos.isomorphic.app.js;

import javax.script.ScriptException;

import static java.util.Arrays.asList;

public interface JSLoader {

  static final String[] scripts = {
    "classpath:webroot/static/js/polyfill.js",
    "classpath:webroot/static/js/ejs.min.js",
    "classpath:webroot/static/js/render.js",
    "classpath:server.min.js"
  };

  static JSLoader create() {
    final JSLoaderImpl jsLoader = new JSLoaderImpl();
    asList(scripts).forEach(s -> jsLoader.load(s));
    return jsLoader;
  }

  JSLoader load(String... script);

  Object invokeFunction(String function, Object... args);

  Object invokeMethod(Object thiz, String method, Object... args);

  Object getReference(String reference);

  JSLoader put(String key, Object obj);

  Object eval(String script) throws ScriptException;
}

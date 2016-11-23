package com.github.bmsantos.isomorphic.app.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSLoaderImpl implements JSLoader {

  private final ScriptEngineManager manager = new ScriptEngineManager();
  private final ScriptEngine engine = manager.getEngineByName("nashorn");

  @Override
  public JSLoader load(String... script) {
    try {
      ((Invocable) engine).invokeFunction("load", script);
    } catch (ScriptException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  @Override
  public Object invokeFunction(String function, Object... args) {
    try {
      return ((Invocable) engine).invokeFunction(function, args);
    } catch (ScriptException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Object invokeMethod(Object thiz, String method, Object... args) {
    try {
      return ((Invocable) engine).invokeMethod(thiz, method, args);
    } catch (ScriptException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Object getReference(String ref) {
    return engine.get(ref);
  }

  @Override
  public JSLoader put(final String key, final Object obj) {
    engine.put(key, obj);
    return this;
  }

  @Override
  public Object eval(final String script) throws ScriptException {
    return engine.eval(script);
  }

}

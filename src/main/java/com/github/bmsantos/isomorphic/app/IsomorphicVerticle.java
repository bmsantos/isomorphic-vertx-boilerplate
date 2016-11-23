package com.github.bmsantos.isomorphic.app;

import java.util.LinkedHashMap;
import java.util.Map;

import com.github.bmsantos.isomorphic.app.js.JSLoader;
import com.github.bmsantos.isomorphic.app.model.Person;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.StaticHandler;

import static com.github.bmsantos.isomorphic.app.views.RenderView.renderView;
import static io.vertx.core.logging.LoggerFactory.getLogger;

public class IsomorphicVerticle extends AbstractVerticle {

  private static final Logger log = getLogger(IsomorphicVerticle.class);

  private JSLoader jsLoader;

  private static final Person[] PERSON_LIST = {
    new Person("Abraham", "Lincoln", 207),
    new Person("Thomas", "Jefferson", 273),
    new Person("George", "Washington", 284)
  };

  @Override
  public void start(final Future<Void> startFuture) throws Exception {
    final Router router = Router.router(vertx);

    jsLoader = JSLoader.create();

    router.getWithRegex("/|/app.*").handler(this::app);

    router.route().handler(StaticHandler.create());

    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(8080);
  }

  private void app(final RoutingContext ctx) {
    final Map model = new LinkedHashMap<String, Object>();
    model.put("requestPath", ctx.request().path());
    model.put("personList", PERSON_LIST);

    ctx.response().putHeader("content-type", "text/html");

    renderView.render(jsLoader, "react", model).setHandler(rawResultHandler(ctx));
  }

  private <T> Handler<AsyncResult<T>> rawResultHandler(RoutingContext context) {
    return ar -> {
      if (ar.succeeded()) {
        T res = ar.result();
        context.response()
          .end(res == null ? "" : res.toString());
      } else {
        internalError(context, ar.cause());
        ar.cause().printStackTrace();
      }
    };
  }

  private void internalError(RoutingContext context, Throwable ex) {
    context.response().setStatusCode(500)
      .putHeader("content-type", "application/json")
      .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
  }


}

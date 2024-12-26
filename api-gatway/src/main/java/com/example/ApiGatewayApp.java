//package com.example;
//
//import io.vertx.core.Vertx;
//import io.vertx.core.http.HttpClient;
//import io.vertx.ext.web.Router;
//import io.quarkus.runtime.StartupEvent;
//import io.vertx.ext.web.RoutingContext;
//import jakarta.enterprise.event.Observes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class ApiGatewayApp {
//
//    Logger logger = LoggerFactory.getLogger(ApiGatewayApp.class);
//    private HttpClient httpClient;
//
//    public void init(@Observes StartupEvent startupEvent, Vertx vertx) {
//        // Initialize the Vert.x HTTP client
//        this.httpClient = vertx.createHttpClient();
//
//        // Set up routing
//        Router router = Router.router(vertx);
//
//        // Forward product requests
//        router.route("/san-pham/*").handler(ctx -> forwardRequest(ctx, "http://localhost:9002"));
//
//        // Forward order requests
//        router.route("/api/orders/*").handler(ctx -> forwardRequest(ctx, "http://localhost:8082"));
//
//        // Forward user requests
//        router.route("/api/users/*").handler(ctx -> forwardRequest(ctx, "http://localhost:8083"));
//
//        // Start the HTTP server
//        vertx.createHttpServer()
//                .requestHandler(router)
//                .listen(9009, result -> {
//                    if (result.succeeded()) {
//                        System.out.println("API Gateway running at http://localhost:8080");
//                    } else {
//                        System.err.println("Failed to start API Gateway: " + result.cause().getMessage());
//                    }
//                });
//    }
//
//    private void forwardRequest(RoutingContext context, String targetUrl) {
//        String requestPath = context.request().path().replace("/api", ""); // Adjust path if needed
//        String targetEndpoint = targetUrl + requestPath;
//
//        httpClient.request(context.request().method(), targetEndpoint)
//                .compose(request -> {
//                    // Forward headers
//                    context.request().headers().forEach(header -> request.putHeader(header.getKey(), header.getValue()));
//
//                    // Send the body
//                    return request.send(context.getBody());
//                })
//                .compose(response -> {
//                    context.response()
//                            .setStatusCode(response.statusCode())
//                            .headers().setAll(response.headers());
//                    return response.body();
//                })
//                .onSuccess(body -> context.response().end(body))
//                .onFailure(err -> {
//                    context.response().setStatusCode(500).end("Failed to proxy request: " + err.getMessage());
//                });
//
//    }
//}
//

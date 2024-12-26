//package com.example;
//
//import io.vertx.core.buffer.Buffer;
//import io.vertx.core.http.HttpClient;
//import io.vertx.core.http.HttpClientOptions;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.ws.rs.core.Application;
//
//@ApplicationScoped
//public class APIGateway extends Application {
//
//    private final HttpClient httpClient;
//    // Set up routing
//
//    public APIGateway() {
//        // Create an HTTP client to forward requests
//        httpClient = io.vertx.core.Vertx.vertx().createHttpClient(new HttpClientOptions());
//    }
//
//    public void setupRouter(Router router) {
//        // Proxy requests to ProductService
//        router.route("/api/products/*").handler(ctx -> proxyRequest(ctx, "http://localhost:9002"));
//
//        // Proxy requests to OrderService
//        router.route("/api/orders/*").handler(ctx -> proxyRequest(ctx, "http://localhost:8082"));
//
//        // Proxy requests to UserService
//        router.route("/api/users/*").handler(ctx -> proxyRequest(ctx, "http://localhost:8083"));
//    }
//
//    private void proxyRequest(RoutingContext context, String targetService) {
//        String requestPath = context.normalizedPath();
//        String targetUrl = targetService + requestPath;
//
//        httpClient.request(context.request().method(), targetUrl)
//                .compose(req -> req.send(context.getBody()))
//                .compose(response -> context.response().setStatusCode(response.statusCode()).end((Buffer) response.body()))
//                .onFailure(err -> context.response().setStatusCode(500).end(err.getMessage()));
//    }
//}

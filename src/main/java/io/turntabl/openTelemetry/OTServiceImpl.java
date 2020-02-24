//package io.turntabl.openTelemetry;
//
//import io.opentelemetry.context.Scope;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.trace.Span;
//import io.opentelemetry.trace.Tracer;
//
//
//public class OTServiceImpl {
//    public String getAllProjects(Span span) {
//        Tracer tracer = OpenTelemetrySdk.getTracerFactory().get("OTController");
//
//        Scope scope = tracer.withSpan(span);
//
//        Span childSpan = tracer.spanBuilder("service get all project").startSpan();
//        childSpan.setAttribute("operation.id", 9);
//        childSpan.addEvent("operation.request_started");
//
//        childSpan.end();
//        return "Get all available projects :)";
//    }
//}

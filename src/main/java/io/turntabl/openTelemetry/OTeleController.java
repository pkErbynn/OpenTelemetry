package io.turntabl.openTelemetry;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;
import io.opentelemetry.context.Scope;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.exporters.logging.LoggingExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class OTeleController {

    @CrossOrigin
    @ApiOperation("with openTele")
    @GetMapping(value = "v1/api/ot")
    public String withOpenTele(){
        SpanProcessor spanProcessor = SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
        .setServiceName("OpenTeleService")
        .setChannel(ManagedChannelBuilder.forAddress("localhost", 14250)
        .usePlaintext().build())
                .build()).build();
        OpenTelemetrySdk.getTracerFactory().addSpanProcessor(spanProcessor);
        Tracer tracer = OpenTelemetrySdk.getTracerFactory().get("otelExample");

        // operation or span name in jaegar
        Span span = tracer.spanBuilder("OTSpan::1").startSpan();
        // tags -> for filtering....setAttribute == setTag in OpenTracing
        span.setAttribute("operation.id", 7);
        span.setAttribute("project.id", 7);
        span.setAttribute("ErbynnKeyTest", "someOperation");
        span.setAttribute("span.request.method", "GET");
        // logs in jaeger
        span.addEvent("operation.request_started");

        try (Scope scope = tracer.withSpan(span))
        {
            Span childSpan = tracer.spanBuilder("OTSpan::2").startSpan();
            childSpan.setAttribute("operation.id", 9);
            childSpan.addEvent("operation.request_started");

            try (Scope childScope = tracer.withSpan(childSpan)) {
            } finally {
                childSpan.end();
            }

        } finally {
            span.end();
        }

        spanProcessor.shutdown();

        return "Hello from opTele endpoint";
    }
}
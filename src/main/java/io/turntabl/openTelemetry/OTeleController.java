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
    @ApiOperation("get all projects")
    @GetMapping(value = "v1/api/project")
    public String getProject(){
       // Configure the JaegerExporter as our exporter.
        SpanProcessor spanProcessor = SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
        .setServiceName("ProjectService")
        .setChannel(ManagedChannelBuilder.forAddress("localhost", 14250)
        .usePlaintext().build())
                .build()).build();
        OpenTelemetrySdk.getTracerFactory().addSpanProcessor(spanProcessor);

        // adding span
        Tracer tracer = OpenTelemetrySdk.getTracerFactory().get("OTController");
        Span span = tracer.spanBuilder("Get All Project").startSpan();
        // tags -> for filtering....setAttribute == setTag in OpenTracing
        span.setAttribute("operation.id", 7);
        span.setAttribute("project.id", 5);
        span.setAttribute("span.request.method", "GET");
        span.setAttribute("ErbynAttribute", "someOperation");
        // shows as logs in jaeger..specific messages emitted by a process or service.
        span.addEvent("operation.request_started");
        span.addEvent("operation.request_complete");
        span.end();
        spanProcessor.shutdown();
        return "All projects :)";
    }
}
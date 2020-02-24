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
import io.opentelemetry.sdk.trace.TracerSdk;
import io.opentelemetry.sdk.trace.TracerSdkFactory;
import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class OTeleController {

    @Autowired
    OTConfig otConfig;

    @CrossOrigin
    @ApiOperation("get all projects")
    @GetMapping(value = "api/v1/project")
    public String getProject(){
        Tracer tracer = OTConfig.observabilityConfig();

        // adding span
        Span span = tracer.spanBuilder("Get All Project").startSpan();
        // tags -> for filtering....setAttribute == setTag in OpenTracing
        span.setAttribute("operation.id", 7);
        span.setAttribute("project.id", 5);
        span.setAttribute("ErbynAttribute", "someOperation");
        // shows as logs in jaeger..specific messages emitted by a process or service.
        span.addEvent("operation.request_started");
        span.addEvent("operation.request_complete");
        span.end();
//        spanProcessor.shutdown();

        return "All projects :)";
    }
}
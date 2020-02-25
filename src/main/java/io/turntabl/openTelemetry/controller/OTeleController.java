package io.turntabl.openTelemetry.controller;

import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.turntabl.openTelemetry.config.OTConfig;
import io.turntabl.openTelemetry.serviceImpl.OTeleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class OTeleController {
    SpanProcessor spanProcessor = OTConfig.spanConfig();
    Tracer tracer = OTConfig.observabilityConfig(spanProcessor);

    @Autowired
    OTeleServiceImpl oTeleService;

    @GetMapping("api/v1/ott")
    public String getOpenTelemetry() {
        Span span = tracer.spanBuilder("ooop").startSpan();
        span.setAttribute("operation.id", 111);
        span.addEvent("operation.111");

        try (Scope scope = tracer.withSpan(span))
        {
            return oTeleService.getOpenTelemetry(tracer);
        } finally {
            span.end();
            spanProcessor.shutdown();
        }
    }

    @GetMapping("api/v1/noot")
    public String noOpenTelemetry() {
        return "NO tracing :)";
    }
}
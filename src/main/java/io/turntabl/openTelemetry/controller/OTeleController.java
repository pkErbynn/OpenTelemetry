package io.turntabl.openTelemetry.controller;

import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import io.turntabl.openTelemetry.config.OTConfig;
import io.turntabl.openTelemetry.serviceImpl.OTeleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class OTeleController {

    @Autowired
    Tracer tracer;

    @Autowired
    OTeleServiceImpl oTeleService;

    @GetMapping("api/v1/ot")
    public String getOpenTracing() {
        Span span = tracer.buildSpan("get-mytracing").start();

        span.log("myLogs");
        span.setTag("myTagKey", 23);

        span.finish();
        return oTeleService.getOpenTelemetry();
    }

    @GetMapping("api/v1/noot")
    public String noOpenTelemetry() {
        return "NO tracing :)";
    }
}
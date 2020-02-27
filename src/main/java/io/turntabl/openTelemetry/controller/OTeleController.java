package io.turntabl.openTelemetry.controller;

import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.trace.SpanProcessor;
//import io.opentracing.Span;
//import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.log.Fields;
import io.opentracing.tag.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import io.turntabl.openTelemetry.config.OTConfig;
import io.turntabl.openTelemetry.serviceImpl.OTeleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api
@RestController
public class OTeleController {

    @Autowired
    Tracer tracer;

    @Autowired
    OTeleServiceImpl oTeleService;

    @GetMapping("api/v1/ot")
    public String getOpenTracing() {
        Span span = tracer.buildSpan("someWork").start();
        try (Scope ignored = (Scope) tracer.scopeManager().activate(span)) {
            // Do things.
        } catch(Exception ex) {
//            Tags.ERROR.set(span, true);
//            span.log("....");
        } finally {
            span.finish();
        }
        return oTeleService.getOpenTelemetry();}

    @GetMapping("api/v1/noot")
    public String noOpenTelemetry() {
        return "NO tracing :)";
    }
}
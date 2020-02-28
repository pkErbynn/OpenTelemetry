package io.turntabl.openTelemetry.controller;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;

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
        Span span = tracer.buildSpan("ContinuousDownstreamTrace").start();
        span.setTag("get_project_id", 11);
        System.out.println(">>>>>>>>> doing some computation here >>>>>>>>>>>>>");

        return oTeleService.getOpenTelemetry(span);
    }


    @GetMapping("api/v1/nod")
    public String noOpenTelemetry() {
        Span span = tracer.buildSpan("DiscontinuousDownstreamTrace").start();
        span.setTag("get_project_id", 11);
        System.out.println("???????? Do some work ?????????????");
        span.finish();

        return "Hey! from discontinuous downstream trace :)";
    }
}
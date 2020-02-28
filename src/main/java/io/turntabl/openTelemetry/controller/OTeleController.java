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
//        Span span = tracer.buildSpan("getOpenTracing").start();
//        span.setTag("get_project_id", 11);
//
//        Span ChildSpan1 = tracer.buildSpan("getOpenTracingChild-1").asChildOf(span).start();
//        ChildSpan1.setTag("get_child_project_id", 11.1);
//        ChildSpan1.finish();
//
//        Span ChildSpan2 = tracer.buildSpan("getOpenTracingChild-2").asChildOf(ChildSpan1).start();
//        ChildSpan2.setTag("get_child_project_id", 11.12);
//        ChildSpan2.finish();
//
//        Span fSpan = tracer.buildSpan("getOpenTracingChild-fSpan").asChildOf(ChildSpan2).start();
//        fSpan.setTag("get_child_project_id", 11.2);
//        fSpan.finish();
//
//        span.finish();
//        --------------------------------------

        Span span = tracer.buildSpan("HandleHTTPRequest").start();
        span.setTag("get_project_id", 11);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");

        try(Scope scope = tracer.activateSpan(span)){
            Span ChildSpan1 = tracer.buildSpan("ChildOfHttp").start();
            ChildSpan1.setTag("get_child_project_id", 11.1);
            System.out.println("..................");

            try(Scope scopeCild = tracer.activateSpan(ChildSpan1)){
                Span ChildSpan2 = tracer.buildSpan("ChildOfHttp").start();
                ChildSpan2.setTag("get_child_project_id", 11.1);
                System.out.println("?????????????????????");
                ChildSpan2.finish();
            }

            ChildSpan1.finish();
        }
        span.finish();
        return oTeleService.getOpenTelemetry();}

    @GetMapping("api/v1/noot")
    public String noOpenTelemetry() {
        return "NO tracing :)";
    }
}
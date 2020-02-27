package io.turntabl.openTelemetry.serviceImpl;

import io.opentelemetry.trace.Span;

import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;


public class OTeleServiceImpl {

    @Autowired
    private Tracer tracer;

    public String getOpenTelemetry(){
//        tracer.activeSpan().setTag("project.id", 1);

        return "Hey from OpenTelemetry :)";
    }
}

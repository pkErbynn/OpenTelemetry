package io.turntabl.openTelemetry.serviceImpl;

import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.Tracer;


public class OTeleServiceImpl {
    public String getOpenTelemetry(Tracer tracer){
        Span childSpan = tracer.spanBuilder("teeee").startSpan();
        childSpan.setAttribute("operation.id", 222);
        childSpan.addEvent("operation.222");
        childSpan.end();
        return "Hey from OpenTelemetry :)";
    }
}

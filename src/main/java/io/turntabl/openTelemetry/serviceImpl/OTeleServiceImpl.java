package io.turntabl.openTelemetry.serviceImpl;

import com.google.common.collect.ImmutableMap;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.springframework.beans.factory.annotation.Autowired;


public class OTeleServiceImpl {

    @Autowired
    private Tracer tracer;

    public String getOpenTelemetry(Span incomingSpan){
        Span ChildSpan1 = null;
        try (Scope scope = tracer.activateSpan(incomingSpan)) {
            ChildSpan1 = tracer.buildSpan("ChildOfHttp").start();
            ChildSpan1.setTag("get_child_project_id", 11.1);
            System.out.println("..................");
            return "Hey from OpenTelemetry :)";

        } finally {
            ChildSpan1.finish();
        }
    }
}

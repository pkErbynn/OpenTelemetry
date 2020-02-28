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
            System.out.println("......i do some work here.........");
            return "Hey from OpenTelemetry :)";

        } finally {
            incomingSpan.finish();
            ChildSpan1.finish();
        }
    }

    public String getTracingWithoutTryBlock(Span iSpan){
        Span ChildSpan1 = tracer.buildSpan("getOpenTracingWithoutTryBlock-B").asChildOf(iSpan).start();
        ChildSpan1.setTag("get_child_project_id", 11.1);
        System.out.println("........");

        Span ChildSpan2 = tracer.buildSpan("getOpenTracingWithoutTryBlock-C").asChildOf(ChildSpan1).start();
        ChildSpan2.setTag("get_child_project_id", 11.1);
        System.out.println(".......");

        iSpan.finish();     // root span closes first followed by the subs accordingly
        ChildSpan1.finish();
        ChildSpan2.finish();

        return "Tracing without try-catch";
    }
}

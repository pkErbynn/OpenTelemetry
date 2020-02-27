package io.turntabl.openTelemetry.serviceImpl;

import io.opentelemetry.context.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.springframework.beans.factory.annotation.Autowired;


public class OTeleServiceImpl {

    @Autowired
    private Tracer tracer;

    public String getOpenTelemetry(){

//        Span span = tracer.buildSpan("get-mytracing insiiiide").start();
//        try (Scope scope = (Scope) tracer.scopeManager().activate(span)) {
//
//            span.log("myLogs");
//            span.setTag("myTagKey", 23);
//
////            span.finish();
//        }

        Span span = tracer.buildSpan("inside").start();
        span.log("myLogs");
        span.setTag("myTagKey", 23);
        System.out.println("before scope.........");
//        try (Scope scope = (Scope) tracer.scopeManager().activate(span)) {
//        } catch(Exception ex) {
//            System.out.println("inside exception");
//            ex.printStackTrace();
//        } finally {
//            span.finish();
//        }
        return "Hey from OpenTelemetry :)";
    }
}

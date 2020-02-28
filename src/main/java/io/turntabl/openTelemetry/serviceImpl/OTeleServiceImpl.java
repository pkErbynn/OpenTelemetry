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

    public static String getHello(){
        return "JOhn Erbyn";
    }

    public String getOpenTelemetry(){

//       ` Span span = tracer.buildSpan("getOpenTracingService").start();
//        span.log("mylog2");
//
////        try (Scope ignored =  tracer.scopeManager().activate(span)) {
////            span.setTag("id", "try-with-resource");
////        }
//        span.finish();`


//        span.setTag("get_project_id", 55);
//        span.log("mylog");
//        span.getBaggageItem("pk");
//        span.finish();


//        Span span = tracer.buildSpan("inside").start();
////        span.log("myLogs");
////        span.setTag("myTagKey", 23);
////        System.out.println("before scope.........");
//        try (Scope scope = (Scope) tracer.scopeManager().activate(span)) {
//        } catch(Exception ex) {
//            System.out.println("inside exception");
//            ex.printStackTrace();
//        } finally
//        {
//            span.finish();
//        }
        return "Hey from OpenTelemetry :)";
    }
}

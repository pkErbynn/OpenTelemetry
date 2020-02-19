package io.turntabl.openTelemetry;

import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class OTController {

    @Autowired
    JaegerTracer jaegerTracer;

    @CrossOrigin
    @ApiOperation("Get Erbynn")
    @GetMapping(value = "v1/api/getErbynn")
    public String getErbynn( @RequestParam("abc") String abc){
//        Span span = (Span) jaegerTracer.buildSpan("Hello from OpenTelemetry").start();
        System.out.println("ID:: " + abc);
        JaegerSpan span = jaegerTracer.buildSpan("Get hello from Erbynn").start();
//        HttpStatus status = HttpStatus.OK;
        span.setTag("pk", "John Erbynn" );
//        span.addEvent("OT Event");
//        System.out.println("Response Status: " + status);
//        jaegerTracer.activeSpan().setTag("john", "erbynn");
//        span.end();
        span.finish();
//        jaegerTracer.close();
        return "Hello from Erbynn";
    }

    @CrossOrigin
    @ApiOperation("Get Alex")
    @GetMapping(value = "v1/api/getAlex")
    public String getAlex(){
//        Span span = (Span) jaegerTracer.buildSpan("Hello from OpenTelemetry").start();
        JaegerSpan span = jaegerTracer.buildSpan("Get hello from Alex").start();
//        HttpStatus status = HttpStatus.OK;
        span.setTag("alex", "Alex Owusu" );
//        span.addEvent("OT Event");
//        System.out.println("Response Status: " + status);
//        jaegerTracer.activeSpan().setTag("john", "erbynn");
//        span.end();
        span.finish();
//        jaegerTracer.close();
        return "Hello from Alex";
    }
}



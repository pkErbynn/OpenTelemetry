package io.turntabl.openTelemetry;

import io.jaegertracing.internal.JaegerSpan;
import io.jaegertracing.internal.JaegerTracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class PingController {

    @Autowired
    JaegerTracer jaegerTracer;

    @CrossOrigin
    @ApiOperation("test pong")
    @GetMapping(value = "v1/api/ping")
    public String ping(){
        JaegerSpan span = jaegerTracer.buildSpan("Get from Ping").start();
        span.setTag("pong", "pongService" );
        span.finish();
        return "Hello from Ping endpoint";
    }
}
package io.turntabl.openTelemetry;

import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.TracerSdkFactory;
import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
import io.opentelemetry.trace.Tracer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OTConfig {

    public static Tracer observabilityConfig(){
      // Configure the JaegerExporter as our exporter.
        SpanProcessor spanProcessor = SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
        .setServiceName("ProjectService")
        .setChannel(ManagedChannelBuilder.forAddress("localhost", 14250)
        .usePlaintext().build())
                .build()).build();
        TracerSdkFactory tracerFactory = OpenTelemetrySdk.getTracerFactory();
        tracerFactory.addSpanProcessor(spanProcessor);
        Tracer tracer = tracerFactory.get("OTController");
        return tracer;

    }



}

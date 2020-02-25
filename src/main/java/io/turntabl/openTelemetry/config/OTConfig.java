package io.turntabl.openTelemetry.config;

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

    public static Tracer observabilityConfig(SpanProcessor spanProcessor){
        TracerSdkFactory tracerFactory = OpenTelemetrySdk.getTracerFactory();
        tracerFactory.addSpanProcessor(spanProcessor);
        return tracerFactory.get("OTController");
    }

    public static SpanProcessor spanConfig(){
        return SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
                .setServiceName("OpenTelemetryService")
                .setChannel(ManagedChannelBuilder.forAddress("jaeger-collector", 14268)
                        .usePlaintext().build())
                .build()).build();
    }
}

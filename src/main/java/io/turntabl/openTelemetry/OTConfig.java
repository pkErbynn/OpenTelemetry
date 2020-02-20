//package io.turntabl.openTelemetry;

//import io.grpc.ManagedChannelBuilder;
//import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.trace.SpanProcessor;
//import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
//import io.opentelemetry.trace.Tracer;

//public class OTConfig {

//    public static Tracer observabilityConfig(){
        // Configure the JaegerExporter as our exporter.
//        SpanProcessor spanProcessor = SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
//                .setServiceName("ProjectService")
//                .setChannel(ManagedChannelBuilder.forAddress("localhost", 14250)
//                        .usePlaintext().build())
//                .build()).build();
//        OpenTelemetrySdk.getTracerFactory().addSpanProcessor();
//        return OpenTelemetrySdk.getTracerFactory().get("otelExample");
//
//    }

//}

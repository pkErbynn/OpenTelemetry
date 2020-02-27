package io.turntabl.openTelemetry;

import io.grpc.ManagedChannelBuilder;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
import io.opentelemetry.trace.Tracer;
import io.turntabl.openTelemetry.serviceImpl.OTeleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class OpenTelemetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenTelemetryApplication.class, args);
	}

    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration().withLogSpans(true);
        return new Configuration("java-6-hello").withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
    }

}

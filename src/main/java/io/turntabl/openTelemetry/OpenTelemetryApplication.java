package io.turntabl.openTelemetry;

import io.grpc.ManagedChannelBuilder;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
import io.opentelemetry.trace.Tracer;
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

	// config serviceName
//    @Bean
//    public static JaegerTracer getTracer() {
//        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
//        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
//        Configuration config = new Configuration("APIGatewayService").withSampler(samplerConfig).withReporter(reporterConfig);
//        return config.getTracer();
//    }


//    @Bean
//    public Tracer jaegerTracer(){
//        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
//        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
//        Configuration config = new Configuration("APIGatewayServiceTelemetry").withSampler(samplerConfig).withReporter(reporterConfig);
//        return config.getTracer();
//    }

}

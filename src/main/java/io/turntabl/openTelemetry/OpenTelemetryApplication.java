package io.turntabl.openTelemetry;

import io.jaegertracing.Configuration;
import io.turntabl.openTelemetry.serviceImpl.OTeleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class OpenTelemetryApplication {

	public static void main(String[] args) {
        System.out.println("System:: " + System.getenv("JAEGER_SAMPLER_MANAGER_HOST_PORT"));
		SpringApplication.run(OpenTelemetryApplication.class, args);
        System.out.println("System:: " + System.getenv("JAEGER_SAMPLER_MANAGER_HOST_PORT"));
	}

//    @Bean
//    public io.opentracing.Tracer initTracer() {
//        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().withType("const").withParam(1);
//        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration().withLogSpans(true);
//        return new Configuration("OpenTracingService").withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
//    }

    @Bean
    public io.opentracing.Tracer initTracer() {
        Configuration.SamplerConfiguration samplerConfig = new Configuration.SamplerConfiguration().fromEnv();
        Configuration.ReporterConfiguration reporterConfig = new Configuration.ReporterConfiguration().fromEnv();
        return new Configuration("OpenTracingService").withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
    }

    @Bean
    public OTeleServiceImpl getTrace(){return new OTeleServiceImpl();}

}

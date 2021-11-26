package eu.enqueue.quarkusft;

import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.quarkus.opentelemetry.exporter.otlp.runtime.LateBoundBatchSpanProcessor;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.CDI;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.azure.monitor.opentelemetry.exporter.AzureMonitorExporterBuilder;
import com.azure.monitor.opentelemetry.exporter.AzureMonitorTraceExporter;

@ApplicationScoped
public class AzureOpenTelemetryInitializer {

    @ConfigProperty(name = "eu.enqueue.quarkusft.appiconnstring")
    String connectionString;

    void startup(@SuppressWarnings("unused") @Observes StartupEvent e) {
        try {
            AzureMonitorTraceExporter azureMonitorTraceExporter = new AzureMonitorExporterBuilder()
                .connectionString(connectionString)
                .buildTraceExporter();
            LateBoundBatchSpanProcessor delayedProcessor = CDI.current()
                .select(LateBoundBatchSpanProcessor.class, Any.Literal.INSTANCE).get();
            delayedProcessor.setBatchSpanProcessorDelegate(BatchSpanProcessor.builder(azureMonitorTraceExporter).build());
        } catch (IllegalArgumentException iae) {
            throw new IllegalStateException("Unable to install Azure Exporter", iae);
        }
    }

}
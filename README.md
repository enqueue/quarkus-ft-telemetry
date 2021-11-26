# Telemetry Playground

Tracing Experiments with Quarkus, Application Insights and OpenTelemetry.

We have a JAX-RS resource which in turn calls another service.
The dependent calls should show up in our traces.

## Getting Started

Add your Application Insights connection String to `src/main/resources/application.properties`

Start the Quarkus

```shell
./gradlew quarkusDev
```

Fire some requests

```shell
curl -v http://localhost:8080/hello
```

Path | Description
---- | -----------
`/hello` | Just a hello
`/direct` | Call downstream service directly
`/retry` | Call downstream service, retry on failure
`/fallback` | Call downstream service, fallback on failure

Look at results in Application Insights.

## Links

- [Quarkus OpenTelemetry Guide](https://quarkus.io/guides/opentelemetry)

# quarkus-ft-telemetry

Tracing Experiments with Quarkus.

We have a JAX-RS resource which in turn calls another service.
The dependent calls should show up in our traces.

## Getting Started

Start the collector

```shell
docker compose run --service-ports -d otel-collector
```

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

Look at results in [Jaeger UI](http://localhost:16686)

## Links

- [Quarkus OpenTelemetry Guide](https://quarkus.io/guides/opentelemetry)

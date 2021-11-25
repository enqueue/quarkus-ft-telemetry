# Telemetry Playground

Tracing Experiments with Quarkus, Application Insights and OpenTelemetry.

We have a JAX-RS resource which in turn calls another service.
The dependent calls should show up in our traces.

## Getting Started

Download Application Insights agent

```shell
curl -L -O https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.2.3/applicationinsights-agent-3.2.3.jar
```

Create `applicationinsights.json` and add your Application Insights connection string to it

```javascript
{
    "connectionString" : "InstrumentationKey=...."
}
```
Compile the application

```shell
./gradlew build
```

Start with application with agent

```shell
java -javaagent:./applicationinsights-agent-3.2.3.jar -jar  build/quarkus-app/quarkus-run.jar
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

Now play around with `eu.enqueue.quarkusft.MyClient/mp-rest/url` property in
`src/main/resources/application.properties` or the `@Path` annotations
in `MyClient.java`. Non-existing servers, reponses which time out,
401s, 404s, etc.

Play around with `applyOn` property of `@Fallback` annotation.

Look at results in Application Insights

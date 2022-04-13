[![Camunda Platform 7](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)

# Camunda Engine Harvester (POC)

This is the code extracted from a simple POC to show how to gather information about multiple Camunda 7 installations within an enterprise.

Note, that this problem is solved out-of-the-box by the SaaS capabilities of Camunda 8.

There are different ways to gather that information. The one shown in this POC is that the harvester (this tool) has all Camunda endpoints configured (for the sake of simplicity of the POC, by a static configuration: [application.properties](blob/master/src/main/resources/application.properties#L5-15)). Then it can leverage the existing (Camunda REST API)[https://docs.camunda.org/manual/latest/reference/rest/] to gather information about [the version](https://docs.camunda.org/manual/latest/reference/rest/version/) and [some metrics](https://docs.camunda.org/manual/latest/reference/rest/metrics/):

![Harvest](/harvester.png)

This POC contains a **Java Spring Boot backend** to

* harvest the information,
* store it in a in-memory H2 db (easy to switch to a real RDMS),
* provide a simple REST endpoint to query it.

It further contains a **Vue.js frontend** to

* display the information.

![Screenshot](/screenshot.png)

There are a couple of extension points not considered in the POC:

* Add engines during runtime (simple, but needs an additional form)
* Validate versions and show handling hints (e.g. this version is out of support, this version needs a patch, ...). 

# How to run

## Run via docker

```
docker run -p 8098:8098 berndruecker/camunda-engine-harvester
```

Visit [http://localhost:8098/](http://localhost:8098/)

## Build the application via maven yourself:

I leveraged [this project template from Jonas Hecht](https://github.com/jonashackt/spring-boot-vuejs/), so you can find a lot of information on requirements and how-to build there. Basically you will need to do a 

```
mvn clean install
```

and run it:

```
mvn --projects backend spring-boot:run
```

Visit [http://localhost:8098/](http://localhost:8098/)

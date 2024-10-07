# Code Repository for Temporal 101 (Java)

It's important to remember that the example code used in this course was designed to support learning a specific aspect of Temporal, not to serve as a ready-to-use template for implementing a production system.

For the exercises, make sure to run `temporal server start-dev --ui-port 8080 --db-filename clusterdata.db` in one terminal to start the Temporal server. For more details on this command, please refer to the `Setting up a Local Development Environment` chapter in the course. Note: If you're using the Gitpod environment to run this exercise, you can skip this step.

## Hands-On Exercises

| Directory Name                | Exercise                                            |
| :---------------------------- | :-------------------------------------------------- |
| `exercises/hello-workflow`    | [Exercise 1](exercises/hello-workflow/README.md)    |
| `exercises/hello-web-ui`      | [Exercise 2](exercises/hello-web-ui/README.md)      |
| `exercises/farewell-workflow` | [Exercise 3](exercises/farewell-workflow/README.md) |
| `exercises/finale-workflow`   | [Exercise 4](exercises/finale-workflow/README.md)   |

## GitPod Environment Shortcuts

If you are executing the exercises in the provided GitPod environment, you
can take advantage of certain aliases to aid in navigation.

| Command | Action                                  |
| :------ | :-------------------------------------- |
| `ex1`   | Change to Exercise 1 Practice Directory |
| `ex1s`  | Change to Exercise 1 Solution Directory |
| `ex2`   | Change to Exercise 2 Practice Directory |
| `ex2s`  | Change to Exercise 2 Solution Directory |
| `ex3`   | Change to Exercise 3 Practice Directory |
| `ex3s`  | Change to Exercise 3 Solution Directory |
| `ex4`   | Change to Exercise 4 Practice Directory |
| `ex4s`  | Change to Exercise 4 Solution Directory |

## Instructor-Led Demonstrations

| Directory Name           | Description                                                         |
| :----------------------- | :------------------------------------------------------------------ |
| `demos/service-workflow` | [Shows a Workflow that uses a microservice](demos/service-workflow) |

## Examples for Self-Study

| Directory Name         | Description                                                                                      |
| :--------------------- | :----------------------------------------------------------------------------------------------- |
| `samples/greeting`     | [The Java Interface and Implementation with business logic for first Workflow](samples/greeting) |
| `samples/retry-policy` | [Example of a Workflow that has a custom Retry Policy](samples/retry-policy)                     |

## Reference

The following links provide additional information that you may find helpful as you work through this course.

- [General Temporal Documentation](https://docs.temporal.io/)
- [Temporal Java SDK Documentation](https://www.javadoc.io/doc/io.temporal/temporal-sdk/latest/index.html)
- [Java Language Documentation](https://docs.oracle.com/en/java/)
- [GitPod Documentation: Troubleshooting](https://www.gitpod.io/docs/troubleshooting)

## Exercise Environment for this Course

You can launch an exercise environment for this course in GitPod by
clicking the button below:

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/temporalio/edu-101-java-code)

Alternatively, you can follow
[these instructions](https://learn.temporal.io/getting_started/java/dev_environment/) to
set up your own Temporal Cluster with Temporal CLI, which you can use as an
exercise environment.

# Additional documentation
[Temporal Cookbook](https://vertexinc.atlassian.net/wiki/spaces/CSA/pages/4808048923/Integration+Platform+Services+-+Temporal+Cookbook)
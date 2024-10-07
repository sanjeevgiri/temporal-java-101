package helloworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class HelloWorkflowWorker {

  public static void main(String[] args) {
    // Set the Service Stub options (SSL context and gRPC endpoint) for working with temporal cloud or on prem
    //WorkflowServiceStubsOptions stubsOptions = WorkflowServiceStubsOptions
    //  .newBuilder()
    //  .setSslContext(sslContext)
    //  .setTarget(gRPCEndpoint)
    //  .build();

    // Create a stub that accesses a Temporal Service
    // WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newServiceStubs(stubsOptions);

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace("ips").build());
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker("greeting-tasks");
    worker.registerWorkflowImplementationTypes(HelloWorkflowWorkflowImpl.class);
    factory.start(); // Start listening to the Task Queue

    // Alternative to temporal CLI, workflow can be started using the temporal Java SDK
    //    WorkflowOptions options = WorkflowOptions.newBuilder()
    //      .setWorkflowId("my-first-workflow")
    //      .setTaskQueue("greeting-tasks")
    //      .build();
    //    HelloWorkflowWorkflow workflow = client.newWorkflowStub(HelloWorkflowWorkflow.class, options);
    //    String greeting = workflow.greetSomeone(args[0]);
    //    String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();
    //    System.out.println(workflowId + " " + greeting);
  }
}

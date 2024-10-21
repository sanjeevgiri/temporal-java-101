package farewellworkflow;

import io.temporal.api.enums.v1.WorkflowIdReusePolicy;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.common.RetryOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class Starter {
  public static void main(String[] args) throws Exception {

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace("ips").build());

    WorkflowOptions options = WorkflowOptions.newBuilder()
      .setRetryOptions(RetryOptions.newBuilder()
        .setMaximumAttempts(1)
        .build())
      .setWorkflowId("greeting-workflow-b3")
      .setTaskQueue("greeting-tasks-with-activities")
      .setWorkflowIdReusePolicy(WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_REJECT_DUPLICATE)
      .build();

    GreetingWorkflow workflow = client
      .newWorkflowStub(GreetingWorkflow.class, options);

    try {
      String greeting = workflow.greetSomeone(args[0]);
      String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();
      System.out.println(workflowId + " " + greeting);
    } catch (Throwable throwable) {
      System.out.println("Workflow failed: perform cleanup ... ack with error.." + throwable.getMessage());
    }

    System.exit(0);
  }
}

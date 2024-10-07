package farewellworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class GreetingWorkflowImpl implements GreetingWorkflow {

  Integer initialInterval = 15;
  Integer maxAttempts = 100;
  RetryOptions retryOptions = RetryOptions.newBuilder()
    .setInitialInterval(Duration.ofSeconds(initialInterval))
    .setBackoffCoefficient(2)
    .setMaximumInterval(Duration.ofSeconds((long) maxAttempts * initialInterval))
    .setMaximumAttempts(maxAttempts)
    .build();

  ActivityOptions options = ActivityOptions.newBuilder()
    .setStartToCloseTimeout(Duration.ofSeconds(5))
    .setRetryOptions(retryOptions)
    .build();

  private final GreetingActivities activities = Workflow.newActivityStub(GreetingActivities.class, options);

  @Override
  public String greetSomeone(String name) {
    String spanishGreeting = activities.greetInSpanish(name);
    String spanishFarewell = activities.farewellInSpanish(name);

    return "\n" + spanishGreeting + "\n" + spanishFarewell;
  }
}

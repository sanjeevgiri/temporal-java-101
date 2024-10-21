package farewellworkflow;


import io.temporal.api.enums.v1.ScheduleOverlapPolicy;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.schedules.Schedule;
import io.temporal.client.schedules.ScheduleActionStartWorkflow;
import io.temporal.client.schedules.ScheduleCalendarSpec;
import io.temporal.client.schedules.ScheduleClient;
import io.temporal.client.schedules.ScheduleClientOptions;
import io.temporal.client.schedules.ScheduleHandle;
import io.temporal.client.schedules.ScheduleIntervalSpec;
import io.temporal.client.schedules.ScheduleOptions;
import io.temporal.client.schedules.ScheduleRange;
import io.temporal.client.schedules.ScheduleSpec;
import io.temporal.serviceclient.WorkflowServiceStubs;
import java.time.Duration;
import java.util.List;

public class StarterScheduled {
  public static void main(String[] args) throws Exception {

//    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
//    // WorkflowClient client = WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace("ips").build());
//    ScheduleClient scheduleClient = ScheduleClient.newInstance(service);
//
//    ScheduleSpec.Builder cal = ScheduleSpec.newBuilder().setCalendars(List.of(
//      ScheduleCalendarSpec.newBuilder()
//        .setMinutes(List.of(new ScheduleRange(0, 59)))
//        .build()));
//
//    Schedule schedule =
//      Schedule.newBuilder()
//        .setAction(
//          ScheduleActionStartWorkflow.newBuilder()
//            .setWorkflowType(GreetingWorkflow.class)
//            .setArguments("World")
//            .setOptions(
//              WorkflowOptions.newBuilder()
//                .setWorkflowId("scheduled-greeting-workflow")
//                .setTaskQueue("greeting-tasks-with-activities")
//                .build())
//            .build())
//        .setSpec(cal.build())
//        .build();
//
//    // Create a schedule on the server
//    ScheduleHandle handle =
//      scheduleClient.createSchedule("ScheduleId2", schedule, ScheduleOptions.newBuilder().build());
//
//    handle.trigger(ScheduleOverlapPolicy.SCHEDULE_OVERLAP_POLICY_SKIP);


    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    ScheduleClientOptions options = ScheduleClientOptions.newBuilder().setNamespace("ips").build();
    ScheduleClient scheduleClient = ScheduleClient.newInstance(service, options);
    WorkflowOptions workflowOptions =
      WorkflowOptions.newBuilder().setWorkflowId("customer1").setTaskQueue("greeting-tasks-with-activities").build();
    ScheduleActionStartWorkflow action =
      ScheduleActionStartWorkflow.newBuilder()
        .setWorkflowType(GreetingWorkflow.class)
        .setArguments("World")
        .setOptions(workflowOptions)
        .build();


    ScheduleSpec.Builder cal = ScheduleSpec.newBuilder().setCalendars(List.of(
      ScheduleCalendarSpec.newBuilder()
        .setMinutes(List.of(new ScheduleRange(0, 59)))
        .build()))
      .setIntervals(List.of(new ScheduleIntervalSpec(Duration.ofSeconds(5))));

    Schedule schedule =
      Schedule.newBuilder()
        .setAction(
          ScheduleActionStartWorkflow.newBuilder()
            .setWorkflowType(GreetingWorkflow.class)
            .setArguments("World")
            .setOptions(
              WorkflowOptions.newBuilder()
                .setWorkflowId("scheduled-greeting-workflow")
                .setTaskQueue("greeting-tasks-with-activities")
                .build())
            .build())
        .setSpec(cal.build())
        .build();

    ScheduleHandle handle = null;

    try {
      handle = scheduleClient.getHandle("customer1-schedule");
      handle.describe();
    } catch (Exception e) {
      handle = null;
      System.out.println("Schedule not found");
    }
    if (handle == null) {
      handle = scheduleClient.createSchedule("customer1-schedule", schedule, ScheduleOptions.newBuilder().build());
    }
    handle.trigger(ScheduleOverlapPolicy.SCHEDULE_OVERLAP_POLICY_SKIP);

//      scheduleClient.createSchedule("customer1-schedule", schedule, ScheduleOptions.newBuilder().build());
//
//    // Manually trigger the schedule once
//    handle.trigger(ScheduleOverlapPolicy.SCHEDULE_OVERLAP_POLICY_ALLOW_ALL);
//
//    // Update the schedule with a spec, so it will run periodically
//    handle.update(
//      (ScheduleUpdateInput input) -> {
//        Schedule.Builder builder = Schedule.newBuilder(input.getDescription().getSchedule());
//
//        builder.setSpec(
//          ScheduleSpec.newBuilder()
//            // Run the schedule every minute
//            .setCalendars(
//              Collections.singletonList(
//                ScheduleCalendarSpec.newBuilder()
//                  .setMinutes(Collections.singletonList(new ScheduleRange(0, 59)))
//                  .build()))
//            // Run the schedule every 5s
//            .setIntervals(
//              Collections.singletonList(new ScheduleIntervalSpec(Duration.ofSeconds(5))))
//            .build());
//        // Make the schedule paused to demonstrate how to unpause a schedule
////        builder.setState(
////          ScheduleState.newBuilder()
////            .setPaused(true)
////            .setLimitedAction(true)
////            .setRemainingActions(10)
////            .build());
//        return new ScheduleUpdate(builder.build());
//      });
//
//    handle.describe();

  }
}

package helloworkflow;

import io.temporal.common.SearchAttributeKey;
import io.temporal.common.SearchAttributeUpdate;
import io.temporal.workflow.Workflow;

public class HelloWorkflowWorkflowImpl implements HelloWorkflowWorkflow {

    private static final SearchAttributeKey<String> DOCUMENT_ID = SearchAttributeKey.forKeyword("documentId");

    @Override
    public String greetSomeone(String name){
        Workflow.upsertTypedSearchAttributes(
          SearchAttributeUpdate.valueSet(DOCUMENT_ID, name)
        );
        return "Hello " + name + "!";
    }
}

package ibf2022.paf.assessment.server.exception;

import ibf2022.paf.assessment.server.models.Task;

public class InsertException extends Exception {
    
    private Task task;

    public InsertException() {
        super();
    }

    public InsertException(String msg) {
        super(msg);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    
}

package dataQuest;

import org.junit.Test;

public class DataQuestTaskTest {

    @Test(expected = NotSuitableLinkException.class)
    public void wrongLinkException() throws NotSuitableLinkException {
        DataQuestTask dqt = new DataQuestTask();
        dqt.solveTaskFromBrett("https://www.google.com");
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomabudjetti;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityTest {
    
    Activity activity;

    public ActivityTest() {
    }

    @Before
    public void setUp() {
        activity = new Activity("Jet-Ski", 50, 6);
    }
    
    @Test
    public void priorityNumberIsCorrect() {
        assertEquals(6, activity.getPriorityNumber());
    }
    
    @Test
    public void priorityNumberIsCorrectWhenItShouldBeZero() {
        activity.setPriorityNumber(392);
        assertEquals(0, activity.getPriorityNumber());
    }


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

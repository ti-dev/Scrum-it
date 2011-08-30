package ch.bsgroup.scrumit.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	PersonTest.class,
	ProjectTest.class,
	SprintTest.class,
	UserStoryTest.class,
	TaskTest.class
})

public class AllTests {
    // this class remains completely empty,
	// being used only as a holder for the above annotations
}
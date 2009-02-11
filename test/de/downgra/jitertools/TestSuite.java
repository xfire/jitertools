package de.downgra.jitertools;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { TestChain.class, TestCount.class, TestCycle.class,
		TestDropwhile.class, TestFilter.class, TestTakewhile.class })
public class TestSuite {
}

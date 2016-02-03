package com.momy.tests;

import com.momy.bean.ServerBean;
import com.momy.utils.ConfigurationsLoader;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationsLoaderTest {
	private ConfigurationsLoader config;
	
	@Before
	public void setUp(){
		config = new ConfigurationsLoader();
	}
	
	@After
	public void tearDown(){
		config = null;
	}
	
	@Test
	public void checkServer(){
		ServerBean server = this.config.getServerConfiguration();
		assertTrue(server.getServerHost().equals("localhost"));
		assertTrue(server.getServerPort().equals("3333"));
		assertTrue(server.getServerRoot().equals("/Users/captain/Documents/myServer"));
	}
	
}

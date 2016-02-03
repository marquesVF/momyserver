/*
 * Momy - A simple Gopher server implementation
 */

package com.momy.core;

import com.momy.utils.ConfigurationsLoader;

public class Momy {

	public static void main(String[] args) {
		ConfigurationsLoader config = new ConfigurationsLoader();
		RequestHandler rh = new RequestHandler(config.getServerConfiguration());
		rh.onListening();
	}

}

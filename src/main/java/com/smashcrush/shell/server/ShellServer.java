package com.smashcrush.shell.server;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.resource.JarResource;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.inject.servlet.GuiceFilter;

public class ShellServer {

	public static void main(String[] args) {
		System.out.println("Starting server on port 8080");

		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(8080);
		server.addConnector(connector);

		// ResourceHandler resource_handler = new ResourceHandler();
		// resource_handler.setDirectoriesListed(true);
		// resource_handler.setWelcomeFiles(new String[] { "index.html" });
		// resource_handler.setResourceBase("bin/assets");

		WebAppContext webapp = new WebAppContext();
		webapp.setWelcomeFiles(new String[] { "index.html" });
		webapp.setContextPath("/");
		webapp.setResourceBase("src/main/webapp");
//		webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
		webapp.setWar(".");

//		Enumeration<URL> ee = null;
//		try {
//			System.out.println("eeexxxx:");
//			ee = ShellServer.class.getResources("com/smashcrush");
//			System.out.println("ummmm:"+ee);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		
//		while(ee.hasMoreElements()) {
//			URL u = ee.nextElement();
//			System.out.println("classpathxxx:"+u);
//			
//		}
		
//		String webDir = ShellServer.class.getClassLoader().getResource("com/smashcrush/webapp").toExternalForm();
//		System.out.println(webDir);
//		webapp.setResourceBase(webDir);
//
//		try {
//			webapp.setBaseResource(JarResource.newResource(ShellServer.class
//					.getResource("classpath:com/smashcrush/webapp")));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		webapp.addFilter(GuiceFilter.class, "/*",
				EnumSet.allOf(DispatcherType.class));

		webapp.addEventListener(new SmashCrushModule());

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { webapp });
		server.setHandler(handlers);

		try {
			server.start();
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

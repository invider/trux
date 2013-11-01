package com.decaflabs.trux.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.decaflabs.trux.Geo;

public class StateHandler extends AbstractHandler {
	
	private final Geo geo;
	
	public StateHandler(Geo geo) {
		this.geo = geo;
	}

	@Override
	public void handle(String arg0, Request request, HttpServletRequest arg2,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/json;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		request.setHandled(true);
		
		// all we need to do is to print the JSON representation of Geo
		response.getWriter().println(this.geo.toJSON());
	}

}

package com.decaflabs.trux.site;

public class UnknownSurface implements Surface {
	
	private static final UnknownSurface instance = new UnknownSurface();
	
	private UnknownSurface() {}
	
	public static final UnknownSurface getInstance() {
		return instance;
	}
	
	@Override
	public String soilSample() {
		return "unknown";
	}

}

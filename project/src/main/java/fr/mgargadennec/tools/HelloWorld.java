package fr.mgargadennec.tools;

public class HelloWorld {
	private String hello;
	private String world;

	public HelloWorld(String hello, String world) {
		this.hello = hello;
		this.world = world;
	}

	@Override
	public String toString() {
		return hello + " " + world;
	}
}

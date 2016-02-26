package fr.mgargadennec.tools;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class HelloWorldPrinter {
	private Timer timer;
	private HelloWorld[] hellos;

	public HelloWorldPrinter(HelloWorld... helloWorlds) {
		this.timer = new Timer();
		this.hellos = helloWorlds;
		this.timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Arrays.stream(hellos).forEach(System.out::println);
			}
		}, 0, 1 * 1000);
	}
	
	public HelloWorld[] getHellos(){
		return hellos;
	}

}

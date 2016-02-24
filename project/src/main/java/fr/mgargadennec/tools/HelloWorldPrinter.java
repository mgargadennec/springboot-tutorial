package fr.mgargadennec.tools;

import java.util.Timer;
import java.util.TimerTask;

public class HelloWorldPrinter {
	private Timer timer;

	public HelloWorldPrinter(HelloWorld firstHello, HelloWorld... helloWorlds) {
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(helloWorlds.toString());
			}
		}, 0, 1 * 1000);
	}

}

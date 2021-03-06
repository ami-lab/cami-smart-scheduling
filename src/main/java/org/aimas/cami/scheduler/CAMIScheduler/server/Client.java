package org.aimas.cami.scheduler.CAMIScheduler.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

public class Client extends AbstractVerticle {

	private final static int SERVER_PORT = 8080;
	private final static String SERVER_HOST = "localhost";

	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		Runner.runExample(Client.class);
	}

	@Override
	public void start() throws Exception {

		HttpClientOptions options = new HttpClientOptions().setDefaultHost(SERVER_HOST);
		HttpClient client = vertx.createHttpClient(options);

		// send a post request to server to add a new activity to the schedule
		postNewActivity(client);

	}

	private void postNewActivity(HttpClient client) {

		String newActivityFilePath = "data\\activityschedule\\New Activity.xml";
		String newActivity = null;
		try {
			newActivity = new String(Files.readAllBytes(Paths.get(newActivityFilePath)));
			// System.out.println(newActivity);
		} catch (IOException e) {
			e.printStackTrace();
		}

		client.post(SERVER_PORT, SERVER_HOST, RoutePaths.API_ROUTE + RoutePaths.NEW_ACTIVITY_ROUTE, response -> {

			System.out.println("Received response with status code " + response.statusCode());

			response.bodyHandler(new Handler<Buffer>() {

				@Override
				public void handle(Buffer event) {
					System.out.println("Got the event: " + event);
				}
			});
		}).end(newActivity);

	}
}
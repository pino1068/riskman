package riskman.command;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.*;

import riskman.app.web.RiskmanServlet;

public class WebServerCommand extends BaseCommand {

	private int port = 8080;

	@Override
	public void execute(String definition) {
		String content = commands.contentOf(definition);
		if (workspace.server==null && definition.contains(":") && content != null && content.trim().length() > 0)
			port = Integer.valueOf(content);
		if (workspace.server != null)
			printlnFormatted(
					"The web server is already running on port %1$s...", workspace.server.getConnectors()[0].getPort());
		else
			try {
				Server server = new Server(port);
				Context root = new Context(server, "/", Context.SESSIONS);
				root.addServlet(
						new ServletHolder(new RiskmanServlet(workspace)), "/*");
				server.start();
				printlnFormatted(
						"web server started on port %1$s. Try with http://localhost:%1$s",
						port);
				workspace.server = server;
			} catch (Exception e) {
				println(e.getMessage());
			}
	}

	@Override
	public String describe() {
		return "	'web:<port>'			- will start the server at http://localhost:<port>";
	}

}

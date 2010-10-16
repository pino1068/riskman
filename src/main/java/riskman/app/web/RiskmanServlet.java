package riskman.app.web;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.servlet.*;

import riskman.app.Workspace;
import riskman.app.console.*;
import riskman.command.*;

public class RiskmanServlet implements Servlet {

	private final Workspace workspace;
	private Commands commands;
	private BatchConsole console;

	public RiskmanServlet(Workspace workspace) {
		console = new BatchConsole();
		this.workspace = workspace.copyTo(new HTMLConsole(console));
		commands = new Commands();
	}

	public void destroy() {
	}

	public ServletConfig getServletConfig() {
		return new GenericServlet() {
			private static final long serialVersionUID = 1L;

			@Override
			public void service(ServletRequest arg0, ServletResponse arg1)
					throws ServletException, IOException {
				// TODO Auto-generated method stub

			}
		};
	}

	public String getServletInfo() {
		return "-";
	}

	public void init(ServletConfig arg0) throws ServletException {
	}

	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		String line = arg0.getParameter("command");
		if (line == null || line.trim().length() == 0)
			console.println("Welcome in Riskman. How can I help you? type with \"help\" ");
		else {
			console.clear();
			Command command = command(line);
			execute(command, line);
		}
		arg1.setContentType("text/html");
		arg1.getOutputStream().println(
				"You asked to run command: " + line + "<br />");
		arg1.getOutputStream().print(
				"<div id='output' style='border:10px;color:red'>");
		arg1.getOutputStream().print(console.output());
		arg1.getOutputStream().print("</div>");
		arg1.getOutputStream()
				.print("<form id=command action='#command'>"
						+ "<input type=text name=command style='width:300px;hight:20px;background-color: blue;' />"
						+ "<input type=submit />" + "</form>");
	}

	private void execute(Command command, String line) {
		try {
			command.execute(line);
		} catch (InvalidParameterException e) {
			noCommand(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void noCommand(String line) {
		final NoCommand noCommand = new NoCommand();
		noCommand.workspace(workspace);
		noCommand.execute(line);
	}

	private Command command(String line) {
		Command command = commands.build(line);
		command.workspace(workspace);
		return command;
	}

}

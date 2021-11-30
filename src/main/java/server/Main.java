package server;

import commons.Connect;
import commons.DefaultServer;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public final class Main {
    public static void main(String[] args) throws Exception {
        Connect.migrate();

        Server server = new DefaultServer().build();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.addServlet(HttpServletDispatcher.class, "/");

        context.addEventListener(new GuiceListener());

        final String hashConfig = Main.class.getResource("/hash_config").toExternalForm();
        final HashLoginService hashLoginService = new HashLoginService("login",hashConfig);
        final ConstraintSecurityHandler security = new SecurityHandlerBuilder().build(hashLoginService);

        server.addBean(hashLoginService);
        security.setHandler(context);
        server.setHandler(security);

        server.start();

    }
}

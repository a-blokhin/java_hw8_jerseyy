package server;

import api.DeleteREST;
import api.GetREST;
import api.PostREST;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;


import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

public final class GuiceListener extends GuiceResteasyBootstrapServletContextListener {

    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        return Collections.singletonList(new GuiceModule());
    }

    @SuppressWarnings("rawtypes")
    private static final class GuiceModule extends AbstractModule {
        @SuppressWarnings("PointlessBinding")
        @Override
        protected void configure() {
            bind(JacksonMessageBodyHandler.class).toInstance(new JacksonMessageBodyHandler());
            bind(DeleteREST.class);
            bind(GetREST.class);
            bind(PostREST.class);
        }
    }
}

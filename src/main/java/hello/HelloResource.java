package hello;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    private static final Logger LOG = Logger.getLogger(HelloResource.class.getName());

    @Inject
    EntityManager em;

    @Transactional
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        Foo foo = new Foo();

        try {
            em.merge(foo);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "merge", e);
        }
        
        em.find(Foo.class, 0L);
        
        return "hello";
    }
}
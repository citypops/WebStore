package citypops.webstore.listener;

import citypops.webstore.persistence.CartRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class HttpSessionListenerImpl implements HttpSessionListener, ApplicationContextAware {

    private CartRepository cartRepository;

    @Autowired
    public HttpSessionListenerImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        cartRepository.deleteCart(sessionId);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ((WebApplicationContext) applicationContext).getServletContext().addListener(this);
    }
}
package com.camerondevelope.fix;

import ognl.OgnlRuntime;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// Idea taken from http://programmingpanda.blogspot.com/2009/07/struts-2-ongl-issue-on-google-app.html
public class ONGLFixListener  implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

   public ONGLFixListener()  {
   }

   public void contextInitialized(ServletContextEvent servletContextEvent)  {
       OgnlRuntime.setSecurityManager(null);
   }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

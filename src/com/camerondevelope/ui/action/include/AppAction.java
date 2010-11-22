package com.camerondevelope.ui.action.include;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 5:34:04 PM
 *
 * Overall action to put generic stuff into
 */
public class AppAction extends ActionSupport {
    private List<String> jsFiles = Lists.newLinkedList();
    private List<String> cssFiles = Lists.newLinkedList();
    private List<String> jsUrls = Lists.newLinkedList();
    private List<String> jsUrlsAsync = Lists.newLinkedList();

    private class ScriptUrl {
        public String url;
        public boolean async;
        private ScriptUrl(String url, boolean async) {
            this.url = url;
            this.async = async;
        }
    }

    public AppAction() {
        addCSSFile("reset");
        addJSUrl("https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js");
    }

    /**
     * Filename is rel path from /war/js/ does not include ".js"
     * @param filename
     */
    protected void addJSFile(String filename) {
        jsFiles.add(filename);
    }

    public List<String> getJSFiles() {
        return jsFiles;
    }

    /**
     * Filename is rel path from /war/css/ does not include ".css"
     * @param filename
     */
    protected void addCSSFile(String filename) {
        cssFiles.add(filename);
    }

    public List<String> getCSSFiles() {
        return cssFiles;
    }

    protected void addJSUrl(String filename) {
        addJSUrl(filename, false);
    }

    /**
     * All urls with async == true are put into the footer
     * @param filename
     * @param async
     */
    protected void addJSUrl(String filename, boolean async) {
        if (async) {
            jsUrlsAsync.add(filename);
        } else {
            jsUrls.add(filename);
        }
    }

    public List<String> getJSUrls() {
        return jsUrls;
    }

    public List<String> getJSUrlsAsync() {
        return jsUrlsAsync;
    }
}

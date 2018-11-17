/**
 * Created the sn.mini.java.web.http.DefaultHttpServlet.java
 *
 * @created 2018年2月5日 上午11:44:32
 * @version 1.0.0
 */
package sn.mini.java.web.http;

import sn.mini.java.web.SNInitializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * sn.mini.java.web.http.DefaultHttpServlet.java
 *
 * @author XChao
 */
public final class DefaultHttpServlet extends HttpServlet {
    private static final long serialVersionUID = -6021307577546368668L;

    private SNHttpServletRequest getSNHttpServletRequest(HttpServletRequest req) {
        return (req instanceof SNHttpServletRequest) ? (SNHttpServletRequest) req : new SNHttpServletRequest(req);
    }

    private ActionProxy getActionProxy(HttpServletRequest req, HttpServletResponse response) throws IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy proxy = SNInitializer.getActionProxy(request.getRequestURI());
        if (proxy == null) {
            for (String name : SNInitializer.getActionProxys().keySet()) {
                if (request.getRequestURI().matches(name)) {
                    proxy = SNInitializer.getActionProxy(name);
                    break;
                }
            }
        }
        if (proxy == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found Page.");
        }
        return proxy;
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found Page.");
            return;
        }
        actionProxy.getController().doDeleteBefore(request, response);
        actionProxy.getController().doService(actionProxy, request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }

    protected void doHead(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }

    protected void doTrace(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        SNHttpServletRequest request = this.getSNHttpServletRequest(req);
        ActionProxy actionProxy = getActionProxy(req, response);
        if (actionProxy != null) {
            actionProxy.getController().doDeleteBefore(request, response);
            actionProxy.getController().doService(actionProxy, request, response);
        }
    }
}

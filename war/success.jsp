<%--
  Created by IntelliJ IDEA.
  User: loyd
  Date: Nov 19, 2010
  Time: 9:27:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
  <head><title>Facebook Login</title></head>
  <body>
  <h1>Facebook Login</h1>

  <div><s:property value="fullname" /></div>
  <div><s:property value="friends" /></div>

  <p><fb:login-button autologoutlink="true"></fb:login-button></p>
    <p><fb:like></fb:like></p>

    <div id="fb-root"></div>
    <script>
      window.fbAsyncInit = function() {
        FB.init({appId: '<s:property value="fbAppId" />', status: true, cookie: true,
                 xfbml: true});
      };
      (function() {
        var e = document.createElement('script');
        e.type = 'text/javascript';
        e.src = document.location.protocol +
          '//connect.facebook.net/en_US/all.js';
        e.async = true;
        document.getElementById('fb-root').appendChild(e);
      }());
    </script>
  </body>
</html>

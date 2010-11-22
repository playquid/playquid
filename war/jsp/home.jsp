<%--
  Created by IntelliJ IDEA.
  User: loyd
  Date: Nov 19, 2010
  Time: 9:27:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<s:include value="../include/header.jsp"><s:param name="title">Home</s:param></s:include>

<h1>Welcome</h1>
<p><fb:login-button autologoutlink="true" perms="publish_stream,create_event,rsvp_event,offline_access,user_events,friends_events,user_work_history,friends_work_history,email,user_location,friends_location"></fb:login-button></p>
<s:if test="fBLoggedIn">
    <p><a href="<s:url action="view" />">View</a></p>
</s:if>

<div id="fb-root"></div>

<script type="text/javascript">
    window.fbAsyncInit = function() {
        FB.init({appId: '<s:property value="fBAppId" />', status: false, cookie: true, xfbml: true});
        App.onFBLogin = '<s:url action="view" />';
        App['fbAsyncInitCallback'] && App['fbAsyncInitCallback']();
    };
</script>

<s:include value="../include/footer.jsp" />

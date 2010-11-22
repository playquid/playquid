<%--
  Created by IntelliJ IDEA.
  User: loyd
  Date: Nov 19, 2010
  Time: 9:27:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<s:include value="include/header.jsp"><s:param name="title">View FB</s:param></s:include>

  <h1>Facebook Info</h1>

  <div><s:property value="fullname" /></div>
  <div><s:property value="friends" /></div>

<s:include value="include/footer.jsp" />

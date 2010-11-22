<%--
  Created by IntelliJ IDEA.
  User: loyd
  Date: Nov 21, 2010
  Time: 4:16:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="jSUrlsAsync">
          <script type="text/javascript" src="<s:property />" async="true"></script>
</s:iterator>
  </body>
</html>

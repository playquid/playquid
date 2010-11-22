<%--
  Created by IntelliJ IDEA.
  User: loyd
  Date: Nov 21, 2010
  Time: 4:13:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      <title>App Title :: <%= request.getParameter("title") %></title>

      <s:iterator value="cSSFiles">
        <link rel="stylesheet" type="text/css" href="css/<s:property />.css" />
      </s:iterator>

      <script type="text/javascript">var App = {};</script>
      <s:iterator value="jSUrls">
          <script type="text/javascript" src="<s:property />"></script>
      </s:iterator>
      <s:iterator value="jSFiles">
          <script type="text/javascript" src="/js/<s:property />.js"></script>
      </s:iterator>
  </head>
  <body>

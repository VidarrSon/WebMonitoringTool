<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.WebPage" %>

<html>
<head>
    <title>Web Monitoring</title>
</head>
<body>
<h1 style="text-align: center">Web Monitoring Page</h1>
<br>
<form align="center" method="get" action="${pageContext.request.contextPath}/insert_table">
    <input type="submit" value="Add new URL">
</form>
<br>
<table border="1" align="center">
    <tr bgcolor="00FF7F">
        <th><b>Website name</b></th>
        <th><b>URL address</b></th>
        <th><b>Status</b></th>
        <th><b>Operations</b></th>
    </tr>
    <%
        ArrayList<WebPage> webPages = (ArrayList<WebPage>) request.getAttribute("webPages");

        for (WebPage webPage : webPages) {
    %>
    <tr>
        <td style="text-align: center" width="150">
            <%=webPage.getUrlName()%>
        </td>

        <td style="text-align: center" width="200">
            <%=webPage.getUrlAddress()%>
        </td>

        <td style="text-align: center" width="300">
            <%=webPage.getStatus()%>
        </td>
        <td width="150">
            <br>
            <form align="center" method="post" action="${pageContext.request.contextPath}/read?id=<%=webPage.getId()%>">
                <input type="submit" value="Edit">
            </form>
            <form align="center" method="post" action="${pageContext.request.contextPath}/change_state?id=<%=webPage.getId()%>">
                <input type="hidden" value="">
                <input type="submit" value="Stop / Resume">
            </form>
            <form align="center" method="post" action="${pageContext.request.contextPath}/delete?id=<%=webPage.getId()%>">
                <input type="submit" value="Remove">
            </form>
        </td>
    </tr>
    <%
        }
        // refresh page every 30 sec
        response.setIntHeader("Refresh", 30);
    %>
</table>
<br>
<hr/>
</body>
</html>

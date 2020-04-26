<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.WebPage" %>

<html>
<head>
    <title>Edit URL</title>
</head>
<body>
<h1>Edit URL</h1>
<%
    WebPage webPage = (WebPage) request.getAttribute("webPage");
%>

<table border="1" width="500" align="center">
    <tr bgcolor="00FF7F">
        <th><b>URL name</b></th>
        <th><b>URL address</b></th>
        <th><b>Monitoring period</b></th>
        <th><b>Response time</b></th>
        <th><b>Response code</b></th>
        <th><b>Response substring</b></th>
        <th><b>Maximum response range</b></th>
        <th><b>Minimum response range</b></th>
    </tr>
    <tr>
        <form align="center" method="post" action="${pageContext.request.contextPath}/update?id=<%=webPage.getId()%>">
            <td>
                <input style="text-align: center" type="text" name="url_name" value="<%=webPage.getUrlName()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="url_address" value="<%=webPage.getUrlAddress()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="monitoring_period" value="<%=webPage.getMonitoringPeriod()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_time" value="<%=webPage.getResponseTime()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_code" value="<%=webPage.getResponseCode()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_substring" value="<%=webPage.getResponseSubstring()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_max" value="<%=webPage.getResponseRangeMax()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_min" value="<%=webPage.getResponseRangeMin()%>">
            </td>
            <input type="submit" value="Save changes">
            <br>
            <br>
            <input type="reset" value="Discard changes">
        </form>
    </tr>
</table>
<form method="get" action="${pageContext.request.contextPath}/monitoring">
    <input type="submit" value="Back to monitoring">
</form>
<hr/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>Add new URL</title>
</head>
<body>
<h1>Add new URL</h1>

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
        <form method="post" action="${pageContext.request.contextPath}/update">
            <td>
                <input style="text-align: center" type="text" name="url_name" placeholder="Twitter">
            </td>

            <td>
                <input style="text-align: center" type="text" name="url_address" placeholder="https://twitter.com/">
            </td>

            <td>
                <input style="text-align: center" type="text" name="monitoring_period" placeholder="10000">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_time" placeholder="600">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_code" placeholder="200">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_substring" placeholder="somestring">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_max" placeholder="400000">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_min" placeholder="1">
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

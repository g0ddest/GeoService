<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <h3>Avaiable links</h3>
    <p><b>/api/suggest?q={QUERY}[&c={COUNT}]</b> where QUERY - text request, COUNT (optional) - max returning objects (default 10)</p>
    <p><b>/api/geocode?name={QUERY}</b> where QUERY - text request</p>
    <p><b>/api/geocode_reverse?lat={LATITUDE}&lng={LONGTITUDE}</b> where LATITUDE and LONGTITUDE is latitude and longtitude in english float format (e.g. 55.7525254 and 37.6074081)</p>
  </body>
</html>

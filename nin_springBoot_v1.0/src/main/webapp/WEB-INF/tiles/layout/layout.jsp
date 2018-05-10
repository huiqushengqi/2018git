<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<title><tiles:getAsString name="title" /></title>
		<tiles:insertAttribute name="header" />
	</head>
	<body>
		<!-- Body -->
		<tiles:insertAttribute name="body" />
		<!-- Footer -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>
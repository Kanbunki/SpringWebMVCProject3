<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書籍IDで検索</title>
</head>
<body>
	<h2>書籍IDで検索</h2>
	<form:form modelAttribute="searchByIdForm">
		<div>
			書籍ID:
			<form:input path="bookId" value=""/>
			<form:errors path="bookId" />
		</div>
		<div>
			<form:button name="backToMethodMenu">戻る</form:button>
			<form:button name="searchById">検索</form:button>
		</div>
	</form:form>
</body>
</html>
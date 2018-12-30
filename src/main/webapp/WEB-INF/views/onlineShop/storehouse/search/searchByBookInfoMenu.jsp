<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書籍情報で検索</title>
</head>
<body>
	<h2>書籍情報で検索</h2>
	<form:form modelAttribute="searchByBookInfoForm">
		<div>
			書籍名：
			<form:input path="bookTitle" />
		</div>
		<div>
			作者：
			<form:input path="bookAuthor" />
		</div>
		<div>
			出版日：<form:input path="fromDate" />　～　<form:input path="toDate" />
			<form:errors path="fromDate" />
			<form:errors path="toDate" />
		</div>
		<div>
			<form:button name="backToMethodMenu">戻る</form:button>
			<form:button name="searchByBookInfo">検索</form:button>
		</div>
	</form:form>
</body>
</html>
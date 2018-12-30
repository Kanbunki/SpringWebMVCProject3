<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書籍検索方法</title>
</head>
<body>
	<h2>書籍検索方法</h2>
	<div>
		<a href="<c:url value='/onlineShop/storehouse/search?method=byId' />">書籍IDで検索</a>
	</div>
	<div>
		<a href="<c:url value='/onlineShop/storehouse/search?method=byBookInfo' />">書籍情報で検索</a>
	</div>
	<div>
		<a href="<c:url value='/onlineShop/storehouse' />" >戻る</a>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
</head>
<body>
	<h2>検索結果</h2>
	<c:if test="${empty bookList}" >
		<div>
			<c:out value="該当する書籍はありません。" />
		</div>
	</c:if>
	<c:forEach var="book" items="${bookList}" >
		<div>
			書籍ID：
			<c:out value="${book.bookId }" />
		</div>
		<div>
			書籍名：
			<c:out value="${book.title }" />
		</div>
		<div>
			作者：
			<c:out value="${book.author }" />
		</div>
		<c:forEach var="chapter" items="${book.chapterList}" >
			<div>
				<c:out value="chapter ${chapter.chapterSeq }： ${chapter.chapterTitle }" />
			</div>
		</c:forEach>
		<div>
			出版日：
			<fmt:formatDate pattern="yyyy-MM-dd" value="${book.publishedDate }"/>
		</div>
		<br/>
	</c:forEach>
	<div>
		<a href="<c:url value='/onlineShop/storehouse/search?method=${backToSearchMenu }' /> ">検索画面へ戻る</a>
	</div>
</body>
</html>
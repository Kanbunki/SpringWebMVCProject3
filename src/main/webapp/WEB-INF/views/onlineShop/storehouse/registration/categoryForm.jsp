<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>カテゴリー登録</title>
</head>
<body>
	<h2>カテゴリー登録</h2>
	<form:form modelAttribute="cateForm">
		<div>
			カテゴリー：
			<form:input path="categoryName" />
			<form:button name="categoryRegistration">登録</form:button>
			<form:errors path="categoryName" />
		</div>
		<table border="1">
			<tr>
				<th>ID</th>
				<th colspan="2">カテゴリー</th>
			</tr>
			<c:forEach var="category" items="${cateForm.categoryList }">
			<tr>
				<td><c:out value="${category.id }" /></td>
				<td><c:out value="${category.name }" /></td>
				<td>
					<form:button name="deleteId" value="${category.id }">削除</form:button>
				</td>
			</tr>
			</c:forEach>
		</table>
		<form:button name="backToBookRegistration">戻る</form:button>
	</form:form>
</body>
</html>
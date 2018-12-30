<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書籍登録</title>
</head>
<body>
	<h2>書籍登録</h2>
	<form:form modelAttribute="registrationForm">
		<div>
			<form:errors/>
		</div>
		<div>
			書籍名：
			<form:input path="bookTitle" />
			<form:errors path="bookTitle" />
		</div>
		<div>
			著者名：
			<form:input path="bookAuthor" />
			<form:errors path="bookAuthor" />
		</div>
		<div>
			カテゴリ：
			<form:select path="categoryId">
				<form:options items="${registrationForm.categoryList }" itemLabel="name" itemValue="id" />
			</form:select>
			<form:button name="addCategory">＋</form:button>
		</div>
		<div>
			出版日：
			<form:input path="publishedDate" />
			<form:errors path="publishedDate" />
		</div>
		<div>
			登録者：
			<input type="radio" name="registrantType" value="new" checked="checked">新規
			<input type="radio" name="registrantType" value="old">既存
		</div>
		<div>
			ID：
			<form:input path="registrantName" />
			<form:errors path="registrantName" />
		</div>
		<div>
			パスワード：
			<form:password path="registrantPassword" />
			<form:errors path="registrantPassword" />
		</div>
		<div>
			確認用パスワード：
			<form:password path="confirmPassword" maxLength="8"/>
			<form:errors path="confirmPassword" />
		</div>
		<div>
			<form:button name="back">戻る</form:button>
			<form:button name="register">登録</form:button>
		</div>
	</form:form>
</body>
</html>
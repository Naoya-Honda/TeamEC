<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/error.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/form.css">
<link rel="stylesheet" href="./css/table.css">

<title>カート画面</title>
</head>
<body>
	<script src="./js/cart.js"></script>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<h1>カート画面</h1>
	</div>

	<s:if test="cartInfoDTOList != null && cartInfoDTOList.size() > 0">
		<s:form action="DeleteCartAction">
			<table class="horizontal-list">
				<tr>
					<th><s:label value="#" /></th>
					<th><s:label value="商品名" /></th>
					<th><s:label value="ふりがな" /></th>
					<th><s:label value="商品画像" /></th>
					<th><s:label value="値段" /></th>
					<th><s:label value="発売会社名" /></th>
					<th><s:label value="発売年月日" /></th>
					<th><s:label value="購入個数" /></th>
					<th><s:label value="合計金額" /></th>
				</tr>

				<s:iterator value="cartInfoDTOList">
					<tr>
						<td><input type="checkbox" class="checkList" name="checkList"
							value='<s:property value="productId" />' onchange="checkValue()"></td>
						<td><s:property value="productName" /></td>
						<td><s:property value="productNameKana" /></td>
						<td><img
							src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
							width="50px" height="50px" /></td>
						<td><s:property value="price" />円</td>
						<td><s:property value="releaseCompany" /></td>
						<td><s:property value="releaseDate" /></td>
						<td><s:property value="productCount" /></td>
						<td><s:property value="totalPrice" />円</td>
					</tr>
				</s:iterator>
			</table>

			<h3>
				<s:label value="カート合計金額" />
				<s:property value="#session.cartTotalPrice" />
				円<br>
			</h3>
			<div class="left_button">
				<s:submit value="削除" id="deleteButton" class="disabled_button"
					disabled="true" />
			</div>
		</s:form>

		<s:form action="SettlementConfirmAction">
			<div class="right_button">
				<s:submit class="button" value="決済" />
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="info">カート情報がありません。</div>
	</s:else>

</body>
</html>
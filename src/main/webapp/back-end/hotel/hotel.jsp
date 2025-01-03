<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="UTF-8">
<title>Hotel首頁</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>Hotel: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Hotel</p>
	<h3>旅館查詢:</h3>

<!-- 錯誤表列 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='allHotel.jsp'>全部旅館</a> <br> <br></li>


		<li>
			<FORM METHOD="post" ACTION="hotel.do">
				<span>輸入旅館編號:</span> <input type="text" name="hotel_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />

		<li>
			<FORM METHOD="post" ACTION="hotel.do">
				<span>選擇旅館編號:</span> <select size="1" name="hotel_id">
					<c:forEach var="hotelVO" items="${hotelSvc.all}">
						<option value="${hotelVO.hotel_id}">${hotelVO.hotel_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="hotel.do">
				<span>選擇旅館名稱:</span> <select size="1" name="hotel_id">
					<c:forEach var="hotelVO" items="${hotelSvc.all}">
						<option value="${hotelVO.hotel_id}">${hotelVO.name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>旅館管理</h3>

	<ul>
		<li><a href='addHotel.jsp'>新增旅館</a></li>
	</ul>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotel.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
HotelVO hotelVO = (HotelVO) request.getAttribute("hotelVO");
%>
--<%=hotelVO == null%>--${hotelVO.hotel_id}--
<!-- line 100 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>旅館資料修改</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>旅館資料修改</h3>
				<h4>
					<a href="hotel.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="hotel.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>旅館編號:<font color="red"><b>*</b></font></td>
				<td><%=hotelVO.getHotel_id()%></td>
			</tr>
			<tr>
				<td>統一編號:</td>
				<td><input type="TEXT" name="tax_id"
					value="<%=hotelVO.getTax_id()%>" size="45" /></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="PASSWORD" name="password"
					value="<%=hotelVO.getPassword()%>" size="45" /></td>
			</tr>
			<tr>
				<td>旅館名稱:</td>
				<td><input type="TEXT" name="name"
					value="<%=hotelVO.getName()%>" size="45" /></td>
			</tr>
			<tr>
				<td>縣市:</td>
				<td><input type="TEXT" name="city"
					value="<%=hotelVO.getCity()%>" size="45" /></td>
			</tr>
			<tr>
				<td>地區:</td>
				<td><input type="TEXT" name="district"
					value="<%=hotelVO.getDistrict()%>" size="45" /></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="address"
					value="<%=hotelVO.getAddress()%>" size="45" /></td>
			</tr>
			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="phone_number"
					value="<%=hotelVO.getPhone_number()%>" size="45" /></td>
			</tr>
			<tr>
				<td>信箱:</td>
				<td><input type="TEXT" name="email"
					value="<%=hotelVO.getEmail()%>" size="45" /></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="status"
					value="<%=hotelVO.getStatus()%>" size="45" /></td>
			</tr>
			<tr>
				<td>建立時間:</td>
				<td><%=hotelVO.getCreate_time()%></td>
			</tr>
			<tr>
				<td>負責人:</td>
				<td><input type="TEXT" name="owner"
					value="<%=hotelVO.getOwner()%>" size="45" /></td>
			</tr>
			<tr>
				<td>旅館介紹:</td>
				<td><textarea name="info_text" rows="4" cols="45"><%=hotelVO.getInfo_text()%></textarea></td>
			</tr>
			<tr>
				<td>審核時間:</td>
				<td><%=hotelVO.getReview_time()%></td>
			</tr>
			<tr>
				<td>更新時間:</td>
				<td><%=hotelVO.getUpdate_time()%></td>
			</tr>
			<tr>
				<td>身分證正面:<img src="data:image/png;base64,<%=hotelVO.getId_front_base64()%>"
					alt="ID Front" width="100"></td>
				<td><input type="file" name="id_front"
					accept="image/*" /></td>
			</tr>
			<tr>
				<td>身分證反面:<img src="data:image/png;base64,<%=hotelVO.getId_back_base64()%>"
					alt="ID Back" width="100"></td>
				<td><input type="file" name="id_back"
					accept="image/*" /></td>
			</tr>
			<tr>
				<td>營業證照:<img src="data:image/png;base64,<%=hotelVO.getLicense_base64()%>"
					alt="License" width="100"></td>
				<td><input type="file" name="license"
					accept="image/*" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="hotel_id" value="<%=hotelVO.getHotel_id()%>">
		<input type="submit" value="送出修改">
	</FORM>

</body>
</html>
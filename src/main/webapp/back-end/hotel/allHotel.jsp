<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="java.util.Base64" %>>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
HotelService hotelSvc = new HotelService();
List<HotelVO> list = hotelSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有旅館資料</title>

<style>
/* 調整表格整體外觀 */
table {
	width: 100%; /* 設定表格寬度為 100% 適應父容器 */
	max-width: 1200px; /* 設定表格最大寬度，避免過寬 */
	margin: 10px auto; /* 置中並增加上下間距 */
	background-color: #f9f9f9;
	border-collapse: collapse; /* 去除雙線邊框 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 增加陰影效果 */
}

/* 表格內部邊框和文字格式 */
table, th, td {
	border: 1px solid #ddd;
	padding: 8px; /* 增加內邊距 */
	text-align: center;
}

/* 表格標題欄 */
th {
	background-color: #f2f2f2;
	color: #333;
	font-weight: bold;
	text-transform: uppercase;
}

/* 縮小圖片尺寸，避免表格過寬 */
td img {
	width: 80px; /* 固定寬度 */
	height: auto; /* 保持比例 */
	border-radius: 5px; /* 圓角 */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 增加陰影 */
}

/* 針對表格中的文字過長情況，限制字數 */
td {
	white-space: nowrap; /* 防止換行 */
	overflow: hidden; /* 隱藏超出部分 */
	text-overflow: ellipsis; /* 顯示省略號 */
}

/* 表格條紋效果（交替行顏色） */
tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:nth-child(odd) {
	background-color: #ffffff;
}

/* 響應式設計：縮小螢幕時調整表格顯示 */
@media ( max-width : 768px) {
	table, th, td {
		font-size: 12px; /* 調整字體大小 */
	}
	td img {
		width: 50px; /* 縮小圖片寬度 */
	}
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有旅館資料</h3>
				<h4>
					<a href="hotel.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>旅館編號</th>
			<th>統一編號</th>
			<th>密碼</th>
			<th>名稱</th>
			<th>縣市</th>
			<th>地區</th>
			<th>地址</th>
			<th>電話</th>
			<th>信箱</th>
			<th>狀態</th>
			<th>建立時間</th>
			<th>負責人</th>
			<th>身分證正面</th>
			<th>身分證反面</th>
			<th>營業證照</th>
			<th>旅館介紹</th>
			<th>審核時間</th>
			<th>更新時間</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="hotelVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${hotelVO.hotel_id}</td>
				<td>${hotelVO.tax_id}</td>
				<td>${hotelVO.password}</td>
				<td>${hotelVO.name}</td>
				<td>${hotelVO.city}</td>
				<td>${hotelVO.district}</td>
				<td>${hotelVO.address}</td>
				<td>${hotelVO.phone_number}</td>
				<td>${hotelVO.email}</td>
				<td>${hotelVO.status}</td>
				<td>${hotelVO.create_time}</td>
				<td>${hotelVO.owner}</td>
				<td><img src="data:image/png;base64,${hotelVO.id_front_base64}"
					alt="ID Front" width="100"></td>
				<td><img src="data:image/png;base64,${hotelVO.id_back_base64}"
					alt="ID Back" width="100"></td>
				<td><img src="data:image/png;base64,${hotelVO.license_base64}"
					alt="License" width="100"></td>
				<td>${hotelVO.info_text}</td>
				<td>${hotelVO.review_time}</td>
				<td>${hotelVO.update_time}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/hotel/hotel.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="hotel_id" value="${hotelVO.hotel_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/hotel/hotel.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="hotel_id" value="${hotelVO.hotel_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
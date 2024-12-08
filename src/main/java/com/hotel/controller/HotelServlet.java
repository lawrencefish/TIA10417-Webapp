package com.hotel.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.hotel.model.HotelService;
import com.hotel.model.HotelVO;

@WebServlet("/hotel.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 超過 1MB 的檔案將寫入磁碟
		maxFileSize = 10 * 1024 * 1024, // 單個檔案最大 5MB
		maxRequestSize = 50 * 1024 * 1024 // 整個請求最大 20MB
)
public class HotelServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("hotel_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入旅館編號");
			}

			Integer hotel_id = null;
			try {
				hotel_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("旅館編號格式不正確");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotel/hotel.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			HotelService hotelSvc = new HotelService();
			HotelVO hotelVO = hotelSvc.getOne(hotel_id);
			if (hotelVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotel/hotel.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("hotelVO", hotelVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/hotel/listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer hotel_id = Integer.valueOf(req.getParameter("hotel_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			HotelService hotelSvc = new HotelService();
			HotelVO hotelVO = hotelSvc.getOne(hotel_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("hotelVO", hotelVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/hotel/update_hotel_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer hotel_id = null;
			try {
				hotel_id = Integer.valueOf(req.getParameter("hotel_id").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("飯店編號: 格式不正確，必須為數字");
			}

			String tax_id = req.getParameter("tax_id");
			if (tax_id == null || tax_id.trim().length() == 0) {
				errorMsgs.add("統一編號: 請勿空白");
			} else if (!tax_id.matches("^[0-9]{8}$")) {
				errorMsgs.add("統一編號: 格式不正確，應為8位數字");
			}

			String password = req.getParameter("password");
			if (password == null || password.trim().length() < 6) {
				errorMsgs.add("密碼: 長度必須至少為6位");
			}

			String name = req.getParameter("name");
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("飯店名稱: 請勿空白");
			}

			String city = req.getParameter("city");
			if (city == null || city.trim().length() == 0) {
				errorMsgs.add("城市: 請勿空白");
			}

			String district = req.getParameter("district");
			if (district == null || district.trim().length() == 0) {
				errorMsgs.add("區域: 請勿空白");
			}

			String address = req.getParameter("address");
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("地址: 請勿空白");
			}

			String phone_number = req.getParameter("phone_number");
			if (phone_number == null || phone_number.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}

			String email = req.getParameter("email");
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("Email: 請勿空白");
			} else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
				errorMsgs.add("Email: 格式不正確");
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("狀態: 格式不正確，必須為數字");
			}

			String owner = req.getParameter("owner");
			if (owner == null || owner.trim().length() == 0) {
				errorMsgs.add("負責人名稱: 請勿空白");
			}

			String info_text = req.getParameter("info_text");
			if (info_text == null || info_text.trim().length() == 0) {
				errorMsgs.add("飯店介紹: 請勿空白");
			}

			// 圖片檔案處理
			// 檔案處理
			// 獲取 Part
			Part idFrontPart = req.getPart("id_front");
			Part idBackPart = req.getPart("id_back");
			Part licensePart = req.getPart("license");

			// 初始化變數
			byte[] idFront = null;
			byte[] idBack = null;
			byte[] license = null;

			HotelService hotelSvcforimg = new HotelService();
			HotelVO hotelVOforimg = hotelSvcforimg.getOne(hotel_id);
			// 處理每個檔案字段
			if (idFrontPart == null || idFrontPart.getSize() == 0) {
				idFront = hotelVOforimg.getId_front();
			} else {
				idFront = idFrontPart.getInputStream().readAllBytes();
			}
			if (idBackPart == null || idBackPart.getSize() == 0) {
				idBack = hotelVOforimg.getId_back();
			} else {
				idBack = idBackPart.getInputStream().readAllBytes();
			}
			if (licensePart == null || licensePart.getSize() == 0) {
				license = hotelVOforimg.getLicense();
			} else {
				license = licensePart.getInputStream().readAllBytes();
			}

			HotelVO hotelVO = new HotelVO();
			hotelVO.setHotel_id(hotel_id);
			hotelVO.setTax_id(tax_id);
			hotelVO.setPassword(password);
			hotelVO.setName(name);
			hotelVO.setCity(city);
			hotelVO.setDistrict(district);
			hotelVO.setAddress(address);
			hotelVO.setPhone_number(phone_number);
			hotelVO.setEmail(email);
			hotelVO.setStatus(status);
			hotelVO.setOwner(owner);
			hotelVO.setInfo_text(info_text);

			// 照片
			hotelVO.setId_front(idFront);
			hotelVO.setId_back(idBack);
			hotelVO.setLicense(license);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				if (idFrontPart != null || idBackPart != null || licensePart != null) {
					errorMsgs.add("請重新上傳照片");
				}
				HotelService hotelSvc = new HotelService();
				HotelVO originalHotelVO = hotelSvc.getOne(hotel_id);

				// 保留只讀字段值
				hotelVO.setCreate_time(originalHotelVO.getCreate_time());
				hotelVO.setReview_time(originalHotelVO.getReview_time());
				hotelVO.setUpdate_time(originalHotelVO.getUpdate_time());
//				if (hotelVO.getId_front() != null) {
//			        hotelVO.setId_front_base64(Base64.getEncoder().encodeToString(hotelVO.getId_front()));
//			    }
//				if (hotelVO.getId_back() != null) {
//			        hotelVO.setId_back_base64(Base64.getEncoder().encodeToString(hotelVO.getId_back()));
//			    }
//				if (hotelVO.getLicense() != null) {
//			        hotelVO.setLicense_base64(Base64.getEncoder().encodeToString(hotelVO.getLicense()));
//			    }
				// 照片
				idFront = hotelVOforimg.getId_front();
				idBack = hotelVOforimg.getId_back();
				license = hotelVOforimg.getLicense();
				hotelVO.setId_front(idFront);
				hotelVO.setId_back(idBack);
				hotelVO.setLicense(license);
				// 將包含錯誤的 hotelVO 返回頁面

				req.setAttribute("hotelVO", hotelVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotel/update_hotel_input.jsp");
				failureView.forward(req, res);

				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			HotelService hotelSvc = new HotelService();
			hotelVO = hotelSvc.updateHotel(hotel_id, tax_id, password, name, city, district, address, phone_number,
					email, status, owner, info_text, idFront, idBack, license);
			hotelVO = hotelSvc.getOne(hotel_id);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("hotelVO", hotelVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/hotel/listOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String tax_id = req.getParameter("tax_id");
			if (tax_id == null || tax_id.trim().length() == 0) {
				errorMsgs.add("統一編號: 請勿空白");
			} else if (!tax_id.matches("^[0-9]{8}$")) {
				errorMsgs.add("統一編號: 格式不正確，應為8位數字");
			}

			String password = req.getParameter("password");
			if (password == null || password.trim().length() < 6) {
				errorMsgs.add("密碼: 長度必須至少為6位");
			}

			String name = req.getParameter("name");
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("飯店名稱: 請勿空白");
			}

			String city = req.getParameter("city");
			if (city == null || city.trim().length() == 0) {
				errorMsgs.add("城市: 請勿空白");
			}

			String district = req.getParameter("district");
			if (district == null || district.trim().length() == 0) {
				errorMsgs.add("區域: 請勿空白");
			}

			String address = req.getParameter("address");
			if (address == null || address.trim().length() == 0) {
				errorMsgs.add("地址: 請勿空白");
			}

			String phone_number = req.getParameter("phone_number");
			if (phone_number == null || phone_number.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			}

			String email = req.getParameter("email");
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("Email: 請勿空白");
			} else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
				errorMsgs.add("Email: 格式不正確");
			}

			Integer status = 0;

			String owner = req.getParameter("owner");
			if (owner == null || owner.trim().length() == 0) {
				errorMsgs.add("負責人名稱: 請勿空白");
			}

			String info_text = req.getParameter("info_text");
			if (info_text == null || info_text.trim().length() == 0) {
				errorMsgs.add("飯店介紹: 請勿空白");
			}

			// 圖片檔案處理
			// 檔案處理
			// 獲取 Part
			Part idFrontPart = req.getPart("id_front");
			Part idBackPart = req.getPart("id_back");
			Part licensePart = req.getPart("license");

			// 初始化變數
			byte[] idFront = null;
			byte[] idBack = null;
			byte[] license = null;

			// 處理每個檔案字段
			if (idFrontPart == null || idFrontPart.getSize() == 0) {
				errorMsgs.add("身分證正面必須新增圖片");
			} else {
				idFront = idFrontPart.getInputStream().readAllBytes();
			}

			if (idBackPart == null || idBackPart.getSize() == 0) {
				errorMsgs.add("身分證反面必須新增圖片");
			} else {
				idBack = idBackPart.getInputStream().readAllBytes();
			}

			if (licensePart == null || licensePart.getSize() == 0) {
				errorMsgs.add("營業證照必須新增圖片");
			} else {
				license = licensePart.getInputStream().readAllBytes();
			}

			HotelVO hotelVO = new HotelVO();
			hotelVO.setTax_id(tax_id);
			hotelVO.setPassword(password);
			hotelVO.setName(name);
			hotelVO.setCity(city);
			hotelVO.setDistrict(district);
			hotelVO.setAddress(address);
			hotelVO.setPhone_number(phone_number);
			hotelVO.setEmail(email);
			hotelVO.setStatus(status);
			hotelVO.setOwner(owner);
			hotelVO.setInfo_text(info_text);

			// 照片
			hotelVO.setId_front(idFront);
			hotelVO.setId_back(idBack);
			hotelVO.setLicense(license);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("hotelVO", hotelVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hotel/addHotel.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			HotelService hotelSvc = new HotelService();
			hotelVO = hotelSvc.addHotel(tax_id, password, name, city, district, address, phone_number, email, status,
					owner, info_text, idFront, idBack, license);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/hotel/allHotel.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer hotel_id = Integer.valueOf(req.getParameter("hotel_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			HotelService hotelSvc = new HotelService();
			hotelSvc.deleteHotel(hotel_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/hotel/allHotel.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}

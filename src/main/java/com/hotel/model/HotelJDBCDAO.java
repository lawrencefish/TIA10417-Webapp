package com.hotel.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelJDBCDAO implements HotelDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tomdb?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT = "INSERT INTO hotel (tax_id, password, name, city, district, address, phone_number, email, status, owner, info_text, id_front, id_back, license) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM hotel ORDER BY hotel_id";
	private static final String GET_ONE = "SELECT * FROM hotel where hotel_id = ?";
	private static final String DELETE = "DELETE FROM hotel where hotel_id = ?";
	private static final String UPDATE = "UPDATE hotel set tax_id=?, password=?, name=?, city=?, district=?, address=?, phone_number=?, email=?, status=?, owner=?, info_text=?, id_front=?, id_back=?, license=? where hotel_id = ?";
	
	@Override
	public void insert(HotelVO hotelVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, hotelVO.getTax_id());
			pstmt.setString(2, hotelVO.getPassword());
			pstmt.setString(3, hotelVO.getName());
			pstmt.setString(4, hotelVO.getCity());
			pstmt.setString(5, hotelVO.getDistrict());
			pstmt.setString(6, hotelVO.getAddress());
			pstmt.setString(7, hotelVO.getPhone_number());
			pstmt.setString(8, hotelVO.getEmail());
			pstmt.setInt(9, hotelVO.getStatus());
			pstmt.setString(10, hotelVO.getOwner());
			pstmt.setString(11, hotelVO.getInfo_text());
			pstmt.setBytes(12, hotelVO.getId_front());
            pstmt.setBytes(13, hotelVO.getId_back());
            pstmt.setBytes(14, hotelVO.getLicense());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(HotelVO hotelVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, hotelVO.getTax_id());
			pstmt.setString(2, hotelVO.getPassword());
			pstmt.setString(3, hotelVO.getName());
			pstmt.setString(4, hotelVO.getCity());
			pstmt.setString(5, hotelVO.getDistrict());
			pstmt.setString(6, hotelVO.getAddress());
			pstmt.setString(7, hotelVO.getPhone_number());
			pstmt.setString(8, hotelVO.getEmail());
			pstmt.setInt(9, hotelVO.getStatus());
			pstmt.setString(10, hotelVO.getOwner());
			pstmt.setString(11, hotelVO.getInfo_text());
			pstmt.setBytes(12, hotelVO.getId_front());
            pstmt.setBytes(13, hotelVO.getId_back());
            pstmt.setBytes(14, hotelVO.getLicense());

            pstmt.setInt(15, hotelVO.getHotel_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer hotel_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hotel_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public HotelVO findByPrimaryKey(Integer hotel_id) {

		HotelVO hotelVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setInt(1, hotel_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				hotelVO = new HotelVO();
				hotelVO.setHotel_id(rs.getInt("hotel_id"));
				hotelVO.setTax_id(rs.getString("tax_id"));
				hotelVO.setPassword(rs.getString("password"));
				hotelVO.setName(rs.getString("name"));
				hotelVO.setCity(rs.getString("city"));
				hotelVO.setDistrict(rs.getString("district"));
				hotelVO.setAddress(rs.getString("address"));
				hotelVO.setPhone_number(rs.getString("phone_number"));
				hotelVO.setEmail(rs.getString("email"));
				hotelVO.setStatus(rs.getInt("status"));
				hotelVO.setCreate_time(rs.getTimestamp("create_time"));
				hotelVO.setOwner(rs.getString("owner"));

				// 處理 BLOB 欄位
				hotelVO.setId_front(rs.getBytes("id_front"));
				hotelVO.setId_back(rs.getBytes("id_back"));
				hotelVO.setLicense(rs.getBytes("license"));

				hotelVO.setInfo_text(rs.getString("info_text"));
				hotelVO.setReview_time(rs.getTimestamp("review_time"));
				hotelVO.setUpdate_time(rs.getTimestamp("update_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return hotelVO;
	}

	@Override
	public List<HotelVO> getAll() {
		List<HotelVO> list = new ArrayList<HotelVO>();
		HotelVO hotelVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				hotelVO = new HotelVO();
				hotelVO.setHotel_id(rs.getInt("hotel_id"));
				hotelVO.setTax_id(rs.getString("tax_id"));
				hotelVO.setPassword(rs.getString("password"));
				hotelVO.setName(rs.getString("name"));
				hotelVO.setCity(rs.getString("city"));
				hotelVO.setDistrict(rs.getString("district"));
				hotelVO.setAddress(rs.getString("address"));
				hotelVO.setPhone_number(rs.getString("phone_number"));
				hotelVO.setEmail(rs.getString("email"));
				hotelVO.setStatus(rs.getInt("status"));
				hotelVO.setCreate_time(rs.getTimestamp("create_time"));
				hotelVO.setOwner(rs.getString("owner"));

				// 處理 BLOB 欄位
				hotelVO.setId_front(rs.getBytes("id_front"));
				hotelVO.setId_back(rs.getBytes("id_back"));
				hotelVO.setLicense(rs.getBytes("license"));

				hotelVO.setInfo_text(rs.getString("info_text"));
				hotelVO.setReview_time(rs.getTimestamp("review_time"));
				hotelVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(hotelVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		
		// 新增
//		HotelVO hotelVO1 = new HotelVO();
//		hotelVO1.setTax_id("12345678"); // 統一編號
//		hotelVO1.setPassword("securepassword"); // 密碼
//		hotelVO1.setName("Hotel Luxury"); // 飯店名稱
//		hotelVO1.setCity("Taipei"); // 城市
//		hotelVO1.setDistrict("Xinyi"); // 行政區
//		hotelVO1.setAddress("No.123, Sec. 1, Keelung Rd., Xinyi Dist., Taipei City"); // 地址
//		hotelVO1.setPhone_number("0987654321"); // 電話號碼
//		hotelVO1.setEmail("luxury@hotel.com"); // 電子郵件
//		hotelVO1.setStatus(1); // 狀態 (1 = 啟用)
//		hotelVO1.setOwner("James Smith"); // 負責人姓名
//		hotelVO1.setInfo_text("A premium hotel located in the heart of Taipei."); // 飯店描述
//		dao.insert(hotelVO1);

		// 修改
//		HotelVO hotelVO2 = new HotelVO();
//		hotelVO2.setHotel_id(3); // 更新的目標 hotel_id，必須是已存在的記錄
//		hotelVO2.setTax_id("12345678"); // 更新的統一編號
//		hotelVO2.setPassword("newsecurepassword"); // 更新的密碼
//		hotelVO2.setName("Updated Hotel Luxury"); // 更新的飯店名稱
//		hotelVO2.setCity("New Taipei"); // 更新的城市
//		hotelVO2.setDistrict("Banqiao"); // 更新的行政區
//		hotelVO2.setAddress("No.456, Sec. 2, Minsheng Rd., Banqiao Dist., New Taipei City"); // 更新的地址
//		hotelVO2.setPhone_number("0987654321"); // 更新的電話號碼
//		hotelVO2.setEmail("updatedluxury@hotel.com"); // 更新的電子郵件
//		hotelVO2.setStatus(2); // 更新的狀態 (2 = 審核沒通過)
//		hotelVO2.setOwner("Updated Owner Name"); // 更新的負責人
//		hotelVO2.setInfo_text("An updated premium hotel with modern facilities."); // 更新的飯店描述
//		dao.update(hotelVO2);

		// 刪除
//		dao.delete(1);

		// 查詢
//		HotelVO hotelVO3 = dao.findByPrimaryKey(1); // 查詢 hotel_id 為 1 的記錄
//		System.out.print(hotelVO3.getHotel_id() + ",");
//	    System.out.print(hotelVO3.getTax_id() + ",");
//	    System.out.print(hotelVO3.getPassword() + ",");
//	    System.out.print(hotelVO3.getName() + ",");
//	    System.out.print(hotelVO3.getCity() + ",");
//	    System.out.print(hotelVO3.getDistrict() + ",");
//	    System.out.print(hotelVO3.getAddress() + ",");
//	    System.out.print(hotelVO3.getPhone_number() + ",");
//	    System.out.print(hotelVO3.getEmail() + ",");
//	    System.out.print(hotelVO3.getStatus() + ",");
//	    System.out.print(hotelVO3.getCreate_time() + ",");
//	    System.out.print(hotelVO3.getOwner() + ",");
//	    System.out.print(hotelVO3.getId_front() != null ? "[id_front exists]" : "[null]" + ",");
//	    System.out.print(hotelVO3.getId_back() != null ? "[id_back exists]" : "[null]" + ",");
//	    System.out.print(hotelVO3.getLicense() != null ? "[license exists]" : "[null]" + ",");
//	    System.out.print(hotelVO3.getInfo_text() + ",");
//	    System.out.print(hotelVO3.getReview_time() + ",");
//	    System.out.print(hotelVO3.getUpdate_time());
//	    System.out.println();

		// 全部查詢
//		List<HotelVO> list = dao.getAll();
//		for (HotelVO hotel : list) {
//			System.out.print(hotel.getHotel_id() + ",");
//		    System.out.print(hotel.getTax_id() + ",");
//		    System.out.print(hotel.getPassword() + ",");
//		    System.out.print(hotel.getName() + ",");
//		    System.out.print(hotel.getCity() + ",");
//		    System.out.print(hotel.getDistrict() + ",");
//		    System.out.print(hotel.getAddress() + ",");
//		    System.out.print(hotel.getPhone_number() + ",");
//		    System.out.print(hotel.getEmail() + ",");
//		    System.out.print(hotel.getStatus() + ",");
//		    System.out.print(hotel.getCreate_time() + ",");
//		    System.out.print(hotel.getOwner() + ",");
//		    System.out.print(hotel.getId_front() != null ? "[id_front exists]" : "[null]" + ",");
//		    System.out.print(hotel.getId_back() != null ? "[id_back exists]" : "[null]" + ",");
//		    System.out.print(hotel.getLicense() != null ? "[license exists]" : "[null]" + ",");
//		    System.out.print(hotel.getInfo_text() + ",");
//		    System.out.print(hotel.getReview_time() + ",");
//		    System.out.print(hotel.getUpdate_time());
//		    System.out.println();
//		}

	}
}

package com.hotel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelJNDIDAO implements HotelDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB87");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, hotel_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}

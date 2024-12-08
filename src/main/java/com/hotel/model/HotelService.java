package com.hotel.model;

import java.util.List;
import java.util.Base64;
import java.util.Collections;

import com.emp.model.EmpVO;

public class HotelService {

	private HotelDAO_interface dao;

	public HotelService() {
		dao = new HotelJNDIDAO();
	}

	public HotelVO addHotel(String tax_id, String password, String name, String city, String district, String address,
			String phone_number, String email, Integer status, String owner, String info_text,byte[] id_front, byte[] id_back, byte[] license) {
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
		hotelVO.setId_front(id_front);
		hotelVO.setId_back(id_back);
		hotelVO.setLicense(license);
		
		
		dao.insert(hotelVO);

		return hotelVO;
	}

	public HotelVO updateHotel(Integer hotel_id, String tax_id, String password, String name, String city,
			String district, String address, String phone_number, String email, Integer status, String owner,
			String info_text,byte[] id_front, byte[] id_back, byte[] license) {
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
		hotelVO.setId_front(id_front);
		hotelVO.setId_back(id_back);
		hotelVO.setLicense(license);
		
		dao.update(hotelVO);

		return hotelVO;
	}

	public void deleteHotel(Integer hotel_id) {
		dao.delete(hotel_id);
	}

	public HotelVO getOne(Integer hotel_id) {
		HotelVO hotelVO = dao.findByPrimaryKey(hotel_id);

	    if (hotelVO == null) {
	        return null; // 如果未找到，返回 null
	    }

	    // 處理圖片的 Base64 編碼
	    if (hotelVO.getId_front() != null) {
	        hotelVO.setId_front_base64(Base64.getEncoder().encodeToString(hotelVO.getId_front()));
	    }
	    if (hotelVO.getId_back() != null) {
	        hotelVO.setId_back_base64(Base64.getEncoder().encodeToString(hotelVO.getId_back()));
	    }
	    if (hotelVO.getLicense() != null) {
	        hotelVO.setLicense_base64(Base64.getEncoder().encodeToString(hotelVO.getLicense()));
	    }

	    return hotelVO;
	}

//	public List<HotelVO> getAll() {
//		List<HotelVO> list = dao.getAll();
//
//		
//		list.forEach((hotel) -> {
//			hotel.setId_front_base64(new String(Base64.getEncoder().encode(hotel.getId_front())));
//			hotel.setId_back_base64(new String(Base64.getEncoder().encode(hotel.getId_back())));
//			hotel.setLicense_base64(new String(Base64.getEncoder().encode(hotel.getLicense())));
//		});
//
//		return list;
//	}
	public List<HotelVO> getAll() {
		List<HotelVO> list = dao.getAll();
		if (list == null || list.isEmpty()) {
			return Collections.emptyList(); // 避免返回 null
		}

		list.forEach((hotel) -> {
			if (hotel.getId_front() != null) {
				hotel.setId_front_base64(Base64.getEncoder().encodeToString(hotel.getId_front()));
			}
			if (hotel.getId_back() != null) {
				hotel.setId_back_base64(Base64.getEncoder().encodeToString(hotel.getId_back()));
			}
			if (hotel.getLicense() != null) {
				hotel.setLicense_base64(Base64.getEncoder().encodeToString(hotel.getLicense()));
			}
		});

		return list;
	}

}

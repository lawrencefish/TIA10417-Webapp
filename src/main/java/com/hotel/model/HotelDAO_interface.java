package com.hotel.model;

import java.util.List;

import com.emp.model.EmpVO;

public interface HotelDAO_interface {
    public void insert(HotelVO hotelVO);
    public void update(HotelVO hotelVO);
    public void delete(Integer hotel_id);
    public HotelVO findByPrimaryKey(Integer hotel_id);
    public List<HotelVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

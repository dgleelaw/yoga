package com.woniuxy.yogasystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.woniuxy.yogasystem.dto.CoachDto;
import com.woniuxy.yogasystem.dto.VenuesDto;
import com.woniuxy.yogasystem.pojo.Address;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Venues_Img;

public interface DtoDao {
@Select("SELECT * FROM coach INNER JOIN address ON coach.uid=address.uid")
public List<CoachDto> findAllCoachDto();
@Select("select * from venues INNER JOIN address ON venues.uid=address.uid")
@Results({
	@Result(id = true, column = "id", property = "id"), 
	@Result(column = "uid", property = "uid"),
	@Result(column = "name", property = "name"), 
	@Result(column = "province", property = "province"),
	@Result(column = "city", property = "city"),
	@Result(column = "town", property = "town"),
	@Result(column = "county", property = "county"),
	@Result(column = "descrie", property = "descrie"),
	@Result(column = "detail", property = "detail"),
	@Result(column = "phone", property = "phone"),
	@Result(column = "vx", property = "vx"),
	@Result(column = "img", property = "img"),
	@Result(column = "vy", property = "vy"),
	@Result(column = "id", property = "list", many = @Many(select = "findVenues_Img", fetchType = FetchType.EAGER))
})
public List<VenuesDto> findAllVenuesDto();
@Select("select * from venues_img")
public List<Venues_Img>findVenues_Img();
}

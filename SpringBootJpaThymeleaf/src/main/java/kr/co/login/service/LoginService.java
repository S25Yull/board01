package kr.co.login.service;

import java.util.Optional;

import kr.co.login.dto.TbUserDto;

public interface LoginService {
	/*관리자 정보 조회*/
	public TbUserDto selectLogin(TbUserDto dto);

	//public Long insertUser(TbUserDto dto);

	public TbUserDto insertUser(TbUserDto dto);
	
}

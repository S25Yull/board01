package kr.co.login.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.login.domain.TbUser;
import kr.co.login.dto.TbUserDto;
import kr.co.login.repository.LoginRepository;
import kr.co.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

/*implements는 인터페이스를 상속받아서 구현하는 클래스*/

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	//@Autowired
	//ModelMapper modelMapper;
	
	/* 관리자 로그인 조회*/
	public TbUserDto selectLogin(TbUserDto dto) {
		return loginRepository.findByUserIdAndUserPw(dto.getUserId(), dto.getUserPw());
	}
	
//	public Long insertUser(TbUserDto dto) {
//		
//		TbUser tbUser = loginRepository.save(dto.toEntity());
//        return tbUser.getUserNo();
//		
//	}

	public TbUserDto insertUser(TbUserDto dto) {
		
		ModelMapper modelMapper = new ModelMapper();
				
		return modelMapper.map(loginRepository.save(dto.toEntity()), TbUserDto.class);
	}	
	
}

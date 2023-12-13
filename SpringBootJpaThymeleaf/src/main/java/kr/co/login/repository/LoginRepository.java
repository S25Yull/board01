package kr.co.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.login.domain.TbUser;
import kr.co.login.dto.TbUserDto;

public interface LoginRepository extends JpaRepository<TbUser, Long> {

	TbUserDto findByUserIdAndUserPw(String userId, String userPw);
	
}

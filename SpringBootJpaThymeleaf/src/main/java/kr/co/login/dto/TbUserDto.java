package kr.co.login.dto;

import java.time.LocalDateTime;

import kr.co.login.domain.TbUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TbUserDto {

	private Long userNo;
	private String userId;
	private String userPw;
	private String userNm;
	private String userMail;
	private LocalDateTime userRegDt;
	private LocalDateTime userModDt;

    public TbUser toEntity(){
        return TbUser.builder()
        		.userNo(userNo)
        		.userId(userId)
                .userPw(userPw)
                .userNm(userNm)
                .userMail(userMail)
                .userRegDt(userRegDt)
                .userModDt(userModDt)
                .build();
    }

}

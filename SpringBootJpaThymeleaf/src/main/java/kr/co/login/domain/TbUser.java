package kr.co.login.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder
@Entity //데이터베이스와 직접 테이블이 연결된 클래스
@EqualsAndHashCode(callSuper = true)
public class TbUser extends BaseTimeEntity{

	@Id	//해당 테이블의 PK 필드를 나타낸다.
	@SequenceGenerator(
			  name = "USER_SEQ",			//시퀀스 제너레이터 이름
			  sequenceName = "TB_USER_SEQ", //시퀀스 이름
			  initialValue = 1,				//시작값
			  allocationSize = 1			//메모리를 통해 할당할 범위 사이즈
			 )
	@GeneratedValue(
			strategy = GenerationType. SEQUENCE, 	//사용할 전략을 시퀀스로 선택
			generator = "USER_SEQ"					//식별자 생성기를 설정해놓은 USER_SEQ으로 설정 
		)
	@Column(name="USER_NO", unique = true)
	private Long userNo;
	
	@Column(name="USER_ID")		//실제 테이블의 컬럼명을 맵핑해준다. 실제 테이블의 컬럼은 대문자_대문자 형태임
	private String userId;
	
	@Column(name="USER_PW")
	private String userPw;
	
	@Column(name="USER_NM")
	private String userNm;
	
	@Column(name="USER_MAIL")	
	private String userMail;
	
	@Column(name="USER_REG_DT")	
	private LocalDateTime userRegDt;
	
	@Column(name="USER_MOD_DT")	
	private LocalDateTime userModDt;
	
}

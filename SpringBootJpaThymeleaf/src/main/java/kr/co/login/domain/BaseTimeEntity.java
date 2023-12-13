package kr.co.login.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

//이 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리

@Data
@MappedSuperclass // JPA Entity 클래스들이 이 클래스를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // 

public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될 때 자동으로 시간이 저장됨
    private LocalDateTime userRegDt;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime userModDt;

}

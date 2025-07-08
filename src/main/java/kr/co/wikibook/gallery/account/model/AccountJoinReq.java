package kr.co.wikibook.gallery.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor // 기본 생성자
@AllArgsConstructor
public class AccountJoinReq {
//    프론트에서 회원가입 시 받아야하는 데이터
    private String name;
    private String loginId;
    private String loginPw;
}

package kr.co.wikibook.gallery.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AccountLoginRes {
    private int id;
    
//    json 생성 시 loginPw는 제외됨
    @JsonIgnore
    private String loginPw;
}

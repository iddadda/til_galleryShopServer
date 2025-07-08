package kr.co.wikibook.gallery.account;

import kr.co.wikibook.gallery.account.model.AccountJoinReq;
import kr.co.wikibook.gallery.account.model.AccountLoginReq;
import kr.co.wikibook.gallery.account.model.AccountLoginRes;
import kr.co.wikibook.gallery.commom.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public int join(AccountJoinReq req) {
//        비밀번호 암호화
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());
//        암호화가 된 비밀번호를 갖는 AccountJoinReq 객체 생성 (아이디, 이름도 포함)
        AccountJoinReq changedReq = new AccountJoinReq(req.getName(), req.getLoginId(), hashedPw);
        return accountMapper.save(changedReq);
    }

    public AccountLoginRes login(AccountLoginReq req) {
        AccountLoginRes res = accountMapper.findByLoginId(req);
// 비밀번호 체크 -> 암호화 전 비밀번호와 암호화된 비밀번호를 인자로 주어 비교하는 메소드
//        비밀번호가 다르면 "return null;" 처리
        if (res == null || !BCrypt.checkpw(req.getLoginPw(), res.getLoginPw())) {
            return null;
        }


        return res;
    }
}

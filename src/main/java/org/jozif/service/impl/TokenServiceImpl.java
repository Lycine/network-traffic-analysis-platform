package org.jozif.service.impl;

import org.jozif.dao.TokenDao;
import org.jozif.entity.Token;
import org.jozif.service.TokenService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hongyu on 2017/4/30.
 */
@CommonsLog
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    private TokenDao tokenDao;

    @Autowired
    public TokenServiceImpl(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @Override
    public Boolean isDuplicateToken(Token token) {
        return null != tokenDao.selectTokenByToken(token);
    }

    @Override
    public Boolean tokenAdd(Token token) {
        return tokenDao.tokenAdd(token)> 0;
    }

    @Override
    public Token tokenFindById(Token token) {
        return tokenDao.selectTokenById(token);
    }

    @Override
    public Token tokenFindByToken(Token token) {
        return tokenDao.selectTokenByToken(token);
    }

    @Override
    public Boolean tokenExpiredDelete() {
        return tokenDao.deleteTokenByExpireTime()> 0;
    }

    @Override
    public Boolean tokenDeleteById(Token token) {
        return tokenDao.deleteTokenById(token) > 0;
    }
}

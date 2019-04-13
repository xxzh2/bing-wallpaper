package com.ginkgo.bing.wallpaper.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgo.bing.wallpaper.mvc.dao.AuthMapper;
import com.ginkgo.bing.wallpaper.mvc.model.AuthEntity;
import com.ginkgo.bing.wallpaper.util.Keygen;

import lombok.extern.slf4j.Slf4j;

/**
 * 授权服务
 * 
 * @author Think
 *
 */
@Slf4j
@Service
public class AuthService {

	@Autowired
	private AuthMapper dao;

	public AuthEntity getAuth(String key) {
		if (key == null)
			return null;
		String unpackedKey = Keygen.unpack(key);
		List<AuthEntity> all = dao.getAuthAll();
		if (unpackedKey == null || all == null || all.size() < 1) {
			return null;
		}
		for (AuthEntity auth : all) {
			String rot = Keygen.rot13(auth.getAppId() + auth.getAppKey());
			String gen = Keygen.getMD5(rot).toLowerCase();
			log.debug(gen);
			if (gen.contains(unpackedKey.toLowerCase())) {
				return auth;
			}
		}
		return null;
	}

	public String getAuthorize(String user, String pwd) {
		return Keygen.keyGen(user, pwd);
	}

}

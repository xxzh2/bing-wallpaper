package com.ginkgo.fms.util;

import org.junit.Test;

import com.ginkgo.bing.wallpaper.util.Keygen;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeygenTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		String gen = Keygen.keyGen("admin", "123456");
		log.info("gen->" +gen);
		
		String decodedGen = Keygen.unpack(gen);
		log.info("decodedGen->" +decodedGen);
	}
	
	
	

}

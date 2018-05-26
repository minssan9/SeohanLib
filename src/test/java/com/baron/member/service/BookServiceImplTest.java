package com.baron.member.service;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.baron.member.model.BoardModel;

@RunWith(SpringJUnit4ClassRunner.class)

public class BookServiceImplTest {

	@Autowired
	private BookService bookservice;



	private URL getSearchUrl(String keyword) throws Exception {
		String key = "B0F933E2847C6447203572CCC68F824A1054E7EF0D966C7B95245288CE95E300";
		String addr = "http://book.interpark.com/api/search.api?";
		String parameter = "";
		System.out.println(keyword);
		key = URLEncoder.encode(key, "UTF-8");
		keyword = URLEncoder.encode(keyword, "UTF-8");
		parameter = parameter + "&" + "query=" + keyword;
		parameter = parameter + "&" + "sort=salesPoint";
		parameter = parameter + "&" + "maxResults=100";

		addr = addr + "key=" + key + parameter;

		URL url = new URL(addr);
		getApiTest(url);
		return url;
	}
	
	private void getApiTest(URL url) throws IOException {
		BufferedReader br;
		/* api 異쒕젰 �솗�씤 */
		br = new BufferedReader(new InputStreamReader(url.openStream()));

		String line;
		StringBuilder sBuffer = new StringBuilder();

		while ((line = br.readLine()) != null) {
			sBuffer.append(line);
			System.out.println(line);
		}

		br.close();
	}
	
}

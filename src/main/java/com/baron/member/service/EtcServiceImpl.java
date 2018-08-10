package com.baron.member.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baron.member.dao.EtcDao;
import com.baron.member.dao.SmsDao;
import com.baron.member.dao.SmsDaoImpl;
import com.baron.member.model.ItDamage;
import com.baron.member.model.SmsModel;

@Service
public class EtcServiceImpl implements EtcService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	private EtcDao etcdao;
	@Autowired
	private SmsDao smsdao;

	@Override
	public List<String> dinnerList(String nowDate) {

		return etcdao.dinnerList(nowDate);
	}

	@Override
	public List<String> lunchList(String nowDate) {

		return etcdao.lunchList(nowDate);
	}

	@Override
	public List<String> breakfastList(String nowDate) {

		return etcdao.breakfastList(nowDate);
	}

	@Override
	public List<ItDamage> damageList() {
		return etcdao.itDamageList();
	}

	@Override
	public void endDamage(ItDamage itDamage) { 
		Calendar cal = Calendar.getInstance();
		String nowDate = sdf.format(cal.getTime());		 
		SmsModel smsModel = new SmsModel();
		itDamage.setCtime(nowDate);
		smsModel.setContent(itDamage.getRtxt() + " - 조치 완료 / 확인 후 미조치사항 전산팀 연락 바람");
		smsModel.setPhone(itDamage.getRtel());
		smsModel.setSendNo("043-530-3174");
		
		 etcdao.endDamage(itDamage);
		 smsdao.sendSms(smsModel);		
	}

}

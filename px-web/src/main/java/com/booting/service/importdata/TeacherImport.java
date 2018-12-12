/**create by liuhua at 2018年1月20日 上午9:57:13**/
package com.booting.service.importdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.booting.common.SpringBeanUtil;
import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.importdata.AbstractDataImport;
import com.star.framework.importdata.RecordType;

public class TeacherImport extends AbstractDataImport<TeacherDTO> {

	private KindergartenWebService kindergartenWebService = SpringBeanUtil.getBean("kindergartenWebService", KindergartenWebService.class);
	
	public TeacherImport(byte[] bs, String filename) {
		super(bs, filename);
	}

	@Override
	public List<RecordType> getRecordTypes() {
		List<RecordType> types = new ArrayList<>();
		types.add(new RecordType("schoolName", String.class, "文本"));
		types.add(new RecordType("teacherName", String.class, "文本"));
		types.add(new RecordType("teacherMobile", Integer.class, String.class, "文本", "电话号码"));
		types.add(new RecordType("intro", String.class, "文本"));
		return types;
	}

	@Override
	public Class<TeacherDTO> getBeanType() {
		return TeacherDTO.class;
	}

	@Override
	public Map<Integer, Object> saveBean(List<TeacherDTO> list) {
		Map<Integer, Object> errorIdx = new LinkedHashMap<>();
		List<TeacherDTO> data = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			TeacherDTO teacherDTO = list.get(i);
			KindergartenDTO school = kindergartenWebService.getKindergarten(teacherDTO.getSchoolName());
			if (null == school) {
				errorIdx.put(i, 0);
				continue;
			}
			teacherDTO.setSchoolId(school.getSchoolId());
			teacherDTO.setCreateTime(new Date());
			teacherDTO.setEnabled(1);
			teacherDTO.setDeleted(0);
			data.add(teacherDTO);
			try {
				kindergartenWebService.saveTeacher(teacherDTO);
			} catch (Exception e) {
				errorIdx.put(i, "保存失败：" + e.getMessage());
				e.printStackTrace();
			}
		}
		return errorIdx;
	}
}

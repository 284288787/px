/**create by liuhua at 2018年1月20日 上午9:57:13**/
package com.booting.service.importdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.booting.common.SpringBeanUtil;
import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.importdata.AbstractDataImport;
import com.star.framework.importdata.RecordType;

public class ClassImport extends AbstractDataImport<ClassDTO> {

	private KindergartenWebService kindergartenWebService = SpringBeanUtil.getBean("kindergartenWebService", KindergartenWebService.class);
	
	public ClassImport(byte[] bs, String filename) {
		super(bs, filename);
	}

	@Override
	public List<RecordType> getRecordTypes() {
		List<RecordType> types = new ArrayList<>();
		types.add(new RecordType("schoolName", String.class, "文本"));
		types.add(new RecordType("teacherName", String.class, "文本"));
		types.add(new RecordType("name", String.class, "文本"));
		types.add(new RecordType("intro", String.class, "文本"));
		return types;
	}

	@Override
	public Class<ClassDTO> getBeanType() {
		return ClassDTO.class;
	}

	@Override
	public Map<Integer, Object> saveBean(List<ClassDTO> list) {
		Map<Integer, Object> errorIdx = new LinkedHashMap<>();
		List<ClassDTO> data = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			ClassDTO classDTO = list.get(i);
			KindergartenDTO school = kindergartenWebService.getKindergarten(classDTO.getSchoolName());
			if (null == school) {
				errorIdx.put(i, 0);
				continue;
			}
			TeacherDTO teacher = kindergartenWebService.getTeacher(school.getSchoolId(), classDTO.getTeacherName());
			if (null == teacher) {
				errorIdx.put(i, 1);
				continue;
			}
			classDTO.setSchoolId(school.getSchoolId());
			classDTO.setTeacherId(teacher.getTeacherId());
			classDTO.setCreateTime(new Date());
			classDTO.setEnabled(1);
			classDTO.setDeleted(0);
			data.add(classDTO);
			try {
				kindergartenWebService.saveClass(classDTO);
			} catch (Exception e) {
				errorIdx.put(i, "保存失败：" + e.getMessage());
				e.printStackTrace();
			}
		}
		return errorIdx;
	}
}

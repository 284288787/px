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
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.importdata.AbstractDataImport;
import com.star.framework.importdata.RecordType;

public class StudentImport extends AbstractDataImport<StudentDTO> {

	private KindergartenWebService kindergartenWebService = SpringBeanUtil.getBean("kindergartenWebService", KindergartenWebService.class);
	
	public StudentImport(byte[] bs, String filename) {
		super(bs, filename);
	}

	@Override
	public List<RecordType> getRecordTypes() {
		List<RecordType> types = new ArrayList<>();
		types.add(new RecordType("schoolName", String.class, "文本"));
		types.add(new RecordType("className", String.class, "文本"));
		types.add(new RecordType("name", String.class, "文本"));
		types.add(new RecordType("sex", String.class, Integer.class, "文本", "{\"男\":\"1\", \"女\":\"0\"}"));
		types.add(new RecordType("birth", Date.class, "日期"));
		types.add(new RecordType("stature", Integer.class, "数字"));
		types.add(new RecordType("weight", Integer.class, "数字", 100));
		types.add(new RecordType("guardianName", String.class, "文本"));
		types.add(new RecordType("guardianMobile", Integer.class, String.class, "文本", "电话号码"));
		types.add(new RecordType("guardianType", String.class, Integer.class, "文本", "{\"父亲\":\"1\", \"母亲\":\"2\", \"爷爷\":\"3\", \"奶奶\":\"4\", \"外公\":\"5\", \"外婆\":\"6\", \"其他\":\"7\"}"));
		return types;
	}

	@Override
	public Class<StudentDTO> getBeanType() {
		return StudentDTO.class;
	}

	@Override
	public Map<Integer, Object> saveBean(List<StudentDTO> list) {
		Map<Integer, Object> errorIdx = new LinkedHashMap<>();
		List<StudentDTO> data = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			StudentDTO studentDTO = list.get(i);
			KindergartenDTO school = kindergartenWebService.getKindergarten(studentDTO.getSchoolName());
			if (null == school) {
				errorIdx.put(i, 0);
				continue;
			}
			ClassDTO classDTO = kindergartenWebService.getClass(school.getSchoolId(), studentDTO.getClassName());
			if (null == classDTO) {
				errorIdx.put(i, 1);
				continue;
			}
			studentDTO.setSchoolId(school.getSchoolId());
			studentDTO.setClassId(classDTO.getClassId());
			studentDTO.setCreateTime(new Date());
			studentDTO.setEnabled(1);
			studentDTO.setDeleted(0);
			data.add(studentDTO);
			try {
				kindergartenWebService.saveStudent(studentDTO);
			} catch (Exception e) {
			  String msg = e.getMessage();
			  if (msg.indexOf("Duplicate") != -1) {
                msg = "学生信息已存在";
              }
				errorIdx.put(i, "保存失败：" + msg);
				e.printStackTrace();
			}
		}
		return errorIdx;
	}
}

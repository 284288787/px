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
import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.importdata.AbstractDataImport;
import com.star.framework.importdata.RecordType;

public class PhysicalDataImport extends AbstractDataImport<PhysicalDataDTO> {

	private KindergartenWebService kindergartenWebService = SpringBeanUtil.getBean("kindergartenWebService", KindergartenWebService.class);
	
	public PhysicalDataImport(byte[] bs, String filename) {
		super(bs, filename);
	}

	@Override
	public List<RecordType> getRecordTypes() {
		List<RecordType> types = new ArrayList<>();
		types.add(new RecordType("schoolName", String.class, "文本"));
		types.add(new RecordType("className", String.class, "文本"));
		types.add(new RecordType("name", String.class, "文本"));
		types.add(new RecordType("testTime", Date.class, "日期"));
		types.add(new RecordType("stature", Integer.class, "数字"));
		types.add(new RecordType("weight", Integer.class, "数字", 100));
		types.add(new RecordType("sitReach", Integer.class, "数字", 100));
		types.add(new RecordType("jump", Integer.class, "数字", 100));
		types.add(new RecordType("throwTennis", Integer.class, "数字", 100));
		types.add(new RecordType("doubleJump", Integer.class, "数字", 100));
		types.add(new RecordType("run10", Integer.class, "数字", 100));
		types.add(new RecordType("balance", Integer.class, "数字", 100));
		types.add(new RecordType("intro", String.class, "文本", true));
		return types;
	}

	@Override
	public Class<PhysicalDataDTO> getBeanType() {
		return  PhysicalDataDTO.class;
	}

	@Override
	public Map<Integer, Object> saveBean(List<PhysicalDataDTO> list) {
		Map<Integer, Object> errorIdx = new LinkedHashMap<>();
		List<PhysicalDataDTO> data = new ArrayList<>();
//		String schoolIds = "";
		for (int i = 0; i < list.size(); i++) {
			PhysicalDataDTO physicalDataDTO = list.get(i);
			KindergartenDTO school = kindergartenWebService.getKindergarten(physicalDataDTO.getSchoolName());
			if (null == school) {
				errorIdx.put(i, 0);
				continue;
			}
			ClassDTO classDTO = kindergartenWebService.getClass(school.getSchoolId(), physicalDataDTO.getClassName());
			if (null == classDTO) {
				errorIdx.put(i, 1);
				continue;
			}
			StudentDTO student = kindergartenWebService.getStudent(classDTO.getClassId(), physicalDataDTO.getName());
			if (null == student) {
				errorIdx.put(i, 2);
				continue;
			}
//			schoolIds += "," + school.getSchoolId();
			physicalDataDTO.setSchoolId(school.getSchoolId());
			physicalDataDTO.setClassId(classDTO.getClassId());
			physicalDataDTO.setStudentId(student.getStudentId());
			physicalDataDTO.setCreateTime(new Date());
			physicalDataDTO.setEnabled(1);
			physicalDataDTO.setDeleted(0);
			data.add(physicalDataDTO);
		}
		if (data.size() > 0) {
			for (PhysicalDataDTO physicalDataDTO : data) {
				PhysicalDataDTO params = new PhysicalDataDTO();
				params.setStudentId(physicalDataDTO.getStudentId());
				params.setTestTime(physicalDataDTO.getTestTime());
				kindergartenWebService.deletePhysicalDataBySql(params);
				kindergartenWebService.setPhysicalDataScore(physicalDataDTO);
			}
			kindergartenWebService.batchSavePhysicalData(data);
		}
		return errorIdx;
	}
}

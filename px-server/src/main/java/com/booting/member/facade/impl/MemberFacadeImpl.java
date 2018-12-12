/** create by auto at 2018-01-16 16:09:43**/
package com.booting.member.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.member.facade.MemberFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.member.dto.MemberDTO;
import com.booting.member.entity.MemberEntity;
import com.booting.member.service.MemberService;

@Service("memberFacade")
public class MemberFacadeImpl implements MemberFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MemberService memberService;


	@Override
	public Long saveMember(MemberDTO memberDTO){
		if (null == memberDTO) {
			return null;
		}
		MemberEntity entity = toMemberEntity(memberDTO);
		memberDTO = memberService.save(entity);
		return memberDTO.getMemberId();
	}

	@Override
	public void batchSaveMember(List<MemberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<MemberEntity> entities = toMemberEntities(dtos);
		memberService.batchSave(entities);
	}

	@Override
	public int updateMember(MemberDTO memberDTO){
		memberDTO = memberService.updateBySql(memberDTO);
		return 1;
	}

	@Override
	public void batchUpdateMember(List<MemberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		memberService.batchUpdate(dtos);
	}

	@Override
	public int deleteMember(long memberId){
		return memberService.delete(memberId);
	}

	@Override
	public MemberDTO getMember(long memberId){
		return memberService.get(memberId);
	}

	@Override
	public MemberDTO getMember(MemberDTO memberDTO){
		return memberService.get(memberDTO);
	}

	@Override
	public List<MemberDTO> getMemberList(MemberDTO memberDTO){
		return memberService.getSimpleList(memberDTO);
	}

	@Override
	public PageList<MemberDTO> getMemberListForPage(MemberDTO memberDTO, int pageNumber, int pageSize){
		return memberService.getSimpleListForPage(memberDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<MemberDTO> getMemberListForPage(QueryParam queryParam){
		return memberService.getSimpleListForPage(queryParam);
	}

	@Override
	public MemberEntity toMemberEntity(MemberDTO dto){
		MemberEntity entity = new MemberEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<MemberEntity> toMemberEntities(List<MemberDTO> dtos){
		List<MemberEntity> entities = new ArrayList<>();
		for(MemberDTO dto : dtos){
			entities.add(toMemberEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

}
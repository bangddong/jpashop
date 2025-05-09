package com.study.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.jpashop.domain.Member;
import com.study.jpashop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원가입
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	/**
	 * 단일 회원 조회
	 */
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId).get();
	}

	/**
	 * 회원 수정
	 */
	@Transactional
	public void update(Long id, String name) {
		Member member = memberRepository.findById(id).get();
		member.setName(name);
	}
}

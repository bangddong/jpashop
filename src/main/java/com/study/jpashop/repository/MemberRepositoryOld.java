package com.study.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.jpashop.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryOld {

	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}

}

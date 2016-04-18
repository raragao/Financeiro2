package com.algaworks.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.model.Pessoa;

public class Pessoas {

	EntityManager manager;

	@Inject
	public Pessoas(EntityManager manager) {
		this.manager = manager;

	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa",
				Pessoa.class);
		return query.getResultList();
	}
}

package com.gkhy.servicebase.service;

import com.gkhy.servicebase.service.repository.IRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public abstract class ServiceImpl<T, E extends Number, Repository extends IRepository<T, E>> {

    protected Repository iRepository;

    public ServiceImpl(Repository iRepository) {
        this.iRepository = iRepository;
    }

    public Optional<T> findOne(Specification<T> spec) {
        return iRepository.findOne(spec);
    }

    public List<T> findAll(Specification<T> spec) {
        return iRepository.findAll(spec);
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return null;
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return iRepository.findAll(spec, sort);
    }

    public long count(Specification<T> spec) {
        return iRepository.count(spec);
    }

    public boolean exists(Specification<T> spec) {
        return iRepository.exists(spec);
    }

    public List<T> findAll() {
        return iRepository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return iRepository.findAll(sort);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return iRepository.findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return iRepository.findAll(example, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return iRepository.findAll(pageable);
    }

    public Optional<T> findById(E e) {
        return iRepository.findById(e);
    }

    public boolean existsById(E e) {
        return iRepository.existsById(e);
    }

    public long count() {
        return iRepository.count();
    }

    public void deleteById(E e) {
        iRepository.deleteById(e);
    }

    public void delete(T entity) {
        iRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends E> es) {
        iRepository.deleteAllById(es);
    }

    public void deleteAll(Iterable<? extends T> entities) {
        iRepository.deleteAll(entities);
    }

    public void deleteAll() {
        iRepository.deleteAll();
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return iRepository.findOne(example);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return iRepository.findAll(example, pageable);
    }

    public <S extends T> long count(Example<S> example) {
        return iRepository.count(example);
    }

    public <S extends T> boolean exists(Example<S> example) {
        return iRepository.exists(example);
    }

    public void removeById(E e) {
        iRepository.deleteById(e);
    }

    public void save(T entity) {
        iRepository.saveAndFlush(entity);
    }

    public void saveAll(Iterable<T> entities) {
        iRepository.saveAllAndFlush(entities);
    }

    public void update(Object o, T entity) {
        BeanUtils.copyProperties(o, entity);
        iRepository.saveAndFlush(entity);
    }
}

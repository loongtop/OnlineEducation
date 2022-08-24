package com.gkhy.servicebase.service.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepositoryBase<T, E extends Number> extends
        IService<T, E>,
        IJpaRepository<T, E> {
}

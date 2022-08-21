package com.gkhy.servicebase.service.repository;

public interface IRepositoryBase<T, E extends Number> extends
        IRepository<T, E>,
        IJpaRepository<T, E> {
}

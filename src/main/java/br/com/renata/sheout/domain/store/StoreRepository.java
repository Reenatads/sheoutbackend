package br.com.renata.sheout.domain.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    public Store findByEmail(String email);
//    public List<Store> findByNameIgnoreCaseContaining(String name);
//
//    public List<Store> findByCategory_Id(Long categoryId);
}

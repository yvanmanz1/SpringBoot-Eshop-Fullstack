package com.example.ecommfullstack.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommfullstack.Models.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	
     
}

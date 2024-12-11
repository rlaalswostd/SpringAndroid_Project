package com.example.new_order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.new_order.entity.Notice;



@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}

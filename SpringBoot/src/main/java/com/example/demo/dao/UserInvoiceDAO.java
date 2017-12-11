package com.example.demo.dao;

import com.example.demo.bean.UserInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yunhua.he
 * @date 2017/12/5
 */
public interface UserInvoiceDAO extends JpaRepository<UserInvoice, Integer> {
}

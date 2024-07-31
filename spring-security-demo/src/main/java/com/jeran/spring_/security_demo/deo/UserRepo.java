package com.jeran.spring_.security_demo.deo;

import com.jeran.spring_.security_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}

//Duplicate find in sql : SELECT username, COUNT(*)
//FROM users
//GROUP BY username
//HAVING COUNT(*) > 1;

//If you found any duplicate entry use this one : DELETE FROM users
//WHERE id IN (
//    SELECT id
//    FROM (
//        SELECT id, ROW_NUMBER() OVER (PARTITION BY username ORDER BY id) AS row_num
//        FROM users
//    ) t
//    WHERE t.row_num > 1
//);
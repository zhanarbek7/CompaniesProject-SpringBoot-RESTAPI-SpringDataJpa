package com.zhanarbek.repository;

import com.zhanarbek.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select case when count(s) > 0 then true else false end " +
            "from Group s where s.groupName = ?1")
    boolean existsByGroupName(String groupName);
}

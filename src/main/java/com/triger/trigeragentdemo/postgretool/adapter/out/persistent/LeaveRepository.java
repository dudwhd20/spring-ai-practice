package com.triger.trigeragentdemo.postgretool.adapter.out.persistent;

import com.triger.trigeragentdemo.postgretool.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Leave getAllByUserName(String userName);
}

package com.triger.trigeragentdemo.rag.adapter.out.persistent;

import com.triger.trigeragentdemo.rag.domain.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}

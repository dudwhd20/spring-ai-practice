package com.triger.trigeragentdemo.postgretool.application.service;

import com.triger.trigeragentdemo.postgretool.adapter.out.persistent.LeaveRepository;
import com.triger.trigeragentdemo.postgretool.model.Leave;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class LeaveRepositoryTool {

    private final LeaveRepository leaveRepository;

    public LeaveRepositoryTool(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Tool(description = "사용자 이름으로 남은 휴가 수를 검색하는 툴")
    Leave getLeftLeave(@ToolParam(description = "사용자 이름이 담기면 된다 한글 이름만 허용 한다") String userName){
        return leaveRepository.getAllByUserName(userName);
    }

}

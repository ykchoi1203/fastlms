package com.zerobase.fastlms.admin.controller;


import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.LogParam;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.model.MemberInput;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.loginLog.dto.LoginLogDto;
import com.zerobase.fastlms.loginLog.service.LoginLogService;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController extends BaseController {
    
    private final MemberService memberService;
    private final LoginLogService loginLogService;
    
    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {
        
        parameter.init();
        List<MemberDto> members = memberService.list(parameter);
        
        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);
        System.out.println("-------------------");
        System.out.println(members.get(0).getLoginDate());
        
        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        
        return "admin/member/list";
    }
    
    @GetMapping("/admin/member/detail.do")
    public String detail(Model model,String userId, LogParam parameter) {
        parameter.init();

        String searchId = userId == null || userId.isEmpty() ? parameter.getSearchValue() : userId;
        MemberDto memberDto = memberService.detail(searchId);
        model.addAttribute("member", memberDto);

        if(parameter.getSearchType() == null || parameter.getSearchValue() == null || parameter.getSearchType().isEmpty() || parameter.getSearchValue().isEmpty()) {
            parameter.setSearchType("userId");
            parameter.setSearchValue(searchId);
        }

        List<LoginLogDto> list = loginLogService.list(parameter);

        long totalCount = 0;
        if (list != null && list.size() > 0) {
            totalCount = list.get(0).getTotalCount();
        }
        parameter.setSearchType("userId");
        parameter.setSearchValue(memberDto.getUserId());
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
       
        return "admin/member/detail";
    }
    
    @PostMapping("/admin/member/status.do")
    public String status(Model model, MemberInput parameter) {

        boolean result = memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());
        
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
    
    
    @PostMapping("/admin/member/password.do")
    public String password(Model model, MemberInput parameter) {
        
        boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getPassword());
        
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }
    
    
    
    
}

package com.codegym.controller;

import java.util.List;

import javax.validation.Valid;

import com.codegym.common.Constants;
import com.codegym.model.Member;
import com.codegym.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codegym.service.impl.MemberServiceImpl;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private IMemberService memberServiceImpl;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> memberTypes() {
		return Constants.MEMBER_TYPES;
	}
	
	@RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
	public String showMembersPage(Model model) {
		model.addAttribute("members", memberServiceImpl.getAll());
		return "/member/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMemeberPage(Model model) {
		model.addAttribute("member", new Member());
		return "/member/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editMemeberPage(@PathVariable(name = "id") Long id, Model model) {
		Member member = memberServiceImpl.get( id );
		if( member != null ) {
			model.addAttribute("member", member);
			return "/member/form";
		} else {
			return "redirect:/member/add";
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMember(@Valid Member member, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if( bindingResult.hasErrors() ) {
			return "/member/form";
		}
		
		if( member.getId() == null ) {
			memberServiceImpl.addNew(member);
			redirectAttributes.addFlashAttribute("successMsg", "'" + member.getFirstName()+" "+member.getLastName() + "' is added as a new member.");
			return "redirect:/member/add";
		} else {
			Member updatedMember = memberServiceImpl.save( member );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + member.getFirstName()+" "+member.getLastName() + "' are saved successfully. ");
			return "redirect:/member/edit/" + updatedMember.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeMember(@PathVariable(name = "id") Long id, Model model) {
		Member member = memberServiceImpl.get( id );
		if( member != null ) {
			if( memberServiceImpl.hasUsage(member) ) {
				model.addAttribute("memberInUse", true);
				return showMembersPage(model);
			} else {
				memberServiceImpl.delete(id);
			}
		}
		return "redirect:/member/list";
	}
	
	
	
	
}

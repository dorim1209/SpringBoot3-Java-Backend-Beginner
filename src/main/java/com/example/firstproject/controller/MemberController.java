package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUp(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        log.info(form.toString());

        Member member = form.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";

    }

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberEntityArray = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityArray);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", memberEntity);

        return "/members/edit";

    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        Member memberEntity = form.toEntity();

        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        if (target != null) {
            memberRepository.save(memberEntity);
        }

        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Member target = memberRepository.findById(id).orElse(null);

        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다!");
        }

        return "redirect:/members";

    }
}

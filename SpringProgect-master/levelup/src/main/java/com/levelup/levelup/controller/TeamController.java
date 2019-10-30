package com.levelup.levelup.controller;

import com.levelup.levelup.entity.Role;
import com.levelup.levelup.entity.Team;
import com.levelup.levelup.entity.User;
import com.levelup.levelup.repository.TeamRepository;
import com.levelup.levelup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('MENTOR')")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/teamlist")
    public String teamlist(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if (filter != null && !filter.isEmpty()) {
            model.addAttribute("teams", teamRepository.findByName(filter));
        }
        else {
            model.addAttribute("teams", teamRepository.findAll());
        }

        model.addAttribute("filter", filter);
        return "teamlist";
    }

    @GetMapping("/addteam")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addTeam() {
        return "addteam";
    }

    @PostMapping("/addteam")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addGroup(@RequestParam String name, Model model) {
        if(name == null || name.isEmpty()) {
            return  "addteam";
        }
        if(teamRepository.findByName(name) != null) {
            return "addteam";
        }
        Team team = new Team();
        team.setName(name);
        teamRepository.save(team);

        return "redirect:/teamlist";
    }


    @GetMapping("/team/{team}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String teamEdit(@PathVariable Team team, /*@RequestParam(required = false, defaultValue = "") String mentorname,*/ Model model) {
        model.addAttribute("team", team);
        model.addAttribute("mentor", team.getMentor());
//        List<User> mentors = userRepository.findByName(mentorname);
//        mentors.addAll(userRepository.findBySurname(mentorname));
//        model.addAttribute("mentors", mentors);
        return "teamEdit";
    }

    @PostMapping("/team")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveTeam(
            @RequestParam String name,
            @RequestParam String mentorname,
            @RequestParam Map<String, String> form,
            @RequestParam("teamId") Team team) {

        team.setName(name);
        team.setMentor(userRepository.findByName(mentorname).get(0));
//        for (String key : form.keySet()) {
//            if( userRepository.findByName(key) != null) {
//               team.setMentor(userRepository.findByName(key).get(0));
//            }
//        }
        teamRepository.save(team);

        return "redirect:/teamlist";
    }
}

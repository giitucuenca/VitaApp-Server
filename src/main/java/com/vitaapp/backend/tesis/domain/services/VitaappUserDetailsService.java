package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.*;
import com.vitaapp.backend.tesis.domain.repository.AdminRepository;
import com.vitaapp.backend.tesis.domain.repository.CarerRepository;
import com.vitaapp.backend.tesis.domain.repository.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class VitaappUserDetailsService implements UserDetailsService {
    @Autowired
    CarerRepository carerRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ElderlyRepository elderlyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String user = s;
        if(s.contains("carer-")) {
            user = s.substring(6);
            Carer carer = carerRepository.getByEmail(user);
            carer.setEmail(s);
            return new CarerDetails(carer);
        } else if(s.contains("older-")) {
            user = s.substring(6);
            Elderly elderly = elderlyRepository.getByUsername(user);
            elderly.setUsername(s);
            return new ElderlyDetails(elderly);
        }
        else if(s.contains("admin-")) {
            user = s.substring(6);
            Admin admin = adminRepository.getByEmail(user);
            admin.setEmail(s);
            return new AdminDetails(admin);
        } else  {
            return null;
        }
    }
}

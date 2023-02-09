package com.sparta.testproject.service;

import com.sparta.testproject.dto.MemberResponseDto;
import com.sparta.testproject.entity.Member;
import com.sparta.testproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberResponseDto findMember( Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당글이 존재하지 않습니다.")
        );
        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember() {
        List<Member> members = memberRepository.findAllByOrderByCreatedAtDesc();
        List<MemberResponseDto> memberResponseDtos = members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
        return memberResponseDtos;
    }
}

package com.sparta.springmemo.controller;

import com.sparta.springmemo.dto.MemoRequestDto;
import com.sparta.springmemo.dto.MemoResponseDto;
import com.sparta.springmemo.entity.Memo;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//controller역할을 하는 클래스라는 걸 알려주기 위해서
@RestController//html을 따로 반환하지는 않기 때문에 이렇게 사용
@RequestMapping("/api")//중복되는 부분있어서 이렇게 처리
public class MemoController {

    private final Map<Long,Memo> memoList=new HashMap<>();
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){//requestDto에 클라이언트에서 보내준 데이터가 들어있을것이다.
        //RequestDto->Entity 받아온RequestDto를 엔티티에 받아 저장해야한다.
        Memo memo = new Memo(requestDto);

        //Memo Max ID Check
        Long maxId=memoList.size()>0? Collections.max(memoList.keySet())+1:1;
        memo.setId(maxId);

        //DB저장
        memoList.put(memo.getId(), memo);

        //Entity -> ResponseDto
        MemoResponseDto memoResponseDto=new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        //Map to List
        List<MemoResponseDto> responseDtoList =memoList.values().stream()
                .map(MemoResponseDto::new).toList();

        return responseDtoList;
    }
}

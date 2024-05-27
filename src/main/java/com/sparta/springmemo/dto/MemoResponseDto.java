package com.sparta.springmemo.dto;

import com.sparta.springmemo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }

    public MemoResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents=contents;
    }
}
//데이터베이스와 소통하는 클래스는 매우 예민하기 때문에 형태가 유사하더라도 이렇게 분리하여 사용할 예정이다.
package com.jojoldu.book.springboot.domain.posts;


import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //lombok - 변경최소화를 위해 @Entity에 @Setter금지
@NoArgsConstructor //lombok - 기본생성자 자동 추가
@Entity //JPA - 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성규칙
    private Long id;

    @Column(length = 500, nullable = false) //생략가능
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성: 빌더 패턴은 복잡한 객체를 생성하는 클래스와 표현하는 클래스를 분리하여, 동일한 절차에서도 서로 다른 표현을 생성하는 방법을 제공
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
/* @Builder가 제공
    Posts posts = Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build();
*/

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

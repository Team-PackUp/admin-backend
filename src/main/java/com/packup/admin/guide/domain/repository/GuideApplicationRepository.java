package com.packup.admin.guide.domain.repository;

import com.packup.admin.guide.domain.GuideApplication;
import com.packup.admin.guide.dto.GuideApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuideApplicationRepository extends JpaRepository<GuideApplication, Long> {

    @Query("""
        select new com.packup.admin.guide.dto.GuideApplicationResponse(
            g.seq,
            u.email,
            d.nickname,
            d.age,
            d.nation,
            statusCode.codeName,
            g.createdAt
        )
        from GuideApplication g
        join g.user u
        left join u.detailInfo d
        left join CommonCode statusCode on g.statusCode = statusCode.codeId
        where g.statusCode in :statuses
    """)
    Page<GuideApplicationResponse> findAllByStatuses(
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );

    @Query("""
        select new com.packup.admin.guide.dto.GuideApplicationResponse(
            g.seq,
            u.email,
            d.nickname,
            d.age,
            d.nation,
            statusCode.codeName,
            g.createdAt
        )
        from GuideApplication g
        join g.user u
        left join u.detailInfo d
        left join CommonCode statusCode on g.statusCode = statusCode.codeId
        where u.email like concat('%', :email, '%')
        and g.statusCode in :statuses
    """)
    Page<GuideApplicationResponse> findByEmailContaining(
            @Param("email") String email,
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );

    @Query("""
        select new com.packup.admin.guide.dto.GuideApplicationResponse(
            g.seq,
            u.email,
            d.nickname,
            d.age,
            d.nation,
            statusCode.codeName,
            g.createdAt
        )
        from GuideApplication g
        join g.user u
        left join u.detailInfo d
        left join CommonCode statusCode on g.statusCode = statusCode.codeId
        where d.nickname like concat('%', :nickname, '%')
        and g.statusCode in :statuses
    """)
    Page<GuideApplicationResponse> findByNicknameContaining(
            @Param("nickname") String nickname,
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );

    @Query("""
        select new com.packup.admin.guide.dto.GuideApplicationResponse(
            g.seq,
            u.email,
            d.nickname,
            d.age,
            d.nation,
            statusCode.codeName,
            g.createdAt
        )
        from GuideApplication g
        join g.user u
        left join u.detailInfo d
        left join CommonCode statusCode on g.statusCode = statusCode.codeId
        where str(u.seq) like concat('%', :seq, '%')
        and g.statusCode in :statuses
    """)
    Page<GuideApplicationResponse> findBySeqContaining(
            @Param("seq") String seq,
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );
}

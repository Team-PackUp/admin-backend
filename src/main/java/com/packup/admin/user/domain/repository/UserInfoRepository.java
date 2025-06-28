package com.packup.admin.user.domain.repository;

import com.packup.admin.user.dto.UserResponse;
import com.packup.admin.user.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            u.joinType,
            d.age,
            d.nation,
            u.banFlag,
            d.banReason,
            d.banAdminSeq,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        where u.email like concat('%', :email, '%')
    """)
    Page<UserResponse> findByEmailContaining(String email, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            u.joinType,
            d.age,
            d.nation,
            u.banFlag,
            d.banReason,
            d.banAdminSeq,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        where d.nickname like concat('%', :nickname, '%')
    """)
    Page<UserResponse> findByNicknameContaining(String nickname, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            u.joinType,
            d.age,
            d.nation,
            u.banFlag,
            d.banReason,
            d.banAdminSeq,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        where str(u.seq) like concat('%', :seq, '%')
    """)
    Page<UserResponse> findBySeqContaining(String seq, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            u.joinType,
            d.age,
            d.nation,
            u.banFlag,
            d.banReason,
            d.banAdminSeq,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
    """)
    Page<UserResponse> findAllUserResponses(Pageable pageable);
}

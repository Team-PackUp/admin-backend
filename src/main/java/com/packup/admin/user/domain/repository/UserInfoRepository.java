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
            joinCode.codeName,
            d.age,
            nationCode.codeName,
            u.banFlag,
            d.banReason,
            a.adminId,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        left join AdminInfo a on d.banAdminSeq = a.seq
        left join CommonCode joinCode on u.joinType = joinCode.codeId
        left join CommonCode nationCode on d.nation = nationCode.codeId
        where u.email like concat('%', :email, '%')
    """)
    Page<UserResponse> findByEmailContaining(String email, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            joinCode.codeName,
            d.age,
            nationCode.codeName,
            u.banFlag,
            d.banReason,
            a.adminId,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        left join AdminInfo a on d.banAdminSeq = a.seq
        left join CommonCode joinCode on u.joinType = joinCode.codeId
        left join CommonCode nationCode on d.nation = nationCode.codeId
        where d.nickname like concat('%', :nickname, '%')
    """)
    Page<UserResponse> findByNicknameContaining(String nickname, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            joinCode.codeName,
            d.age,
            nationCode.codeName,
            u.banFlag,
            d.banReason,
            a.adminId,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        left join AdminInfo a on d.banAdminSeq = a.seq
        left join CommonCode joinCode on u.joinType = joinCode.codeId
        left join CommonCode nationCode on d.nation = nationCode.codeId
        where str(u.seq) like concat('%', :seq, '%')
    """)
    Page<UserResponse> findBySeqContaining(String seq, Pageable pageable);

    @Query("""
        select new com.packup.admin.user.dto.UserResponse(
            u.seq,
            u.email,
            d.nickname,
            joinCode.codeName,
            d.age,
            nationCode.codeName,
            u.banFlag,
            d.banReason,
            a.adminId,
            u.withdrawFlag,
            u.createdAt
        )
        from UserInfo u
        left join u.detailInfo d
        left join AdminInfo a on d.banAdminSeq = a.seq
        left join CommonCode joinCode on u.joinType = joinCode.codeId
        left join CommonCode nationCode on d.nation = nationCode.codeId
    """)
    Page<UserResponse> findAllUserResponses(Pageable pageable);
}

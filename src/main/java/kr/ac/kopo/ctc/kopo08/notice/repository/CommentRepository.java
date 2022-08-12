package kr.ac.kopo.ctc.kopo08.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo08.notice.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

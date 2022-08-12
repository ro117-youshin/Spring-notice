package kr.ac.kopo.ctc.kopo08.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Page<Notice> findAll(Pageable pageable);
	
	Notice findOneById(Long id);	
	
	@Modifying
	@Query("update Notice n set n.viewcnt = n.viewcnt + 1 where n.id = :id")
	int updateview(Long id);
}

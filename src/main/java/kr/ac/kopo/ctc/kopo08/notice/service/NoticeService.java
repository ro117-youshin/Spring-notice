package kr.ac.kopo.ctc.kopo08.notice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;
import kr.ac.kopo.ctc.kopo08.notice.dto.Pagination;

public interface NoticeService {
	public Notice findById(Long id);
	public Page<Notice> findAll(Pageable pageable);
	public Notice createNotice(Long Id, String title, String content);
	public int updateView(Long id);
	public Pagination getPagination(int currPage, int countPerPage, int pageSize, int totalCount);
	public Notice save(Notice notice);
}

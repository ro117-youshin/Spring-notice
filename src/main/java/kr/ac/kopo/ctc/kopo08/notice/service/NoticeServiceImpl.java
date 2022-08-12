package kr.ac.kopo.ctc.kopo08.notice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;
import kr.ac.kopo.ctc.kopo08.notice.dto.Pagination;
import kr.ac.kopo.ctc.kopo08.notice.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeRepository noticeRepository;
	
	public Notice findById(Long id) {
		Notice findById = noticeRepository.findById(id).get();
		return findById;
	}
	
	// get notice data
	public Page<Notice> findAll(Pageable pageable) {
		Page<Notice> List = this.noticeRepository.findAll(pageable);		
		return List;
	}
	
	// create notice
	public Notice createNotice(Long Id, String title, String content) {
		Notice notice = new Notice();
		notice.setId(Id);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setCreatedDate(LocalDateTime.now());		
//		notice.setRootid(notice.getRootid() + 1);
//		notice.setRelevel(0);
//		notice.setRecnt(0);
		return noticeRepository.save(notice);	
	}
	
	@Override
	@Transactional
	public int updateView(Long id) {
		// TODO Auto-generated method stub
		return noticeRepository.updateview(id);
	}
	
	public Pagination getPagination(int currPage, int countPerPage, int pageSize, int totalCount) {
		// TODO Auto-generated method stub
		Pagination pagination = new Pagination();
		// 현재 페이지가 양의 정수 이상일 경우에만 시작
		if (currPage <= 0) {
			currPage = 1;
		}
		// 페이지 사이즈 결정
		pagination.setPageSize(pageSize);
		
		int ppPage = 0; // << 화살표, 첫 페이지로
		// << 화살표는 무조건 1로
		pagination.setPpPage(ppPage);
		
		int fristPage = ((currPage - 1) / pageSize) * pageSize + 1; // 페이지 사이즈의 첫 페이지
		int backPage = fristPage - pageSize; // 앞으로 넘어갈 때

		// < 화살표
		int pPage; // < 화살표, 이전 페이지로
		if (fristPage == 1) {
			pPage = fristPage;
		} else {
			pPage = backPage;
		}
		pagination.setpPage(pPage);

		int lastPage; // 마지막 페이지 구하기, >> 화살표
		if (totalCount % countPerPage == 0) {
			lastPage = (totalCount / countPerPage);
		} else {
			lastPage = ((totalCount / countPerPage) + 1);
		}

		// > 화살표
		int nPage; // > 화살표, 다음 페이지로
		int nextPage = fristPage + pageSize;
		if (nextPage >= lastPage) {
			nPage = lastPage;
		} else {
			nPage = nextPage;
		}
		pagination.setnPage(nPage);

		// 현재 페이지가 마지막 페이지 보다 많은 수가 들어갈 경우 현재페이지는 마지막 페이지로
		if (currPage > lastPage) {
			currPage = lastPage;
		}
		
		// >> 화살표
		pagination.setNnPage(lastPage);
		// 현재 페이지
		pagination.setcPage(currPage);
		
		pagination.setsPage(fristPage);
		
		int endPage = ((currPage - 1) / pageSize) * pageSize + pageSize;
		if (lastPage < endPage) {
			endPage = lastPage;
		}
		pagination.setePage(endPage);

		return pagination;
	}

	@Override
	public Notice save(Notice notice) {
		// TODO Auto-generated method stub
		return noticeRepository.save(notice);
	}


	
}

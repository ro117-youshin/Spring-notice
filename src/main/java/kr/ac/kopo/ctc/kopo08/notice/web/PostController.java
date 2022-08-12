package kr.ac.kopo.ctc.kopo08.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;
import kr.ac.kopo.ctc.kopo08.notice.repository.NoticeRepository;
import kr.ac.kopo.ctc.kopo08.notice.service.NoticeService;

@RestController
public class PostController {

	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	NoticeService noticeService;
	
	@GetMapping(value = "/notice/api", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<Notice>> getNotice(Pageable pageable) {
		Page<Notice> lists = noticeService.findAll(pageable);

		return new ResponseEntity<Page<Notice>>(lists, HttpStatus.OK);
	}
	
	@GetMapping(value = "/notice/api/new", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Notice> getNewNotice() {
		Notice notice = new Notice();
		return new ResponseEntity<Notice>(notice, HttpStatus.OK);
	}
	
//	@PostMapping(value="notice/api/create")
//	public Notice createNotice(@RequestBody Notice notice) {
//		return noticeService.createNotice(notice);
//	}
//	
//	@PostMapping(value = "/notice/api/create")
//	public String writeNotice(Notice notice) {
//		noticeRepository.save(notice);
//		return "/Board";
//	}

}

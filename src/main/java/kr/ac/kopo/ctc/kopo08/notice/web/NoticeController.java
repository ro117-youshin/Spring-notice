package kr.ac.kopo.ctc.kopo08.notice.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;
import kr.ac.kopo.ctc.kopo08.notice.repository.NoticeRepository;
import kr.ac.kopo.ctc.kopo08.notice.service.NoticeServiceImpl;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeServiceImpl noticeServiceImpl;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/notice/mainView";
	}
	
	@RequestMapping(value = "/notice/mainView")
	public String view(Model model, @PageableDefault(size=10) Pageable pageable,
			@RequestParam(value="page", defaultValue="0") int currentPage) {
		
		if (currentPage >= 0) {
			pageable = PageRequest.of(currentPage, 10, Sort.by(Sort.Direction.DESC, "id"));
		}
		
		Page<Notice> notice = noticeServiceImpl.findAll(pageable);
		List<Notice> noticeToList = notice.getContent();
		model.addAttribute("list", noticeToList); 
		model.addAttribute("p", noticeServiceImpl.getPagination(pageable.getPageNumber() + 1, 10, pageable.getPageSize(), noticeRepository.findAll().size()));
		return "List";
	}
	
	// 목록 -> 등록
	@RequestMapping(value = "/notice/moveToWrite")
	public String moveToWrite() {
		return "ListWrite";
	}
	
	// 공지사항 등록
	@PostMapping(value = "/notice/writeComplete")
	public String writeNoice(@ModelAttribute Notice notice) {
		noticeServiceImpl.createNotice(notice.getId(), notice.getTitle(), notice.getContent());
		
//		if(noticeServiceImpl.findById(id) == null) {
//		} else {
//			noticeServiceImpl.createNotice(notice.getId(), notice.getTitle(), notice.getContent());
//			notice.setRecnt(notice.getRecnt() + 1);
//			notice.setRelevel(notice.getRelevel() + 1);
//			notice.setRootid(notice.getRootid());
//			
//		}
		// rootid = id;
		// recnt++;
		// relevel + 1;
		
		return "redirect:/notice/mainView";
	}
	
	@RequestMapping(value = "/notice/listview")
	public String moveToOneview(Model model, @RequestParam(value = "id") Long id) {
		Notice notice = noticeServiceImpl.findById(id);
		noticeServiceImpl.updateView(id); // views ++
		model.addAttribute("noticeInfo", notice);
		return "ListView";
	}
	
	// listview > modify
	@RequestMapping(value = "/notice/movetomodify")
	public String moveToModify(Model model, @RequestParam(value = "id") Long id) {
		Notice notice = noticeServiceImpl.findById(id);
		model.addAttribute("getNotice", notice);
		return "ListModify";
	}
	
	@PostMapping(value = "/notice/modify")
	public String modify(@RequestParam(value = "id") Long id, @ModelAttribute Notice notice) {
		Notice modifyNotice = noticeServiceImpl.findById(id);
		modifyNotice.setId(modifyNotice.getId());
		modifyNotice.setTitle(notice.getTitle());
		modifyNotice.setContent(notice.getContent());
		modifyNotice.setCreatedDate(modifyNotice.getCreatedDate());
		modifyNotice.setModifiedDate(LocalDateTime.now());
		
		noticeServiceImpl.save(modifyNotice);
		return "redirect:/notice/mainView";
	}
	
	@RequestMapping(value = "/notice/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		noticeRepository.deleteById(id);
		return "redirect:/notice/mainView";
	}
	
}

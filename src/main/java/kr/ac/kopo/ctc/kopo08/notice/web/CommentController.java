package kr.ac.kopo.ctc.kopo08.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.kopo.ctc.kopo08.notice.domain.Comment;
import kr.ac.kopo.ctc.kopo08.notice.domain.Notice;
import kr.ac.kopo.ctc.kopo08.notice.repository.CommentRepository;
import kr.ac.kopo.ctc.kopo08.notice.repository.NoticeRepository;
import kr.ac.kopo.ctc.kopo08.notice.service.CommentServiceImpl;
import kr.ac.kopo.ctc.kopo08.notice.service.NoticeServiceImpl;

@Controller
public class CommentController {
	
	@Autowired
	NoticeServiceImpl noticeServiceImpl;
	
	@Autowired
	CommentServiceImpl commentServiceImpl;
	
	@PostMapping("/comment/create")
	public String createComment(@RequestParam(value="noticeid") Long id, @ModelAttribute Comment comment) {
		Comment createComment = new Comment();
		Notice insertParentNotice = noticeServiceImpl.findById(id);
		createComment.setAuthor(comment.getAuthor());
		createComment.setContent(comment.getContent());
		createComment.setNotice(insertParentNotice);
		commentServiceImpl.save(createComment);
		return "redirect:/notice/listview?id=" + id;
	}
	
	@PostMapping("/comment/modify")
	public String modify(@RequestParam(value="modifycommentid") Long commentId, 
			@RequestParam(value="modifynoticeid") Long noticeId,
			@ModelAttribute Comment comment) {
		
		Comment modifyComment = commentServiceImpl.findById(commentId);
		Notice insertParentNotice = noticeServiceImpl.findById(noticeId); 

		modifyComment.setAuthor(comment.getAuthor());
		modifyComment.setContent(comment.getContent());
		modifyComment.setNotice(insertParentNotice); // 자식은 list 출력 부분
		commentServiceImpl.save(modifyComment);
		return "redirect:/notice/listview?id=" + insertParentNotice.getId();
	}
	
	@RequestMapping("/comment/modify")
	public String moveToModify(@RequestParam(value="id") Long id, Model model) {
		Comment comment = commentServiceImpl.findById(id);
		model.addAttribute("getComment", comment);
		return "CommentModify";
	}
	
	@RequestMapping("/comment/delete")
	public String delete(@RequestParam(value="id") Long id) {
		Notice notice = commentServiceImpl.findById(id).getNotice();
		commentServiceImpl.deleteById(id);
		return "redirect:/notice/listview?id=" + notice.getId();
	}
	
}
